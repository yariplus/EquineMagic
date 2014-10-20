
package com.yaricraft.equinemagic.proxy;

import com.yaricraft.equinemagic.EquineMagic;
import com.yaricraft.equinemagic.fluids.EquineMagicFluid;
import com.yaricraft.equinemagic.handler.BlockEventHandler;
import com.yaricraft.equinemagic.handler.BucketHandler;
import com.yaricraft.equinemagic.handler.GuiHandler;
import com.yaricraft.equinemagic.handler.LivingEventHandler;
import com.yaricraft.equinemagic.handler.PlayerHandler;
import com.yaricraft.equinemagic.tileentity.TileSolarCauldron;
import com.yaricraft.equinemagic.tileentity.TileSpectralAscensionDevice;
import com.yaricraft.equinemagic.reference.ModNames;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy implements IProxy
{
    public void registerEventHandlers()
    {
        BucketHandler.INSTANCE.buckets.put(EquineMagicFluid.blockFluidSpectraSlurry, EquineMagicFluid.itemBucketSpectraSlurry);

        MinecraftForge.EVENT_BUS.register(new LivingEventHandler());
        MinecraftForge.EVENT_BUS.register(new BlockEventHandler());
        MinecraftForge.EVENT_BUS.register(new BucketHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerHandler());

        NetworkRegistry.INSTANCE.registerGuiHandler(EquineMagic.instance, new GuiHandler());
    }

    public void registerRenderers() {}
}
