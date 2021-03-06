package tech.tobot.morevillagers.block;

import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.recipe.JIngredient;
import net.devtech.arrp.json.recipe.JIngredients;
import net.devtech.arrp.json.recipe.JRecipe;
import net.devtech.arrp.json.recipe.JResult;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.ModModule;
import tech.tobot.morevillagers.base.block.CustomBlock;
import tech.tobot.morevillagers.base.handler.ItemGroupHandler;
import tech.tobot.morevillagers.module.Core;

public class CustomWorkstation extends CustomBlock {
  public CustomWorkstation(ModModule module, String profession, @Nullable Item craftingItem) {
    super(module, workstation(profession),
          FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).drops(new Identifier(module.mod, workstation(profession)))
              .breakByTool(FabricToolTags.AXES).hardness(0.5F).breakByHand(true)
    );
    this.lootTableId = new Identifier(module.mod, "blocks/" + workstation(profession));
    
    ItemGroupHandler.addItem(asItem());
    
    MoreVillagers.LOG.debug("Created workstation " + blockIdentifier + " with loot table " + lootTableId);
    
    JState blockState = JState
        .state(JState.variant(JState.model(new Identifier(module.mod, "block/" + workstation(profession)).toString())));
    MoreVillagers.LOG.debug("Generated Block State for " + blockIdentifier);
    MoreVillagers.RESOURCE_PACK.addBlockState(blockState, blockIdentifier);
    
    JModel itemModel = JModel.model("morevillagers:block/" + workstation(profession));
    MoreVillagers.LOG.debug("Generated Item Model for " + blockIdentifier);
    MoreVillagers.RESOURCE_PACK.addModel(itemModel, new Identifier(module.mod, "block/" + workstation(profession)));
    
    if(craftingItem == null) return;
    
    JModel blockModel = JModel.model("morevillagers:block/base_workstation").textures(
        JModel.textures().var("top", new Identifier(module.mod, "block/" + workstation(profession)).toString()));
    MoreVillagers.LOG.debug("Generated Block Model for " + blockIdentifier);
    MoreVillagers.RESOURCE_PACK.addModel(blockModel, new Identifier(module.mod, "block/" + workstation(profession)));
    
    CustomWorkstation baseWorkstation = Core.getBaseWorkstation();
    
    JRecipe recipe = JRecipe.shapeless(
        JIngredients.ingredients().add(JIngredient.ingredient().item(baseWorkstation.asItem()))
            .add(JIngredient.ingredient().item(craftingItem)), JResult.item(this.asItem()));
    MoreVillagers.LOG.debug("Generated Recipe for " + blockIdentifier);
    MoreVillagers.RESOURCE_PACK
        .addRecipe(new Identifier(MoreVillagers.MOD_ID, workstation(profession) + "_shapeless_recipe"), recipe);
  }
  
  private static String workstation(String profession) {
    return profession + "_workstation";
  }
  
  @Override
  public Item asItem() {
    return Registry.ITEM.get(this.blockIdentifier);
  }
}
