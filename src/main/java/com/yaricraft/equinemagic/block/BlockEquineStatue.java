package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.EquineMagic;
import com.yaricraft.equinemagic.EquineMagicPlayer;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.network.MessageExtendedProperties;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.util.LogHelper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Yari on 11/14/2014.
 */
public class BlockEquineStatue extends EquineMagicBlock implements ITileEntityProvider
{
    public BlockEquineStatue()
    {
        super(Material.iron);
        this.setBlockName(ModNames.EQUINE_STATUE);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return null;
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest)
    {
        if (!world.isRemote)
        {
            LogHelper.info("client increment");
            MessageExtendedProperties message = new MessageExtendedProperties();
            message.chaos = ((EquineMagicPlayer) player.getExtendedProperties("EquineMagicPlayer")).chaos;
            ((EquineMagicPlayer) player.getExtendedProperties("EquineMagicPlayer")).darkness = ((EquineMagicPlayer) player.getExtendedProperties("EquineMagicPlayer")).darkness + 50;
            message.darkness = ((EquineMagicPlayer) player.getExtendedProperties("EquineMagicPlayer")).darkness;
            message.magic = ((EquineMagicPlayer) player.getExtendedProperties("EquineMagicPlayer")).magic;
            EquineMagic.network.sendTo(message, (EntityPlayerMP)player);
        }

        return removedByPlayer(world, player, x, y, z);
    }
}
