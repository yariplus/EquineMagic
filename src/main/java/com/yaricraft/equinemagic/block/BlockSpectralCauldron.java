package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
import com.yaricraft.equinemagic.init.EquineMagicBlock;
import com.yaricraft.equinemagic.tileentity.TileSpectralCauldron;
import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

/**
 * Created by Yari on 9/15/2014.
 */
public class BlockSpectralCauldron extends EquineMagicBlock implements ITileEntityProvider
{
    public BlockSpectralCauldron()
    {
        super(Material.iron);
        this.setBlockTextureName(ModNames.SPECTRAL_CAULDRON);
        this.setBlockName(ModNames.SPECTRAL_CAULDRON);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
    {
        float f = 0.0625F;
        float f2 = f*6;

        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f*2, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);

        this.setBlockBounds(0.0F, 0.0F, 0.0F, f, f2, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f2, f);
        super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);

        this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, f2, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
        this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, f2, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);

        this.setBlockBoundsForItemRender();

        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F * 5, 1.0F);
    }

    @Override
    public EEquineFoci getFoci(int meta)
    {
        switch (1)
        {
            default:
                return EEquineFoci.PEGASUS;
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
    {
        if (!world.isRemote && itemStack.getTagCompound() != null && itemStack.getTagCompound().hasKey("FluidStack") && itemStack.getTagCompound().getCompoundTag("FluidStack").hasKey("Amount"))
        {
            ((TileSpectralCauldron)world.getTileEntity(x, y, z)).tank.setFluid(new FluidStack(EquineMagicFluid.fluidSpectraSlurry, itemStack.getTagCompound().getCompoundTag("FluidStack").getInteger("Amount")));
        }
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity != null)
        {
            if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops"))
            {
                TileSpectralCauldron cauldron = (TileSpectralCauldron) tileEntity;
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
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileSpectralCauldron();
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
}
