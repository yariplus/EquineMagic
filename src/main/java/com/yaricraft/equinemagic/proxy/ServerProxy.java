
package com.yaricraft.equinemagic.proxy;

import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;

public class ServerProxy extends CommonProxy
{
    @Override
    public void registerKeybindings() { /* NOOP */ }

    @Override
    public void registerRenderers() { /* NOOP */ }

    @Override
    public World getClientWorld()
    {
        return null;
    } //cpw

    @Override
    public void registerIcons(TextureStitchEvent.Pre event) { /* NOOP */ };

    @Override
    public void initializeIcons(TextureStitchEvent.Post event) { /* NOOP */ };
}
