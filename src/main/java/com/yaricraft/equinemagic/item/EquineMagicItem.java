
package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.EquineFoci;
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
    public EquineFoci foci = EquineFoci.PONY;

    public static final EquineMagicItem warmFluxingRod = new ItemFluxingRod();
    public static final EquineMagicItem warmEgg = new ItemWarmEgg();
    public static final EquineMagicItem warmFeather = new ItemWarmFeather();
    public static final EquineMagicItem warmMycelium = new ItemWarmMycelium();
    public static final EquineMagicItem warmScale = new ItemWarmScale();
    public static final EquineMagicItem warmTear = new ItemWarmTear();
    public static final EquineMagicItem warmWing = new ItemWarmWing();

    public static final EquineMagicItem dustAlicorn = new ItemDustAlicorn();
    public static final EquineMagicItem dustChroma  = new ItemDustChroma();
    public static final EquineMagicItem dustPegagin = new ItemDustPegagin();
    public static final EquineMagicItem dustSpectra = new ItemDustSpectra();
    public static final EquineMagicItem dustSilky = new ItemDustSilky();
    public static final EquineMagicItem dustSilkyGunpowder = new ItemDustSilkyGunpowder();

    public static final EquineMagicItem bookResearch = new ItemBookResearchNotes();

    public static final Item spectral_cauldron = (new ItemReed(EquineMagicBlock.spectral_cauldron)).setUnlocalizedName(EquineMagicBlock.spectral_cauldron.getUnwrappedUnlocalizedName()).setTextureName("cauldron").setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    public static final Item spectral_ascender = (new ItemReed(EquineMagicBlock.spectral_ascender)).setUnlocalizedName(EquineMagicBlock.spectral_ascender.getUnwrappedUnlocalizedName()).setTextureName("magma_cream").setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);

    public static final EquineMagicItem itemSpectralChip = new ItemSpectralChip();

    public static final Item crystalPrimatic = new ItemCrystalPrimatic();

    public static void init()
    {
        GameRegistry.registerItem(warmFluxingRod, warmFluxingRod.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(warmEgg, warmEgg.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(warmFeather, warmFeather.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(warmMycelium, warmMycelium.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(warmScale, warmScale.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(warmTear, warmTear.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(warmWing, warmWing.getUnlocalizedName().substring(6 + ModData.MODID.length()));

        GameRegistry.registerItem(dustAlicorn, dustAlicorn.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(dustChroma, dustChroma.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(dustPegagin, dustPegagin.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(dustSpectra, dustSpectra.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(dustSilky, dustSilky.getUnlocalizedName().substring(6 + ModData.MODID.length()));
        GameRegistry.registerItem(dustSilkyGunpowder, dustSilkyGunpowder.getUnlocalizedName().substring(6 + ModData.MODID.length()));

        GameRegistry.registerItem(bookResearch, bookResearch.getUnlocalizedName().substring(6 + ModData.MODID.length()));

        GameRegistry.registerItem(spectral_ascender, EquineMagicBlock.spectral_ascender.getRegisteryNameAsItem());
        GameRegistry.registerItem(spectral_cauldron, EquineMagicBlock.spectral_cauldron.getRegisteryNameAsItem());

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
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
    {
        list.add(1, "Focus: " + foci.toString());
    }

	@Override
    // Returns "item.MODID:ITEMNAME.name"
	public String getUnlocalizedName()
	{
		return String.format("item.%s%s", ModData.MODID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
    // Returns "item.MODID:ITEMNAME.name"
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return String.format("item.%s%s", ModData.MODID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

    // Returns "MODID:ITEMNAME.name"
	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

    // Returns "MODID:ITEMNAME.name"
    protected String getUnwrappedUnlocalizedName()
    {
        return this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1);
    }

    // Returns ITEM.name
    protected String getRegistryName()
    {
        return this.getUnlocalizedName().substring(6 + ModData.MODID.length());
    }
}
