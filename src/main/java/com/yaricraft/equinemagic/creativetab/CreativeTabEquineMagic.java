
package com.yaricraft.equinemagic.creativetab;

import com.yaricraft.equinemagic.item.EquineMagicItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.yaricraft.equinemagic.reference.ModData;

public class CreativeTabEquineMagic
{
	public static final CreativeTabs tabEquineMagic = new CreativeTabs(ModData.MODID)
	{   
		@Override
		public Item getTabIconItem()
		{
			return EquineMagicItem.dustAlicorn;
		}
	};
}
