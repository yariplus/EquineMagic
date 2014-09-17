package com.yaricraft.equinemagic.items;

import com.yaricraft.equinemagic.blocks.BlockSilkyTNT;
import com.yaricraft.equinemagic.blocks.EquineMagicBlock;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.block.*;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Yari on 9/8/2014.
 */
public class ItemDustAlicorn extends EquineMagicItem
{
    public ItemDustAlicorn() {
        super();
        this.setUnlocalizedName(ModNames.DUST_ALICORN);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        // TODO: Clean this shit up
        // TODO: Add a test for the amount of Alicorn dust the player is holding and use that to adjust the blast range.

        if(!world.isRemote)
        {
            Block hit = world.getBlock(x, y, z);
            if(hit instanceof BlockBookshelf) {
                world.setBlock(x, y, z, Blocks.air);
                world.notifyBlockOfNeighborChange(x, y, z, Blocks.air);

                if (Math.random() < 0.20D)
                {
                    player.addChatMessage(new ChatComponentText("I found some useful Equine history here, I'll start keeping research notes in a book!"));
                    --stack.stackSize;
                    world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(EquineMagicItem.bookResearch)));
                } else {
                    player.addChatMessage(new ChatComponentText("These books didn't contain any useful information."));
                    if (Math.random() < 0.20D) {
                        player.addChatMessage(new ChatComponentText("Dang, the dust has been used up."));
                        --stack.stackSize;
                    }
                }

                return true;
            }

            if(hit instanceof BlockSilkyTNT)
            {
                world.setBlock(x, y, z, Blocks.air);

                for(int xClearing = -4; xClearing <= 4; xClearing++)
                {
                    for(int zClearing = -4; zClearing <= 4; zClearing++)
                    {
                        for(int yClearing = 0; yClearing <= 2; yClearing++)
                        {
                            Block active = world.getBlock(x + xClearing, y + yClearing, z + zClearing);
                            if(!(active instanceof BlockAir))
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
                            if(!(active instanceof BlockAir))
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
                        if(!(active instanceof BlockAir))
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
                        if(!(active instanceof BlockAir))
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
                        if(!(active instanceof BlockAir))
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
                            if(!(active instanceof BlockDirt || active instanceof BlockGrass || active instanceof BlockAir))
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