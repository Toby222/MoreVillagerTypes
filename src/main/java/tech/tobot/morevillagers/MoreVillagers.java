package tech.tobot.morevillagers;

import java.util.Arrays;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import tech.tobot.morevillagers.base.Loader;
import tech.tobot.morevillagers.base.handler.ItemGroupHandler;
import tech.tobot.morevillagers.base.handler.LogHandler;
import tech.tobot.morevillagers.module.Beekeeper;
import tech.tobot.morevillagers.module.Core;
import tech.tobot.morevillagers.module.Coupon;
import tech.tobot.morevillagers.module.Innkeeper;
import tech.tobot.morevillagers.module.Lumberjack;

public class MoreVillagers implements ModInitializer {
	public static final String MOD_ID = "morevillagers";
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "creative_tab"))
			.icon(() -> new ItemStack(Core.getBaseWorkstation()))
			.appendItems(items -> items.addAll(ItemGroupHandler.getItems())).build();
	public static final LogHandler LOG = new LogHandler(MOD_ID);

	private static boolean hasRunFirst = false;

	public static void runFirst() {
		if (hasRunFirst)
			return;

		new Loader(MOD_ID, Arrays.asList(Core.class, Beekeeper.class, Lumberjack.class, Innkeeper.class, Coupon.class));
	}

	@Override
	public void onInitialize() {
		runFirst();
	}
}
