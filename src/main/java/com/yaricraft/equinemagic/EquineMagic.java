
package com.yaricraft.equinemagic;

import com.yaricraft.equinemagic.blocks.EquineMagicBlock;
import com.yaricraft.equinemagic.items.EquineMagicItem;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.proxy.IProxy;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = ModData.MODID, version = ModData.VERSION)
public class EquineMagic
{
	@Mod.Instance
	public static EquineMagic	instance;

	@SidedProxy(clientSide = ModData.CLIENT_PROXY_CLASS, serverSide = ModData.SERVER_PROXY_CLASS)
	public static IProxy		proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
        EquineMagicItem.init();
        EquineMagicBlock.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}

}