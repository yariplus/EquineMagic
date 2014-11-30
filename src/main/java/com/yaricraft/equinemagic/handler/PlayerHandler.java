package com.yaricraft.equinemagic.handler;

import com.yaricraft.equinemagic.EquineMagic;
import com.yaricraft.equinemagic.EquineMagicPlayer;
import com.yaricraft.equinemagic.network.EquineMessageExtendedProperties;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

/**
 * Created by Yari on 9/26/2014.
 */
public class PlayerHandler
{
    public static int playerTickDelay;

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer && EquineMagicPlayer.get((EntityPlayer) event.entity) == null) event.entity.registerExtendedProperties(EquineMagicPlayer.NAME, new EquineMagicPlayer());
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if (event.entity instanceof EntityPlayerMP)
        {
            EquineMagicPlayer props = ((EquineMagicPlayer) event.entity.getExtendedProperties("EquineMagicPlayer"));
            if (props.darkness > 0)
            {
                props.darkness--;
            }

            if (props.magic < 50)
            {
                props.magic++;
            }

            EquineMessageExtendedProperties message = new EquineMessageExtendedProperties();
            message.chaos = props.chaos;
            message.darkness = props.darkness;
            message.magic = props.magic;
            EquineMagic.network.sendTo(message, (EntityPlayerMP) event.entity);
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        // Send extended properties to player.
        if (event.entity instanceof EntityPlayerMP)
        {
            EquineMessageExtendedProperties message = new EquineMessageExtendedProperties();
            message.chaos = ((EquineMagicPlayer) event.entity.getExtendedProperties("EquineMagicPlayer")).chaos;
            message.darkness = ((EquineMagicPlayer) event.entity.getExtendedProperties("EquineMagicPlayer")).darkness;
            message.magic = ((EquineMagicPlayer) event.entity.getExtendedProperties("EquineMagicPlayer")).magic;
            EquineMagic.network.sendTo(message, (EntityPlayerMP) event.entity);
        }
    }
}
