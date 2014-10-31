
package com.yaricraft.equinemagic.proxy;

import cofh.core.render.IconRegistry;
import cofh.lib.util.helpers.StringHelper;
import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
import com.yaricraft.equinemagic.handler.ClientTickHandler;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.renderer.RendererTileSpectralAscensionDevice;
import com.yaricraft.equinemagic.tileentity.TileSpectralCauldron;
import com.yaricraft.equinemagic.renderer.RendererTileSolarCauldron;
import com.yaricraft.equinemagic.tileentity.TileSpectralAscender;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.Fluid;

public class ClientProxy extends CommonProxy
{
    public void registerRenderers()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileSpectralCauldron.class, new RendererTileSolarCauldron());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSpectralAscender.class, new RendererTileSpectralAscensionDevice());

        FMLCommonHandler.instance().bus().register(new ClientTickHandler());
    }

    // cpw
    @Override
    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    // cofh
    @Override
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void registerIcons(TextureStitchEvent.Pre event)
    {
        if (event.map.getTextureType() == 0)
        {
            registerFluidIcons(EquineMagicFluid.fluidSpectraSlurry, event.map);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void initializeIcons(TextureStitchEvent.Post event) {
        setFluidIcons(EquineMagicFluid.fluidSpectraSlurry);
    }

    public static void registerFluidIcons(Fluid fluid, IIconRegister ir)
    {
        IconRegistry.addIcon("fluid", ModData.MODID + ":" + fluid.getUnlocalizedName().substring(fluid.getUnlocalizedName().indexOf(".") + 1) + "_still", ir);
        IconRegistry.addIcon("fluid", ModData.MODID + ":" + fluid.getUnlocalizedName().substring(fluid.getUnlocalizedName().indexOf(".") + 1) + "_flowing", ir);
    }

    public static void setFluidIcons(Fluid fluid)
    {
        String name = StringHelper.titleCase(fluid.getName());
        fluid.setIcons(IconRegistry.getIcon("fluid"), IconRegistry.getIcon("fluid", 1));
    }
}
