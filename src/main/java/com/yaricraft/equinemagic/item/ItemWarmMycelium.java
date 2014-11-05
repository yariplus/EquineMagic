
package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;

public class ItemWarmMycelium extends EquineMagicItem
{
	public ItemWarmMycelium()
	{
		super();
		this.setUnlocalizedName("warm_mycelium");
		this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.PEGASUS;
	}

}
