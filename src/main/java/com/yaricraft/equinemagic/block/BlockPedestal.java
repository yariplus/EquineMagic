package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.tileentity.TilePedestal;
import com.yaricraft.equinemagic.tileentity.TileSpectralCauldron;
import cpw.mods.fml.client.registry.RenderingRegistry;
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
public class BlockPedestal extends EquineMagicBlock implements ITileEntityProvider
{
    public BlockPedestal()
    {
        super(Material.iron);
        this.setBlockTextureName(ModNames.EQUINE_DECOR + ModData.ASSETSUF_ICON[2] + ModData.ASSET_SPACER + ModData.ASSETSUF_META[0]);
        this.setBlockName(ModNames.Blocks.PEDESTAL);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);

        renderId = RenderingRegistry.getNextAvailableRenderId();
    }

    public static int renderId;

    @Override
    public int getRenderType(){
        return renderId;
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);

        this.setBlockBoundsForItemRender();
    }

    @Override
    public EEquineFoci getFoci(int meta)
    {
        switch (1)
        {
            default:
                return EEquineFoci.ELEMENTAL;
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
    {
      //if (!world.isRemote && itemStack.getTagCompound() != null && itemStack.getTagCompound().hasKey("FluidStack") && itemStack.getTagCompound().getCompoundTag("FluidStack").hasKey("Amount"))
      //{
      //    ((TileSpectralCauldron)world.getTileEntity(x, y, z)).tank.setFluid(new FluidStack(EquineMagicFluid.fluidSpectraSlurry, itemStack.getTagCompound().getCompoundTag("FluidStack").getInteger("Amount")));
      //}
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity != null)
        {
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
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TilePedestal();
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
