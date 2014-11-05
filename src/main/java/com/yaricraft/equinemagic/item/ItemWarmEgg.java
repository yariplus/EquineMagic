
package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;

public class ItemWarmEgg extends EquineMagicItem
{
	public ItemWarmEgg()
	{
		super();
		this.setUnlocalizedName("warm_egg");
		this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.PEGASUS;
	}

}
