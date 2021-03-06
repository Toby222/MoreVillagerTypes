package tech.tobot.morevillagers.base.block;

import net.devtech.arrp.json.loot.JLootTable;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.ModModule;
import tech.tobot.morevillagers.base.handler.RegistryHandler;

import java.util.function.BiConsumer;

public interface ICustomBlock {
  boolean enabled();
  
  default Identifier register(ModModule module, String name) {
    Identifier id = new Identifier(module.mod, name);
    RegistryHandler.block(id, (Block) this);
    createBlockItem(id);
    
    JLootTable lootTable = JLootTable.loot("minecraft:block").pool(
        JLootTable.pool().entry(JLootTable.entry().type("minecraft:item").name(id.toString())).rolls(1)
            .condition(JLootTable.predicate("minecraft:survives_explosion")));
    MoreVillagers.RESOURCE_PACK.addLootTable(new Identifier(module.mod, "blocks/" + name), lootTable);
    
    return id;
  }
  
  default ItemGroup getItemGroup() {
    return ItemGroup.BUILDING_BLOCKS;
  }
  
  default int getMaxStackSize() {
    return 64;
  }
  
  default void createBlockItem(Identifier id) {
    MoreVillagers.LOG.debug("Creating block item for: " + id.toString());
    Item.Settings settings = new Item.Settings();
    
    ItemGroup itemGroup = getItemGroup();
    if(itemGroup != null) settings.group(itemGroup);
    
    settings.maxCount(getMaxStackSize());
    
    CustomBlockItem blockItem = new CustomBlockItem(this, settings);
    RegistryHandler.item(id, blockItem);
  }
  
  default BiConsumer<ItemStack, Boolean> getInventoryTickConsumer() {
    return null;
  }
}
