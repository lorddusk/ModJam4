package info.ppservers.ac.blocks;


import cpw.mods.fml.common.registry.GameRegistry;
import info.ppservers.ac.AlchemicalCombination;
import info.ppservers.ac.tileentity.TileEntityAlchFurnace;
import net.minecraft.block.Block;

/**
 * Created by Tim on 5/15/2014.
 */
public class AlchBlock {
    public static Block furnace;
    public static Block furnaceBurning;

    public static void init(){
        furnace = new AlchFurnace(false).setCreativeTab(AlchemicalCombination.ACTab);
        furnaceBurning = new AlchFurnace(true).setLightLevel(0.875F);
    }

    public static void registerBlocks(){
        GameRegistry.registerBlock(furnace,Info.FURNACE_NAME);
    }

    public static void registerTileEntities(){
        GameRegistry.registerTileEntity(TileEntityAlchFurnace.class, Info.FURNACE_KEY);
    }

    private AlchBlock(){};
}
