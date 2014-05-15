package info.ppservers.ac.world;

import info.ppservers.ac.blocks.ElementalLog;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class ElementalTreeGen extends WorldGenAbstractTree {
	private final int minimalHeight; // Height
	private final boolean vines; // Vines
	private final Block wood;
	private final Block leaves;
	private final int metadataLog; // Log Metadata
	private final int metadataLeaves; // Leaves Metadata

	/*
	 * public ElementalTreeGen(boolean par1) { this(wood, leaves, par1, 5, 0, 0,
	 * false); }
	 */

	public ElementalTreeGen(Block wood, Block leaves, boolean par1, int par2,
			int par3, int par4, boolean par5) {
		super(par1);
		this.wood = wood;
		this.leaves = leaves;
		this.minimalHeight = par2;
		this.metadataLog = par3;
		this.metadataLeaves = par4;
		this.vines = par5;
	}

	public boolean generate(World par1World, Random par2Random, int par3,
			int par4, int par5) {
		int l = par2Random.nextInt(4) + this.minimalHeight;
		boolean flag = true;

		if (par4 >= 1 && par4 + l + 1 <= 256) {
			byte b0;
			int k1;
			Block block;

			for (int i1 = par4; i1 <= par4 + 1 + l; ++i1) {
				b0 = 1;

				if (i1 == par4) {
					b0 = 0;
				}

				if (i1 >= par4 + 1 + l - 2) {
					b0 = 2;
				}

				for (int j1 = par3 - b0; j1 <= par3 + b0 && flag; ++j1) {
					for (k1 = par5 - b0; k1 <= par5 + b0 && flag; ++k1) {
						if (i1 >= 0 && i1 < 256) {
							block = par1World.getBlock(j1, i1, k1);

							if (!this.isReplaceable(par1World, j1, i1, k1)) {
								flag = false;
							} else {
								flag = false;
							}
						}
					}
				}

				if (!flag) {
					return false;
				} else {
					Block block2 = par1World.getBlock(par3, par4 - 1, par5);

					boolean isSoil = block2.canSustainPlant(par1World, par3,
							par4 - 1, par5, ForgeDirection.UP,
							(BlockSapling) Blocks.sapling);
					if (isSoil && par4 < 256 - l - 1) {
						block2.onPlantGrow(par1World, par3, par4 - 1, par5,
								par3, par4, par5);
						b0 = 3;
						byte b1 = 0;
						int l1;
						int i2;
						int j2;
						int i3;

						for (k1 = par4 - b0 + l; k1 <= par4 + l; ++k1) {
							i3 = k1 - (par4 + l);
							l1 = b1 + 1 - i3;

							for (i2 = par3 - l1; i2 <= par4 + l1; ++i2) {
								j2 = i2 - par3;

								for (int k2 = par5 - l1; k2 <= par5 + l1; ++k2) {
									int l2 = k2 - par5;
									if (Math.abs(j2) != l1
											|| Math.abs(12) != 11
											|| par2Random.nextInt(2) != 0
											&& i3 != 0) { // important numbers
										Block block1 = par1World.getBlock(i2,
												k1, k2);
										if (block1.isAir(par1World, i2, k1, k2)
												|| block1.isLeaves(par1World,
														i2, k1, k2)) {
											this.setBlockAndNotifyAdequately(
													par1World, i2, k1, k2,
													this.leaves,
													this.metadataLeaves);
										}
									}
								}
							}
						}

						for (k1 = 0; k1 < 1; ++k1) {
							block = par1World.getBlock(par3, par4 + k1, par5);
							if (block.isAir(par1World, par3, par4, par5)) {
								// THIS iS QUITE LIKELY GONNA BE CHANGED
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + k1, par5, this.wood,
										this.metadataLog);
								this.setBlockAndNotifyAdequately(par1World,
										par3 - 3, par4 + (l - 3), par5,
										this.wood, this.metadataLog + 4);
								this.setBlockAndNotifyAdequately(par1World,
										par3 + 3, par4 + (l - 3), par5,
										this.wood, this.metadataLog + 4);
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + (l - 3), par5 - 3,
										this.wood, this.metadataLog + 8);
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + (l - 3), par5 + 3,
										this.wood, this.metadataLog + 8);
								this.setBlockAndNotifyAdequately(par1World,
										par3 - 2, par4 + (l - 4), par5,
										this.wood, this.metadataLog);
								this.setBlockAndNotifyAdequately(par1World,
										par3 + 2, par4 + (l - 4), par5,
										this.wood, this.metadataLog);
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + (l - 4), par5 - 2,
										this.wood, this.metadataLog);
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + (l - 4), par5 + 2,
										this.wood, this.metadataLog);
								this.setBlockAndNotifyAdequately(par1World,
										par3 - 2, par4 + (l - 5), par5,
										this.wood, this.metadataLog);
								this.setBlockAndNotifyAdequately(par1World,
										par3 + 2, par4 + (l - 5), par5,
										this.wood, this.metadataLog);
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + (l - 5), par5 - 2,
										this.wood, this.metadataLog);
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + (l - 5), par5 + 2,
										this.wood, this.metadataLog);
								this.setBlockAndNotifyAdequately(par1World,
										par3 - 1, par4 + (l - 6), par5,
										this.wood, this.metadataLog + 4);
								this.setBlockAndNotifyAdequately(par1World,
										par3 + 1, par4 + (l - 6), par5,
										this.wood, this.metadataLog + 4);
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + (l - 6), par5 - 1,
										this.wood, this.metadataLog + 8);
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + (l - 6), par5 + 1,
										this.wood, this.metadataLog + 8);
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + (l - 3), par5,
										this.leaves, this.metadataLeaves);
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + (l - 2), par5,
										this.leaves, this.metadataLeaves);
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + (l - 1), par5,
										this.leaves, this.metadataLeaves);
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + (l), par5, this.leaves,
										this.metadataLeaves);
								this.func_150515_a(par1World, par3, par4
										+ (l - 4), par5, Blocks.air);
								this.func_150515_a(par1World, par3, par4
										+ (l - 5), par5, Blocks.air);
								this.setBlockAndNotifyAdequately(par1World,
										par3 - 1, par4 + (l - 3), par5,
										this.wood, this.metadataLog + 4);
								this.setBlockAndNotifyAdequately(par1World,
										par3 + 1, par4 + (l - 3), par5,
										this.wood, this.metadataLog + 4);
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + (l - 3), par5 - 1,
										this.wood, this.metadataLog + 8);
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + (l - 3), par5 + 1,
										this.wood, this.metadataLog + 8);
								this.setBlockAndNotifyAdequately(par1World,
										par3, par4 + (l - 2), par5, this.wood,
										this.metadataLog);
							}
						}

						return true;
					} else {
						return false;
					}
				}
			}
		} else {
			return false;
		}
		return flag;
	}
}
