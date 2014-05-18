package info.ppservers.ac.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import info.ppservers.ac.AlchemicalCombination;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import sun.font.CreatedFontTracker;

import java.util.List;

/**
 * Created by Tim on 5/16/2014.
 */
public class AlchGem extends Item {
    public AlchGem() {
        super();
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
        this.setCreativeTab(AlchemicalCombination.ACTab);
        this.setUnlocalizedName("gems");
    }

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        icons = new IIcon[Info.GEM_ICONS.length];

        for (int i = 0; i < icons.length; i++) {
            register.registerIcon("alchcom:" + Info.GEM_ICONS[i]);
        }
    }

    public static final String[] names = Info.GEM_ICONS;

    public String getUnlocalizedName(ItemStack itemStack) {
        int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, Info.GEM_ICONS.length);
        return super.getUnlocalizedName() + "." + names[i];
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int dmg) {
        int j = MathHelper.clamp_int(dmg, 0, Info.GEM_ICONS.length);
        return this.icons[j];
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item par1, CreativeTabs par2, List par3) {
        for (int x = 0; x < Info.GEM_ICONS.length; ++x) {
            par3.add(new ItemStack(this, 1, x));
        }
    }
}
