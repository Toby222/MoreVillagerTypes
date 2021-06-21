package tech.tobot.morevillagers.trades;

import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.tag.ItemTags;
import net.minecraft.text.TranslatableText;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;
import tech.tobot.morevillagers.base.helper.VillagerHelper.SingleItemTypeTrade;

import java.util.List;
import java.util.Random;

public class BeekeeperTradeOffers {
  private BeekeeperTradeOffers() {
  }
  
  public static class EmeraldsForFlowers extends SingleItemTypeTrade {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      List<Item> flowers = ItemTags.FLOWERS.values();
      setInput(flowers.get(random.nextInt(flowers.size())), random.nextInt(3) + 13);
      setOutput(Items.EMERALD, 1);
      return super.create(entity, random);
    }
  }
  
  public static class EmeraldsForCharcoal extends SingleItemTypeTrade {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      setInput(Items.CHARCOAL, random.nextInt(3) + 13);
      setOutput(Items.EMERALD, 1);
      return super.create(entity, random);
    }
  }
  
  public static class EmeraldsForHoneycomb extends SingleItemTypeTrade {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      setInput(Items.HONEYCOMB, 10);
      setOutput(Items.EMERALD, random.nextInt(2) + 1);
      return super.create(entity, random);
    }
  }
  
  public static class BottlesForEmerald extends SingleItemTypeTrade {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      setInput(Items.EMERALD, 1);
      setOutput(Items.GLASS_BOTTLE, random.nextInt(4) + 2);
      return super.create(entity, random);
    }
  }
  
  public static class BeeswaxForEmeralds extends SingleItemTypeTrade {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      setInput(Items.EMERALD, 3);
      setOutput(Items.HONEYCOMB, 1);
      
      return super.create(entity, random);
    }
  }
  
  public static class CampfireForEmerald extends SingleItemTypeTrade {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      setInput(Items.EMERALD, 1);
      setOutput(Items.CAMPFIRE, 1);
      return super.create(entity, random);
    }
  }
  
  public static class LeadForEmeralds extends SingleItemTypeTrade {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      setInput(Items.EMERALD, 6);
      setOutput(Items.LEAD, 1);
      return super.create(entity, random);
    }
  }
  
  public static class HoneyBottlesForEmeralds implements TradeOffers.Factory {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      int       count = random.nextInt(2) + 1;
      ItemStack in1   = new ItemStack(Items.EMERALD, count);
      ItemStack out   = new ItemStack(Items.HONEY_BOTTLE, count);
      return new TradeOffer(in1, out, 20, 2, 0.05F);
    }
  }
  
  public static class BeehiveForEmeralds implements TradeOffers.Factory {
    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
      int       count = random.nextInt(14) + 21;
      ItemStack in1   = new ItemStack(Items.EMERALD, count);
      ItemStack out   = new ItemStack(Items.BEEHIVE);
      
      NbtList bees = new NbtList();
      
      for(int i = 0; i < BeehiveBlockEntity.MAX_BEE_COUNT; i++) {
        BeeEntity beeEntity = new BeeEntity(EntityType.BEE, entity.world);
        NbtCompound beeEntityData = new NbtCompound();
        beeEntity.saveSelfNbt(beeEntityData);
        
        NbtCompound bee = new NbtCompound();
        bee.put("EntityData", beeEntityData);
        bee.putInt("TicksInHive", 0);
        bee.putInt("MinOccupationTicks", 0);
        bees.add(bee);
      }
      
      NbtCompound beesTag  = new NbtCompound();
      NbtCompound honeyTag = new NbtCompound();
      beesTag.put("Bees", bees);
      honeyTag.putInt("honey_level", 0);
      out.putSubTag("BlockEntityTag", beesTag);
      out.putSubTag("BlockStateTag", honeyTag);
      out.setCustomName(new TranslatableText("item.morevillagers.populated_beehive"));
      
      return new TradeOffer(in1, out, 1, 10, 0.2F);
    }
  }
}
