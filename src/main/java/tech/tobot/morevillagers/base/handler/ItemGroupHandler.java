package tech.tobot.morevillagers.base.handler;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemGroupHandler {
  private static final List<ItemStack> ITEMS = new ArrayList<>();
  
  private ItemGroupHandler() {
    super();
  }
  
  public static void addItem(Item item) {
    addItem(new ItemStack(item));
  }
  
  public static void addItem(ItemStack item) {
    ITEMS.add(item);
  }
  
  public static void addItems(List<ItemStack> items) {
    ITEMS.addAll(items);
  }
  
  public static List<ItemStack> getItems() {
    return ITEMS;
  }
}
