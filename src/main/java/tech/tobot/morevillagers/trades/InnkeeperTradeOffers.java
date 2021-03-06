package tech.tobot.morevillagers.trades;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.helper.VillagerHelper.SingleItemTypeTrade;

import java.util.Random;

public class InnkeeperTradeOffers {
  private InnkeeperTradeOffers() {
  }
  
  // Tier 1
  
  public static class WaterbottleForEmerald implements TradeOffers.Factory {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      ItemStack input = new ItemStack(Items.EMERALD);
      
      ItemStack waterBottle = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER);
      waterBottle.setCustomName(
          new TranslatableText("item.morevillagers.mineral_water").setStyle(Style.EMPTY.withItalic(false)));
      
      return new TradeOffer(input, waterBottle, 20, 1, 0.05F);
    }
  }
  
  public static class GunpowderForEmerald extends SingleItemTypeTrade {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      setInput(Items.EMERALD, random.nextInt(2) + 1);
      setOutput(Items.GUNPOWDER, random.nextInt(5) + 2);
      return super.create(entity, random);
    }
  }
  
  // Tier 2
  
  public static class CheapDrinkForEmerald implements TradeOffers.Factory {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      ItemStack input = new ItemStack(Items.EMERALD, 2);
      
      ItemStack output = new ItemStack(Items.POTION);
      PotionUtil.setPotion(output, Registry.POTION.get(new Identifier(MoreVillagers.MOD_ID, "booze")));
      
      output.setCustomName(new TranslatableText("item.morevillagers.booze").setStyle(Style.EMPTY.withItalic(false)));
      
      return new TradeOffer(input, output, 20, 2, 0.05F);
    }
  }
  
  public static class EmeraldForBottles extends SingleItemTypeTrade {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      setInput(Items.GLASS_BOTTLE, random.nextInt(1) + 3);
      setOutput(Items.EMERALD, 1);
      return super.create(entity, random);
    }
  }
  
  // Tier 3
  
  public static class FireResistanceForEmeralds implements TradeOffers.Factory {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      ItemStack output = new ItemStack(Items.POTION);
      
      PotionUtil.setPotion(output, Registry.POTION.get(new Identifier(MoreVillagers.MOD_ID, "spiked_fire_resistance")));
      
      output.setCustomName(
          new TranslatableText("item.morevillagers.spiked_fire_resistance").setStyle(Style.EMPTY.withItalic(false)));
      
      return new TradeOffer(new ItemStack(Items.EMERALD, random.nextInt(5) + 5), output, 10, 5, 0.05F);
    }
  }
  
  // Tier 4
  
  public static class FermentedSpiderEyesForEmeralds extends SingleItemTypeTrade {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      setInput(Items.EMERALD, 2 + random.nextInt(1));
      setOutput(Items.FERMENTED_SPIDER_EYE, 1 + random.nextInt(1));
      return super.create(entity, random);
    }
  }
  
  public static class RabbitsFootForEmeralds extends SingleItemTypeTrade {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      setInput(Items.EMERALD, 3 + random.nextInt(1));
      setOutput(Items.RABBIT_FOOT, 1);
      experience = 5;
      return super.create(entity, random);
    }
  }
  
  // Tier 5
  
  public static class AurumPotabileForEmeralds implements TradeOffers.Factory {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      ItemStack input = new ItemStack(Items.EMERALD, 8 + random.nextInt(5));
      
      ItemStack output = new ItemStack(Items.POTION);
      
      PotionUtil.setPotion(output, Registry.POTION.get(new Identifier(MoreVillagers.MOD_ID, "aurum_potabile")));
      output.setCustomName(new LiteralText("Aurum Potabile").setStyle(Style.EMPTY.withItalic(false)));
      
      return new TradeOffer(input, output, 5, 5, 0.05F);
    }
  }
}
