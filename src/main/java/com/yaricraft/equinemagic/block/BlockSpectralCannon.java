package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.tileentity.TileSpectralCannon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Yari on 9/20/2014.
 */
public class BlockSpectralCannon extends BlockHorizontalPillar implements ITileEntityProvider
{
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockSpectralCannon()
    {
        super(Material.iron);
        this.setBlockName(ModNames.SPECTRAL_CANNON);
        this.setBlockTextureName(ModNames.SPECTRAL_CANNON);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.PEGASUS;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileSpectralCannon();
    }

    @Override
    public int onBlockPlaced(World world, int iX, int iY, int iZ, int iSide, float fX, float fY, float fZ, int meta)
    {
        if (iSide < 2) iSide+=2;

        // 5 -X
        // 4 +X
        // 3 -Z
        // 2 +Z
        // 1 -Y
        // 0 +Y

        return iSide;
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
        switch (side)
        {
            case 2:
                if(meta == 3) return icons[1];
                break;
            case 3:
                if(meta == 2) return icons[1];
                break;
            case 4:
                if(meta == 5) return icons[1];
                break;
            case 5:
                if(meta == 4) return icons[1];
                break;
        }
        return icons[0];
    }
}
