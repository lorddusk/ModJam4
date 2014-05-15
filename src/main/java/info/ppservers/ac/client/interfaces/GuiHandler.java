package info.ppservers.ac.client.interfaces;

import cpw.mods.fml.common.network.IGuiHandler;
import info.ppservers.ac.blocks.alchfurnace.GuiAlchFurnace;
import info.ppservers.ac.blocks.alchfurnace.ContainerAlchFurnace;
import info.ppservers.ac.blocks.alchfurnace.TileEntityAlchFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Tim on 5/15/2014.
 */
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x,y,z);
        switch (ID) {
            case 0:
                return new ContainerAlchFurnace(player.inventory,(TileEntityAlchFurnace)tileEntity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x,y,z);
        switch (ID) {
            case 0:
                return new GuiAlchFurnace(player.inventory,(TileEntityAlchFurnace)tileEntity);
        }
        return null;
    }
}
