package info.ppservers.ac.crafting;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import java.lang.Float;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Tim on 5/15/2014.
 */
public class AlchFurnaceRecipes{

    private static final AlchFurnaceRecipes smeltingBase = new AlchFurnaceRecipes();

    private Map smeltingList = new HashMap();
    private Map experienceList = new HashMap();

    public static AlchFurnaceRecipes smelting() {
        return smeltingBase;
    }

    private AlchFurnaceRecipes() {
        this.addBlockSmelting(Blocks.iron_ore, new ItemStack(Items.iron_ingot), 0.7F);
        this.addBlockSmelting(Blocks.gold_ore, new ItemStack(Items.gold_ingot), 1.0F);
        this.addBlockSmelting(Blocks.diamond_ore, new ItemStack(Items.diamond), 1.0F);
        this.addBlockSmelting(Blocks.sand, new ItemStack(Blocks.glass), 0.1F);
        this.addItemSmelting(Items.porkchop, new ItemStack(Items.cooked_porkchop), 0.35F);
        this.addItemSmelting(Items.beef, new ItemStack(Items.cooked_beef), 0.35F);
        this.addItemSmelting(Items.chicken, new ItemStack(Items.cooked_chicken), 0.35F);
        this.addBlockSmelting(Blocks.cobblestone, new ItemStack(Blocks.stone), 0.1F);
        this.addItemSmelting(Items.clay_ball, new ItemStack(Items.brick), 0.3F);
        this.addBlockSmelting(Blocks.clay, new ItemStack(Blocks.hardened_clay), 0.35F);
        this.addBlockSmelting(Blocks.cactus, new ItemStack(Items.dye, 1, 2), 0.2F);
        this.addBlockSmelting(Blocks.log, new ItemStack(Items.coal, 1, 1), 0.15F);
        this.addBlockSmelting(Blocks.log2, new ItemStack(Items.coal, 1, 1), 0.15F);
        this.addBlockSmelting(Blocks.emerald_ore, new ItemStack(Items.emerald), 1.0F);
        this.addItemSmelting(Items.potato, new ItemStack(Items.baked_potato), 0.35F);
        this.addBlockSmelting(Blocks.netherrack, new ItemStack(Items.netherbrick), 0.1F);
        ItemFishFood.FishType[] afishtype = ItemFishFood.FishType.values();
        int i = afishtype.length;

        for (int j = 0; j < i; ++j) {
            ItemFishFood.FishType fishtype = afishtype[j];

            if (fishtype.func_150973_i()) {
                this.addSmelting(new ItemStack(Items.fish, 1, fishtype.func_150976_a()), new ItemStack(Items.cooked_fished, 1, fishtype.func_150976_a()), 0.35F);
            }
        }

        this.addBlockSmelting(Blocks.coal_ore, new ItemStack(Items.coal), 0.1F);
        this.addBlockSmelting(Blocks.redstone_ore, new ItemStack(Items.redstone), 0.7F);
        this.addBlockSmelting(Blocks.lapis_ore, new ItemStack(Items.dye, 1, 4), 0.2F);
        this.addBlockSmelting(Blocks.quartz_ore, new ItemStack(Items.quartz), 0.2F);
    }

    public void addBlockSmelting(Block block, ItemStack itemStack, float xp) {
        this.addItemSmelting(Item.getItemFromBlock(block), itemStack, xp);
    }

    public void addItemSmelting(Item item, ItemStack itemStack, float xp) {
        this.addSmelting(new ItemStack(item, 1, 32767), itemStack, xp);
    }

    public void addSmelting(ItemStack input, ItemStack output, float xp) {
        this.smeltingList.put(input, output);
        this.experienceList.put(output, Float.valueOf(xp));
    }

    public ItemStack getSmeltingResult(ItemStack itemStack) {
        Iterator iterator = this.smeltingList.entrySet().iterator();
        Entry entry;

        do {
            if (!iterator.hasNext()) {
                return null;
            }
            entry = (Entry) iterator.next();
        }
        while (!this.functionA(itemStack, (ItemStack) entry.getKey()));

        return (ItemStack) entry.getValue();
    }

    private boolean functionA(ItemStack input, ItemStack output) {
        return output.getItem() == input.getItem() && (output.getItemDamage() == 32767 || output.getItemDamage() == input.getItemDamage());
    }

    public Map getSmeltingList() {
        return this.smeltingList;
    }

    public float getExperience(ItemStack itemStack) {
        float ret = itemStack.getItem().getSmeltingExperience(itemStack);
        if (ret != -1) {
            return ret;
        }

        Iterator iterator = this.experienceList.entrySet().iterator();
        Entry entry;

        do {
            if (!iterator.hasNext()) {
                return 0.0F;
            }
            entry = (Entry) iterator.next();
        }
        while (!this.functionA(itemStack, (ItemStack) entry.getKey()));

        return ((Float) entry.getValue()).floatValue();
    }
}
