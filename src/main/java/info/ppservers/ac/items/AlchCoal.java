package info.ppservers.ac.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import info.ppservers.ac.AlchemicalCombination;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * Created by Tim on 5/15/2014.
 */
public class AlchCoal extends Item {

    @SideOnly(Side.CLIENT)
    private IIcon coalIcon;

    public AlchCoal() {
        this.setMaxDamage(0);
        this.setCreativeTab(AlchemicalCombination.ACTab);
    }

    public String getUnlocalizedName(ItemStack itemStack){
        return "alchCoal";
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register){
        coalIcon = register.registerIcon(Info.TEXTURE_LOCATION +":"+ Info.COAL_ICON);
    }
}
