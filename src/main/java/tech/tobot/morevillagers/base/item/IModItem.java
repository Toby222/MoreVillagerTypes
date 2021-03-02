package tech.tobot.morevillagers.base.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import tech.tobot.morevillagers.base.ModModule;
import tech.tobot.morevillagers.base.handler.RegistryHandler;

public interface IModItem {
  boolean enabled();

  default void register(ModModule module, String blockName) {
    RegistryHandler.item(new Identifier(module.mod, blockName), (Item) this);
  }

  default void setBurnTime(int burnTime) {
    FuelRegistry.INSTANCE.add((Item) this, burnTime);
  }
}