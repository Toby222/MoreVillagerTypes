package tech.tobot.morevillagers.base.handler;

import org.jetbrains.annotations.Nullable;
import tech.tobot.morevillagers.base.CustomVillagerProfession;
import tech.tobot.morevillagers.block.CustomWorkstation;

import java.util.HashMap;
import java.util.Map;

public class CustomWorkstationHandler {
  private static final Map<Class<? extends CustomVillagerProfession>, CustomWorkstation> workstations = new HashMap<>();
  
  private CustomWorkstationHandler() {
  }
  
  @Nullable
  public static CustomWorkstation getWorkstation(Class<? extends CustomVillagerProfession> profession) {
    return workstations.getOrDefault(profession, null);
  }
  
  public static void setWorkstation(
      Class<? extends CustomVillagerProfession> profession, CustomWorkstation workstation
  ) {
    workstations.putIfAbsent(profession, workstation);
  }
}
