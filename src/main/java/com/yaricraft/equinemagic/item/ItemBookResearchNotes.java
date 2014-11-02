package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.EquineFoci;
import com.yaricraft.equinemagic.EquineMagic;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

/**
 * Created by Yari on 9/14/2014.
 */
public class ItemBookResearchNotes extends EquineMagicItem
{
    public ItemBookResearchNotes() {
        super();
        this.setUnlocalizedName(ModNames.BOOK_RESEARCH);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EquineFoci.UNICORN;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        if (world.isRemote)
        {
            if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            {
                player.openGui(EquineMagic.instance, ModData.GUIID_BOOK_RESEARCH, world, (int) player.posX, (int) player.posY, (int) player.posZ);
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
