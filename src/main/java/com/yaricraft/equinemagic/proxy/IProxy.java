
package com.yaricraft.equinemagic.proxy;

import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;

public interface IProxy
{
    public abstract void registerEventHandlers();

    public abstract void registerRenderers();

    public World getClientWorld();

    public void registerIcons(TextureStitchEvent.Pre event);

    public void initializeIcons(TextureStitchEvent.Post event);

    public void registerKeybindings();
}
