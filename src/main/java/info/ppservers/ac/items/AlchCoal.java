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
        this.setUnlocalizedName("alchCoal");
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName();
    }


    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
        return coalIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register){
        coalIcon = register.registerIcon("alchcom:"+ Info.COAL_ICON);
    }
}
