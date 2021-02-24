package tech.tobot.morevillagers;

import java.util.Arrays;

import net.fabricmc.api.ModInitializer;
import tech.tobot.morevillagers.base.Loader;
import tech.tobot.morevillagers.base.handler.LogHandler;
import tech.tobot.morevillagers.module.Beekeepers;
import tech.tobot.morevillagers.module.Core;

public class MoreVillagers implements ModInitializer {
	public static final String MOD_ID = "morevillagers";
	public static LogHandler LOG = new LogHandler("morevillagers");

	private static boolean hasRunFirst = false;

	public static void runFirst() {
		if (hasRunFirst)
			return;

		new Loader(MOD_ID, Arrays.asList(Beekeepers.class, Core.class));
	}

	@Override
	public void onInitialize() {
		runFirst();
	}
}
