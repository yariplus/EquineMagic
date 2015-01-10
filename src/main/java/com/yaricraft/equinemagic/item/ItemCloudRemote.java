package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.EquineMagic;
import com.yaricraft.equinemagic.EquineMagicPlayer;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.init.EquineMagicItem;
import com.yaricraft.equinemagic.network.MessageExtendedProperties;
import com.yaricraft.equinemagic.reference.MCData;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.util.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Yari on 12/20/2014.
 */
public class ItemCloudRemote extends EquineMagicItem
{
    public ItemCloudRemote()
    {
        super();
        this.setUnlocalizedName(ModNames.Items.CLOUD_REMOTE);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.PEGASUS;
    }

    @Override
    public int getItemStackLimit(ItemStack itemStack)
    {
        if (itemStack.hasTagCompound()) return 1;
        return 64;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
    {
        super.addInformation(itemStack, entityPlayer, list, bool);

        NBTTagCompound nbtTag = itemStack.getTagCompound();
        if (nbtTag != null)
        {
            if (nbtTag.hasKey("LastCloudX") && nbtTag.hasKey("LastCloudY") && nbtTag.hasKey("LastCloudZ"))
            {
                int cloudX = nbtTag.getInteger("LastCloudX");
                int cloudY = nbtTag.getInteger("LastCloudY");
                int cloudZ = nbtTag.getInteger("LastCloudZ");
                list.add(list.size(), "Last Cloud Used: " +cloudX+ ", " +cloudY+ ", " +cloudZ);

                int distX = (int)entityPlayer.posX - cloudX;
                int distY = (int)entityPlayer.posY - cloudY;
                int distZ = (int)entityPlayer.posZ - cloudZ;
                int dist = Math.abs(distX) + Math.abs(distY) + Math.abs(distZ);

                if (dist > 64) list.add(list.size(), StringHelper.Color.RED + "OUT OF RANGE");
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (player instanceof EntityPlayerMP)
        {
            NBTTagCompound nbtTag = itemStack.getTagCompound();
            if (nbtTag != null && nbtTag.hasKey("LastCloudX") && nbtTag.hasKey("LastCloudY") && nbtTag.hasKey("LastCloudZ"))
            {
                int cloudX = nbtTag.getInteger("LastCloudX");
                int cloudY = nbtTag.getInteger("LastCloudY");
                int cloudZ = nbtTag.getInteger("LastCloudZ");

                int distX = (int)player.posX - cloudX;
                int distY = (int)player.posY - cloudY;
                int distZ = (int)player.posZ - cloudZ;
                int dist = Math.abs(distX) + Math.abs(distY) + Math.abs(distZ);

                if (dist > 64) return itemStack;

                EquineMagicPlayer equineMagicPlayer = (EquineMagicPlayer) player.getExtendedProperties(EquineMagicPlayer.NAME);

                if (equineMagicPlayer.magic < 10) return itemStack;

                equineMagicPlayer.magic -= 10;

                MessageExtendedProperties message = new MessageExtendedProperties();
                message.magic = equineMagicPlayer.magic;
                EquineMagic.network.sendTo(message, (EntityPlayerMP)player);

                double offsetY = 0;
                if (!(world.getBlock(cloudX, cloudY+1, cloudZ) instanceof BlockAir)) offsetY = 0.5D;

                ((EntityPlayerMP)player).playerNetServerHandler.setPlayerLocation(cloudX + 0.5D, cloudY + 1.015625D + offsetY, cloudZ + 0.5D, ((EntityPlayerMP) player).rotationYaw, ((EntityPlayerMP) player).rotationPitch);

                itemStack.setTagCompound(null);

                return itemStack;
            }
        }

        return itemStack;
    }
}
