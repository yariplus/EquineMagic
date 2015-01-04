package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.enums.EEquineDust;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.enums.EEquineGem;
import com.yaricraft.equinemagic.enums.EEquineOre;
import com.yaricraft.equinemagic.init.EquineMagicBlock;
import com.yaricraft.equinemagic.init.EquineMagicItem;
import com.yaricraft.equinemagic.reference.MCData;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.reference.ModNames;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * Created by Yari on 11/3/2014.
 */
public class BlockEquineOre extends EquineMagicBlock
{
    public BlockEquineOre()
    {
        super(Material.iron);
        this.setBlockName(ModNames.EQUINE_ORE);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        for (int i = 0; i < EEquineOre.values().length; i++ ) this.subNames[i] = EEquineOre.values()[i].toString();
        setHardness(3.0F);
        for (EEquineOre ore: EEquineOre.values())
        {
            switch (ore)
            {
                case OPAL:
                    setHarvestLevel("pickaxe", 1, ore.ordinal());
                    break;
                case ZIRCON:
                    setHarvestLevel("pickaxe", 2, ore.ordinal());
                    break;
                case DOLOMITE:
                    setHarvestLevel("pickaxe", 1, ore.ordinal());
                    break;
                case SPECTRA:
                    setHarvestLevel("pickaxe", 2, ore.ordinal());
                    break;
            }
        }
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }

    public int damageDropped(int meta)
    {
        Random random = new Random();
        EEquineOre ore = EEquineOre.values()[meta];
        switch (ore)
        {
            case OPAL:
                if (random.nextInt(3)==2) return EEquineGem.BLACK_OPAL.ordinal();
                return EEquineGem.OPAL.ordinal();
            case ZIRCON:
                return EEquineGem.ZIRCON.ordinal();
            case DOLOMITE:
                return EEquineGem.DOLOMITE.ordinal();
            case SPECTRA:
                return EEquineDust.DIRTY_SPECTRA.ordinal();
        }
        return 0;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        EEquineOre ore = EEquineOre.values()[meta];
        switch (ore)
        {
            case OPAL:
                return EquineMagicItem.equine_gem;
            case ZIRCON:
                return EquineMagicItem.equine_gem;
            case DOLOMITE:
                return EquineMagicItem.equine_gem;
            case SPECTRA:
                return EquineMagicItem.equine_dust;
        }
        return Items.cookie;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        switch (EEquineOre.values()[meta])
        {
            case OPAL:
            case ZIRCON:
            case DOLOMITE:
                return 1 + random.nextInt(2);
            case SPECTRA:
                return 2 + random.nextInt(3);
        }
        return 1;
    }

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[MCData.THREE_SIDED * MCData.BLOCK_META_MAX];

        for (int i = 0; i < icons.length / MCData.THREE_SIDED; i++)
        {
            for(int j = 0; j < MCData.THREE_SIDED; j++)
            {
                icons[i * MCData.THREE_SIDED + j] = iconRegister.registerIcon(this.getUnwrappedUnlocalizedName() + ModData.ASSET_SPACER + subNames[i]);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if(side>2) side = 2;
        return icons[meta * MCData.THREE_SIDED + side];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < EEquineOre.values().length; i++)
        {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
}
