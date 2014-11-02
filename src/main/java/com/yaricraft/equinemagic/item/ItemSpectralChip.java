package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.EquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModNames;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Yari on 10/22/2014.
 */
public class ItemSpectralChip extends EquineMagicItem implements IItemSpectralChip
{
    public static final String[] subTypes = new String[] {"area", "dome", "fill", "grind", "helix", "plane", "silk", "target", "tiles", "vortex"};

    public ItemSpectralChip()
    {
        super();
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setUnlocalizedName(ModNames.SPECTRAL_CHIP);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EquineFoci.PEGASUS;
    }

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.icons = new IIcon[subTypes.length];

        for (int i = 0; i < subTypes.length; ++i)
        {
            this.icons[i] = iconRegister.registerIcon(this.getUnwrappedUnlocalizedName() + "_" + subTypes[i]);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        int j = MathHelper.clamp_int(meta, 0, subTypes.length - 1);
        return this.icons[j];
    }

    public String getUnlocalizedName(ItemStack itemStack)
    {
        int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, subTypes.length - 1);
        return super.getUnlocalizedName() + "_" + subTypes[i];
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
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < subTypes.length; ++i)
        {
           list.add(new ItemStack(item, 1, i));
        }
    }


    //* IItemSpectralChip *//
    // What is a processed block replaced with?
    @Override
    public Block ReplaceBlock(int meta, Block block)
    {
        switch (meta)
        {
            // "Area", "Dome", "Fill", "Grind", "Helix", "Plane", "Silk", "Target", "Tiles", "Vortex"
            case 2:
                return Blocks.stone;
        }
        return block;
    }

    // What do you get when the block is mined?
    @Override
    public ItemStack MineBlock(int meta, Block block)
    {
        switch (meta)
        {
            // "Area", "Dome", "Fill", "Grind", "Helix", "Plane", "Silk", "Target", "Tiles", "Vortex"

        }
        return null;
    }

    // Store an itemstack.
    // returns true if successful.
    @Override
    public boolean StoreStack(int meta, ItemStack itemstack, World world, int x, int y, int z)
    {
        return false;
    }
}
