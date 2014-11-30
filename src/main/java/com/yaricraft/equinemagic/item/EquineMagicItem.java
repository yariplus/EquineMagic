
package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.block.EquineMagicBlock;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;

import com.yaricraft.equinemagic.reference.ModData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemStack;

import java.util.List;

public class EquineMagicItem extends Item
{
    public EEquineFoci foci = EEquineFoci.PONY;

    public static final EquineMagicItem equine_dust = new ItemEquineDust();
    public static final EquineMagicItem equine_gem = new ItemEquineGem();

    public static final EquineMagicItem warmFluxingRod = new ItemFluxingRod();
    public static final EquineMagicItem warmEgg = new ItemWarmEgg();
    public static final EquineMagicItem warmFeather = new ItemWarmFeather();
    public static final EquineMagicItem warmMycelium = new ItemWarmMycelium();
    public static final EquineMagicItem warmScale = new ItemWarmScale();
    public static final EquineMagicItem warmTear = new ItemWarmTear();
    public static final EquineMagicItem warmWing = new ItemWarmWing();

    public static final EquineMagicItem dustAlicorn = new ItemDustAlicorn();
    public static final EquineMagicItem dustSilky = new ItemDustSilky();
    public static final EquineMagicItem dustSilkyGunpowder = new ItemDustSilkyGunpowder();

    public static final EquineMagicItem bookResearch = new ItemBookResearchNotes();

    public static final EquineMagicItem itemSpectralChip = new ItemSpectralChip();

    public static final Item crystalPrimatic = new ItemCrystalPrimatic();

    public static void init()
    {
        GameRegistry.registerItem(equine_dust, equine_dust.getRegistryName());
        GameRegistry.registerItem(equine_gem, equine_gem.getRegistryName());

        GameRegistry.registerItem(warmFluxingRod, warmFluxingRod.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(warmEgg, warmEgg.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(warmFeather, warmFeather.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(warmMycelium, warmMycelium.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(warmScale, warmScale.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(warmTear, warmTear.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(warmWing, warmWing.getUnlocalizedName().substring(6 + ModData.MODID.length()));

        GameRegistry.registerItem(dustAlicorn, dustAlicorn.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(dustSilky, dustSilky.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(dustSilkyGunpowder, dustSilkyGunpowder.getUnlocalizedName().substring(6 + ModData.MODID.length()));

        GameRegistry.registerItem(bookResearch, bookResearch.getUnlocalizedName().substring(6 + ModData.MODID.length()));

        GameRegistry.registerItem(itemSpectralChip, itemSpectralChip.getRegistryName());

        GameRegistry.registerItem(crystalPrimatic, crystalPrimatic.getUnlocalizedName().substring(6 + ModData.MODID.length()));
    }

	public EquineMagicItem()
	{
		super();
		this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
	}

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(getAssetBase());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
    {
        list.add(1, "Focus: " + foci.toString());
    }

    // Returns item.MODID:ITEM
    @Override
    public String getUnlocalizedName() { return "item." + this.getAssetBase(); }
    @Override
    public String getUnlocalizedName(ItemStack itemStack) { return "item." + this.getAssetBase(); }

    // Returns MODID:ITEM
    public String getAssetBase() { return ModData.MODID + ":" + this.getRegistryName(); }

    // Returns ITEM
    public String getRegistryName()
    {
        return this.getUnwrappedName(super.getUnlocalizedName());
    }

    // Takes off "item."
    protected String getUnwrappedName(String unlocalizedName) { return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1); }
}
