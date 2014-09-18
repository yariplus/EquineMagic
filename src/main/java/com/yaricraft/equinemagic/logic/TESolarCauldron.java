package com.yaricraft.equinemagic.logic;

import com.yaricraft.equinemagic.blocks.EquineMagicBlock;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;

/**
 * Created by Yari on 9/17/2014.
 */
public class TESolarCauldron extends EquineMagicLogic
{
    // How long have the contents been cooking? In ticks.
    public int[] cooktime = {0, 0, 0};

    // What's inside?
    public Block[] slot = {Blocks.air, Blocks.air, Blocks.air};

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        cooktime[0] = nbt.getInteger("CookTime0");
        cooktime[1] = nbt.getInteger("CookTime1");
        cooktime[2] = nbt.getInteger("CookTime2");
        slot[0] = GameRegistry.findBlock(ModData.MODID, nbt.getString("Block0"));
        slot[0] = GameRegistry.findBlock(ModData.MODID, nbt.getString("Block1"));
        slot[0] = GameRegistry.findBlock(ModData.MODID, nbt.getString("Block2"));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        LogHelper.info("Saving NBT...");
        nbt.setInteger("CookTime0", cooktime[0]);
        nbt.setInteger("CookTime1", cooktime[1]);
        nbt.setInteger("CookTime2", cooktime[2]);
        LogHelper.info(slot[0].getUnlocalizedName() + " stored as");
        LogHelper.info(slot[0].getUnlocalizedName().substring(slot[0].getUnlocalizedName().indexOf(".") + 1));
        nbt.setString("Block0", slot[0].getUnlocalizedName().substring(slot[0].getUnlocalizedName().indexOf(".") + 1));
        nbt.setString("Block1", slot[1].getUnlocalizedName().substring(slot[1].getUnlocalizedName().indexOf(".") + 1));
        nbt.setString("Block2", slot[2].getUnlocalizedName().substring(slot[2].getUnlocalizedName().indexOf(".") + 1));
    }
}
