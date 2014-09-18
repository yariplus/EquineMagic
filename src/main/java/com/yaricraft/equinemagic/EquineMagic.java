
package com.yaricraft.equinemagic;

import com.yaricraft.equinemagic.blocks.EquineMagicBlock;
import com.yaricraft.equinemagic.fluids.EquineMagicFluid;
import com.yaricraft.equinemagic.items.EquineMagicItem;
import com.yaricraft.equinemagic.logic.EquineMagicLogic;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.proxy.IProxy;

import com.yaricraft.equinemagic.reference.ModNames;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockStone;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.oredict.OreDictionary;

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
        // Register things
        EquineMagicItem.init();
        EquineMagicBlock.init();
        EquineMagicFluid.init();
        EquineMagicLogic.init();

        registerDictionaryItems();
	}

    @Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
        // Register the Items Event Handler
        proxy.registerEventHandlers();

        // Add loots
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(EquineMagicItem.dustAlicorn, 0, 1, 2, 5));
        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(EquineMagicItem.dustAlicorn, 0, 1, 2, 5));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(EquineMagicItem.dustAlicorn, 0, 1, 2, 5));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(EquineMagicItem.dustAlicorn, 0, 1, 2, 5));

        addRecipes();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
        // NOOP
    }

    private void registerDictionaryItems()
    {
        OreDictionary.registerOre(ModNames.ORE_CHROMA, EquineMagicBlock.blockOreChroma);
        OreDictionary.registerOre(ModNames.ORE_PEGAGIN, EquineMagicBlock.blockOrePegagin);
        OreDictionary.registerOre(ModNames.ORE_SPECTRA, EquineMagicBlock.blockOreSpectra);

        OreDictionary.registerOre(ModNames.DUST_CHROMA, EquineMagicItem.dustChroma);
        OreDictionary.registerOre(ModNames.DUST_PEGAGIN, EquineMagicItem.dustPegagin);
        OreDictionary.registerOre(ModNames.DUST_SPECTRA, EquineMagicItem.dustSpectra);
        OreDictionary.registerOre(ModNames.DUST_SILKY, EquineMagicItem.dustSilky);
        OreDictionary.registerOre(ModNames.DUST_SILKY_GUNPOWDER, EquineMagicItem.getDustSilkyGunpowder);

        OreDictionary.registerOre(ModNames.BLOCK_DECOR, EquineMagicBlock.blockDecor);
        OreDictionary.registerOre(ModNames.BLOCK_SILKY_TNT, EquineMagicBlock.blockSilkyTNT);
        OreDictionary.registerOre(ModNames.BLOCK_SOLAR_CAULDRON, EquineMagicBlock.solarCauldron);

        if(OreDictionary.getOres("blockWool").size() < 16)
        {
            for (int i = 0; i < 16; i++)
            {
                OreDictionary.registerOre("blockWool", new ItemStack(Blocks.wool, 1, i));
            }
        }
    }

    private void addRecipes()
    {
        //
        for ( int i = 15; i >= 0; i--)
        {
            GameRegistry.addShapelessRecipe(
                    new ItemStack(OreDictionary.getOres(ModNames.BLOCK_DECOR).get(0).getItem(), 1, (i + 1) % 16),
                    new ItemStack(OreDictionary.getOres(ModNames.BLOCK_DECOR).get(0).getItem(), 1, i)
            );
        }


            GameRegistry.addShapelessRecipe(
                    new ItemStack(OreDictionary.getOres(ModNames.DUST_SILKY).get(0).getItem(), 4),
                    new ItemStack(OreDictionary.getOres(ModNames.DUST_PEGAGIN).get(0).getItem()),
                    Blocks.wool);



            GameRegistry.addShapelessRecipe(
                    new ItemStack(OreDictionary.getOres(ModNames.DUST_SILKY).get(0).getItem(), 8),
                    new ItemStack(OreDictionary.getOres(ModNames.DUST_SPECTRA).get(0).getItem()),
                    Blocks.wool);


        GameRegistry.addShapelessRecipe(
                new ItemStack(OreDictionary.getOres(ModNames.DUST_SILKY_GUNPOWDER).get(0).getItem()),
                new ItemStack(OreDictionary.getOres(ModNames.DUST_SILKY).get(0).getItem()),
                Items.gunpowder);

        GameRegistry.addRecipe(new ItemStack(OreDictionary.getOres(ModNames.BLOCK_DECOR).get(0).getItem(), 32), new Object[]{
                "SDS",
                "DSD",
                "SDS",
                'D', new ItemStack(OreDictionary.getOres(ModNames.DUST_CHROMA).get(0).getItem()),
                'S', Blocks.stone
        });

        GameRegistry.addRecipe(new ItemStack(OreDictionary.getOres(ModNames.BLOCK_SILKY_TNT).get(0).getItem()), new Object[]{
                "SDS",
                "DSD",
                "SDS",
                'D', Blocks.sand,
                'S', new ItemStack(OreDictionary.getOres(ModNames.DUST_SILKY_GUNPOWDER).get(0).getItem())
        });

        GameRegistry.addRecipe(new ItemStack(OreDictionary.getOres(ModNames.BLOCK_SOLAR_CAULDRON).get(0).getItem()), new Object[]{
                "IGI",
                "GCG",
                "IGI",
                'G', Blocks.glass_pane,
                'I', Items.iron_ingot,
                'C', Items.cauldron
        });
    }
}