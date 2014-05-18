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
public class ElementalStick extends Item {

    @SideOnly(Side.CLIENT)
    private IIcon eStick;

    public ElementalStick(){
        this.setCreativeTab(AlchemicalCombination.ACTab);
        this.setMaxStackSize(64);
    }

    public String getUnlocalizedName(ItemStack itemStack){
        return "elementalStick";
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register){
        eStick = register.registerIcon("alchcom:"+Info.ELEMENTAL_STICK_NAME);
    }
}
