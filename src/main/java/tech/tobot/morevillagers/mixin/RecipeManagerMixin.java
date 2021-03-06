package tech.tobot.morevillagers.mixin;

import com.google.gson.JsonElement;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.handler.RecipeHandler;

import java.util.Map;

@Mixin(value = RecipeManager.class) public class RecipeManagerMixin {
  @Inject(method = "apply", at = @At("HEAD"))
  private void hookApply(
      Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci
  ) {
    MoreVillagers.LOG.info("Adding " + RecipeHandler.RECIPES_TO_ADD.size() + " recipe(s).");
    RecipeHandler.RECIPES_TO_ADD.forEach(map::put);
  }
}