package tech.tobot.morevillagers.base.handler;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.toml.TomlFormat;
import com.electronwill.nightconfig.toml.TomlWriter;
import com.moandjiezana.toml.Toml;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.ModModule;
import tech.tobot.morevillagers.base.iface.Config;

import java.io.File;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ConfigHandler {
  private ConfigHandler() {
  }
  
  public static void createConfig(String mod, Map<String, ModModule> modules) {
    String configPath = "./config/" + mod + ".toml";
    
    // this blank config is appended and then written out. LinkedHashMap supplier
    // sorts the contents alphabetically
    CommentedConfig writeConfig = TomlFormat.newConfig(LinkedHashMap::new);
    Path            path        = Paths.get(configPath);
    File            file        = path.toFile();
    Toml            readConfig  = new Toml();
    if(file.exists()) readConfig.read(file);
    
    List<String> moduleNames = new ArrayList<>(modules.keySet());
    Collections.sort(moduleNames);
    
    // parse config and apply values to modules
    moduleNames.forEach(moduleName -> {
      ModModule module = modules.get(moduleName);
      
      // set module enabled/disabled
      String moduleEnabled       = moduleName + " Enabled";
      String moduleEnabledQuoted = "\"" + moduleEnabled + "\"";
      module.enabled = module.alwaysEnabled || readConfig.getBoolean(moduleEnabledQuoted, module.enabledByDefault);
      
      writeConfig.setComment(moduleEnabled, module.description);
      writeConfig.add(moduleEnabled, module.enabled);
      
      // get and set module config options
      ArrayList<Field> classFields = new ArrayList<>(Arrays.asList(module.getClass().getDeclaredFields()));
      classFields.forEach(classField -> {
        try {
          Config annotation = classField.getDeclaredAnnotation(Config.class);
          if(annotation == null) {
            return;
          }
          // set the static property as writable so that the config can modify it
          classField.setAccessible(true);
          String name        = annotation.name();
          String description = annotation.description();
          
          if(name.isEmpty()) {
            name = classField.getName();
          }
          Object classValue  = classField.get(null);
          Object configValue = null;
          
          if(readConfig.contains(moduleName)) {
            
            // get the block of key/value pairs from the config
            Toml                moduleKeys = readConfig.getTable(moduleName);
            Map<String, Object> mappedKeys = new HashMap<>();
            
            // key names sometimes have quotes, map to remove them
            moduleKeys.toMap().forEach((k, v) -> mappedKeys.put(k.replace("\"", ""), v));
            configValue = mappedKeys.get(name);
          }
          // there's some weirdness with casting, deal with that here
          if(configValue != null && classValue instanceof Integer && configValue instanceof Double) {
            configValue = (int) (double) configValue;
            classField.set(null, configValue);
          }
          else if(configValue != null && classValue instanceof Integer && configValue instanceof Long) {
            configValue = (int) (long) configValue;
            classField.set(null, configValue);
          }
          if(configValue == null) {
            configValue = classValue;
          }
          
          // set the key/value pair. The "." specifies that it is nested
          String moduleConfigName = moduleName + "." + name;
          writeConfig.setComment(moduleConfigName, description);
          writeConfig.add(moduleConfigName, configValue);
          
        } catch(Exception e) {
          MoreVillagers.LOG.error("Failed to set config for " + moduleName + ": " + e.getMessage());
        }
      });
    });
    
    try {
      // write out and close the file
      TomlWriter tomlWriter = new TomlWriter();
      Writer     buffer     = Files.newBufferedWriter(path);
      tomlWriter.write(writeConfig, buffer);
      buffer.close();
      
    } catch(Exception e) {
      MoreVillagers.LOG.error("Failed to write config: " + e.getMessage());
    }
  }
}
