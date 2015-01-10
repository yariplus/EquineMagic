package com.yaricraft.equinemagic.client.renderer;

import com.yaricraft.equinemagic.util.DrawingHelper;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

/**
 * Created by Yari on 11/15/2014.
 */
public class RendererPedestal implements ISimpleBlockRenderingHandler
{
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
    {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        Tessellator tessellator = Tessellator.instance;

        // Go to the block's location
        tessellator.addTranslation(x, y, z);

        IIcon c = block.getIcon(1, 1);
        float u = c.getMinU();
        float v = c.getMinV();
        float U = c.getMaxU();
        float V = c.getMaxV();

        /*
        Vec3 vector0 = Vec3.createVectorHelper(DrawingHelper.D8TH,     DrawingHelper.D8TH,     DrawingHelper.D8TH);

        Vec3 vector1 = Vec3.createVectorHelper(    DrawingHelper.D8TH,                      DrawingHelper.D8TH,     DrawingHelper.D8TH);
        Vec3 vector2 = Vec3.createVectorHelper(1 - DrawingHelper.D8TH - DrawingHelper.D4TH, DrawingHelper.D8TH,     DrawingHelper.D8TH);
        Vec3 vector3 = Vec3.createVectorHelper(1 - DrawingHelper.D4TH,                      0,                  0);

        DrawingHelper.renderQuad(vector0, vector1, vector2, vector3, c, true);

        //

        vector0 = Vec3.createVectorHelper(0, 0, 0);

        vector1 = Vec3.createVectorHelper(1 - DrawingHelper.D8TH,          DrawingHelper.D8TH,      DrawingHelper.D8TH);
        vector2 = Vec3.createVectorHelper(1 - DrawingHelper.D4TH,          DrawingHelper.D8TH,      DrawingHelper.D8TH);
        vector3 = Vec3.createVectorHelper(1 - DrawingHelper.D4TH,          0,                       0);

        DrawingHelper.renderQuad(vector0, vector1, vector2, vector3, c, true);

        */

        tessellator.setNormal(0, 1, 0);

        tessellator.addTranslation(0, 0, 0);
        tessellator.addVertexWithUV( 0, 1, 0, u, V);
        tessellator.addVertexWithUV( 1, 1, 0, u, v);
        tessellator.addVertexWithUV( 1, 0, 0, U, v);
        tessellator.addVertexWithUV( 0, 0, 0, U, V);

        tessellator.addTranslation( 1, 0, 0);
        tessellator.addVertexWithUV(  0,   1,   0, u, V);
        tessellator.addVertexWithUV(  0,   1,   1, u, v);
        tessellator.addVertexWithUV(  0,   0,   1, U, v);
        tessellator.addVertexWithUV(  0,   0,   0, U, V);

        tessellator.addTranslation(0, 0, 1);
        tessellator.addVertexWithUV(   0,   1,   0, u, V);
        tessellator.addVertexWithUV(  -1,   1,   0, u, v);
        tessellator.addVertexWithUV(  -1,   0,   0, U, v);
        tessellator.addVertexWithUV(   0,   0,   0, U, V);

        tessellator.addTranslation(-1, 0, 0);
        tessellator.addVertexWithUV(   0,   1,    0, u, V);
        tessellator.addVertexWithUV(   0,   1,   -1, u, v);
        tessellator.addVertexWithUV(   0,   0,   -1, U, v);
        tessellator.addVertexWithUV(   0,   0,    0, U, V);

        tessellator.addTranslation(0, 0, -1);
        tessellator.addVertexWithUV(    1,   0,    0, u, V);
        tessellator.addVertexWithUV(    1,   0,    1, u, v);
        tessellator.addVertexWithUV(    0,   0,    1, U, v);
        tessellator.addVertexWithUV(    0,   0,    0, U, V);

        tessellator.addTranslation(0, 1, 0);
        tessellator.addVertexWithUV(   0,   0,    1, u, V);
        tessellator.addVertexWithUV(   1,   0,    1, u, v);
        tessellator.addVertexWithUV(   1,   0,    0, U, v);
        tessellator.addVertexWithUV(   0,   0,    0, U, V);

        tessellator.addTranslation(0, -1, 0);

        // Return to origin
        tessellator.addTranslation(-x, -y, -z);

        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return false;
    }

    @Override
    public int getRenderId()
    {
        return 0;
    }
}
