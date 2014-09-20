
package com.yaricraft.equinemagic.proxy;

import com.yaricraft.equinemagic.logic.TileCauldron;
import com.yaricraft.equinemagic.renderer.TESR;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    public void registerRenderers()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileCauldron.class, new TESR());
    }
}
