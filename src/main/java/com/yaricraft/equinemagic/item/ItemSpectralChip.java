package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.enums.ESpectralChip;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.tileentity.TileSpectralManipulator;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yari on 10/22/2014.
 */
public class ItemSpectralChip extends EquineMagicItem implements IItemSpectralChip
{
    public ItemSpectralChip()
    {
        super();
        this.setHasSubtypes(true);
        this.setUnlocalizedName(ModNames.SPECTRAL_CHIP);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.PEGASUS;
    }

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.icons = new IIcon[ESpectralChip.values().length];
        for (int i = 0; i < icons.length; i++) this.icons[i] = iconRegister.registerIcon(getAssetBase() + ModData.ASSET_SPACER + ESpectralChip.values()[i].toString());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) { return this.icons[MathHelper.clamp_int(meta, 0, ESpectralChip.values().length - 1)]; }

    public String getUnlocalizedName(ItemStack itemStack)
    {
        int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, ESpectralChip.values().length - 1);
        return getUnlocalizedName() + ModData.ASSET_SPACER + ESpectralChip.values()[i].toString();
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
    public void getSubItems(Item item, CreativeTabs tabs, List list) { for (int i = 0; i < ESpectralChip.values().length; i++) list.add(new ItemStack(item, 1, i)); }

    //* IItemSpectralChip *//
    // What is a processed block replaced with?
    @Override
    public Block replaceBlock(int meta, Block block)
    {
        switch (ESpectralChip.values()[meta])
        {
            case AREA:
                break;
            case DOME:
                break;
            case FILL:
                return Blocks.stone;
            case GRIND:
                break;
            case HELIX:
                break;
            case PLANE:
                break;
            case SILK:
                break;
            case TARGET:
                break;
            case TILES:
                break;
            case VORTEX:
                break;
        }

        return block;
    }

    // What do you get when the block is mined?
    @Override
    public ItemStack alterDrop(int chipMeta, Block minedBlock, int minedMeta, Item dropItem, int dropQuantity, int dropMeta)
    {
        switch (ESpectralChip.values()[chipMeta])
        {
            case AREA:
                break;
            case DOME:
                break;
            case FILL:
                break;
            case GRIND:
                break;
            case HELIX:
                break;
            case PLANE:
                break;
            case SILK:
                return new ItemStack(minedBlock);
            case TARGET:
                break;
            case TILES:
                break;
            case VORTEX:
                break;
        }

        return null;
    }

    // Store an itemstack.
    // returns true if successful.
    @Override
    public boolean storeStack(int meta, ItemStack itemstack, World world, int x, int y, int z)
    {
        switch (ESpectralChip.values()[meta])
        {
            case AREA:
                break;
            case DOME:
                break;
            case FILL:
                break;
            case GRIND:
                break;
            case HELIX:
                break;
            case PLANE:
                break;
            case SILK:
                break;
            case TARGET:
                break;
            case TILES:
                break;
            case VORTEX:
                break;
        }
        return false;
    }

    @Override
    public void install(int meta, TileSpectralManipulator manipulator)
    {
        switch (ESpectralChip.values()[meta])
        {
            case AREA:
                switch (manipulator.type)
                {
                    case MINER:
                        break;
                    case CANNON:
                        break;
                    case ASCENDER:
                        break;
                    case BRIDGER:
                        break;
                    case BUILDER:
                        break;
                }
                break;
            case DOME:
                switch (manipulator.type)
                {
                    case MINER:
                        break;
                    case CANNON:
                        break;
                    case ASCENDER:
                        break;
                    case BRIDGER:
                        break;
                    case BUILDER:
                        break;
                }
                break;
            case FILL:
                switch (manipulator.type)
                {
                    case MINER:
                        break;
                    case CANNON:
                        break;
                    case ASCENDER:
                        break;
                    case BRIDGER:
                        break;
                    case BUILDER:
                        break;
                }
                break;
            case GRIND:
                switch (manipulator.type)
                {
                    case MINER:
                        break;
                    case CANNON:
                        break;
                    case ASCENDER:
                        break;
                    case BRIDGER:
                        break;
                    case BUILDER:
                        break;
                }
                break;
            case HELIX:
                switch (manipulator.type)
                {
                    case MINER:
                        break;
                    case CANNON:
                        break;
                    case ASCENDER:
                        break;
                    case BRIDGER:
                        break;
                    case BUILDER:
                        break;
                }
                break;
            case PLANE:
                switch (manipulator.type)
                {
                    case MINER:
                        break;
                    case CANNON:
                        manipulator.fillers.add(Blocks.double_stone_slab);
                        manipulator.fillers.add(Blocks.stone);
                        manipulator.hOffset = -1;
                        manipulator.wOffset--;
                        int filler = 0;
                        int sizeRows = manipulator.patterns.get(0).size();
                        int sizeColumns = manipulator.patterns.get(0).get(0).size();

                        ArrayList<ArrayList<Integer>> newRows = new ArrayList<ArrayList<Integer>>();
                        manipulator.patterns.add(newRows);
                        for (int r = 0; r < sizeRows; r++)
                        {
                            ArrayList<Integer> newColumns = new ArrayList<Integer>();
                            newRows.add(newColumns);
                            for (int i = 0; i < sizeColumns; i++) newColumns.add(0);
                        }

                        for (ArrayList<ArrayList<Integer>> rows : manipulator.patterns)
                        {
                            rows.get(0).add(0);
                            rows.get(0).add(0);
                            for (int column = 0; column < sizeColumns + 2; column++)
                            {
                                rows.get(0).set(column,manipulator.fillers.size() - 1 + filler);
                                filler = filler == 0 ? -1 : 0;
                            }
                            for (int row = 1; row < sizeRows; row++)
                            {
                                rows.get(row).add(0,rows.get(row).get(0));
                                rows.get(row).add(rows.get(row).get(rows.get(row).size()-1));
                            }
                        }
                        break;
                    case ASCENDER:
                        break;
                    case BRIDGER:
                        break;
                    case BUILDER:
                        break;
                }
                break;
            case SILK:
                switch (manipulator.type)
                {
                    case MINER:
                        break;
                    case CANNON:
                        break;
                    case ASCENDER:
                        break;
                    case BRIDGER:
                        break;
                    case BUILDER:
                        break;
                }
                break;
            case TARGET:
                switch (manipulator.type)
                {
                    case MINER:
                        break;
                    case CANNON:
                        break;
                    case ASCENDER:
                        break;
                    case BRIDGER:
                        break;
                    case BUILDER:
                        break;
                }
                break;
            case TILES:
                switch (manipulator.type)
                {
                    case MINER:
                        break;
                    case CANNON:
                        break;
                    case ASCENDER:
                        break;
                    case BRIDGER:
                        break;
                    case BUILDER:
                        break;
                }
                break;
            case VORTEX:
                switch (manipulator.type)
                {
                    case MINER:
                        break;
                    case CANNON:
                        break;
                    case ASCENDER:
                        break;
                    case BRIDGER:
                        break;
                    case BUILDER:
                        break;
                }
                break;
        }
    }
}
