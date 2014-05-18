package info.ppservers.ac.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import info.ppservers.ac.AlchemicalCombination;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * Created by Tim on 5/16/2014.
 */
public class TrolliumIngot extends Item {

    @SideOnly(Side.CLIENT)
    private IIcon trollIngot;

    public TrolliumIngot(){
        this.setMaxStackSize(64);
        this.setCreativeTab(AlchemicalCombination.ACTab);
        this.setUnlocalizedName("trolliumIngot");
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register){
        trollIngot = register.registerIcon("alchcom:"+Info.TROLLIUM_INGOT_ICON);
    }
}
