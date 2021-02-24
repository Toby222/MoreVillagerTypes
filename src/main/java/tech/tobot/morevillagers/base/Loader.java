package tech.tobot.morevillagers.base;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import tech.tobot.morevillagers.base.iface.Module;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.handler.ConfigHandler;
import tech.tobot.morevillagers.base.handler.ModuleHandler;

public class Loader {
  private final String MOD_ID;
  private final List<Class<? extends ModModule>> CLASSES;
  private final Map<String, ModModule> LOADED_MODULES = new TreeMap<>();

  public Loader(String modId, List<Class<? extends ModModule>> classes) {
    MOD_ID = modId;
    CLASSES = classes;

    MoreVillagers.LOG.info("Starting MoreVillagers module '" + modId + "'");

    ModuleHandler.INSTANCE.addLoader(this);

    register();
    init();

    MoreVillagers.LOG.info("Done setting up '" + modId + "'");
  }

  public String getModId() {
    return MOD_ID;
  }

  public List<Class<? extends ModModule>> getClasses() {
    return CLASSES;
  }

  protected void register() {
    CLASSES.forEach(clazz -> {
      try {
        ModModule module = clazz.getDeclaredConstructor().newInstance();
        if (clazz.isAnnotationPresent(Module.class)) {
          Module annotation = clazz.getAnnotation(Module.class);

          // mod is now a required string
          if (annotation.mod().isEmpty())
            throw new Exception("mod name must be defined");

          module.mod = annotation.mod();
          module.alwaysEnabled = annotation.alwaysEnabled();
          module.enabledByDefault = annotation.enabledByDefault();
          module.enabled = module.enabledByDefault;
          module.description = annotation.description();
          module.client = annotation.client();

          String moduleName = module.getName();
          LOADED_MODULES.put(moduleName, module);

        } else {
          throw new RuntimeException("No module annotation for class " + clazz.toString());
        }

      } catch (Exception e) {
        throw new RuntimeException("Could not initialize module class: " + clazz.toString(), e);
      }
    });

    // config for this module set
    ConfigHandler.createConfig(MOD_ID, LOADED_MODULES);

    // add and run register method for all loaded modules
    LOADED_MODULES.forEach((moduleName, module) -> ModuleHandler.INSTANCE.register(module));
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
    LOADED_MODULES.values().forEach(consumer);
  }

  protected void eachEnabledModule(Consumer<ModModule> consumer) {
    LOADED_MODULES.values().stream().filter(m -> m.enabled).forEach(consumer);
  }
}
