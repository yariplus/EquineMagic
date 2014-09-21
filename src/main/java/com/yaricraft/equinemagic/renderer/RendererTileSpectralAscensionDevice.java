package com.yaricraft.equinemagic.renderer;

import com.yaricraft.equinemagic.reference.ModData;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

/**
 * Created by Yari on 9/20/2014.
 */
public class RendererTileSpectralAscensionDevice extends TileEntitySpecialRenderer
{
    IModelCustom model;
    public static final ResourceLocation texture = new ResourceLocation(ModData.MODID.toLowerCase(), "textures/blocks/blockDecor_Bottom_0.png");

    public RendererTileSpectralAscensionDevice(){
        model = AdvancedModelLoader.loadModel(new ResourceLocation(ModData.MODID.toLowerCase(), "models/spectralAscensionDevice.obj"));
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslated(x+0.5, y+0.350, z+0.5);
        this.bindTexture(texture);
        //Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        GL11.glPushMatrix();
        GL11.glScaled(0.30D, 0.18D, 0.30D);
        model.renderAll();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
