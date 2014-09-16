
package com.yaricraft.equinemagic.proxy;

import com.yaricraft.equinemagic.fluids.EquineMagicFluid;
import com.yaricraft.equinemagic.handler.BlockEventHandler;
import com.yaricraft.equinemagic.handler.BucketHandler;
import com.yaricraft.equinemagic.handler.LivingEventHandler;
import com.yaricraft.equinemagic.items.EquineMagicItem;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy implements IProxy
{
    public void registerEventHandlers()
    {
        BucketHandler.INSTANCE.buckets.put(EquineMagicFluid.blockFluidSpectraSlurry, EquineMagicFluid.itemBucketSpectraSlurry);

        MinecraftForge.EVENT_BUS.register(new LivingEventHandler());
        MinecraftForge.EVENT_BUS.register(new BlockEventHandler());
        MinecraftForge.EVENT_BUS.register(new BucketHandler());
    }
}
