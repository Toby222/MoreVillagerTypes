package tech.tobot.morevillagers.module;

import static tech.tobot.morevillagers.base.helper.VillagerHelper.addTrade;

import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.CustomVillagerProfession;
import tech.tobot.morevillagers.base.iface.Config;
import tech.tobot.morevillagers.base.iface.Module;
import tech.tobot.morevillagers.trades.BeekeeperTradeOffers;

@Module(mod = MoreVillagers.MOD_ID, description = "Beekeepers are villagers that trade beekeeping items.")
public class Beekeeper extends CustomVillagerProfession {

  @Config(name = "Beekeeper house weight", description = "Chance of a custom building to spawn. For reference, a vanilla library is 5.")
  public static int buildingWeight = 5;

  public Beekeeper() {
    super("beekeeper", Items.HONEY_BOTTLE, SoundEvents.BLOCK_BEEHIVE_WORK);
  }

  protected void addTrades() {
    // register beekeeper trades
    addTrade(profession, 1, new BeekeeperTradeOffers.EmeraldsForFlowers());
    addTrade(profession, 1, new BeekeeperTradeOffers.BottlesForEmerald());
    addTrade(profession, 2, new BeekeeperTradeOffers.EmeraldsForCharcoal());
    addTrade(profession, 2, new BeekeeperTradeOffers.BeeswaxForEmeralds());
    addTrade(profession, 3, new BeekeeperTradeOffers.EmeraldsForHoneycomb());
    addTrade(profession, 3, new BeekeeperTradeOffers.CampfireForEmerald());
    addTrade(profession, 4, new BeekeeperTradeOffers.LeadForEmeralds());
    addTrade(profession, 5, new BeekeeperTradeOffers.PopulatedBeehiveForEmeralds());
  }
}
