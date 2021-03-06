package tech.tobot.morevillagers.module;

import net.minecraft.item.Item;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.ModModule;
import tech.tobot.morevillagers.base.iface.Module;
import tech.tobot.morevillagers.client.CoreClient;
import tech.tobot.morevillagers.item.CouponItem;

@Module(mod = MoreVillagers.MOD_ID, client = CoreClient.class, description =
    "Item that lowers lowers trade prices " + "of" + " Villagers it's used on.")
public class Coupon extends ModModule {
  private static Item couponItem;
  
  public static Item getCouponItem() {
    return couponItem;
  }
  
  @Override
  public void register() {
    couponItem = new CouponItem(this);
  }
}
