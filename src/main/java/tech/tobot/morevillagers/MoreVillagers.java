package tech.tobot.morevillagers;

import java.util.Arrays;

import net.fabricmc.api.ModInitializer;
import tech.tobot.morevillagers.base.Loader;
import tech.tobot.morevillagers.base.handler.LogHandler;
import tech.tobot.morevillagers.module.Beekeeper;
import tech.tobot.morevillagers.module.Core;
import tech.tobot.morevillagers.module.Lumberjack;

public class MoreVillagers implements ModInitializer {
	public static final String MOD_ID = "morevillagers";
	public static final LogHandler LOG = new LogHandler(MOD_ID);

	private static boolean hasRunFirst = false;

	public static void runFirst() {
		if (hasRunFirst)
			return;

		new Loader(MOD_ID, Arrays.asList(Beekeeper.class, Core.class, Lumberjack.class));
	}

	@Override
	public void onInitialize() {
		runFirst();
	}
}
