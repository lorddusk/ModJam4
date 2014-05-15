package info.ppservers.ac.blocks;


import cpw.mods.fml.common.registry.GameRegistry;
import info.ppservers.ac.AlchemicalCombination;
import info.ppservers.ac.tileentity.TileEntityAlchFurnace;
import net.minecraft.block.Block;

/**
 * Created by Tim on 5/15/2014.
 */
public class BlockHandler {
    public static Block furnace;
    public static Block furnaceBurning;
    public static Block elementalLog;
    // public static Block elementalLeaves;
    // public static Block elementalPlanks;
    

    public static void init(){
        furnace = new AlchFurnace(false).setCreativeTab(AlchemicalCombination.ACTab);
        furnaceBurning = new AlchFurnace(true).setLightLevel(0.875F);
        elementalLog = new ElementalLog().setCreativeTab(AlchemicalCombination.ACTab);
    }

    public static void registerBlocks(){
        GameRegistry.registerBlock(furnace,Info.FURNACE_NAME);
        GameRegistry.registerBlock(elementalLog, Info.ELEMENTALLOG_NAME);
        // GameRegistry.registerBlock(elementalLeaves, Info.ELEMENTALLEAVES_NAME);
        // GameRegistry.registerBlock(elementalPlanks, Info.ELEMENTALPLANKS_NAME);
    }

    public static void registerTileEntities(){
        GameRegistry.registerTileEntity(TileEntityAlchFurnace.class, Info.FURNACE_KEY);
    }

    private BlockHandler(){};
}
