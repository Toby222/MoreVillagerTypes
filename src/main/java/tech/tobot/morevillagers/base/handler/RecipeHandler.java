package tech.tobot.morevillagers.base.handler;

import com.google.gson.JsonElement;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class RecipeHandler {
  public static final Map<Identifier, JsonElement> RECIPES_TO_ADD = new HashMap<>();
  
  private RecipeHandler() {
  }
  
  public static void addRecipe(Identifier identifier, JsonElement recipe) {
    RECIPES_TO_ADD.put(identifier, recipe);
  }
}
