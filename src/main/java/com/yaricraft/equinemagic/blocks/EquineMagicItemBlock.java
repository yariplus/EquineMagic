package com.yaricraft.equinemagic.blocks;

import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class EquineMagicItemBlock extends ItemBlock
{
    public EquineMagicItemBlock(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int i = itemStack.getItemDamage();
        if (i < 0 || i >= 16) {
            i = 0;
        }

        return super.getUnlocalizedName() + "." + Integer.toString(i);
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

}