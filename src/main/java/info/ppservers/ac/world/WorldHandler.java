package info.ppservers.ac.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class WorldHandler implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
	}
	
	public static void registerWorld() {
		IWorldGenerator generator = null;
		int modGenerationWeight = 0;
		
		GameRegistry.registerWorldGenerator(generator, modGenerationWeight);
	}

}
