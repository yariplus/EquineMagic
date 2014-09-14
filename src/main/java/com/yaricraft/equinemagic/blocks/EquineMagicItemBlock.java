package com.yaricraft.equinemagic.blocks;

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
        if (i < 0 || i >= ModNames.BLOCK_DECOR_SUFFIX.length) {
            i = 0;
        }

        return super.getUnlocalizedName() + "." + ModNames.BLOCK_DECOR_SUFFIX[i];
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

}