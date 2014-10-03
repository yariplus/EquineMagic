
package com.yaricraft.equinemagic.proxy;

import com.yaricraft.equinemagic.renderer.RendererTileSpectralAscensionDevice;
import com.yaricraft.equinemagic.tileentity.TileSolarCauldron;
import com.yaricraft.equinemagic.renderer.RendererTileSolarCauldron;
import com.yaricraft.equinemagic.tileentity.TileSpectralAscensionDevice;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
    public void registerRenderers()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileSolarCauldron.class, new RendererTileSolarCauldron());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSpectralAscensionDevice.class, new RendererTileSpectralAscensionDevice());
    }
}
