package info.ppservers.ac;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import info.ppservers.ac.proxies.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;

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

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){

    }

    @EventHandler
    public void init(FMLInitializationEvent event){

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }
}
