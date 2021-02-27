package tech.tobot.morevillagers.module;

import static tech.tobot.morevillagers.base.helper.VillagerHelper.addTrade;
import static tech.tobot.morevillagers.event.StructureSetupCallback.addVillageHouse;

import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.CustomVillagerProfession;
import tech.tobot.morevillagers.base.iface.Config;
import tech.tobot.morevillagers.base.iface.Module;
import tech.tobot.morevillagers.event.StructureSetupCallback.VillageType;
import tech.tobot.morevillagers.trades.LumberjackTradeOffers;

@Module(mod = MoreVillagers.MOD_ID, description = "Lumberjacks are villagers that trade beekeeping items. Their job site is the beehive.")
public class Lumberjack extends CustomVillagerProfession {

  @Config(name = "Lumberjack house weight", description = "Chance of a custom building to spawn. For reference, a vanilla library is 5.")
  public static int buildingWeight = 5;
  @Config(name = "Enable new bookshelf trades", description = "Enable trading emeralds for bookshelves. (Changing won't affect already existing villagers)")
  public static boolean addBookshelfTrade = true;

  public Lumberjack() {
    super("lumberjack", Items.IRON_AXE, SoundEvents.BLOCK_WOOD_BREAK);
  }

  protected void addTrades() {
    // register lumberjack trades
    addTrade(profession, 1, new LumberjackTradeOffers.EmeraldsForOverworldLogs());
    addTrade(profession, 1, new LumberjackTradeOffers.CommonSaplingsForEmeralds());
    addTrade(profession, 1, new LumberjackTradeOffers.LaddersForEmeralds());
    addTrade(profession, 2, new LumberjackTradeOffers.EmeraldsForBones());
    addTrade(profession, 2, new LumberjackTradeOffers.BedForEmeralds());
    addTrade(profession, 2, new LumberjackTradeOffers.FencesForEmeralds());
    addTrade(profession, 3, new LumberjackTradeOffers.BarkForLogs());
    addTrade(profession, 3, new LumberjackTradeOffers.EmeraldsForStems());
    addTrade(profession, 3, new LumberjackTradeOffers.DoorsForEmeralds());
    addTrade(profession, 3, new LumberjackTradeOffers.UncommonSaplingsForEmeralds());
    addTrade(profession, 4, new LumberjackTradeOffers.BarrelForEmeralds());
    addTrade(profession, 4, new LumberjackTradeOffers.MusicBlocksForLogs());
    if (addBookshelfTrade)
      addTrade(profession, 5, new LumberjackTradeOffers.BookshelfForEmeralds());
    addTrade(profession, 5, new LumberjackTradeOffers.WorkstationForEmeralds());
  }

  protected void addVillageHouses() {
    // register lumberjack structures
    addVillageHouse(VillageType.DESERT, new Identifier("charm:village/desert/houses/desert_lumberjack_1"),
        buildingWeight);
    addVillageHouse(VillageType.DESERT, new Identifier("charm:village/desert/houses/desert_lumberjack_2"),
        buildingWeight);
    addVillageHouse(VillageType.PLAINS, new Identifier("charm:village/plains/houses/plains_lumberjack_1"),
        buildingWeight);
    addVillageHouse(VillageType.SAVANNA, new Identifier("charm:village/savanna/houses/savanna_lumberjack_1"),
        buildingWeight);
    addVillageHouse(VillageType.TAIGA, new Identifier("charm:village/taiga/houses/taiga_lumberjack_1"), buildingWeight);
    addVillageHouse(VillageType.SNOWY, new Identifier("charm:village/snowy/houses/snowy_lumberjack_2"), buildingWeight);
  }
}
