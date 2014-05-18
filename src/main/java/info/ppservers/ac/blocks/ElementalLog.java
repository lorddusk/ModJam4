package info.ppservers.ac.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import info.ppservers.ac.ModInformation;
import info.ppservers.ac.blocks.BlockHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Random;

public class ElementalLog extends BlockLog{
	
	public ElementalLog() {
		super();
        this.setHardness(1.5F);
        this.setResistance(5F);
		this.setStepSound(soundTypeWood);
		this.setBlockName(Info.ELEMENTALLOG_NAME);

		}

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int metadata) {
        return blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		this.blockIcon = p_149651_1_.registerIcon("alchcom:ElementalLog");
	}

    @Override
    public Item getItemDropped (int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(this);
    }

    public int getFlammability (IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
    {
        return metadata % 4 != 2 ? this.getFlammability(world, x, y, z, face) : 0;
    }

    public int getFireSpreadSpeed (World world, int x, int y, int z, int metadata, ForgeDirection face)
    {
        return metadata % 4 != 2 ? this.getFireSpreadSpeed(world, x, y, z, face) : 0;
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z){
        return true;
    }

    @Override
    public boolean isWood (IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public ArrayList<ItemStack> getDrops (World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this));
        return ret;
    }

}

