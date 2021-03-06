package tech.tobot.morevillagers.mixin.accessor;

import net.minecraft.block.Block;
import net.minecraft.block.FireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import tech.tobot.morevillagers.base.block.ICustomBlock;

@Mixin(FireBlock.class) public interface FireBlockAccessor {
  /**
   * Used by abstract blocks to set flammability data.
   * <p>
   * {@link ICustomBlock#setFireInfo(int, int)}
   */
  @Invoker
  void invokeRegisterFlammableBlock(Block block, int encouragement, int flammability);
}
