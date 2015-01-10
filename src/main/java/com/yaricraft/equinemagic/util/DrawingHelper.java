package com.yaricraft.equinemagic.util;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;

/**
 * Created by Yari on 11/17/2014.
 */
public class DrawingHelper
{
    public static final double D64TH  = 0.015625;
    public static final double D32TH  = 0.03125;
    public static final double D16TH  = 0.0625;
    public static final double D8TH   = 0.125;
    public static final double D4TH   = 0.25;
    public static final double D2TH   = 0.5;

    public static final float F64TH  = 0.015625F;
    public static final float F32TH  = 0.03125F;
    public static final float F16TH  = 0.0625F;
    public static final float F8TH   = 0.125F;
    public static final float F4TH   = 0.25F;
    public static final float F2TH   = 0.5F;

    public static void renderQuad(float xT, float yT, float zT,
                                  double x1, double y1, double z1,
                                  double x2, double y2, double z2,
                                  double x3, double y3, double z3,
                                  IIcon icon, boolean mirror)
    {
        float u = icon.getMinU();
        float v = icon.getMinV();
        float U = icon.getMaxU();
        float V = icon.getMaxV();

        Tessellator tes = Tessellator.instance;
        tes.addTranslation(xT, yT, zT);

        tes.addVertexWithUV(x1, y1, z1, u, v);
        tes.addVertexWithUV(x2, y2, z2, u, V);
        tes.addVertexWithUV(x3, y3, z3, U, V);
        tes.addVertexWithUV( 0,  0,  0, U, v);

        if (mirror)
        {
            tes.addVertexWithUV(x3, y3, z3, u, v);
            tes.addVertexWithUV(x2, y2, z2, u, V);
            tes.addVertexWithUV(x1, y1, z1, U, V);
            tes.addVertexWithUV(0, 0, 0, U, v);
        }

        tes.addTranslation(-xT, -yT, -zT);
    }

    public static void renderQuadRotatedCenterX4(Vec3 translation, Vec3 offset1, Vec3 offset2, Vec3 offset3,
                                  IIcon icon, boolean mirror)
    {
        Vec3 centeredTranslation = translation.addVector(-0.5, 0, -0.5);
        Vec3 centeredOffset1 = offset1.addVector(-0.5, 0, -0.5);
        Vec3 centeredOffset2 = offset2.addVector(-0.5, 0, -0.5);
        Vec3 centeredOffset3 = offset3.addVector(-0.5, 0, -0.5);

        float rotation = 0;
        while(rotation >= Math.PI)
        {
            centeredTranslation.rotateAroundY(rotation);
            centeredOffset1.rotateAroundY(rotation);
            centeredOffset2.rotateAroundY(rotation);
            centeredOffset3.rotateAroundY(rotation);

            renderQuad(centeredTranslation,
                       centeredOffset1,
                       centeredOffset2,
                       centeredOffset3, icon, mirror);
            rotation += Math.PI / 4;
        }
    }

    public static void renderQuad(Vec3 translation, Vec3 offset1, Vec3 offset2, Vec3 offset3,
                                  IIcon icon, boolean mirror)
    {
        float u = icon.getMinU();
        float v = icon.getMinV();
        float U = icon.getMaxU();
        float V = icon.getMaxV();

        Tessellator tes = Tessellator.instance;
        //tes.startDrawingQuads();
        //tes.setNormal(0,0,0);
        tes.addTranslation((float)translation.xCoord, (float)translation.yCoord, (float)translation.zCoord);

        tes.addVertexWithUV(offset1.xCoord, offset1.yCoord, offset1.zCoord, u, V);
        tes.addVertexWithUV(offset2.xCoord, offset2.yCoord, offset2.zCoord, U, V);
        tes.addVertexWithUV(offset3.xCoord, offset3.yCoord, offset3.zCoord, U, v);
        tes.addVertexWithUV(0, 0, 0, u, v);

        if (mirror)
        {
            tes.addVertexWithUV(offset3.xCoord, offset3.yCoord, offset3.zCoord, U, v);
            tes.addVertexWithUV(offset2.xCoord, offset2.yCoord, offset2.zCoord, U, V);
            tes.addVertexWithUV(offset1.xCoord, offset1.yCoord, offset1.zCoord, u, V);
            tes.addVertexWithUV(0, 0, 0, u, v);
        }

        tes.addTranslation(-(float)translation.xCoord, -(float)translation.yCoord, -(float)translation.zCoord);
    }

    //pokefen
    public static void drawBlock(Block block, IIcon icon, RenderBlocks renderer)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
    }
    public static void drawBlock(Block block, int meta, RenderBlocks renderer)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, meta));
        tessellator.draw();
    }

}
