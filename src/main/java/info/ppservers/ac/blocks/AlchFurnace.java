package info.ppservers.ac.blocks;

import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import info.ppservers.ac.tileentity.TileEntityAlchFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import javax.swing.text.html.parser.Entity;
import java.util.Random;

/**
 * Created by Tim on 5/15/2014.
 */
public class AlchFurnace extends BlockContainer {

    private final Random random = new Random();
    private final boolean isActive;
    private static boolean keepInventory;

    @SideOnly(Side.CLIENT)
    private IIcon furnaceTop;
    @SideOnly(Side.CLIENT)
    private IIcon furnaceFront;

    public AlchFurnace(boolean par1) {
        super(Material.rock);
        this.isActive = par1;
        this.setStepSound(soundTypePiston);
        this.setHardness(3.5F);
        this.setBlockName(Info.FURNACE_NAME);

    }

    public Item getItemDropped(int par1, Random random, int par3) {
        return Item.getItemFromBlock(AlchBlock.furnace);
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
    }

    private void setDefaultDirection(World world, int x, int y, int z) {
        if (!world.isRemote) {
            Block block = world.getBlock(x, y, z - 1);
            Block block1 = world.getBlock(x, y, z + 1);
            Block block2 = world.getBlock(x - 1, y, z);
            Block block3 = world.getBlock(x + 1, y, z);
            byte b = 3;

            if (block.isOpaqueCube() && !block1.isOpaqueCube()) {
                b = 3;
            }
            if (block1.isOpaqueCube() && !block.isOpaqueCube()) {
                b = 2;
            }
            if (block2.isOpaqueCube() && !block3.isOpaqueCube()) {
                b = 5;
            }
            if (block3.isOpaqueCube() && !block2.isOpaqueCube()) {
                b = 4;
            }
            world.setBlockMetadataWithNotify(x, y, z, b, 2);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        return par1 == 1 ? this.furnaceTop : (par1 == 0 ? this.furnaceTop : (par1 != par2 ? this.blockIcon : this.furnaceFront));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1) {
        this.blockIcon = par1.registerIcon("alch_furnace_side");
        this.furnaceFront = par1.registerIcon(this.isActive ? "alch_furnace_on" : "alch_furnace_off");
        this.furnaceTop = par1.registerIcon("alch_furnace_top");
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, int par7, int par8, int par9) {
        if (world.isRemote) {
            return true;
        } else {
            TileEntityAlchFurnace tileEntityAlchFurnace = (TileEntityAlchFurnace) world.getTileEntity(x, y, z);
            if (tileEntityAlchFurnace != null) {
                player.func_146101_a(tileEntityAlchFurnace);
            }
            return true;

        }
    }

    public static void updateFurnaceBlockState(boolean par1, World world, int x, int y, int z){
        int l = world.getBlockMetadata(x,y,z);
        TileEntity tileEntity = world.getTileEntity(x,y,z);
        keepInventory = true;

        if(par1){
            world.setBlock(x,y,z,AlchBlock.furnaceBurning);
        }
        else{
            world.setBlock(x,y,z,AlchBlock.furnace);
        }

        keepInventory  = false;
        world.setBlockMetadataWithNotify(x,y,z,l,2);
        if(tileEntity != null){
            tileEntity.validate();
            world.setTileEntity(x,y,z,tileEntity);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return null;
    }
}
