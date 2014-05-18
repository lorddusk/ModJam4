package info.ppservers.ac.blocks.alchinfuser;

import info.ppservers.ac.items.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 5/16/2014.
 */
public class InfuserRecipes {

    private static InfuserRecipes alchInfuserRecipe = null;
    private List<InfuserRecipeHandler> infuserRecipes;

    private InfuserRecipes(){
        infuserRecipes = new ArrayList<InfuserRecipeHandler>();
    }

    public static InfuserRecipes getInstance(){
        if(alchInfuserRecipe == null){
            alchInfuserRecipe = new InfuserRecipes();
            alchInfuserRecipe.init();
        }
        return alchInfuserRecipe;
    }

    private void init(){
        //tier 0 > tier 1
        alchInfuserRecipe.addRecipe(new ItemStack(ItemHandler.alchGem,1,1), new ItemStack(Items.redstone), new ItemStack(ItemHandler.alchGem,1,0));
        alchInfuserRecipe.addRecipe(new ItemStack(ItemHandler.alchGem,1,1), new ItemStack(Items.dye,1,4), new ItemStack(ItemHandler.alchGem,1,0));
        //tier 1 > tier 2
        alchInfuserRecipe.addRecipe(new ItemStack(ItemHandler.alchGem,1,2), new ItemStack(Items.quartz), new ItemStack(ItemHandler.alchGem,1,1));
        //tier 2 > tier 3
        alchInfuserRecipe.addRecipe(new ItemStack(ItemHandler.alchGem,1,3), new ItemStack(Items.diamond), new ItemStack(ItemHandler.alchGem,1,2));
        alchInfuserRecipe.addRecipe(new ItemStack(ItemHandler.alchGem,1,3), new ItemStack(Items.emerald), new ItemStack(ItemHandler.alchGem,1,2));
        //tier 1 > water
        alchInfuserRecipe.addRecipe(new ItemStack(ItemHandler.waterGem,1,0), new ItemStack(Items.reeds),new ItemStack(ItemHandler.alchGem,1,1));
    }

    public void addRecipe(ItemStack recipeOutput, ItemStack recipeInputStack, ItemStack recipeInputGem){
        addRecipe(new InfuserRecipeHandler(recipeOutput,recipeInputStack,recipeInputGem));
    }

    public void addRecipe(InfuserRecipeHandler recipe){
        if(!infuserRecipes.contains(recipe)){
            infuserRecipes.add(recipe);
            System.out.println(recipe.toString());
        }
        else
        {
            System.out.println(String.format("Attempted to add Recipe : '%s' but already exists", recipe));
        }
    }

    public ItemStack getResult(ItemStack recipeInputStack, ItemStack recipeInputGem){
        for(InfuserRecipeHandler recipe : infuserRecipes){
            if(recipe.matches(recipeInputStack,recipeInputGem)){
                return recipe.getRecipeOutput();
            }
        }
        return null;
    }

    public InfuserRecipeHandler getRecipe(ItemStack recipeInputStack, ItemStack recipeInputGem){
        for(InfuserRecipeHandler recipe : infuserRecipes){
            if(recipe.matches(recipeInputStack,recipeInputGem)){
                return recipe;
            }
        }
        return null;
    }

    public List<InfuserRecipeHandler> getInfuserRecipes(){
        return infuserRecipes;
    }
}
