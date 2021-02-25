package tech.tobot.morevillagers.module;

import static tech.tobot.morevillagers.base.helper.VillagerHelper.addTrade;
import static tech.tobot.morevillagers.event.StructureSetupCallback.addVillageHouse;

import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.CustomVillagerProfession;
import tech.tobot.morevillagers.base.iface.Config;
import tech.tobot.morevillagers.base.iface.Module;
import tech.tobot.morevillagers.event.StructureSetupCallback.VillageType;
import tech.tobot.morevillagers.trades.BeekeeperTradeOffers;

@Module(mod = MoreVillagers.MOD_ID, description = "Beekeepers are villagers that trade beekeeping items. Their job site is the beehive.")
public class Beekeeper extends CustomVillagerProfession {
  public static SoundEvent workSound = SoundEvents.BLOCK_BEEHIVE_WORK;

  @Config(name = "Beekeeper house weight", description = "Chance of a custom building to spawn. For reference, a vanilla library is 5.")
  public static int buildingWeight = 5;

  public Beekeeper() {
    super("beekeeper");
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

  protected void addVillageHouses() {
    // register beekeeper structures
    addVillageHouse(VillageType.TAIGA, new Identifier(MoreVillagers.MOD_ID, "village/taiga/houses/taiga_beekeeper_1"),
        buildingWeight);
    addVillageHouse(VillageType.PLAINS,
        new Identifier(MoreVillagers.MOD_ID, "village/plains/houses/plains_beekeeper_1"), buildingWeight);
    addVillageHouse(VillageType.DESERT,
        new Identifier(MoreVillagers.MOD_ID, "village/desert/houses/desert_beekeeper_1"), buildingWeight);
    addVillageHouse(VillageType.SAVANNA,
        new Identifier(MoreVillagers.MOD_ID, "village/savanna/houses/savanna_beekeeper_1"), buildingWeight);
    addVillageHouse(VillageType.SAVANNA,
        new Identifier(MoreVillagers.MOD_ID, "village/savanna/houses/savanna_beekeeper_2"), buildingWeight);

    // beekeeper + lumberjack
    // addVillageHouse(VillageType.SNOWY, new Identifier(MoreVillagers.MOD_ID,
    // "village/snowy/houses/snowy_lumberbee_1"), buildingWeight);
    // addVillageHouse(VillageType.PLAINS, new Identifier(MoreVillagers.MOD_ID,
    // "village/plains/houses/plains_beejack_1"), buildingWeight);
  }
}
