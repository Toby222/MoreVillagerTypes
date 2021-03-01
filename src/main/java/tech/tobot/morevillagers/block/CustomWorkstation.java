package tech.tobot.morevillagers.block;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.ModModule;
import tech.tobot.morevillagers.base.block.CustomBlock;
import tech.tobot.morevillagers.base.handler.ItemGroupHandler;
import tech.tobot.morevillagers.base.handler.RecipeHandler;

public class CustomWorkstation extends CustomBlock {
  private static String workstation(String profession) {
    return profession + "_workstation";
  }

  @Override
  public Item asItem() {
    return Registry.ITEM.get(this.blockIdentifier);
  }

  public CustomWorkstation(ModModule module, String profession, @Nullable Item craftingItem) {
    super(module, workstation(profession),
        FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)
            .drops(new Identifier(MoreVillagers.MOD_ID, workstation(profession))).breakByTool(FabricToolTags.AXES)
            .hardness(0.5F)
            .breakByHand(true));
    this.lootTableId = new Identifier(MoreVillagers.MOD_ID, "blocks/workstations/" + workstation(profession));

    ItemGroupHandler.addItem(asItem());

    MoreVillagers.LOG.debug("Created workstation " + blockIdentifier + " with loot table " + lootTableId);

    if (craftingItem == null)
      return;

    JsonObject workstationRecipe = new JsonObject();
    workstationRecipe.addProperty("type", "minecraft:crafting_shapeless");

    JsonArray ingredients = new JsonArray();

    JsonObject baseWorkstationObject = new JsonObject();
    baseWorkstationObject.addProperty("item", "morevillagers:base_workstation");
    ingredients.add(baseWorkstationObject);

    JsonObject craftingItemObject = new JsonObject();
    craftingItemObject.addProperty("item", Registry.ITEM.getId(craftingItem).toString());
    ingredients.add(craftingItemObject);
    workstationRecipe.add("ingredients", ingredients);

    JsonObject resultObject = new JsonObject();
    resultObject.addProperty("item", this.blockIdentifier.toString());
    resultObject.addProperty("count", 1);

    workstationRecipe.add("result", resultObject);

    RecipeHandler.addRecipe(new Identifier(MoreVillagers.MOD_ID, profession + "_workstation_shapeless_recipe"),
        workstationRecipe);
  }
}
