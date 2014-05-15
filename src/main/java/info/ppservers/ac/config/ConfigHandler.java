package info.ppservers.ac.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Tim on 5/15/2014.
 */
public class ConfigHandler {

    public static Configuration configuration;

    public static void init(String configPath){
        ModConfig.init(new File(configPath +"AlchCom.cfg"));
    }
}
