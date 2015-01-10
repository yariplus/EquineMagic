
package com.yaricraft.equinemagic.item.old;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.init.EquineMagicItem;

public class ItemWarmWing extends EquineMagicItem
{
	public ItemWarmWing()
	{
		super();
		this.setUnlocalizedName("warm_wing");
		this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.PEGASUS;
	}
}
