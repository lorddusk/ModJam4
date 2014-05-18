package info.ppservers.ac.world;

import cpw.mods.fml.common.IWorldGenerator;
import info.ppservers.ac.blocks.BlockHandler;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

/**
 * Created by Tim on 5/17/2014.
 */
public class BaseTreeWorldgen implements IWorldGenerator {
    public BaseTreeWorldgen() {
        genElemental = new ElementalTreeGen(false,0,0);
    }

    ElementalTreeGen genElemental;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        int xSpawn, ySpawn, zSpawn;
        int xPos = chunkX * 16 + 8, zPos = chunkZ * 16 + 8;
        String biomeName = world.getWorldChunkManager().getBiomeGenAt(xPos,zPos).biomeName;

        if(biomeName == null){
            return;
        }

        if(biomeName == "Plains" || biomeName == "Meadow"){
            xSpawn = xPos + random.nextInt(16);
            ySpawn = 64;
            zSpawn = zPos + random.nextInt(16);
            genElemental.generate(world,random,xSpawn,ySpawn,zSpawn);
        }
    }
}
