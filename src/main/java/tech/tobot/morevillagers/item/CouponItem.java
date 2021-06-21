package tech.tobot.morevillagers.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.UseAction;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.ModModule;
import tech.tobot.morevillagers.base.item.ModItem;

public class CouponItem extends ModItem {
  public CouponItem(ModModule module) {
    super(module, "coupon", new Item.Settings().maxCount(16));
  }
  
  @Override
  public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
    MoreVillagers.LOG.debug("Used CouponItem on Entity");
    if(!(entity instanceof VillagerEntity)) {
      return ActionResult.FAIL;
    }
    
    VillagerEntity villager = (VillagerEntity) entity;
    TradeOfferList tradeOffers = villager.getOffers();
    if(tradeOffers.isEmpty()) {
      return ActionResult.FAIL;
    }
    
    Integer i = entity.world.random.nextInt(tradeOffers.size());
    MoreVillagers.LOG.debug("Selected trade #" + i + " .. (" + user.world.isClient + ")");
    TradeOffer randomOffer = tradeOffers.get(i);
    ItemStack  secondBuy   = randomOffer.getSecondBuyItem();
    ItemStack  firstBuy    = randomOffer.getOriginalFirstBuyItem();
    MoreVillagers.LOG
        .debug("Used CouponItem - First: " + firstBuy.getItem().equals(Items.EMERALD) + ".." + firstBuy.getCount());
    
    if(secondBuy.getCount() > 1) {
      MoreVillagers.LOG.debug("Used CouponItem - Lowering second item");
      secondBuy.decrement(1);
    }
    else if(firstBuy.getCount() > 1) {
      MoreVillagers.LOG.debug("Used CouponItem - Lowering first item");
      firstBuy.decrement(1);
    }
    else {
      return ActionResult.success(false);
    }
    
    if(user.world.isClient) {
      return ActionResult.success(true);
    }
    
    tradeOffers.set(i, new TradeOffer(firstBuy, secondBuy, randomOffer.getSellItem(), randomOffer.getUses(),
                                      randomOffer.getMaxUses(), randomOffer.getMerchantExperience(),
                                      randomOffer.getPriceMultiplier()
    ));
    villager.setOffers(tradeOffers);
    
    if(!user.isCreative()) {
      stack.decrement(1);
    }
    
    return ActionResult.success(true);
  }
  
  @Override
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.NONE;
  }
  
  @Override
  public boolean enabled() {
    return module.enabled;
  }
}
