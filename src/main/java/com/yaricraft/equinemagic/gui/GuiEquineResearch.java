package com.yaricraft.equinemagic.gui;

import com.yaricraft.equinemagic.reference.ModData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Yari on 9/16/2014.
 */
public class GuiEquineResearch extends GuiContainer
{
    public static ResourceLocation background = new ResourceLocation(ModData.MODID.toLowerCase(), "textures/gui/bookbase.png");

    public GuiEquineResearch(Container container)
    {
        super(container);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int mx, int my)
    {
        drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int i = width - xSize >> 1;
        int j = height - ySize >> 1;
        Minecraft.getMinecraft().getTextureManager().bindTexture(background);
        drawTexturedModalRect(i, j, 0, 0, xSize, ySize);
    }
}
