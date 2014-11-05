
package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;

public class ItemWarmFeather extends EquineMagicItem
{
	public ItemWarmFeather()
	{
		super();
		this.setUnlocalizedName("warm_feather");
		this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.PEGASUS;
	}

}
