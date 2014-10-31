package com.yaricraft.equinemagic.handler;

import com.yaricraft.equinemagic.EquineMagicPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;
import scala.Int;

import java.util.EnumSet;

/**
 * Created by Yari on 10/28/2014.
 */
public class ClientTickHandler
{
    private Minecraft mc;
    private boolean showInChat = true;

    public ClientTickHandler()
    {
        mc = FMLClientHandler.instance().getClient();
    }

    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent event)
    {
        if (event.phase.equals(TickEvent.Phase.START))
            return;

        if ((mc.inGameHasFocus || mc.currentScreen == null || (mc.currentScreen instanceof GuiChat && showInChat))
                && !mc.gameSettings.showDebugInfo)
        {
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            ScaledResolution scaledResolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

            /*
            NBTTagCompound nbt = new NBTTagCompound();
            mc.thePlayer.getExtendedProperties(EquineMagicPlayer.NAME).saveNBTData(nbt);
            String s = String.valueOf(nbt.getInteger("Magic"));
            mc.fontRenderer.drawStringWithShadow("TEST STRING = " + s, 30, scaledResolution.getScaledHeight() - 30, 0xffffff);
            */

            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}
