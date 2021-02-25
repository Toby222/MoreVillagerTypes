package tech.tobot.morevillagers.base.block;

import java.util.Arrays;
import java.util.List;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import tech.tobot.morevillagers.base.ModModule;

public abstract class CustomBlock extends Block implements ICustomBlock {
  public ModModule module;
  private final List<String> modDependencies;
  public final Identifier blockIdentifier;

  protected CustomBlock(ModModule module, String name, AbstractBlock.Settings props, String... modDependencies) {
    super(props);

    this.module = module;
    this.modDependencies = Arrays.asList(modDependencies);
    this.blockIdentifier = register(module, name);
  }

  @Override
  public void addStacksForDisplay(ItemGroup group, DefaultedList<ItemStack> items) {
    if (enabled())
      super.addStacksForDisplay(group, items);
  }

  @Override
  public boolean enabled() {
    return module.enabled
        && modDependencies.stream().allMatch(modName -> FabricLoader.getInstance().isModLoaded(modName));
  }
}
