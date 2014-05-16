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
public class AlchChisel extends Item {

    @SideOnly(Side.CLIENT)
    private IIcon alchChiselIcon;

    public AlchChisel() {
        this.setCreativeTab(AlchemicalCombination.ACTab);
        this.setMaxStackSize(1);
        this.setMaxDamage(7);
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return "alchChisel";
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        alchChiselIcon = register.registerIcon(Info.TEXTURE_LOCATION + ":" + Info.ALCHCHISEL_NAME);
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
        return false;
    }

    @Override
    public boolean getShareTag() {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack copiedStack = itemStack.copy();
        copiedStack.setItemDamage(copiedStack.getItemDamage() + 1);
        copiedStack.stackSize = 1;
        return copiedStack;
    }
}
