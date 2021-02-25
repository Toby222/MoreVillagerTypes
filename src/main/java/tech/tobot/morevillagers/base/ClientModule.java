package tech.tobot.morevillagers.base;

import net.minecraft.client.MinecraftClient;

public abstract class ClientModule {
    protected ModModule module;

    protected ClientModule(ModModule module) {
        this.module = module;
    }

    public ModModule getModule() {
        return module;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public void register() {
        // run on client side, even if module disabled
    }

    public void init() {
        // run on client side, only if module enabled
    }

    public void loadWorld(MinecraftClient client) {
        // run on client side, only if module enabled
    }
}