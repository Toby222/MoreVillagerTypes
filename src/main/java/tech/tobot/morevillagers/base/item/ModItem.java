package tech.tobot.morevillagers.base.item;

import net.minecraft.item.Item;
import tech.tobot.morevillagers.base.ModModule;
import tech.tobot.morevillagers.base.handler.ItemGroupHandler;

public class ModItem extends Item implements IModItem {
  protected ModModule module;

  public ModItem(ModModule module, String name, Item.Settings props) {
    super(props);
    this.module = module;
    register(module, name);
    ItemGroupHandler.addItem(this);
  }

  @Override
  public boolean enabled() {
    return module.enabled;
  }
}
