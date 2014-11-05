
package com.yaricraft.equinemagic.item;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;

public class ItemWarmScale extends EquineMagicItem
{
	public ItemWarmScale()
	{
		super();
		this.setUnlocalizedName("warm_scale");
		this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.PEGASUS;
	}

}
