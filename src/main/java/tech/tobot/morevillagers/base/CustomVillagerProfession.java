package tech.tobot.morevillagers.base;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.helper.VillagerHelper;
import tech.tobot.morevillagers.block.Workstation;

public abstract class CustomVillagerProfession extends ModModule {
  public final String professionName;
  public final Identifier villagerIdentifier;
  public static Workstation workStation;
  public final SoundEvent workSound;
  public static VillagerProfession profession;

  protected CustomVillagerProfession(String professionName) {
    this.professionName = professionName;
    villagerIdentifier = new Identifier(MoreVillagers.MOD_ID, professionName);
    workSound = null;
  }

  protected CustomVillagerProfession(String professionName, SoundEvent workSound) {
    this.professionName = professionName;
    villagerIdentifier = new Identifier(MoreVillagers.MOD_ID, professionName);
    this.workSound = workSound;
  }

  @Override
  public void register() {
    workStation = new Workstation(this, professionName);
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
