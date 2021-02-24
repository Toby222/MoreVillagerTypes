package tech.tobot.morevillagers.module;

import net.minecraft.util.Identifier;
import tech.tobot.morevillagers.MoreVillagers;
import tech.tobot.morevillagers.base.ModModule;
import tech.tobot.morevillagers.base.iface.Config;
import tech.tobot.morevillagers.base.iface.Module;
import tech.tobot.morevillagers.client.CoreClient;

@Module(mod = MoreVillagers.MOD_ID, client = CoreClient.class, alwaysEnabled = true, description = "Core configuration values.")
public class Core extends ModModule {
    public static final Identifier MSG_SERVER_OPEN_INVENTORY = new Identifier(MoreVillagers.MOD_ID,
            "server_open_inventory");

    @Config(name = "Debug mode", description = "If true, routes additional debug messages into the standard game log.")
    public static boolean debug = false;
}
