package info.ppservers.ac.world;

import cpw.mods.fml.common.IWorldGenerator;
import info.ppservers.ac.blocks.BlockHandler;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

/**
 * Created by Tim on 5/16/2014.
 */
public class TrolliumGen implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.dimensionId){
            case -1:
                generateNether(world,random,chunkX * 16, chunkZ * 16);
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
            case 1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateEnd(World world, Random random, int x, int z){

    }
    private void generateNether(World world, Random random, int x, int z){

    }
    private void generateSurface(World world, Random random, int x, int z){
        this.addOreSpawn(BlockHandler.trolliumOre, world, random, x, z, 16, 16, 4 + random.nextInt(3), 5, 15, 50);
    }

    public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVein, int spawnChance, int minY, int maxY){
        assert maxY > minY : "Maximum Y must be greater than the minimum Y";
        assert maxX > 0 && maxX <= 16 : "Maximum X must be greater than 0 and less than 16";
        assert minY > 0 : "Minimum Y must be greater than 0";
        assert maxY < 256 && maxY > 0 : "Maximum Y must be less than 256 and greater than 0";
        assert maxZ > 0 && maxZ <= 16 : "Maximum Z must be greater than 0 and less than 16";

        int diffMinMaxY = maxY - minY;
        for(int i = 0;i<spawnChance;i++){
            int posX = blockXPos + random.nextInt(maxX);
            int posY = minY + random.nextInt(diffMinMaxY);
            int posZ = blockZPos + random.nextInt(maxZ);
            (new WorldGenMinable(block, maxVein)).generate(world,random,posX,posY,posZ);
        }
    }
}
