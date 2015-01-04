package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.init.EquineMagicBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Yari on 11/5/2014.
 */
public abstract class BlockERotatedPillar extends EquineMagicBlock
{
    @SideOnly(Side.CLIENT)
    protected IIcon field_150164_N;

    protected BlockERotatedPillar(Material p_i45425_1_)
    {
        super(p_i45425_1_);
    }

    public int getRenderType()
    {
        return 31;
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
    {
        int j1 = p_149660_9_ & 3;
        byte b0 = 0;

        switch (side)
        {
            case 0:
            case 1:
                b0 = 0;
                break;
            case 2:
            case 3:
                b0 = 8;
                break;
            case 4:
            case 5:
                b0 = 4;
        }

        return j1 | b0;
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        int k = meta & 12;
        int l = meta & 3;
        return k == 0 && (side == 1 || side == 0) ? this.getTopIcon(l) : (k == 4 && (side == 5 || side == 4) ? this.getTopIcon(l) : (k == 8 && (side == 2 || side == 3) ? this.getTopIcon(l) : this.getSideIcon(l)));
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int p_149692_1_)
    {
        return p_149692_1_ & 3;
    }

    @SideOnly(Side.CLIENT)
    protected abstract IIcon getSideIcon(int p_150163_1_);

    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int p_150161_1_)
    {
        return this.field_150164_N;
    }

    public int func_150162_k(int p_150162_1_)
    {
        return p_150162_1_ & 3;
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int p_149644_1_)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, this.func_150162_k(p_149644_1_));
    }
}
