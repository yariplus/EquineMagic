
package com.yaricraft.equinemagic.reference;

public class ModData
{
	public static final String	MODID				= "EquineMagic";
	public static final String	MODNAME				= "EquineMagic";
	public static final String	VERSION				= "0.0.1";

	public static final boolean	DEBUG				= true;

	public static final String	CLIENT_PROXY_CLASS	= "com.yaricraft.equinemagic.proxy.ClientProxy";
	public static final String	SERVER_PROXY_CLASS	= "com.yaricraft.equinemagic.proxy.ServerProxy";
	public static final String	GUI_FACTORY_CLASS	= "com.yaricraft.equinemagic.client.gui.GUIFactory";

    public static final String ASSETSUF_ICON_TOP    = "_Top";
    public static final String ASSETSUF_ICON_SIDE   = "_Side";
    public static final String ASSETSUF_ICON_BOTTOM = "_Bottom";
    public static final String ASSETSUF_ICON_FRONT  = "_Front";
    public static final String ASSETSUF_ICON_BACK   = "_Back";
    public static final String ASSETSUF_ICON_LEFT   = "_Left";
    public static final String ASSETSUF_ICON_RIGHT  = "_Right";

    public static final String[] ASSETSUF_ICON = new String[] {ASSETSUF_ICON_TOP, ASSETSUF_ICON_BOTTOM, ASSETSUF_ICON_FRONT, ASSETSUF_ICON_BACK, ASSETSUF_ICON_LEFT, ASSETSUF_ICON_RIGHT};
    public static final String[] ASSETSUF_META  = new String[] {"_0","_1","_2","_3","_4","_5","_6","_7","_8","_9","_10","_11","_12","_13","_14","_15"};

    public static final int GUIID_BOOK_RESEARCH = 1;
}
