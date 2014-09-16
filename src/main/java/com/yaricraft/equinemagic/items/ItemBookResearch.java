package com.yaricraft.equinemagic.items;

import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModNames;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockBookshelf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * Created by Yari on 9/14/2014.
 */
public class ItemBookResearch extends EquineMagicItem
{
    public ItemBookResearch() {
        super();
        this.setUnlocalizedName(ModNames.ITEM_BOOK_RESEARCH);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            Block hit = world.getBlock(x, y, z);
            String hitname = hit.getLocalizedName();
            if(hitname.contains("."))
            {
                hitname = hitname.substring(0, hitname.length() - 5);
                if(hitname.contains("."))
                {
                    hitname = hitname.substring(5, hitname.length());
                }
            }
            hitname = hitname.toLowerCase();

            player.addChatMessage(new ChatComponentText("I don't think this " + hitname + " is relevant to my Equine research."));
            return true;
        }

        return false;
    }
}
