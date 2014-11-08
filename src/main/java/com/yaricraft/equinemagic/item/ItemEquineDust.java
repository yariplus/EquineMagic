package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.enums.EEquineDust;
import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.enums.EEquineOre;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.reference.ModNames;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Yari on 11/4/2014.
 */
public class ItemEquineDust extends EquineMagicItem
{
    public ItemEquineDust()
    {
        super();
        this.setHasSubtypes(true);
        this.setUnlocalizedName(ModNames.EQUINE_DUST);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.ELEMENTAL;
    }

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.icons = new IIcon[EEquineDust.values().length];
        for (int i = 0; i < icons.length; i++) this.icons[i] = iconRegister.registerIcon(getAssetBase() + ModData.ASSET_SPACER + EEquineDust.values()[i].toString());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) { return this.icons[MathHelper.clamp_int(meta, 0, EEquineDust.values().length - 1)]; }

    public String getUnlocalizedName(ItemStack itemStack)
    {
        int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, EEquineDust.values().length - 1);
        return getUnlocalizedName() + ModData.ASSET_SPACER + EEquineDust.values()[i].toString();
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list) { for (int i = 0; i < EEquineDust.values().length; ++i) list.add(new ItemStack(item, 1, i)); }
}
