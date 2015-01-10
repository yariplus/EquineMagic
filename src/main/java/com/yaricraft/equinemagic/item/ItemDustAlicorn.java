package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.EquineMagic;
import com.yaricraft.equinemagic.EquineMagicPlayer;
import com.yaricraft.equinemagic.entity.passive.EntityAura;
import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.block.BlockEquineTNT;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.init.EquineMagicItem;
import com.yaricraft.equinemagic.network.MessageExtendedProperties;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.util.LogHelper;
import net.minecraft.block.*;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

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
        this.foci = EEquineFoci.UNICORN;
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        // TODO: Clean this shit up
        // TODO: Add a test for the amount of Alicorn dust the player is holding and use that to adjust the blast range.

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

                return true;
            }
        }

        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            Vec3 lookVec = player.getLookVec();
            int blockX;
            int blockY;
            int blockZ;
            int _try = 1;
            while (_try <= 30)
            {
                LogHelper.info("try " + _try);

                blockX = MathHelper.floor_double(player.posX + lookVec.xCoord * _try);
                blockY = MathHelper.floor_double((player.posY + 1.5D) + lookVec.yCoord * _try);
                blockZ = MathHelper.floor_double(player.posZ + lookVec.zCoord * _try);
                Block block = world.getBlock(blockX, blockY, blockZ);

                if (block != null && !(block instanceof BlockAir))
                {
                    int x = (int)player.posX;
                    int y = (int)player.posY;
                    int z = (int)player.posZ;
                    List<EntityAura> entities = world.getEntitiesWithinAABB(EntityAura.class, AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1).expand(12, 6, 12));
                    EquineMagicPlayer equineMagicPlayer = (EquineMagicPlayer) player.getExtendedProperties(EquineMagicPlayer.NAME);

                    if (entities != null && entities.size() > 0)
                    {
                        String uuid;

                        for (EntityAura entity : entities)
                        {
                            if (equineMagicPlayer.magic < 5) break;
                            equineMagicPlayer.magic -= 5;

                            uuid = entity.getUniqueID().toString();
                            if (!equineMagicPlayer.aEquineAuras.contains(uuid)) equineMagicPlayer.aEquineAuras.add(uuid);

                            entity.Shoot(Vec3.createVectorHelper(blockX, blockY, blockZ));
                        }

                        MessageExtendedProperties message = new MessageExtendedProperties();
                        message.magic = equineMagicPlayer.magic;
                        EquineMagic.network.sendTo(message, (EntityPlayerMP)player);
                    }

                    break;
                }

                _try++;
            }
        }

        return itemStack;
    }
}