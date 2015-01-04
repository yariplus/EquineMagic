package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.init.EquineMagicBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class EquineMagicItemBlock extends ItemBlock
{
    public EquineMagicItemBlock(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
    {
        EEquineFoci foci = ((EquineMagicBlock)Block.getBlockFromItem(itemStack.getItem())).getFoci(itemStack.getItemDamage());
        if (foci != null) list.add(1, "Focus: " + foci.toString());

        NBTTagCompound tagCompound = itemStack.getTagCompound();
        if (tagCompound != null)
        {
            if (tagCompound.hasKey("FluidStack") && tagCompound.getCompoundTag("FluidStack").hasKey("Amount"))
            {
                list.add(2, "Contains " + tagCompound.getCompoundTag("FluidStack").getInteger("Amount") + "mb Spectra.");
            }
        }

    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }
}