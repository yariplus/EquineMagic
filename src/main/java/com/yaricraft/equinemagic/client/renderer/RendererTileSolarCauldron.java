package com.yaricraft.equinemagic.client.renderer;

import com.yaricraft.equinemagic.fluid.BlockFluidSpectraSlurry;
import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.tileentity.TileSpectralCauldron;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

/**
 * Created by Yari on 9/19/2014.
 */
public class RendererTileSolarCauldron extends TileEntitySpecialRenderer
{
    IModelCustom model;
    public static final ResourceLocation texture = new ResourceLocation(ModData.MODID.toLowerCase(), "textures/blocks/block_decor_front_0.png");

    public RendererTileSolarCauldron()
    {
        model = AdvancedModelLoader.loadModel(new ResourceLocation(ModData.MODID.toLowerCase(), "models/basin.obj"));
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);

        if (((TileSpectralCauldron)tileentity).itemStacks[0] != null)
        {
            TileSpectralCauldron cauldron = (TileSpectralCauldron) tileentity;

            Tessellator tessellator = Tessellator.instance;

            ItemStack itemStack = cauldron.itemStacks[0];
            IIcon icon = itemStack.getIconIndex();

            float u = ((IIcon) icon).getMinU();
            float U = ((IIcon) icon).getMaxU();
            float v = ((IIcon) icon).getMinV();
            float V = ((IIcon) icon).getMaxV();

            this.bindTexture(TextureMap.locationItemsTexture);

            GL11.glPushMatrix();
            if (((TileSpectralCauldron)tileentity).tank.getFluidAmount() > 1)
            {
                GL11.glTranslated(4*0.0625, 4*0.0625, 4*0.0625);
            }else
            {
                GL11.glTranslated(4*0.0625, 1*0.0625, 4*0.0625);
            }
            GL11.glScaled(0.5, 1, 0.5);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glRotated(90, 0.5, 0, 0);
            ItemRenderer.renderItemIn2D(tessellator, U, v, u, V, ((IIcon) icon).getIconWidth(), ((IIcon) icon).getIconHeight(), 0.0625F);
            GL11.glPopMatrix();
        }

        if (((TileSpectralCauldron)tileentity).tank.getFluidAmount() > 1)
        {
            TileSpectralCauldron cauldron = (TileSpectralCauldron) tileentity;

            Tessellator tessellator = Tessellator.instance;

            FluidStack fluidStack = cauldron.tank.getFluid();
            IIcon icon = EquineMagicFluid.blockFluidSpectraSlurry.getIcon(0, 0);

            if (icon == null)
            {
                //TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
                //ResourceLocation resourcelocation = texturemanager.getResourceLocation(icon.g.getItemSpriteNumber());
                //icon = ((TextureMap) texturemanager.getTexture(resourcelocation)).getAtlasSprite("missingno");
            }

            float u = ((IIcon) icon).getMinU();
            float U = ((IIcon) icon).getMaxU();
            float v = ((IIcon) icon).getMinV();
            float V = ((IIcon) icon).getMaxV();

            this.bindTexture(TextureMap.locationBlocksTexture);

            GL11.glPushMatrix();
            GL11.glTranslated(0.0625, 3 * 0.0625, 0.0625);
            GL11.glScaled(1 - 0.125, 1, 1 - 0.125);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glRotated(90, 0.5, 0, 0);
            ItemRenderer.renderItemIn2D(tessellator, U, v, u, V, ((IIcon) icon).getIconWidth(), ((IIcon) icon).getIconHeight(), 0.0625F);
            GL11.glPopMatrix();
        }

        this.bindTexture(texture);
        GL11.glPushMatrix();
        GL11.glTranslated(0.5, 0, 0.5);
        GL11.glScaled(1.0D/24.0D, 1.0D/24.0D, 1.0D/24.0D);
        model.renderAll();
        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }
}
