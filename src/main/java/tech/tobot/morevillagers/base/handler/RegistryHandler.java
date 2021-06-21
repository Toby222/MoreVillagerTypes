package tech.tobot.morevillagers.base.handler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

@SuppressWarnings("UnusedReturnValue") public class RegistryHandler {
  public static Block block(Identifier id, Block block) {
    return Registry.register(Registry.BLOCK, id, block);
  }
  
  public static Item item(Identifier id, Item item) {
    return Registry.register(Registry.ITEM, id, item);
  }
  
  public static PointOfInterestType pointOfInterestType(Identifier id, PointOfInterestType poit) {
    return Registry.register(Registry.POINT_OF_INTEREST_TYPE, id, poit);
  }
  
  public static VillagerProfession villagerProfession(Identifier id, VillagerProfession profession) {
    return Registry.register(Registry.VILLAGER_PROFESSION, id, profession);
  }
}
