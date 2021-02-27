package tech.tobot.morevillagers.base;

import org.jetbrains.annotations.Nullable;

import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.handler.CustomWorkstationHandler;
import tech.tobot.morevillagers.base.helper.VillagerHelper;
import tech.tobot.morevillagers.block.CustomWorkstation;

public abstract class CustomVillagerProfession extends ModModule {
  public final String professionName;
  public final Identifier villagerIdentifier;
  private final Item workstationCraftingItem;
  @Nullable
  public final SoundEvent workSound;
  public static VillagerProfession profession;

  protected CustomVillagerProfession(String professionName, @Nullable Item workstationCraftingItem,
      @Nullable SoundEvent workSound) {
    this.professionName = professionName;
    this.villagerIdentifier = new Identifier(MoreVillagers.MOD_ID, professionName);
    this.workstationCraftingItem = workstationCraftingItem;
    this.workSound = workSound;
  }

  public CustomWorkstation getWorkstation() {
    return CustomWorkstationHandler.getWorkstation(getClass());
  }

  @Override
  public void register() {
    CustomWorkstationHandler.setWorkstation(this.getClass(),
        new CustomWorkstation(this, professionName, workstationCraftingItem));
  }

  @Override
  public void init() {
    PointOfInterestType poit = VillagerHelper.addPointOfInterestType(getWorkstation().blockIdentifier, getWorkstation(),
        1);
    profession = VillagerHelper.addProfession(villagerIdentifier, poit, workSound);

    addTrades();
    addVillageHouses();
  }

  protected abstract void addTrades();

  protected abstract void addVillageHouses();
}
