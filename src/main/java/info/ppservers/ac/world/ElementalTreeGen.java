package info.ppservers.ac.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

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
			int i1;
			byte b0;
			int j1;
			int k1;
			
			for (i1 = par4; i1 <= par4 + 1 + l; ++i1) {
				b0 = 1;
				
				if (i1 == par4) {
					b0 = 0;
				}
				
				if (i1 >= par4 + 1 + l - 2) {
					b0 = 2;
				}
				
				for (int l1 = par3 - b0; l1 <= par3 + b0 && flag; ++l1) {
					for (j1 = par5 - b0; j1 <= par5 + b0 && flag; ++j1) {
						if (i1 >= 0 && i1 < 256) {
							k1 = par1World.getBlockMetadata(l1, i1, j1); // problematic
							
							Block block = Block.
						}
					}
				}
			}
		}
		
		return (Boolean) null;
	}

}
