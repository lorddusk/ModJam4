package info.ppservers.ac.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import info.ppservers.ac.AlchemicalCombination;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class ElementalSapling extends BlockSapling {
    public ElementalSapling() {
        super();
        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setHardness(0.0F);
        this.setStepSound(Block.soundTypeGrass);
        this.setCreativeTab(AlchemicalCombination.ACTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
        return blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("alchcom:elementalSapling");
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        if (block == null || block.isReplaceable(world, x, y, z)) {
            Block lowerID = world.getBlock(x, y - 1, z);
            //return canThisPlantGrowOnThisBlockID(lowerID);
            if (!canThisPlantGrowOnThisBlock(lowerID)) {
                Block upperID = world.getBlock(x, y + 1, z);
                return canThisPlantGrowOnThisBlock(upperID);
            } else
                return true;
        }
        return false;
    }

    public String getUnlocalizedName(ItemStack itemStack){
        return "elementalSapling";
    }

    public boolean canThisPlantGrowOnThisBlock(Block id) {
        return id == Blocks.grass || id == Blocks.dirt;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z) % 8;
        switch (meta) {
            case 0:
            case 1:
            case 2:
            case 3:
                Block soil = world.getBlock(x, y - 1, z);
                return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && (soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this));
            default:
                return true;
        }
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z) % 8;
        if (meta <= 3)
            return EnumPlantType.Plains;
        else
            return EnumPlantType.Nether;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (world.isRemote) {
            return;
        }
        super.updateTick(world, x, y, z, random);
        int md = world.getBlockMetadata(x, y, z);
        if (md % 8 == 0) {
            if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(120) == 0) {
                if ((md & 8) == 0)
                    world.setBlockMetadataWithNotify(x, y, z, md | 8, 4);

                else {
                    int numSaplings = 0;
                    for (int xPos = -3; xPos <= 3; xPos++) {
                        for (int zPos = -3; zPos <= 3; zPos++) {
                            int ecks = x + xPos, zee = z + zPos;
                            if (world.getBlock(x + xPos, y, z + zPos) == this && world.getBlockMetadata(x + xPos, y, z + zPos) % 8 == 0) {
                                numSaplings++;
                            }
                        }
                    }

                    if (numSaplings >= 40) {
                        for (int xPos = -4; xPos <= 4; xPos++) {
                            for (int zPos = -4; zPos <= 4; zPos++) {
                                int ecks = x + xPos, zee = z + zPos;
                                if (world.getBlock(ecks, y, zee) == this && world.getBlockMetadata(ecks, y, zee) % 8 == 0) {
                                    world.setBlock(ecks, y, zee, Blocks.air, 0, 4);
                                }
                            }
                        }
                        func_149879_c(world, x, y, z, random);
                    }
                }
            }
        } else if (md % 8 <= 3) {
            if (random.nextInt(10) == 0 && world.getBlockLightValue(x, y + 1, z) >= 9)//&& random.nextInt(120) == 0)
            {
                if ((md & 8) == 0)
                    world.setBlockMetadataWithNotify(x, y, z, md | 8, 4);

                else
                    func_149879_c(world, x, y, z, random);
            }
        } else if (random.nextInt(10) == 0) {
            if ((md & 8) == 0)
                world.setBlockMetadataWithNotify(x, y, z, md | 8, 4);

            else
                func_149879_c(world, x, y, z, random);
        }
    }

    public void func_149879_c(World p_149879_1_, int p_149879_2_, int p_149879_3_, int p_149879_4_, Random p_149879_5_) {
        int l = p_149879_1_.getBlockMetadata(p_149879_2_, p_149879_3_, p_149879_4_);

        if ((l & 8) == 0) {
            p_149879_1_.setBlockMetadataWithNotify(p_149879_2_, p_149879_3_, p_149879_4_, l | 8, 4);
        } else {
            this.func_149878_d(p_149879_1_, p_149879_2_, p_149879_3_, p_149879_4_, p_149879_5_);
        }
    }

    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
        return true;
    }

    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
        return (double) p_149852_1_.rand.nextFloat() < 0.45D;
    }

    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
        this.func_149879_c(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_, p_149853_2_);
    }

}
