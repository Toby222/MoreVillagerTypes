package tech.tobot.morevillagers.base.block;

import java.util.function.BiConsumer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.ModModule;
import tech.tobot.morevillagers.base.handler.RegistryHandler;
import tech.tobot.morevillagers.mixin.accessor.FireBlockAccessor;

public interface ICustomBlock {
  boolean enabled();

  default Identifier register(ModModule module, String name) {
    Identifier id = new Identifier(module.mod, name);
    RegistryHandler.block(id, (Block) this);
    createBlockItem(id);
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
    if (itemGroup != null)
      settings.group(itemGroup);

    settings.maxCount(getMaxStackSize());

    CustomBlockItem blockItem = new CustomBlockItem(this, settings);
    RegistryHandler.item(id, blockItem);
  }

  default BiConsumer<ItemStack, Boolean> getInventoryTickConsumer() {
    return null;
  }

  default void setBurnTime(int burnTime) {
    FuelRegistry.INSTANCE.add((Block) this, burnTime);
  }

  default void setFireInfo(int encouragement, int flammability) {
    ((FireBlockAccessor) Blocks.FIRE).invokeRegisterFlammableBlock((Block) this, encouragement, flammability);
  }
}
