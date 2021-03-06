package tech.tobot.morevillagers.mixin;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.tobot.morevillagers.item.CouponItem;

@Mixin(value = VillagerEntity.class) public class VillagerEntityInteractionMixin {
  @Inject(method = "interactMob", at = @At(value = "HEAD"), cancellable = true)
  private void onUseCoupon(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> info) {
    if(player.getStackInHand(hand).getItem() instanceof CouponItem) {
      info.setReturnValue(player.getStackInHand(hand).useOnEntity(player, (VillagerEntity) (Object) this, hand));
    }
  }
}
