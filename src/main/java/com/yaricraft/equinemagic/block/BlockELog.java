package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Yari on 11/5/2014.
 */
public class BlockELog extends BlockERotatedPillar
{
    @SideOnly(Side.CLIENT)
    protected IIcon[] iconsSide;
    @SideOnly(Side.CLIENT)
    protected IIcon[] iconsTop;

    public BlockELog()
    {
        super(Material.wood);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.setHardness(2.0F);
        this.setStepSound(soundTypeWood);

        setBlockName("log");
        setBlockTextureName("log");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        iconsTop = new IIcon[16];
        iconsSide = new IIcon[16];

        iconsTop[0] = iconRegister.registerIcon(this.getUnwrappedUnlocalizedName() + ModData.ASSETSUF_ICON_TOP);
        iconsSide[0] = iconRegister.registerIcon(this.getUnwrappedUnlocalizedName() + ModData.ASSETSUF_ICON_SIDE);
    }

    public static int func_150165_c(int p_150165_0_)
    {
        return p_150165_0_ & 3;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 1;
    }

    public Item getItemDropped(int p_149650_1_, Random random, int p_149650_3_)
    {
        return Item.getItemFromBlock(this);
    }

    public void breakBlock(World world, int x, int y, int z, Block block1, int meta)
    {
        byte b0 = 4;
        int i1 = b0 + 1;

        if (world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1))
        {
            for (int j1 = -b0; j1 <= b0; ++j1)
            {
                for (int k1 = -b0; k1 <= b0; ++k1)
                {
                    for (int l1 = -b0; l1 <= b0; ++l1)
                    {
                        Block block = world.getBlock(x + j1, y + k1, z + l1);
                        if (block.isLeaves(world, x + j1, y + k1, z + l1))
                        {
                            block.beginLeavesDecay(world, x + j1, y + k1, z + l1);
                        }
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int meta)
    {
        return this.iconsSide[meta % this.iconsSide.length];
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int meta)
    {
        return this.iconsTop[meta % this.iconsTop.length];
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean isWood(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
}
