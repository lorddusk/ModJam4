package info.ppservers.ac;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import info.ppservers.ac.blocks.BlockHandler;
import info.ppservers.ac.blocks.renders.RenderAlchFurnace;
import info.ppservers.ac.client.interfaces.GuiHandler;
import info.ppservers.ac.config.ConfigHandler;
import info.ppservers.ac.items.FuelHandler;
import info.ppservers.ac.items.ItemHandler;
import info.ppservers.ac.proxies.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;

/**
 * Created by Tim on 5/15/2014.
 */

@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION)
public class AlchemicalCombination {

    @Instance(ModInformation.ID)
    public static AlchemicalCombination instance;

    @SidedProxy(clientSide = "info.ppservers.ac.proxies.ClientProxy", serverSide = "info.ppservers.ac.proxies.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs ACTab = new ACTab(CreativeTabs.getNextID(), "Alchemical Combination");

    public static GuiHandler guiHandler = new GuiHandler();

    public static String path;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        path = event.getModConfigurationDirectory().getAbsolutePath() + File.separator + ModInformation.CONFIG_LOC_NAME.toLowerCase() + File.separator;
        ConfigHandler.init(path);

        ItemHandler.init();
        ItemHandler.registerItems();

        BlockHandler.init();
        BlockHandler.registerBlocks();
        BlockHandler.registerTileEntities();

        GameRegistry.registerFuelHandler(new FuelHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        System.out.println("Register GuiHandler");
        instance = this;
        NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
        MinecraftForge.EVENT_BUS.register(this);
        System.out.println("Done");

        BlockHandler.registerRenders();

        ItemHandler.registerRecipes();
        BlockHandler.registerRecipes();

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
