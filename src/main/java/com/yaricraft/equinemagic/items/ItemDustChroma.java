
package com.yaricraft.equinemagic.items;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBookshelf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemDustChroma extends EquineMagicItem
{
    public ItemDustChroma()
    {
        super();
        this.setUnlocalizedName(ModNames.DUST_CHROMA);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }
}
