package info.ppservers.ac.world;

import java.util.Random;
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
		
	}

}
