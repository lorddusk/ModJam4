package info.ppservers.ac.blocks;


import cpw.mods.fml.common.registry.GameRegistry;
import info.ppservers.ac.AlchemicalCombination;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Tim on 5/15/2014.
 */
public class AlchBlock {
    
	
	public static Block AlchLava;

    public static void init(){
        AlchLava = new AlchLava(Material.lava);
    }

    public static void registerBlocks(){
       GameRegistry.registerBlock(AlchLava, Info.FLAVA_NAME).setCreativeTab(AlchemicalCombination.ACTab);
    }

    public static void registerTileEntities(){
        
    }

    private AlchBlock(){};
}
