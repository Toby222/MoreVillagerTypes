package tech.tobot.morevillagers.base;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.helper.VillagerHelper;
import tech.tobot.morevillagers.block.Workstation;

public abstract class CustomVillagerProfession extends ModModule {
  public static final String PROFESSION_NAME = null;
  public static Workstation workStation;
  public static SoundEvent workSound;
  public static VillagerProfession profession;
  public static final Identifier villagerIdentifier = new Identifier(MoreVillagers.MOD_ID, PROFESSION_NAME);

  @Override
  public void register() {
    workStation = new Workstation(this, PROFESSION_NAME);
  }

  @Override
  public void init() {
    PointOfInterestType poit = VillagerHelper.addPointOfInterestType(workStation.blockIdentifier, workStation, 1);
    profession = VillagerHelper.addProfession(villagerIdentifier, poit, workSound);

    addTrades();
    addVillageHouses();
  }

  protected abstract void addTrades();

  protected abstract void addVillageHouses();
}
