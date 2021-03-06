package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.block.BlockEquineTNT;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.init.EquineMagicItem;
import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.block.*;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * Created by Yari on 9/8/2014.
 */
public class ItemAlicornWand extends EquineMagicItem
{
    public ItemAlicornWand() {
        super();
        this.setUnlocalizedName(ModNames.DUST_ALICORN);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.UNICORN;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World p_77659_2_, EntityPlayer player)
    {
        return itemStack;
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            Block hit = world.getBlock(x, y, z);

            if(hit instanceof BlockBookshelf)
            {
                world.setBlock(x, y, z, Blocks.air);
                world.notifyBlockOfNeighborChange(x, y, z, Blocks.air);

                --stack.stackSize;
                world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.bookResearch)));

                return true;
            }

            if(hit instanceof BlockEquineTNT)
            {
                world.setBlock(x, y, z, Blocks.air);

                if (Math.random() < 0.10D)
                {
                    player.addChatMessage(new ChatComponentText("The dust has been used up."));
                    --stack.stackSize;
                }

                for(int xClearing = -4; xClearing <= 4; xClearing++)
                {
                    for(int zClearing = -4; zClearing <= 4; zClearing++)
                    {
                        for(int yClearing = 0; yClearing <= 2; yClearing++)
                        {
                            Block active = world.getBlock(x + xClearing, y + yClearing, z + zClearing);
                            if(!(active instanceof BlockAir || world.getTileEntity(x + xClearing, y + yClearing, z + zClearing) != null))
                            {
                                world.setBlock(x + xClearing, y + yClearing, z + zClearing, Blocks.air);
                                world.spawnEntityInWorld(new EntityItem(world, x + xClearing, y + yClearing, z + zClearing, new ItemStack(active)));
                            }
                        }
                    }
                }

                for(int xClearing = -4; xClearing <= 4; xClearing++)
                {
                    for(int zClearing = -4; zClearing <= 4; zClearing++)
                    {
                        for(int yClearing = 0; yClearing <= 4; yClearing++)
                        {
                            Block active = world.getBlock(x + xClearing, y + yClearing, z + zClearing);
                            if(!(active instanceof BlockAir || world.getTileEntity(x + xClearing, y + yClearing, z + zClearing) != null))
                            {
                                world.setBlock(x + xClearing, y + yClearing, z + zClearing, Blocks.air);
                                world.spawnEntityInWorld(new EntityItem(world, x + xClearing, y + yClearing, z + zClearing, new ItemStack(active)));
                            }
                        }
                    }
                }

                for(int xClearing = -3; xClearing <= 3; xClearing++)
                {
                    for(int zClearing = -3; zClearing <= 3; zClearing++)
                    {
                        Block active = world.getBlock(x + xClearing, y + 5, z + zClearing);
                        if(!(active instanceof BlockAir || world.getTileEntity(x + xClearing, y + 5, z + zClearing) != null))
                        {
                            world.setBlock(x + xClearing, y + 5, z + zClearing, Blocks.air);
                            world.spawnEntityInWorld(new EntityItem(world, x + xClearing, y + 5, z + zClearing, new ItemStack(active)));
                        }
                    }
                }

                for(int xClearing = -2; xClearing <= 2; xClearing++)
                {
                    for(int zClearing = -2; zClearing <= 2; zClearing++)
                    {
                        Block active = world.getBlock(x + xClearing, y + 6, z + zClearing);
                        if(!(active instanceof BlockAir || world.getTileEntity(x + xClearing, y + 6, z + zClearing) != null))
                        {
                            world.setBlock(x + xClearing, y + 6, z + zClearing, Blocks.air);
                            world.spawnEntityInWorld(new EntityItem(world, x + xClearing, y + 6, z + zClearing, new ItemStack(active)));
                        }
                    }
                }

                for(int xClearing = -1; xClearing <= 1; xClearing++)
                {
                    for(int zClearing = -1; zClearing <= 1; zClearing++)
                    {
                        Block active = world.getBlock(x + xClearing, y + 7, z + zClearing);
                        if(!(active instanceof BlockAir || world.getTileEntity(x + xClearing, y + 7, z + zClearing) != null))
                        {
                            world.setBlock(x + xClearing, y + 7, z + zClearing, Blocks.air);
                            world.spawnEntityInWorld(new EntityItem(world, x + xClearing, y + 7, z + zClearing, new ItemStack(active)));
                        }
                    }
                }

                for(int xClearing = -4; xClearing <= 4; xClearing++)
                {
                    for(int zClearing = -4; zClearing <= 4; zClearing++)
                    {
                        for(int yClearing = -1; yClearing >= -3; yClearing--)
                        {
                            Block active = world.getBlock(x + xClearing, y + yClearing, z + zClearing);
                            if(!(active instanceof BlockDirt || active instanceof BlockGrass ||
                                 active instanceof BlockAir || y + yClearing < 2 ||
                                 world.getTileEntity(x + xClearing, y + yClearing, z + zClearing) != null ||
                                 y + yClearing < 5))
                            {
                                if(yClearing == -1)
                                {
                                    if(Math.random() < 0.1)
                                    {
                                        world.setBlock(x + xClearing, y + yClearing, z + zClearing, Blocks.water);
                                    }else
                                    {
                                        world.setBlock(x + xClearing, y + yClearing, z + zClearing, Blocks.grass);
                                        if(Math.random() < 0.1)
                                        {
                                            switch ((int)(Math.random() * 3))
                                            {
                                                case 0:
                                                    world.setBlock(x + xClearing, y, z + zClearing, Blocks.tallgrass);
                                                case 1:
                                                    world.setBlock(x + xClearing, y, z + zClearing, Blocks.red_flower);
                                                case 2:
                                                    world.setBlock(x + xClearing, y, z + zClearing, Blocks.yellow_flower);
                                            }
                                        }
                                    }
                                }else
                                {
                                    world.setBlock(x + xClearing, y + yClearing, z + zClearing, Blocks.dirt);
                                }
                                if(!(active.getMaterial() instanceof MaterialLiquid))
                                {
                                    world.spawnEntityInWorld(new EntityItem(world, x + xClearing, y, z + zClearing, new ItemStack(active)));
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
}