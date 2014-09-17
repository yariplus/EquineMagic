
package com.yaricraft.equinemagic.proxy;

import com.yaricraft.equinemagic.EquineMagic;
import com.yaricraft.equinemagic.fluids.EquineMagicFluid;
import com.yaricraft.equinemagic.handler.BlockEventHandler;
import com.yaricraft.equinemagic.handler.BucketHandler;
import com.yaricraft.equinemagic.handler.GuiHandler;
import com.yaricraft.equinemagic.handler.LivingEventHandler;
import com.yaricraft.equinemagic.items.EquineMagicItem;
import cpw.mods.fml.common.network.NetworkRegistry;
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
    }
}
