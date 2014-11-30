package com.yaricraft.equinemagic.client.gui;

import com.yaricraft.equinemagic.EquineMagicPlayer;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.util.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

/**
 * Created by Yari on 11/14/2014.
 */
public class GuiEquineHUD extends Gui
{
    private Minecraft mc;

    public static final ResourceLocation textureBarBase = new ResourceLocation(ModData.MODID.toLowerCase(), "textures/gui/em_bar.png");

    boolean debug = true;

    public GuiEquineHUD(Minecraft mc)
    {
        super();
        this.mc = mc;
    }

    //
    // This event is called by GuiIngameForge during each frame by
    // GuiIngameForge.pre() and GuiIngameForce.post().
    //
    @SubscribeEvent
    public void onRenderExperienceBar(RenderGameOverlayEvent event)
    {
        if (!((EquineMagicPlayer)Minecraft.getMinecraft().thePlayer.getExtendedProperties(EquineMagicPlayer.NAME)).hud)
        {
            LogHelper.info("HUD is false");
            return;
        }

        //
        // We draw after the ExperienceBar has drawn.  The event raised by GuiIngameForge.pre()
        // will return true from isCancelable.  If you call event.setCanceled(true) in
        // that case, the portion of rendering which this event represents will be canceled.
        // We want to draw *after* the experience bar is drawn, so we make sure isCancelable() returns
        // false and that the eventType represents the ExperienceBar event.
        if(event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE)
        {
            return;
        }

        // GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        ScaledResolution scaledResolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

        if (debug)
        {
            LogHelper.info(scaledResolution.getScaledWidth());
            LogHelper.info(scaledResolution.getScaledHeight());
            debug = false;
        }

        int magic  =  ((EquineMagicPlayer)mc.thePlayer.getExtendedProperties(EquineMagicPlayer.NAME)).magic;
        int chaos  =  ((EquineMagicPlayer)mc.thePlayer.getExtendedProperties(EquineMagicPlayer.NAME)).chaos;
        int shadow =  ((EquineMagicPlayer)mc.thePlayer.getExtendedProperties(EquineMagicPlayer.NAME)).darkness;

        mc.renderEngine.bindTexture(textureBarBase);
        drawTexturedModalRect(0, scaledResolution.getScaledHeight()-50, 0, 0, 100, 50);
        drawTexturedModalRect(47, (scaledResolution.getScaledHeight()-50) + 30 + 0, 100, 0, 1 + magic, 4);
        drawTexturedModalRect(47, (scaledResolution.getScaledHeight()-50) + 34 + 1, 100, 4, 1 + chaos, 4);
        drawTexturedModalRect(47, (scaledResolution.getScaledHeight()-50) + 38 + 2, 100, 8, 1 + shadow / 50, 4);



        //mc.fontRenderer.drawStringWithShadow("C=" + chaos,  10, scaledResolution.getScaledHeight() - 15, 0xffffff);

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
}
