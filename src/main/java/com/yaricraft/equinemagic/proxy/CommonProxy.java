
package com.yaricraft.equinemagic.proxy;

import com.yaricraft.equinemagic.EquineMagic;
import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
import com.yaricraft.equinemagic.handler.BlockEventHandler;
import com.yaricraft.equinemagic.handler.BucketHandler;
import com.yaricraft.equinemagic.handler.GuiHandler;
import com.yaricraft.equinemagic.handler.LivingEventHandler;
import com.yaricraft.equinemagic.handler.PlayerHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
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

    // cpw
    public World getClientWorld()
    {
        return null;
    }

    public void registerIcons(TextureStitchEvent.Pre event) {};

    public void initializeIcons(TextureStitchEvent.Post event) {};
}
