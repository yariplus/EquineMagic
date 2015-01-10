
package com.yaricraft.equinemagic;

import cofh.api.modhelpers.ThermalExpansionHelper;
import com.yaricraft.equinemagic.init.EquineMagicBlock;
import com.yaricraft.equinemagic.entity.monster.EntityChangeling;
import com.yaricraft.equinemagic.entity.passive.EntityAura;
import com.yaricraft.equinemagic.enums.EElementalShard;
import com.yaricraft.equinemagic.enums.EEquineDust;
import com.yaricraft.equinemagic.enums.EEquineGem;
import com.yaricraft.equinemagic.enums.EEquineOre;
import com.yaricraft.equinemagic.fluid.EquineMagicFluid;
import com.yaricraft.equinemagic.init.EquineMagicItem;
import com.yaricraft.equinemagic.network.HandlerExtendedProperties;
import com.yaricraft.equinemagic.network.HandlerPlayerMovement;
import com.yaricraft.equinemagic.network.MessageExtendedProperties;
import com.yaricraft.equinemagic.network.MessagePlayerMovement;
import com.yaricraft.equinemagic.reference.ModData;
import com.yaricraft.equinemagic.proxy.IProxy;

import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.init.EquineMagicTile;
import com.yaricraft.equinemagic.util.StringHelper;
import com.yaricraft.equinemagic.world.gen.feature.EquineWorldGenerator;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.biome.BiomeGenBase;
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

    public static SimpleNetworkWrapper network;

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

        network = NetworkRegistry.INSTANCE.newSimpleChannel(ModData.MODID);
        // Message1 is handled by the Message1Handler class, it has discriminator id 1 and it's on the client
        // Send Magic Levels to the client.
        network.registerMessage(HandlerExtendedProperties.class, MessageExtendedProperties.class, 1, Side.CLIENT);

        // Send player movement to the server.
        network.registerMessage(HandlerPlayerMovement.class, MessagePlayerMovement.class, 2, Side.SERVER);

        // Send Cloud Remote info to the client.
        //network.registerMessage(HandlerCloud.class, MessageCloud.class, 3, Side.CLIENT);
    }

    @Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
        proxy.registerKeybindings();
        //
        GameRegistry.registerWorldGenerator(new EquineWorldGenerator(), 10);

        // Register the Items Event Handler
        proxy.registerEventHandlers();
        MinecraftForge.EVENT_BUS.register(proxy);

        // Add loots
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(EquineMagicItem.dustAlicorn, 0, 1, 4, 10));
        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(EquineMagicItem.dustAlicorn, 0, 1, 4, 10));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(EquineMagicItem.dustAlicorn, 0, 1, 4, 10));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(EquineMagicItem.dustAlicorn, 0, 1, 4, 10));

        addRecipes();

        ThermalExpansionHelper.addPulverizerRecipe(2400,
                new ItemStack(EquineMagicItem.equine_gem,1,EEquineGem.CHROMA.ordinal()),
                new ItemStack(EquineMagicItem.equine_dust,8,EEquineDust.CHROMA.ordinal()),
                new ItemStack(EquineMagicItem.equine_dust,2,EEquineDust.OPAL.ordinal()),
                50
        );

        ThermalExpansionHelper.addPulverizerRecipe(2400,
                new ItemStack(EquineMagicItem.equine_gem,1,EEquineGem.DOLOMITE.ordinal()),
                new ItemStack(EquineMagicItem.equine_dust,8,EEquineDust.DOLOMITE.ordinal())
        );

        ThermalExpansionHelper.addPulverizerRecipe(4800,
                new ItemStack(EquineMagicItem.equine_gem,1,EEquineGem.SPECTRA.ordinal()),
                new ItemStack(EquineMagicItem.equine_dust,2,EEquineDust.DIRTY_SPECTRA.ordinal()),
                new ItemStack(EquineMagicItem.equine_dust,1,EEquineDust.IMPURE_SPECTRA.ordinal()),
                10
        );

        ThermalExpansionHelper.addPulverizerRecipe(2400,
                new ItemStack(EquineMagicItem.equine_gem,1,EEquineGem.ZIRCON.ordinal()),
                new ItemStack(EquineMagicItem.equine_dust,2,EEquineDust.ZIRCON.ordinal())
        );

        ThermalExpansionHelper.addPulverizerRecipe(2400,
                new ItemStack(EquineMagicItem.equine_gem,1,EEquineGem.OPAL.ordinal()),
                new ItemStack(EquineMagicItem.equine_dust,8,EEquineDust.OPAL.ordinal())
        );

        ThermalExpansionHelper.addPulverizerRecipe(2400,
                new ItemStack(EquineMagicItem.equine_gem,1,EEquineGem.BLACK_OPAL.ordinal()),
                new ItemStack(EquineMagicItem.equine_dust,8,EEquineDust.BLACK_OPAL.ordinal())
        );

        int id = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityChangeling.class, "changeling", id, 128, 0);
        EntityRegistry.registerModEntity(EntityChangeling.class, "changeling", id, instance, 20, 5, true);

        EntityRegistry.addSpawn(EntityChangeling.class, 30, 1, 2, EnumCreatureType.monster, BiomeGenBase.plains);

        id = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityAura.class, "equine_aura", id, 64, 48);
        EntityRegistry.registerModEntity(EntityAura.class, "equine_aura", id, instance, 20, 5, true);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

    }

    private void registerDictionaryItems()
    {
        for (EEquineDust dust : EEquineDust.values())
            OreDictionary.registerOre(StringHelper.dictPrefix(ModNames.EQUINE_DUST) + StringHelper.vanillaCaseToCamelCase(dust.toString()), new ItemStack(EquineMagicItem.equine_dust, 1, dust.ordinal()));

        for (EEquineOre ore : EEquineOre.values())
            OreDictionary.registerOre(StringHelper.dictPrefix(ModNames.EQUINE_ORE) + StringHelper.vanillaCaseToCamelCase(ore.toString()), new ItemStack(EquineMagicBlock.equine_ore, 1, ore.ordinal()));

        for (EEquineGem gem : EEquineGem.values())
            OreDictionary.registerOre(StringHelper.dictPrefix(ModNames.EQUINE_GEM) + StringHelper.vanillaCaseToCamelCase(gem.toString()), new ItemStack(EquineMagicItem.equine_gem, 1, gem.ordinal()));

        OreDictionary.registerOre(ModNames.DUST_SILKY, EquineMagicItem.dustSilky);
        OreDictionary.registerOre(ModNames.DUST_SILKY_GUNPOWDER, EquineMagicItem.dustSilkyGunpowder);

        OreDictionary.registerOre(ModNames.EQUINE_DECOR, EquineMagicBlock.blockDecor);
        OreDictionary.registerOre(ModNames.EQUINE_TNT, EquineMagicBlock.equine_tnt);

        OreDictionary.registerOre(ModNames.SPECTRAL_CAULDRON, EquineMagicBlock.spectral_cauldron);

        OreDictionary.registerOre(ModNames.SPECTRAL_CANNON, EquineMagicBlock.spectral_cannon);
        OreDictionary.registerOre(ModNames.SPECTRAL_MINER, EquineMagicBlock.spectral_miner);

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

        for ( EEquineGem gem : EEquineGem.values() )
        {
            GameRegistry.addRecipe(new ItemStack(EquineMagicBlock.block_compressed, 1, gem.ordinal()), new Object[]{
                    "GGG",
                    "GGG",
                    "GGG",
                    'G', new ItemStack(EquineMagicItem.equine_gem, 1, gem.ordinal())
            });

            GameRegistry.addShapelessRecipe(new ItemStack(EquineMagicItem.equine_gem, 9, gem.ordinal()), new ItemStack(EquineMagicBlock.block_compressed, 1, gem.ordinal()) );
        }

        GameRegistry.addShapelessRecipe(
            new ItemStack(OreDictionary.getOres(ModNames.DUST_SILKY).get(0).getItem(), 4),
            new ItemStack(OreDictionary.getOres(StringHelper.dictPrefix(ModNames.EQUINE_DUST) + StringHelper.vanillaCaseToCamelCase(EEquineDust.DIRTY_SPECTRA.toString())).get(0).getItem()),
            Blocks.wool);



        GameRegistry.addShapelessRecipe(
            new ItemStack(OreDictionary.getOres(ModNames.DUST_SILKY).get(0).getItem(), 8),
            new ItemStack(OreDictionary.getOres(StringHelper.dictPrefix(ModNames.EQUINE_DUST) + StringHelper.vanillaCaseToCamelCase(EEquineDust.IMPURE_SPECTRA.toString())).get(0).getItem()),
            Blocks.wool);


        GameRegistry.addShapelessRecipe(
                new ItemStack(OreDictionary.getOres(ModNames.DUST_SILKY_GUNPOWDER).get(0).getItem()),
                new ItemStack(OreDictionary.getOres(ModNames.DUST_SILKY).get(0).getItem()),
                Items.gunpowder);

        GameRegistry.addShapelessRecipe(
            new ItemStack(EquineMagicItem.aura_summoner, 1, 0),
            new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.BLUE.ordinal()),
            new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.GREEN.ordinal()),
            new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.RED.ordinal()),
            new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.ORANGE.ordinal()),
            new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.PINK.ordinal()),
            new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.VIOLET.ordinal()));

        GameRegistry.addRecipe(new ItemStack(OreDictionary.getOres(ModNames.EQUINE_DECOR).get(0).getItem(), 32), new Object[]{
                "SDS",
                "DSD",
                "SDS",
                'D', new ItemStack(OreDictionary.getOres(StringHelper.dictPrefix(ModNames.EQUINE_DUST) + StringHelper.vanillaCaseToCamelCase(EEquineDust.CHROMA.toString())).get(0).getItem()),
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

        GameRegistry.addRecipe(new ItemStack(EquineMagicBlock.equine_bell), new Object[]{
                " I ",
                "ICI",
                "I I",
                'I', Items.iron_ingot,
                'C', new ItemStack(OreDictionary.getOres(ModNames.CRYSTAL_PRIMATIC).get(0).getItem())
        });

        GameRegistry.addRecipe(new ItemStack(EquineMagicBlock.cloudavator, 4), new Object[]{
                " D ",
                "DSD",
                " D ",
                'D', new ItemStack(OreDictionary.getOres(ModNames.EQUINE_DECOR).get(0).getItem()),
                'S', new ItemStack(EquineMagicItem.equine_dust, 1, EEquineDust.DIRTY_SPECTRA.ordinal())
        });

        GameRegistry.addRecipe(new ItemStack(EquineMagicItem.cloud_remote), new Object[]{
                "R",
                "S",
                'R', Blocks.redstone_torch,
                'S', new ItemStack(EquineMagicItem.equine_dust, 1, EEquineDust.DIRTY_SPECTRA.ordinal())
        });
    }
}