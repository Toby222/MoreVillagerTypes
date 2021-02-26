package tech.tobot.morevillagers.base.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jetbrains.annotations.Nullable;

import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.Loader;
import tech.tobot.morevillagers.base.ModModule;
import tech.tobot.morevillagers.base.helper.StringHelper;

public class ModuleHandler {
  public static final ModuleHandler INSTANCE = new ModuleHandler();
  public static final Map<String, ModModule> LOADED_MODULES = new TreeMap<>();

  private static final Map<String, Loader> LOADER_INSTANCES = new HashMap<>();
  private static final List<Class<? extends ModModule>> ENABLED_MODULES = new ArrayList<>(); // this is a cache of
                                                                                             // enabled classes

  // private ModuleHandler() {
  // // listen for server world loading events
  // LoadWorldCallback.EVENT.register(server -> {
  // // load late so that tags are populated at this point
  // DecorationHandler.init();
  // });
  // }

  public void addLoader(Loader loader) {
    LOADER_INSTANCES.put(loader.getModId(), loader);
  }

  @Nullable
  public Loader getLoader(String modId) {
    return LOADER_INSTANCES.getOrDefault(modId, null);
  }

  public void register(ModModule module) {
    LOADED_MODULES.put(module.getName(), module);

    MoreVillagers.LOG.debug("Registering module " + module.getName());
    module.register();
  }

  public void depends(ModModule module) {
    String name = module.getName();
    boolean isEnabled = module.enabled;
    boolean dependencyCheck = module.depends();

    String message = "Module " + name;
    if (!isEnabled) {
      message += " is not enabled.";
    } else if (!dependencyCheck) {
      message += " did not pass dependency check, disabling.";
    } else {
      message += " is enabled.";
    }

    MoreVillagers.LOG.debug("[ModuleHandler] " + message);
    module.enabled = isEnabled && dependencyCheck;
  }

  public void init(ModModule module) {
    // this is a cache for quick lookup of enabled classes
    ModuleHandler.ENABLED_MODULES.add(module.getClass());

    MoreVillagers.LOG.info("Initialising module " + module.getName());
    module.init();
  }

  @Nullable
  public static ModModule getModule(String moduleName) {
    return LOADED_MODULES.getOrDefault(StringHelper.snakeToUpperCamel(moduleName), null);
  }

  public static Map<String, ModModule> getLoadedModules() {
    return LOADED_MODULES;
  }

  /**
   * Use this within static hook methods for quick check if a module is enabled.
   * 
   * @param clazz
   *                Module to check
   * @return True if the module is enabled
   */
  public static boolean enabled(Class<? extends ModModule> clazz) {
    return ENABLED_MODULES.contains(clazz);
  }

  /**
   * Use this anywhere to check a module's enabled status.
   * 
   * @param moduleName
   *                     Name (modid:module_name) of module to check
   * @return True if the module is enabled
   */
  public static boolean enabled(String moduleName) {
    String[] split = moduleName.split(":");
    String modModule = split[1];

    ModModule module = getModule(modModule);
    return module != null && module.enabled;
  }
}
