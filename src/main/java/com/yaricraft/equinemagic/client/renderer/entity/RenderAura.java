package com.yaricraft.equinemagic.client.renderer.entity;

import com.yaricraft.equinemagic.block.BlockDecor;
import com.yaricraft.equinemagic.block.EquineMagicBlock;
import com.yaricraft.equinemagic.reference.ModData;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Yari on 12/9/2014.
 */
public class RenderAura extends Render
{
    public static final ResourceLocation texture = new ResourceLocation(ModData.MODID.toLowerCase(), "textures/blocks/block_decor_front_0.png");

    @Override
    public void doRender(Entity entity, double x, double y, double z, float meta, float pass)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);

        Tessellator tessellator = Tessellator.instance;

        IIcon icon = EquineMagicBlock.blockDecor.getIcon(0,0);

        float u = ((IIcon) icon).getMinU();
        float U = ((IIcon) icon).getMaxU();
        float v = ((IIcon) icon).getMinV();
        float V = ((IIcon) icon).getMaxV();

        this.bindTexture(TextureMap.locationBlocksTexture);

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, (double) u, (double) V);
        tessellator.addVertexWithUV(1.0D, 0.0D, 0.0D, (double) U, (double) V);
        tessellator.addVertexWithUV(1.0D, 1.0D, 0.0D, (double) U, (double) v);
        tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, (double) u, (double) v);
        tessellator.draw();

        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return null;
    }
}
