package info.ppservers.ac.liquid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Tim on 5/18/2014.
 */
public class MojoWater extends Block {

    public MojoWater(Material material) {
        super(material);
        float f = 0.0F;
        float f1 = 0.0F;
        this.setBlockBounds(0.0F + f1, 0.0F + f, 0.0F + f1, 1.0F + f1, 1.0F + f, 1.0F + f1);
        this.setTickRandomly(true);
    }

    public static float getLiquidHeightPercent(int par1) {
        if (par1 >= 8) {
            par1 = 0;
        }
        return (float) (par1 + 1) / 9.0F;
    }

    protected int getEffectiveFlowDecay(IBlockAccess iBlockAccess, int x, int y, int z) {
        if (iBlockAccess.getBlock(x, y, z).getMaterial() != this.blockMaterial) {
            return -1;
        } else {
            int l = iBlockAccess.getBlockMetadata(x, y, z);
            if (l >= 8) {
                l = 0;
            }
            return l;
        }
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean canCollideCheck(int par1, boolean par2) {
        return par2 && par1 == 0;
    }

    public boolean isBlockSolid(IBlockAccess blockAccess, int x, int y, int z, int p_149747_5_) {
        Material material = blockAccess.getBlock(x, y, z).getMaterial();
        return material == this.blockMaterial ? false : (p_149747_5_ == 1 ? true : (material == Material.ice ? false : super.isBlockSolid(blockAccess, x, y, z, p_149747_5_)));
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int p_149646_5_) {
        Material material = blockAccess.getBlock(x, y, z).getMaterial();
        return material == this.blockMaterial ? false : (p_149646_5_ == 1 ? true : super.shouldSideBeRendered(blockAccess, x, y, z, p_149646_5_));
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    public int getRenderType() {
        return 4;
    }

    public Item getItemDropped(int i, Random random, int i1) {
        return null;
    }

    public int quantityDropped(Random random) {
        return 0;
    }

    public Vec3 getFlowVector(IBlockAccess blockAccess, int x, int y, int z) {
        Vec3 vec3 = blockAccess.getWorldVec3Pool().getVecFromPool(0.0D, 0.0D, 0.0D);
        int l = this.getEffectiveFlowDecay(blockAccess, x, y, z);

        for (int i1 = 0; i1 < 4; ++i1) {
            int j1 = x;
            int k1 = z;

            if (i1 == 0) {
                j1 = x - 1;
            }

            if (i1 == 1) {
                k1 = z - 1;
            }

            if (i1 == 2) {
                ++j1;
            }

            if (i1 == 3) {
                ++k1;
            }

            int l1 = this.getEffectiveFlowDecay(blockAccess, j1, y, k1);
            int i2;

            if (l1 < 0) {
                if (!blockAccess.getBlock(j1, y, k1).getMaterial().blocksMovement()) {
                    l1 = this.getEffectiveFlowDecay(blockAccess, j1, y - 1, k1);

                    if (l1 >= 0) {
                        i2 = l1 - (l - 8);
                        vec3 = vec3.addVector((double) ((j1 - x) * i2), (double) ((y - y) * i2), (double) ((k1 - z) * i2));
                    }
                }
            } else if (l1 >= 0) {
                i2 = l1 - l;
                vec3 = vec3.addVector((double) ((j1 - x) * i2), (double) ((y - y) * i2), (double) ((k1 - z) * i2));
            }
        }

        if (blockAccess.getBlockMetadata(x, y, z) >= 8) {
            boolean flag = false;

            if (flag || this.isBlockSolid(blockAccess, x, y, z - 1, 2)) {
                flag = true;
            }

            if (flag || this.isBlockSolid(blockAccess, x, y, z + 1, 3)) {
                flag = true;
            }

            if (flag || this.isBlockSolid(blockAccess, x - 1, y, z, 4)) {
                flag = true;
            }

            if (flag || this.isBlockSolid(blockAccess, x + 1, y, z, 5)) {
                flag = true;
            }

            if (flag || this.isBlockSolid(blockAccess, x, y + 1, z - 1, 2)) {
                flag = true;
            }

            if (flag || this.isBlockSolid(blockAccess, x, y + 1, z + 1, 3)) {
                flag = true;
            }

            if (flag || this.isBlockSolid(blockAccess, x - 1, y + 1, z, 4)) {
                flag = true;
            }

            if (flag || this.isBlockSolid(blockAccess, x + 1, y + 1, z, 5)) {
                flag = true;
            }

            if (flag) {
                vec3 = vec3.normalize().addVector(0.0D, -6.0D, 0.0D);
            }
        }

        vec3 = vec3.normalize();
        return vec3;
    }

    /**
     * Can add to the passed in vector for a movement vector to be applied to the entity. Args: x, y, z, entity, vec3d
     */
    public void velocityToAddToEntity(World world, int x, int y, int z, Entity entity, Vec3 vector) {
        Vec3 vec31 = this.getFlowVector(world, x, y, z);
        vector.xCoord += vec31.xCoord;
        vector.yCoord += vec31.yCoord;
        vector.zCoord += vec31.zCoord;
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World world) {
        return this.blockMaterial == Material.water ? 5 : (this.blockMaterial == Material.lava ? (world.provider.hasNoSky ? 10 : 30) : 0);
    }

    @SideOnly(Side.CLIENT)
    public int getMixedBrightnessForBlock(IBlockAccess p_149677_1_, int p_149677_2_, int p_149677_3_, int p_149677_4_) {
        int l = p_149677_1_.getLightBrightnessForSkyBlocks(p_149677_2_, p_149677_3_, p_149677_4_, 0);
        int i1 = p_149677_1_.getLightBrightnessForSkyBlocks(p_149677_2_, p_149677_3_ + 1, p_149677_4_, 0);
        int j1 = l & 255;
        int k1 = i1 & 255;
        int l1 = l >> 16 & 255;
        int i2 = i1 >> 16 & 255;
        return (j1 > k1 ? j1 : k1) | (l1 > i2 ? l1 : i2) << 16;
    }

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return this.blockMaterial == Material.water ? 1 : 0;
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        int l;

        if (this.blockMaterial == Material.water) {
            if (random.nextInt(10) == 0) {
                l = world.getBlockMetadata(x, y, z);

                if (l <= 0 || l >= 8) {
                    world.spawnParticle("suspended", (double) ((float) x + random.nextFloat()), (double) ((float) y + random.nextFloat()), (double) ((float) z + random.nextFloat()), 0.0D, 0.0D, 0.0D);
                }
            }

            for (l = 0; l < 0; ++l) {
                int i1 = random.nextInt(4);
                int j1 = x;
                int k1 = z;

                if (i1 == 0) {
                    j1 = x - 1;
                }

                if (i1 == 1) {
                    ++j1;
                }

                if (i1 == 2) {
                    k1 = z - 1;
                }

                if (i1 == 3) {
                    ++k1;
                }

                if (world.getBlock(j1, y, k1).getMaterial() == Material.air && (world.getBlock(j1, y - 1, k1).getMaterial().blocksMovement() || world.getBlock(j1, y - 1, k1).getMaterial().isLiquid())) {
                    float f = 0.0625F;
                    double d0 = (double) ((float) x + random.nextFloat());
                    double d1 = (double) ((float) y + random.nextFloat());
                    double d2 = (double) ((float) z + random.nextFloat());

                    if (i1 == 0) {
                        d0 = (double) ((float) x - f);
                    }

                    if (i1 == 1) {
                        d0 = (double) ((float) (x + 1) + f);
                    }

                    if (i1 == 2) {
                        d2 = (double) ((float) z - f);
                    }

                    if (i1 == 3) {
                        d2 = (double) ((float) (z + 1) + f);
                    }

                    double d3 = 0.0D;
                    double d4 = 0.0D;

                    if (i1 == 0) {
                        d3 = (double) (-f);
                    }

                    if (i1 == 1) {
                        d3 = (double) f;
                    }

                    if (i1 == 2) {
                        d4 = (double) (-f);
                    }

                    if (i1 == 3) {
                        d4 = (double) f;
                    }

                    world.spawnParticle("splash", d0, d1, d2, d3, 0.0D, d4);
                }
            }
        }

        if (this.blockMaterial == Material.water && random.nextInt(64) == 0) {
            l = world.getBlockMetadata(x, y, z);

            if (l > 0 && l < 8) {
                world.playSound((double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), "liquid.water", random.nextFloat() * 0.25F + 0.75F, random.nextFloat() * 1.0F + 0.5F, false);
            }
        }

        double d5;
        double d6;
        double d7;

        if (random.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && !world.getBlock(x, y - 2, z).getMaterial().blocksMovement()) {
            d5 = (double) ((float) x + random.nextFloat());
            d6 = (double) y - 1.05D;
            d7 = (double) ((float) z + random.nextFloat());

            if (this.blockMaterial == Material.water) {
                world.spawnParticle("dripWater", d5, d6, d7, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
