package tech.tobot.morevillagers.module;

import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.ModModule;
import tech.tobot.morevillagers.base.iface.Config;
import tech.tobot.morevillagers.base.iface.Module;
import tech.tobot.morevillagers.client.CoreClient;

@Module(mod = MoreVillagers.MOD_ID, client = CoreClient.class, alwaysEnabled = true, description = "Core configuration values.")
public class Core extends ModModule {
    @Config(name = "Debug mode", description = "If true, routes additional debug messages into the standard game log.")
    public static final boolean DEBUG = false;
}
