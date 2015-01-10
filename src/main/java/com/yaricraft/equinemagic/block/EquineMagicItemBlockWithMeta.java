package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.init.EquineMagicBlock;
import com.yaricraft.equinemagic.reference.ModData;
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
        if (i < 0 || i > 15) i = 0;
        String subName = ((EquineMagicBlock)Block.getBlockFromItem(itemStack.getItem())).subNames[i];
        return super.getUnlocalizedName() + ModData.ASSET_SPACER + subName;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
    {
        EEquineFoci foci = ((EquineMagicBlock)Block.getBlockFromItem(itemStack.getItem())).getFoci(itemStack.getItemDamage());
        if (foci != null) list.add(1, "Focus: " + foci.toString());
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }
}