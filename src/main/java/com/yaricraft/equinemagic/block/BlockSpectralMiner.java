package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.tileentity.TileSpectralMiner;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by Yari on 9/20/2014.
 */
public class BlockSpectralMiner extends EquineMagicBlock implements ITileEntityProvider
{
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockSpectralMiner()
    {
        super(Material.iron);
        this.setBlockName(ModNames.SPECTRAL_MINER);
        this.setBlockTextureName(ModNames.SPECTRAL_MINER);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.PEGASUS;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
    {
        if (!world.isRemote && itemStack.getTagCompound() != null && itemStack.getTagCompound().hasKey("FluidStack") && itemStack.getTagCompound().getCompoundTag("FluidStack").hasKey("Amount"))
        {
            ((TileSpectralMiner)world.getTileEntity(x, y, z)).tank.setFluid(new FluidStack(EquineMagicFluid.fluidSpectraSlurry, itemStack.getTagCompound().getCompoundTag("FluidStack").getInteger("Amount")));
        }
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity != null)
        {
            if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops"))
            {
                TileSpectralMiner cauldron = (TileSpectralMiner) tileEntity;
                //if (captureDrops.get())
                //{
                //    capturedDrops.get().add(itemStack);
                //    return;
                //}
                float f = 0.7F;
                double d0 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                double d1 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                double d2 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;

                ItemStack itemStack = new ItemStack(Item.getItemFromBlock(this));
                NBTTagCompound rootTag = new NBTTagCompound();
                NBTTagCompound fluidStackTag = new NBTTagCompound();
                cauldron.tank.writeToNBT(fluidStackTag);
                rootTag.setTag("FluidStack", fluidStackTag);
                itemStack.setTagCompound(rootTag);

                EntityItem entityitem = new EntityItem(world, (double) x + d0, (double) y + d1, (double) z + d2, itemStack);
                entityitem.delayBeforeCanPickup = 10;
                world.spawnEntityInWorld(entityitem);
            }

            world.removeTileEntity(x, y, z);
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
    {
        // NOOP
    }

    @Override
    protected void dropBlockAsItem(World world, int x, int y, int z, ItemStack itemStack)
    {
        // NOOP
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[2];
        icons[0] = iconRegister.registerIcon(this.getUnwrappedUnlocalizedName());
        icons[1] = iconRegister.registerIcon("EquineMagic:spectral_active");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if(side == 0) return icons[1];
        return icons[0];
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileSpectralMiner();
    }
}
