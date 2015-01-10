
package com.yaricraft.equinemagic.item.old;

import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.init.EquineMagicItem;

public class ItemFluxingRod extends EquineMagicItem
{
	public ItemFluxingRod()
	{
		super();
		this.setUnlocalizedName("warm_rod");
		this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        this.foci = EEquineFoci.PEGASUS;
	}
}
