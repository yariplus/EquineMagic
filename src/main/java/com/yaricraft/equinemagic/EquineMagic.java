
package com.yaricraft.equinemagic;

import codechicken.nei.api.API;
import com.yaricraft.equinemagic.block.EquineMagicBlock;
import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
import com.yaricraft.equinemagic.item.EquineMagicItem;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.proxy.IProxy;

import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.tileentity.EquineMagicTile;
import com.yaricraft.equinemagic.utility.LogHelper;
import cpw.mods.fml.common.FMLModContainer;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
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
        // Register all the things
        EquineMagicItem.init();
        EquineMagicBlock.init();
        EquineMagicFluid.init();
        proxy.registerRenderers();
        EquineMagicTile.init();
        registerDictionaryItems();
    }

    @Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
        // Register the Items Event Handler
        proxy.registerEventHandlers();
        MinecraftForge.EVENT_BUS.register(proxy);

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
        if (Loader.isModLoaded("NotEnoughItems"))
        {
            API.hideItem(new ItemStack(EquineMagicBlock.spectral_ascender));
            API.hideItem(new ItemStack(EquineMagicBlock.spectral_cauldron));
        }
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
        OreDictionary.registerOre(ModNames.DUST_SILKY_GUNPOWDER, EquineMagicItem.dustSilkyGunpowder);

        OreDictionary.registerOre(ModNames.EQUINE_DECOR, EquineMagicBlock.blockDecor);
        OreDictionary.registerOre(ModNames.EQUINE_TNT, EquineMagicBlock.blockSilkyTNT);

        OreDictionary.registerOre(ModNames.SPECTRAL_CAULDRON, EquineMagicItem.spectral_cauldron);

        OreDictionary.registerOre(ModNames.SPECTRAL_ASCENDER, EquineMagicItem.spectral_ascender);
        OreDictionary.registerOre(ModNames.SPECTRAL_CANNON, EquineMagicBlock.spectralCannon);
        OreDictionary.registerOre(ModNames.SPECTRAL_MINER, EquineMagicBlock.spectralMiner);

        OreDictionary.registerOre(ModNames.CRYSTAL_PRIMATIC, EquineMagicItem.crystalPrimatic);

        OreDictionary.registerOre(ModNames.EQUINE_CRAFTER, EquineMagicBlock.blockEquineCrafter);

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
                    new ItemStack(OreDictionary.getOres(ModNames.EQUINE_DECOR).get(0).getItem(), 1, (i + 1) % 16),
                    new ItemStack(OreDictionary.getOres(ModNames.EQUINE_DECOR).get(0).getItem(), 1, i)
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

        GameRegistry.addRecipe(new ItemStack(OreDictionary.getOres(ModNames.EQUINE_DECOR).get(0).getItem(), 32), new Object[]{
                "SDS",
                "DSD",
                "SDS",
                'D', new ItemStack(OreDictionary.getOres(ModNames.DUST_CHROMA).get(0).getItem()),
                'S', Blocks.stone
        });

        GameRegistry.addRecipe(new ItemStack(OreDictionary.getOres(ModNames.EQUINE_TNT).get(0).getItem()), new Object[]{
                "SDS",
                "DSD",
                "SDS",
                'D', Blocks.sand,
                'S', new ItemStack(OreDictionary.getOres(ModNames.DUST_SILKY_GUNPOWDER).get(0).getItem())
        });

        GameRegistry.addRecipe(new ItemStack(OreDictionary.getOres(ModNames.SPECTRAL_CAULDRON).get(0).getItem()), new Object[]{
                "IGI",
                "GCG",
                "IGI",
                'G', Blocks.glass_pane,
                'I', Items.iron_ingot,
                'C', Items.cauldron
        });

        GameRegistry.addRecipe(new ItemStack(OreDictionary.getOres(ModNames.SPECTRAL_ASCENDER).get(0).getItem()), new Object[]{
                "DED",
                "GCG",
                "DGD",
                'D', new ItemStack(OreDictionary.getOres(ModNames.EQUINE_DECOR).get(0).getItem()),
                'G', Blocks.glass_pane,
                'C', EquineMagicItem.crystalPrimatic,
                'E', Items.ender_eye
        });

        GameRegistry.addRecipe(new ItemStack(OreDictionary.getOres(ModNames.CRYSTAL_PRIMATIC).get(0).getItem()), new Object[]{
                " O ",
                "ODO",
                " O ",
                'O', Blocks.obsidian,
                'D', Items.diamond
        });

        GameRegistry.addRecipe(new ItemStack(OreDictionary.getOres(ModNames.SPECTRAL_MINER).get(0).getItem()), new Object[]{
                "DHD",
                "DCD",
                "DPD",
                'D', new ItemStack(OreDictionary.getOres(ModNames.EQUINE_DECOR).get(0).getItem()),
                'P', Items.diamond_pickaxe,
                'H', Blocks.hopper,
                'C', new ItemStack(OreDictionary.getOres(ModNames.CRYSTAL_PRIMATIC).get(0).getItem())
        });

        GameRegistry.addRecipe(new ItemStack(OreDictionary.getOres(ModNames.SPECTRAL_CANNON).get(0).getItem()), new Object[]{
                "DDD",
                "BCP",
                "DDD",
                'D', new ItemStack(OreDictionary.getOres(ModNames.EQUINE_DECOR).get(0).getItem()),
                'P', Items.diamond_pickaxe,
                'B', Items.bow,
                'C', new ItemStack(OreDictionary.getOres(ModNames.CRYSTAL_PRIMATIC).get(0).getItem())
        });

        GameRegistry.addRecipe(new ItemStack(OreDictionary.getOres(ModNames.EQUINE_CRAFTER).get(0).getItem()), new Object[]{
                "III",
                "PCP",
                "DDD",
                'D', new ItemStack(OreDictionary.getOres(ModNames.EQUINE_DECOR).get(0).getItem()),
                'P', Blocks.piston,
                'I', Items.iron_ingot,
                'C', Blocks.crafting_table
        });
    }
}