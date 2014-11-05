package com.yaricraft.equinemagic.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class EquineMagicItemBlockWithMeta extends ItemBlock
{
    public EquineMagicItemBlockWithMeta(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        int i = itemStack.getItemDamage();

        if (i < 0 || i >= 16) i = 0;

        return super.getUnlocalizedName() + "." + Integer.toString(i);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
    {
        EquineMagicBlock block = ((EquineMagicBlock)Block.getBlockFromItem(itemStack.getItem()));
        if (block.foci != null)
        {
            list.add(1, "Focus: " + block.foci.toString());
        }
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

}