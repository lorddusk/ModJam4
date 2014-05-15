package info.ppservers.ac.client.interfaces;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import info.ppservers.ac.tileentity.TileEntityAlchFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tim on 5/15/2014.
 */
public class GuiHandler implements IGuiHandler{

    public static final int GUI_ID_ALCHFURNACE = 0;

    protected final Map<Integer, IGuiHandler> guiHandlers = new HashMap<Integer, IGuiHandler>();

    public void registerGuiHandler(int id,IGuiHandler handler){
        guiHandlers.put(id,handler);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        IGuiHandler handler = guiHandlers.get(ID);
        if(handler != null){
            return handler.getServerGuiElement(ID,player,world,x,y,z);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        IGuiHandler handler = guiHandlers.get(ID);
        if(handler != null){
            return handler.getClientGuiElement(ID,player,world,x,y,z);
        }
        return null;
    }
}
