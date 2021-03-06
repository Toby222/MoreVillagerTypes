package tech.tobot.morevillagers.base;

import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.handler.ConfigHandler;
import tech.tobot.morevillagers.base.handler.ModuleHandler;
import tech.tobot.morevillagers.base.iface.Module;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

public class Loader {
  private final String                           modId;
  private final List<Class<? extends ModModule>> classes;
  private final Map<String, ModModule>           loadedModules = new TreeMap<>();
  
  public Loader(String modId, List<Class<? extends ModModule>> classes) {
    this.modId   = modId;
    this.classes = classes;
    
    MoreVillagers.LOG.info("Starting MoreVillagers module '" + modId + "'");
    
    ModuleHandler.INSTANCE.addLoader(this);
    
    register();
    init();
    
    MoreVillagers.LOG.info("Done setting up '" + modId + "'");
  }
  
  public String getModId() {
    return modId;
  }
  
  public List<Class<? extends ModModule>> getClasses() {
    return classes;
  }
  
  protected void register() {
    classes.forEach(clazz -> {
      try {
        ModModule module = clazz.getDeclaredConstructor().newInstance();
        if(clazz.isAnnotationPresent(Module.class)) {
          Module annotation = clazz.getAnnotation(Module.class);
          
          // mod is now a required string
          if(annotation.mod().isEmpty()) throw new Exception("mod name must be defined");
          
          module.mod              = annotation.mod();
          module.alwaysEnabled    = annotation.alwaysEnabled();
          module.enabledByDefault = annotation.enabledByDefault();
          module.enabled          = module.enabledByDefault;
          module.description      = annotation.description();
          module.client           = annotation.client();
          
          String moduleName = module.getName();
          loadedModules.put(moduleName, module);
          
        }
        else {
          throw new RuntimeException("No module annotation for class " + clazz.toString());
        }
        
      } catch(Exception e) {
        throw new RuntimeException("Could not initialize module class: " + clazz.toString(), e);
      }
    });
    
    // config for this module set
    ConfigHandler.createConfig(modId, loadedModules);
    
    // add and run register method for all loaded modules
    loadedModules.forEach((moduleName, module) -> ModuleHandler.INSTANCE.register(module));
  }
  
  protected void init() {
    // run dependency check on each module
    eachModule(ModuleHandler.INSTANCE::depends);
    
    // init, only enabled modules are run
    eachEnabledModule(ModuleHandler.INSTANCE::init);
    
    // listen for server world loading events
    // LoadWorldCallback.EVENT.register(server -> eachEnabledModule(m ->
    // m.loadWorld(server)));
  }
  
  protected void eachModule(Consumer<ModModule> consumer) {
    loadedModules.values().forEach(consumer);
  }
  
  protected void eachEnabledModule(Consumer<ModModule> consumer) {
    loadedModules.values().stream().filter(m -> m.enabled).forEach(consumer);
  }
}
