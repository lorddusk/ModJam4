package info.ppservers.ac.client.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;


/**
 * Created by Tim on 5/15/2014.
 */
public abstract class ResourceHelper {
    public static ResourceLocation getResource(String name){
        return new ResourceLocation("alchcom", "textures/gui/" +name+".png");
    }

    public static void bindResource(ResourceLocation resourceLocation){
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
    }

    private ResourceHelper(){};
}
