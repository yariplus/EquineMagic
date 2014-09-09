
package com.yaricraft.equinemagic.items;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;

import com.yaricraft.equinemagic.reference.ModData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;

public class EquineMagicItem extends Item
{
    public static final EquineMagicItem warmFluxingRod = new ItemFluxingRod();
    public static final EquineMagicItem warmEgg = new ItemWarmEgg();
    public static final EquineMagicItem warmFeather = new ItemWarmFeather();
    public static final EquineMagicItem warmMycelium = new ItemWarmMycelium();
    public static final EquineMagicItem warmScale = new ItemWarmScale();
    public static final EquineMagicItem warmTear = new ItemWarmTear();
    public static final EquineMagicItem warmWing = new ItemWarmWing();

    public static final EquineMagicItem dustChroma = new ItemDustChroma();
    public static final EquineMagicItem dustAlicorn = new ItemDustAlicorn();
    public static final EquineMagicItem dustSpectra = new ItemDustSpectra();

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
        GameRegistry.registerItem(dustSpectra, dustSpectra.getUnlocalizedName().substring(6 + ModData.MODID.length()));
    }

	public EquineMagicItem()
	{
		super();
		this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
	}

	@Override
	public String getUnlocalizedName()
	{
		return String.format("item.%s%s", ModData.MODID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
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

	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
