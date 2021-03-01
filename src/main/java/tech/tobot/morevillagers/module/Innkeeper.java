package tech.tobot.morevillagers.module;

import static tech.tobot.morevillagers.base.helper.VillagerHelper.addTrade;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.CustomVillagerProfession;
import tech.tobot.morevillagers.base.iface.Module;
import tech.tobot.morevillagers.trades.InnkeeperTradeOffers;

@Module(mod = MoreVillagers.MOD_ID, description = "Innkeepers are villagers that trade Potions and some ingredients.")
public class Innkeeper extends CustomVillagerProfession {
  public Innkeeper() {
    super("innkeeper", Items.POTION, SoundEvents.BLOCK_BEEHIVE_WORK);
  }

  @Override
  public void register() {
    super.register();

    Potion booze = new Potion(new StatusEffectInstance(StatusEffects.RESISTANCE, 30 * 20, 2),
        new StatusEffectInstance(StatusEffects.NAUSEA, 10 * 20));
    Registry.register(Registry.POTION, new Identifier(MoreVillagers.MOD_ID, "booze"), booze);

    Potion aurumPotabile = new Potion(new StatusEffectInstance(StatusEffects.ABSORPTION, 120 * 20, 4),
        new StatusEffectInstance(StatusEffects.RESISTANCE, 120 * 20, 4),
        new StatusEffectInstance(StatusEffects.NAUSEA, 10 * 20));

    Registry.register(Registry.POTION, new Identifier(MoreVillagers.MOD_ID, "aurum_potabile"), aurumPotabile);

    Potion spikedFireResistance = new Potion(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 5 * 60 * 20),
        new StatusEffectInstance(StatusEffects.NAUSEA, 5 * 20));
    Registry.register(Registry.POTION, new Identifier(MoreVillagers.MOD_ID, "spiked_fire_resistance"),
        spikedFireResistance);
  }

  protected void addTrades() {
    // register innkeeper trades
    addTrade(profession, 1, new InnkeeperTradeOffers.WaterbottleForEmerald());
    addTrade(profession, 1, new InnkeeperTradeOffers.GunpowderForEmerald());
    addTrade(profession, 2, new InnkeeperTradeOffers.CheapDrinkForEmerald());
    addTrade(profession, 2, new InnkeeperTradeOffers.EmeraldForBottles());
    addTrade(profession, 3, new InnkeeperTradeOffers.FireResistanceForEmeralds());
    addTrade(profession, 4, new InnkeeperTradeOffers.FermentedSpiderEyesForEmeralds());
    addTrade(profession, 4, new InnkeeperTradeOffers.RabbitsFootForEmeralds());
    addTrade(profession, 5, new InnkeeperTradeOffers.AurumPotabileForEmeralds());
  }
}
