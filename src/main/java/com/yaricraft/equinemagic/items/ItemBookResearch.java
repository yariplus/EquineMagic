package com.yaricraft.equinemagic.items;

import com.yaricraft.equinemagic.EquineMagic;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.utility.LogHelper;
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
import org.lwjgl.input.Keyboard;

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
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
        {
            if(!world.isRemote)
            {
            }else
            {
                LogHelper.info("Pressed Right Click with Book");
                player.openGui(EquineMagic.instance, ModData.GUIID_BOOK_RESEARCH, world, (int)player.posX, (int)player.posY, (int)player.posZ);
            }
        }else
        {
            if(!world.isRemote)
            {
            }else{
            }
        }

        return itemstack;
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
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
        }else{

        }

        return true;
    }
}
