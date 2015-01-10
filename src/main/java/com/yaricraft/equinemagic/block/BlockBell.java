package com.yaricraft.equinemagic.block;

import com.yaricraft.equinemagic.EquineMagic;
import com.yaricraft.equinemagic.EquineMagicPlayer;
import com.yaricraft.equinemagic.creativetab.CreativeTabEquineMagic;
import com.yaricraft.equinemagic.enums.EElementalShard;
import com.yaricraft.equinemagic.enums.EEquineFoci;
import com.yaricraft.equinemagic.enums.EEquineGem;
import com.yaricraft.equinemagic.enums.EFatedMob;
import com.yaricraft.equinemagic.init.EquineMagicBlock;
import com.yaricraft.equinemagic.init.EquineMagicItem;
import com.yaricraft.equinemagic.network.MessageExtendedProperties;
import com.yaricraft.equinemagic.reference.ModNames;
import com.yaricraft.equinemagic.tileentity.TileBell;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.*;

/**
 * Created by Yari on 11/12/2014.
 */
public class BlockBell extends EquineMagicBlock implements ITileEntityProvider
{
    List<Class<? extends EntityMob>> fatedEntities = new ArrayList<Class<? extends EntityMob>>();

    public BlockBell()
    {
        super(Material.iron);
        this.setBlockName(ModNames.EQUINE_BELL);
        this.setCreativeTab(CreativeTabEquineMagic.tabEquineMagic);
        setHardness(2.0F);

        renderId = RenderingRegistry.getNextAvailableRenderId();

        fatedEntities.add(EntityZombie.class);
    }

    @Override
    public EEquineFoci getFoci(int meta)
    {
        switch (1)
        {
            default:
                return EEquineFoci.SHADOW;
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1).expand(8, 4, 8));
            if (entities != null && entities.size() > 0)
            {
                Random random = new Random();
                EntityLivingBase entity = entities.get(random.nextInt(entities.size()));
                for (EFatedMob fatedmob : EFatedMob.values())
                {
                    if (entity.getClass() == fatedmob.clazz)
                    {
                        //int damage = 10;
                        switch (fatedmob)
                        {
                            case ZOMBIE:
                                entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10);
                                if (entity.getHealth() <= 0)
                                {
                                    switch (world.rand.nextInt(9))
                                    {
                                        case 0:
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.BLUE.ordinal())));
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.GREEN.ordinal())));
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.ORANGE.ordinal())));
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.VIOLET.ordinal())));
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.PINK.ordinal())));
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.RED.ordinal())));
                                        case 1:
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.equine_gem, 1+world.rand.nextInt(4), EEquineGem.BLACK_OPAL.ordinal())));
                                        case 2:
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.equine_gem, 1+world.rand.nextInt(2), EEquineGem.CHROMA.ordinal())));
                                            break;
                                        case 3:
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.BLUE.ordinal())));
                                            break;
                                        case 4:
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.RED.ordinal())));
                                            break;
                                        case 5:
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.PINK.ordinal())));
                                            break;
                                        case 6:
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.VIOLET.ordinal())));
                                            break;
                                        case 7:
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.ORANGE.ordinal())));
                                            break;
                                        case 8:
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.elemental_shard, 1, EElementalShard.GREEN.ordinal())));
                                            break;
                                    }
                                }
                                break;
                            case SKELETON:
                                entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10);
                                if (entity.getHealth() <= 0)
                                {
                                    switch (world.rand.nextInt(3))
                                    {
                                        case 0:
                                        case 1:
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.equine_gem, 1+world.rand.nextInt(2), EEquineGem.OPAL.ordinal())));
                                            break;
                                        case 2:
                                            world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y - 0.5D, z + 0.5D, new ItemStack(EquineMagicItem.equine_gem, 1+world.rand.nextInt(2), EEquineGem.CHROMA.ordinal())));
                                            break;
                                    }
                                }
                                break;
                            case PLAYER:
                                EntityPlayerMP playerMP = (EntityPlayerMP)entity;
                                EquineMagicPlayer equineMagicPlayer = (EquineMagicPlayer)playerMP.getExtendedProperties(EquineMagicPlayer.NAME);
                                if ( equineMagicPlayer.darkness >= 250)
                                {
                                    entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 5);
                                }
                                break;
                        }
                    }
                }
            }

            EquineMagicPlayer equineMagicPlayer = (EquineMagicPlayer) player.getExtendedProperties(EquineMagicPlayer.NAME);
            equineMagicPlayer.darkness += 50;
            if (equineMagicPlayer.darkness > 500) equineMagicPlayer.darkness = 500;

            MessageExtendedProperties message = new MessageExtendedProperties();
            message.darkness = equineMagicPlayer.darkness;
            EquineMagic.network.sendTo(message, (EntityPlayerMP)player);
        }

        return false;
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public static int renderId;

    @Override
    public int getRenderType(){
        return renderId;
    }

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[2];
        icons[0] = iconRegister.registerIcon(this.getUnwrappedUnlocalizedName());
        icons[1] = iconRegister.registerIcon(this.getUnwrappedUnlocalizedName() + "_side");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        switch (meta)
        {
            case 0:
                return icons[0];
            case 1:
                return icons[1];
        }
        return icons[0];
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileBell();
    }
}
