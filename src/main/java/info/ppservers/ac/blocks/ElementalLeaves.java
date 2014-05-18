package info.ppservers.ac.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class ElementalLeaves extends BlockLeaves {

    int[] adjacentTreeBlocks;

    public ElementalLeaves() {
        super();
        this.setTickRandomly(true);
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setStepSound(soundTypeGrass);
        this.setBlockName(Info.ELEMENTALLEAVES_NAME);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        double d0 = 0.5D;
        double d1 = 1.0D;
        return ColorizerFoliage.getFoliageColor(d0, d1);
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1) {
        return ColorizerFoliage.getFoliageColorBasic();
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess par1, int par2, int par3, int par4) {
        int l = 0;
        int i1 = 0;
        int j1 = 0;

        for (int k1 = -1; k1 <= 1; ++k1) {
            for (int l1 = -1; l1 <= 1; ++l1) {
                int i2 = par1.getBiomeGenForCoords(par2 + l1, par4 + k1).getBiomeFoliageColor(par2 + l1, par3, par4 + k1);
                l += (i2 & 16711680) >> 16;
                i1 += (i2 & 65280) >> 8;
                j1 += i2 & 255;
            }
        }
        return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            int meta = world.getBlockMetadata(x, y, z);
            if ((meta) == 0) {
                boolean nearbyTree = false;
                byte range = 4;
                for (int posX = x - range; posX <= x + range; posX++) {
                    for (int posY = y - range; posY <= y + range; posY++) {
                        for (int posZ = z - range; posZ <= z + range; posZ++) {
                            Block block = world.getBlock(posX, posY, posZ);
                            if (block != null && block.canSustainLeaves(world, posX, posY, posZ)) {
                                nearbyTree = true;
                            }
                        }
                    }
                    if (!nearbyTree) {
                        this.removeLeaves(world, x, y, z);
                    }
                }
            }
        }
    }

    public void removeLeaves(World world, int x, int y, int z) {
        this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        world.setBlock(x, y, z, Blocks.air, 0, 7);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        this.blockIcon = p_149651_1_.registerIcon("alchcom:ElementalLeaves");
    }

    @Override
    public int quantityDropped(Random random) {
        return random.nextInt(20) == 0 ? 1 : 0;
    }

    @Override
    public Item getItemDropped(int par1, Random random, int par2) {
        return Item.getItemFromBlock(BlockHandler.elementalSapling);
    }

    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
        if (!par1World.isRemote) {
            ArrayList<ItemStack> items = getDrops(par1World, par2, par3, par4, par5, par7);

            for (ItemStack item : items) {
                if (par1World.rand.nextFloat() <= par6) {
                    this.dropBlockAsItem(par1World, par2, par3, par4, item);
                }
            }
        }
    }

    @Override
    public IIcon getIcon(int var1, int var2) {
        return blockIcon;
    }

    @Override
    public boolean shouldSideBeRendered (IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.field_150121_P ? super.shouldSideBeRendered(var1, var2, var3, var4, var5) : true;
    }

    @Override
    public int getLightOpacity (IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z) % 4;
        if (meta == 0)
        {
            return 255;
        }
        return super.getLightOpacity(world, x, y, z);//this.getLightOpacity(world, x, y, z);//lightOpacity[blockID];
    }

    @Override
    public String[] func_150125_e() {
        return null;
    }


}

