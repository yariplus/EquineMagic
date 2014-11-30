package com.yaricraft.equinemagic.network;

import com.yaricraft.equinemagic.EquineMagicPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;

/**
 * Created by Yari on 11/14/2014.
 */
public class EquineMessageHandlerExtendedProperties implements IMessageHandler<EquineMessageExtendedProperties, IMessage>
{
    public EquineMessageHandlerExtendedProperties() {}

    @Override
    public IMessage onMessage(EquineMessageExtendedProperties message, MessageContext ctx)
    {
        ((EquineMagicPlayer)Minecraft.getMinecraft().thePlayer.getExtendedProperties("EquineMagicPlayer")).chaos = message.chaos;
        ((EquineMagicPlayer)Minecraft.getMinecraft().thePlayer.getExtendedProperties("EquineMagicPlayer")).darkness = message.darkness;
        ((EquineMagicPlayer)Minecraft.getMinecraft().thePlayer.getExtendedProperties("EquineMagicPlayer")).magic = message.magic;
        //((EquineMagicPlayer)Minecraft.getMinecraft().thePlayer.getExtendedProperties("EquineMagicPlayer")).hud = message.hud;

        return null;
    }
}
