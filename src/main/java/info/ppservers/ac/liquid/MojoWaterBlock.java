package info.ppservers.ac.liquid;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by Tim on 5/19/2014.
 */
public class MojoWaterBlock extends AlchFluid {
    public MojoWaterBlock(Fluid fluid, Material material, String texture) {
        super(fluid, material, texture);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
        if(entity instanceof EntityLivingBase){
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.heal.id,10,1));
        }
    }
}
