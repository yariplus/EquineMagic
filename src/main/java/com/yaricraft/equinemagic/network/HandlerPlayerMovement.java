package com.yaricraft.equinemagic.network;

import com.yaricraft.equinemagic.EquineMagic;
import com.yaricraft.equinemagic.EquineMagicPlayer;
import com.yaricraft.equinemagic.block.EquineMagicBlock;
import com.yaricraft.equinemagic.init.EquineMagicItem;
import com.yaricraft.equinemagic.util.LogHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidBlock;

import java.util.UUID;

/**
 * Created by Yari on 12/19/2014.
 */
public class HandlerPlayerMovement implements IMessageHandler<MessagePlayerMovement, IMessage>
{
    @Override
    public IMessage onMessage(MessagePlayerMovement message, MessageContext ctx)
    {
        // MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(String.format("Moving Player %s", ForgeDirection.values()[message.direction].toString())));

        EntityPlayer player = MinecraftServer.getServer().getEntityWorld().func_152378_a(new UUID(message.mbits, message.lbits));
        //EntityPlayer player = Minecraft.getMinecraft().theWorld.func_152378_a(new UUID(message.mbits, message.lbits));

        if (!(player instanceof EntityPlayerMP))
        {
            LogHelper.error("Couldn't find player for MessagePlayerMovement packet.");
            return null;
        }

        if (player == null)
        {
            LogHelper.error("A MessagePlayerMovement packet sent a null player.");
            return null;
        }

        final World world = player.worldObj;
        if (world == null) return null;

        final int x = MathHelper.floor_double(player.posX);
        final int y = MathHelper.floor_double(player.boundingBox.minY) - 1;
        final int z = MathHelper.floor_double(player.posZ);
        Block blockStanding = world.getBlock(x, y, z);
        Block blockBelow = world.getBlock(x, y-1, z);
        if ((blockStanding == EquineMagicBlock.cloudavator || blockBelow == EquineMagicBlock.cloudavator) && canUseMagic(player))
        {
            int i = 0;
            int foundAir = 0;
            switch (ForgeDirection.values()[message.direction])
            {
                case DOWN:
                    i = y - 1;
                    while (i > 0)
                    {
                        blockStanding = world.getBlock(x, i, z);
                        if (blockStanding.isOpaqueCube())
                        {
                            if (foundAir > 3)
                            {
                                storeCloudOnRemote(player, x, y, z);
                                double offsetY = 0;
                                if (!(world.getBlock(x, i+1, z) instanceof BlockAir)) offsetY = 0.5D;
                                ((EntityPlayerMP)player).playerNetServerHandler.setPlayerLocation(x + 0.5D, i + 1.015625D + offsetY, z + 0.5D, ((EntityPlayerMP) player).rotationYaw, ((EntityPlayerMP) player).rotationPitch);
                                break;
                            }
                            foundAir = 0;
                        }else
                        {
                            if (!(blockStanding instanceof BlockLiquid || blockStanding instanceof IFluidBlock)) foundAir++;
                        }
                        i--;
                    }
                    break;
                case UP:
                    i = y + 1;
                    foundAir = 0;
                    int lastSolid = -1;
                    while (i < 255)
                    {
                        blockStanding = world.getBlock(x, i, z);
                        if (blockStanding.isOpaqueCube())
                        {
                            foundAir = 0;
                            lastSolid = i;
                        }else
                        {
                            if (!(blockStanding instanceof BlockLiquid || blockStanding instanceof IFluidBlock))
                            {
                                foundAir++;
                                if (foundAir > 3 && lastSolid != -1)
                                {
                                    storeCloudOnRemote(player, x, y, z);
                                    double offsetY = 0;
                                    if (!(world.getBlock(x, lastSolid+1, z) instanceof BlockAir)) offsetY = 0.5D;
                                    ((EntityPlayerMP)player).playerNetServerHandler.setPlayerLocation(x + 0.5D, lastSolid + 1.015625D + offsetY, z + 0.5D, ((EntityPlayerMP) player).rotationYaw, ((EntityPlayerMP) player).rotationPitch);
                                    break;
                                }
                            }
                        }
                        i++;
                    }
                    break;
            }
        }

        return null;
    }

    private void storeCloudOnRemote(EntityPlayer player, int x, int y, int z)
    {
        if (player.getHeldItem() != null && player.getHeldItem().getItem() == EquineMagicItem.cloud_remote)
        {
            NBTTagCompound nbtTag = new NBTTagCompound();

            nbtTag.setInteger("LastCloudX", x);
            nbtTag.setInteger("LastCloudY", y);
            nbtTag.setInteger("LastCloudZ", z);

            player.getHeldItem().setTagCompound(nbtTag);

            return;
        }

        for (int i = 0; i < 9 * 4; i++)
        {
            ItemStack itemStack = player.inventory.getStackInSlot(i);
            if (itemStack != null && itemStack.getItem() == EquineMagicItem.cloud_remote)
            {
                NBTTagCompound nbtTag = itemStack.getTagCompound();

                if (!(nbtTag != null && nbtTag.hasKey("LastCloudX")))
                {
                    nbtTag = new NBTTagCompound();

                    nbtTag.setInteger("LastCloudX", x);
                    nbtTag.setInteger("LastCloudY", y);
                    nbtTag.setInteger("LastCloudZ", z);

                    itemStack.setTagCompound(nbtTag);

                    return;
                }
            }
        }

        for (int i = 0; i < 9 * 4; i++)
        {
            ItemStack itemStack = player.inventory.getStackInSlot(i);
            if (itemStack != null && itemStack.getItem() == EquineMagicItem.cloud_remote)
            {
                NBTTagCompound nbtTag = new NBTTagCompound();

                nbtTag.setInteger("LastCloudX", x);
                nbtTag.setInteger("LastCloudY", y);
                nbtTag.setInteger("LastCloudZ", z);

                itemStack.setTagCompound(nbtTag);

                return;
            }
        }
    }

    private boolean canUseMagic(EntityPlayer player)
    {
        EquineMagicPlayer equineMagicPlayer = (EquineMagicPlayer) player.getExtendedProperties(EquineMagicPlayer.NAME);

        if (equineMagicPlayer.magic < 10) return false;

        equineMagicPlayer.magic -= 10;

        MessageExtendedProperties message = new MessageExtendedProperties();
        message.magic = equineMagicPlayer.magic;
        EquineMagic.network.sendTo(message, (EntityPlayerMP)player);

        return true;
    }
}
