package info.ppservers.ac.blocks.alchfurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import info.ppservers.ac.AlchemicalCombination;
import info.ppservers.ac.blocks.BlockHandler;
import info.ppservers.ac.blocks.Info;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Tim on 5/15/2014.
 */
public class AlchFurnace extends BlockContainer {

    private final Random random = new Random();
    private final boolean isActive;
    private static boolean keepInventory;

    @SideOnly(Side.CLIENT)
    private IIcon furnaceTop;
    @SideOnly(Side.CLIENT)
    private IIcon furnaceFront;

    public AlchFurnace(boolean par1) {
        super(Material.rock);
        this.isActive = par1;
        this.setStepSound(soundTypePiston);
        this.setHardness(3.5F);
        this.setBlockName(Info.FURNACE_NAME);

    }

    public Item getItemDropped(int par1, Random random, int par3) {
        return Item.getItemFromBlock(BlockHandler.furnace);
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
    }

    private void setDefaultDirection(World world, int x, int y, int z) {
        if (!world.isRemote) {
            Block block = world.getBlock(x, y, z - 1);
            Block block1 = world.getBlock(x, y, z + 1);
            Block block2 = world.getBlock(x - 1, y, z);
            Block block3 = world.getBlock(x + 1, y, z);
            byte b = 3;

            if (block.isOpaqueCube() && !block1.isOpaqueCube()) {
                b = 3;
            }
            if (block1.isOpaqueCube() && !block.isOpaqueCube()) {
                b = 2;
            }
            if (block2.isOpaqueCube() && !block3.isOpaqueCube()) {
                b = 5;
            }
            if (block3.isOpaqueCube() && !block2.isOpaqueCube()) {
                b = 4;
            }
            world.setBlockMetadataWithNotify(x, y, z, b, 2);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        return par1 == 1 ? this.furnaceTop : (par1 == 0 ? this.furnaceTop : (par1 != par2 ? this.blockIcon : this.furnaceFront));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1) {
        this.blockIcon = par1.registerIcon("alch_furnace_side");
        this.furnaceFront = par1.registerIcon(this.isActive ? "alch_furnace_on" : "alch_furnace_off");
        this.furnaceTop = par1.registerIcon("alch_furnace_top");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (world.isRemote) {
            return true;
        } else if (!player.isSneaking()){
            TileEntityAlchFurnace tileEntity = (TileEntityAlchFurnace) world.getTileEntity(x, y, z);
            if (tileEntity != null) {
                player.openGui(AlchemicalCombination.instance, 0, world, x, y, z);
            }
            return true;
        }
        else{
            return false;
        }
    }

    public static void updateFurnaceBlockState(boolean par1, World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        keepInventory = true;

        if (par1) {
            world.setBlock(x, y, z, BlockHandler.furnaceBurning);
        } else {
            world.setBlock(x, y, z, BlockHandler.furnace);
        }

        keepInventory = false;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
        if (tileEntity != null) {
            tileEntity.validate();
            world.setTileEntity(x, y, z, tileEntity);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityAlchFurnace();
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        int l = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if (l == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }
        if (l == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if (l == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
        if (itemStack.hasDisplayName()) {
            ((TileEntityAlchFurnace) world.getTileEntity(x, y, z)).func_145951_a(itemStack.getDisplayName());
        }
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        if (!keepInventory) {
            TileEntityAlchFurnace tileEntityAlchFurnace = (TileEntityAlchFurnace) world.getTileEntity(x, y, z);
            if (tileEntityAlchFurnace != null) {
                for (int i = 0; i < tileEntityAlchFurnace.getSizeInventory(); ++i) {
                    ItemStack itemStack = tileEntityAlchFurnace.getStackInSlot(i);
                    if (itemStack != null) {
                        float f = this.random.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.random.nextFloat() * 0.8F + 0.1F;

                        while (itemStack.stackSize > 0) {
                            int j = this.random.nextInt(21) + 10;
                            if (j > itemStack.stackSize) {
                                j = itemStack.stackSize;
                            }
                            itemStack.stackSize -= j;
                            EntityItem entityItem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2),
                                    new ItemStack(itemStack.getItem(), j, itemStack.getItemDamage()));

                            if (itemStack.hasTagCompound()) {
                                entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                            }
                            float f3 = 0.05F;
                            entityItem.motionX = (double) ((float) this.random.nextGaussian() * f3);
                            entityItem.motionY = (double) ((float) this.random.nextGaussian() * f3 + 0.2F);
                            entityItem.motionZ = (double) ((float) this.random.nextGaussian() * f3);
                            world.spawnEntityInWorld(entityItem);
                        }
                    }
                }
                world.func_147453_f(x, y, z, block);
            }
        }
        super.breakBlock(world, x, y, z, block, par6);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (isActive) {
            int l = world.getBlockMetadata(x, y, z);
            float f = (float) x + 0.5F;
            float f1 = (float) y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
            float f2 = (float) z + 0.5F;
            float f3 = 0.52F;
            float f4 = random.nextFloat() * 0.6F - 0.3F;

            if (l == 4) {
                world.spawnParticle("smoke", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
            } else if (l == 5) {
                world.spawnParticle("smoke", (double) (f + f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f + f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
            } else if (l == 2) {
                world.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 - f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 - f3), 0.0D, 0.0D, 0.0D);
            } else if (l == 3) {
                world.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 + f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 + f3), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public boolean hasComparatorInputOverride() {
        return true;
    }

    public int getComparatorInputOverride(World world, int x, int y, int z, int par5) {
        return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(BlockHandler.furnace);
    }
}
