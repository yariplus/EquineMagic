
package com.yaricraft.equinemagic.proxy;

import cofh.core.render.IconRegistry;
import com.yaricraft.equinemagic.init.EquineMagicBlock;
import com.yaricraft.equinemagic.client.handler.KeyInputHandler;
import com.yaricraft.equinemagic.client.model.ModelChangeling;
import com.yaricraft.equinemagic.client.renderer.RendererBell;
import com.yaricraft.equinemagic.client.renderer.RendererPedestal;
import com.yaricraft.equinemagic.client.renderer.entity.RenderAura;
import com.yaricraft.equinemagic.client.renderer.entity.RenderChangeling;
import com.yaricraft.equinemagic.client.settings.KeyBindings;
import com.yaricraft.equinemagic.entity.monster.EntityChangeling;
import com.yaricraft.equinemagic.entity.passive.EntityAura;
import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
import com.yaricraft.equinemagic.client.gui.GuiEquineHUD;
import com.yaricraft.equinemagic.handler.PlayerHandler;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.client.renderer.RendererTileSpectralAscensionDevice;
import com.yaricraft.equinemagic.tileentity.TileSpectralCauldron;
import com.yaricraft.equinemagic.client.renderer.RendererTileSolarCauldron;
import com.yaricraft.equinemagic.tileentity.TileSpectralAscender;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerEventHandlers()
    {
        super.registerEventHandlers();

        FMLCommonHandler.instance().bus().register(new PlayerHandler());
    }

    public void registerRenderers()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileSpectralCauldron.class, new RendererTileSolarCauldron());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSpectralAscender.class, new RendererTileSpectralAscensionDevice());

        //FMLCommonHandler.instance().bus().register(new ClientTickHandler());
        MinecraftForge.EVENT_BUS.register(new GuiEquineHUD(Minecraft.getMinecraft()));

        RenderingRegistry.registerBlockHandler(EquineMagicBlock.equine_bell.getRenderType(), new RendererBell());
        RenderingRegistry.registerBlockHandler(EquineMagicBlock.pedestal.getRenderType(), new RendererPedestal());

        RenderingRegistry.registerEntityRenderingHandler(EntityChangeling.class, new RenderChangeling(new ModelChangeling(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityAura.class, new RenderAura());
    }

    @Override
    public ResourceLocation getPlayerSkin()
    {
        return Minecraft.getMinecraft().thePlayer.getLocationSkin();
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

    public static void registerFluidIcons(Fluid fluid, IIconRegister ir)
    {
        IconRegistry.addIcon("fluid", ModData.MODID + ":" + fluid.getUnlocalizedName().substring(fluid.getUnlocalizedName().indexOf(".") + 1) + "_still", ir);
        IconRegistry.addIcon("fluid", ModData.MODID + ":" + fluid.getUnlocalizedName().substring(fluid.getUnlocalizedName().indexOf(".") + 1) + "_flowing", ir);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void initializeIcons(TextureStitchEvent.Post event)
    {
        setFluidIcons(EquineMagicFluid.fluidSpectraSlurry);
    }

    public static void setFluidIcons(Fluid fluid)
    {
        fluid.setIcons(IconRegistry.getIcon("fluid"), IconRegistry.getIcon("fluid", 1));
    }

    @Override
    public void registerKeybindings()
    {
        ClientRegistry.registerKeyBinding(KeyBindings.hud_toggle);

        FMLCommonHandler.instance().bus().register(new KeyInputHandler());
    }
}
