package info.ppservers.ac.blocks;

import java.util.Random;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.client.particle.EntityEnchantmentTableParticleFX;
import net.minecraft.client.particle.EntityExplodeFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import info.ppservers.ac.blocks.BlockHandler;

public class ElementalLeaves extends BlockLeaves{
	
	public ElementalLeaves() {
		super();
		this.setStepSound(soundTypeWood);
		this.setBlockName(Info.ELEMENTALLEAVES_NAME);
	}
	
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		this.blockIcon = p_149651_1_.registerIcon("alchcom:ElementalLeaves");
	}

	@Override
	public IIcon getIcon(int var1, int var2) {
		// TODO Auto-generated method stub
		return blockIcon;
	}

	@Override
	public String[] func_150125_e() {
		// TODO Auto-generated method stub
		return null;
	}	
	
	
	
}

