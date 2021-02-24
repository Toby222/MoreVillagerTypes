package tech.tobot.morevillagers.module;

import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.ModModule;
import tech.tobot.morevillagers.base.helper.VillagerHelper;
import tech.tobot.morevillagers.base.iface.Config;
import tech.tobot.morevillagers.base.iface.Module;
import tech.tobot.morevillagers.event.StructureSetupCallback.VillageType;
import tech.tobot.morevillagers.mixin.accessor.PointOfInterestTypeAccessor;
import tech.tobot.morevillagers.trades.BeekeeperTradeOffers;

import static tech.tobot.morevillagers.base.helper.VillagerHelper.addTrade;
import static tech.tobot.morevillagers.event.StructureSetupCallback.addVillageHouse;

@Module(mod = MoreVillagers.MOD_ID, description = "Beekeepers are villagers that trade beekeeping items. Their job site is the beehive.")
public class Beekeepers extends ModModule {
  public static Identifier ID = new Identifier(MoreVillagers.MOD_ID, "beekeeper");
  public static VillagerProfession BEEKEEPER;

  @Config(name = "Beekeeper house weight", description = "Chance of a custom building to spawn. For reference, a vanilla library is 5.")
  public static int buildingWeight = 5;

  @Override
  public void init() {
    BEEKEEPER = VillagerHelper.addProfession(ID, PointOfInterestType.BEEHIVE, SoundEvents.BLOCK_BEEHIVE_WORK);

    // HACK: set ticketCount so that villager can use it as job site
    ((PointOfInterestTypeAccessor) PointOfInterestType.BEEHIVE).setTicketCount(1);

    // register beekeeper trades
    addTrade(BEEKEEPER, 1, new BeekeeperTradeOffers.EmeraldsForFlowers());
    addTrade(BEEKEEPER, 1, new BeekeeperTradeOffers.BottlesForEmerald());
    addTrade(BEEKEEPER, 2, new BeekeeperTradeOffers.EmeraldsForCharcoal());
    addTrade(BEEKEEPER, 2, new BeekeeperTradeOffers.BeeswaxForEmeralds());
    addTrade(BEEKEEPER, 3, new BeekeeperTradeOffers.EmeraldsForHoneycomb());
    addTrade(BEEKEEPER, 3, new BeekeeperTradeOffers.CampfireForEmerald());
    addTrade(BEEKEEPER, 4, new BeekeeperTradeOffers.LeadForEmeralds());
    addTrade(BEEKEEPER, 5, new BeekeeperTradeOffers.PopulatedBeehiveForEmeralds());

    // register beekeeper structures
    addVillageHouse(VillageType.PLAINS, new Identifier(MoreVillagers.MOD_ID, "village/plains/houses/plains_beejack_1"),
        buildingWeight);
    addVillageHouse(VillageType.PLAINS, new Identifier(MoreVillagers.MOD_ID,"village/plains/houses/plains_beekeeper_1"),
        buildingWeight);
    addVillageHouse(VillageType.DESERT, new Identifier(MoreVillagers.MOD_ID,"village/desert/houses/desert_beekeeper_1"),
        buildingWeight);
    addVillageHouse(VillageType.SAVANNA, new Identifier(MoreVillagers.MOD_ID,"village/savanna/houses/savanna_beekeeper_1"),
        buildingWeight);
    addVillageHouse(VillageType.SAVANNA, new Identifier(MoreVillagers.MOD_ID,"village/savanna/houses/savanna_beekeeper_2"),
        buildingWeight);
    addVillageHouse(VillageType.TAIGA, new Identifier(MoreVillagers.MOD_ID,"village/taiga/houses/taiga_beekeeper_1"), buildingWeight);
    addVillageHouse(VillageType.SNOWY, new Identifier(MoreVillagers.MOD_ID,"village/snowy/houses/snowy_lumberbee_1"), buildingWeight);
  }
}
