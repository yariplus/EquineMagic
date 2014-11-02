
package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.EquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;

public class ItemWarmWing extends EquineMagicItem
{
	public ItemWarmWing()
	{
		super();
		this.setUnlocalizedName("warm_wing");
		this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EquineFoci.PEGASUS;
	}
}
