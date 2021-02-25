package tech.tobot.morevillagers.block;

import net.minecraft.block.Blocks;
import tech.tobot.morevillagers.base.CustomVillagerProfession;
import tech.tobot.morevillagers.base.block.CustomBlock;

public class Workstation extends CustomBlock {
  public Workstation(CustomVillagerProfession module, String profession) {
    super(module, profession + "_workstation", Settings.copy(Blocks.OAK_PLANKS));
  }
}
