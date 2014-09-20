
package com.yaricraft.equinemagic.proxy;

import com.yaricraft.equinemagic.EquineMagic;
import com.yaricraft.equinemagic.fluids.EquineMagicFluid;
import com.yaricraft.equinemagic.handler.BlockEventHandler;
import com.yaricraft.equinemagic.handler.BucketHandler;
import com.yaricraft.equinemagic.handler.GuiHandler;
import com.yaricraft.equinemagic.handler.LivingEventHandler;
import com.yaricraft.equinemagic.logic.TileCauldron;
import com.yaricraft.equinemagic.logic.TileSpectralAscensionDevice;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.renderer.TESR;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy implements IProxy
{
    public void registerEventHandlers()
    {
        BucketHandler.INSTANCE.buckets.put(EquineMagicFluid.blockFluidSpectraSlurry, EquineMagicFluid.itemBucketSpectraSlurry);

        MinecraftForge.EVENT_BUS.register(new LivingEventHandler());
        MinecraftForge.EVENT_BUS.register(new BlockEventHandler());
        MinecraftForge.EVENT_BUS.register(new BucketHandler());

        NetworkRegistry.INSTANCE.registerGuiHandler(EquineMagic.instance, new GuiHandler());

        GameRegistry.registerTileEntity(TileCauldron.class, ModNames.BLOCK_SOLAR_CAULDRON);
        GameRegistry.registerTileEntity(TileSpectralAscensionDevice.class, ModNames.BLOCK_SPECTRAL_ASCENSION_DEVICE);
    }

    public void registerRenderers() {}
}
