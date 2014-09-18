package com.yaricraft.equinemagic.blocks;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.logic.TESolarCauldron;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.logic.EquineMagicLogic;
import com.yaricraft.equinemagic.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by Yari on 9/15/2014.
 */
public class BlockSolarCauldron extends EquineMagicBlock implements ITileEntityProvider
{
    public BlockSolarCauldron()
    {
        super();
        this.setBlockName(ModNames.BLOCK_SOLAR_CAULDRON);
        this.setBlockTextureName(ModNames.BLOCK_SOLAR_CAULDRON);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TESolarCauldron();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote && player.getHeldItem() == null)
        {
            TESolarCauldron teSolarCauldron = (TESolarCauldron) world.getTileEntity(x, y, z);
            if (teSolarCauldron == null)
            {
                player.addChatMessage(new ChatComponentText("This cauldron is broken."));
                LogHelper.error("SolarCauldron missing tile entity at " + x + "," + y + "," + z);
            } else
            {
                Block slot0 = teSolarCauldron.slot[0];
                Block slot1 = teSolarCauldron.slot[1];
                Block slot2 = teSolarCauldron.slot[2];

                if (slot0 == Blocks.air && slot1 == Blocks.air && slot2 == Blocks.air)
                {
                    player.addChatMessage(new ChatComponentText("Nothing in the cauldron."));
                } else
                {
                    player.addChatMessage(new ChatComponentText("I'm not sure what's inside."));
                }
            }

        }

        return false;
    }
}
