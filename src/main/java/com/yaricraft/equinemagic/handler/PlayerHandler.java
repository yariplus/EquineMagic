package com.yaricraft.equinemagic.handler;

import com.yaricraft.equinemagic.EquineMagic;
import com.yaricraft.equinemagic.EquineMagicPlayer;
import com.yaricraft.equinemagic.network.MessageExtendedProperties;
import com.yaricraft.equinemagic.network.MessagePlayerMovement;
import com.yaricraft.equinemagic.util.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MovementInput;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

import java.util.UUID;

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

            MessageExtendedProperties message = new MessageExtendedProperties();
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
            MessageExtendedProperties message = new MessageExtendedProperties();
            message.chaos = ((EquineMagicPlayer) event.entity.getExtendedProperties("EquineMagicPlayer")).chaos;
            message.darkness = ((EquineMagicPlayer) event.entity.getExtendedProperties("EquineMagicPlayer")).darkness;
            message.magic = ((EquineMagicPlayer) event.entity.getExtendedProperties("EquineMagicPlayer")).magic;
            EquineMagic.network.sendTo(message, (EntityPlayerMP) event.entity);
        }
    }

    private static boolean wasJumping = false;
    private static boolean wasSneaking = false;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent evt)
    {
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        if (player != null)
        {
            int direction = -1;

            MovementInput input = player.movementInput;
            if (input.jump && !wasJumping) { direction = ForgeDirection.UP.ordinal(); }
            if (input.sneak && !wasSneaking) { direction = ForgeDirection.DOWN.ordinal(); }

            wasJumping = input.jump;
            wasSneaking = input.sneak;

            if (direction != -1)
            {
                MessagePlayerMovement message = new MessagePlayerMovement();
                message.direction = direction;
                message.lbits = player.getUniqueID().getLeastSignificantBits();
                message.mbits = player.getUniqueID().getMostSignificantBits();
                EquineMagic.network.sendToServer(message);
            }
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityInteractEvent event)
    {
    }
}
