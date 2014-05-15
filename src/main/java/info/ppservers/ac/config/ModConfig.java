package info.ppservers.ac.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Tim on 5/15/2014.
 */
public class ModConfig {

    public static void init(File file){
        Configuration config = new Configuration(file);

        config.load();


        config.save();
    }
}
