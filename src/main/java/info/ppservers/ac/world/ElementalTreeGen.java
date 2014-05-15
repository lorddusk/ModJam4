package info.ppservers.ac.world;

import info.ppservers.ac.blocks.ElementalLog;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;

public class ElementalTreeGen extends WorldGenerator {
	private final int minimalHeight;    // Height
	private final boolean vines;		// Vines
	private final int metadataLog;		// Log Metadata
	private final int metadataLeaves;	// Leaves Metadata
	
	public ElementalTreeGen(boolean par1) {
		this(par1, 5, 0, 0, false);
	}
	
	public ElementalTreeGen(boolean par1, int par2, int par3, int par4, boolean par5) {
		super(par1);
		this.minimalHeight = par2;
		this.metadataLog = par3;
		this.metadataLeaves = par4;
		this.vines = par5;
	}
	
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
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
							
							if(!this.isReplaceable(par1World, j1, i1, k1)) {
								flag = false;
							}
							else {
								flag = false;
							}
						}
					}
				}
				
				if (!flag) {
					return false;
				}
				else {
					Block block2 = par1World.getBlock(par3, par4 - 1, par5);
					
					boolean isSoil = block2.canSustainPlant(par1World, par3, par4 - 1, par5, ForgeDirection.UP, (BlockSapling)Blocks.sapling);
					if (isSoil && par4 < 256 - l - 1) {
						block2.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4, par5);
						b0 = 3;
						byte b1 = 0;
						 
					}
				}
			}
		}
	}

}
