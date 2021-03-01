package tech.tobot.morevillagers.base.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGroupHandler {
  private ItemGroupHandler() {
    super();
  }

  private static final List<ItemStack> ITEMS = new ArrayList<>();

  public static void addItem(Item item) {
    addItem(new ItemStack(item));
  }

  public static void addItem(ItemStack item) {
    ITEMS.add(item);
  }

  public static void addItems(ItemStack... items) {
    ITEMS.addAll(List.of(items));
  }

  public static void addItems(Item... items) {
    ITEMS.addAll(List.of(items).stream().map(ItemStack::new).collect(Collectors.toList()));
  }

  public static List<ItemStack> getItems() {
    return ITEMS;
  }
}
