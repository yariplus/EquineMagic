package com.yaricraft.equinemagic.handler;

import com.yaricraft.equinemagic.EquineMagicPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;

/**
 * Created by Yari on 9/26/2014.
 */
public class PlayerHandler
{
    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer && EquineMagicPlayer.get((EntityPlayer) event.entity) == null) event.entity.registerExtendedProperties(EquineMagicPlayer.NAME, new EquineMagicPlayer());
    }
}
