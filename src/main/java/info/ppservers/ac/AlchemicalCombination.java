package info.ppservers.ac;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import info.ppservers.ac.config.ConfigHandler;
import info.ppservers.ac.proxies.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;

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

    public static String path;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        path = event.getModConfigurationDirectory().getAbsolutePath() + File.separator + ModInformation.CONFIG_LOC_NAME.toLowerCase() + File.separator;
        ConfigHandler.init(path);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }
}
