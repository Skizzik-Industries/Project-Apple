package com.skizzium.projectapple.init.data.server.recipes;

import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.item.PA_Items;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class PA_RecipeProvider extends RecipeProvider {
    public PA_RecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipe) {
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.BLACK_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.BLUE_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.BROWN_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.GREEN_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.ORANGE_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.PINK_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.YELLOW_GEM.get());

        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.BLACK_GEM.get(), PA_Blocks.BLACK_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.BLUE_GEM.get(), PA_Blocks.BLUE_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.BROWN_GEM.get(), PA_Blocks.BROWN_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.GREEN_GEM.get(), PA_Blocks.GREEN_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.ORANGE_GEM.get(), PA_Blocks.ORANGE_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.PINK_GEM.get(), PA_Blocks.PINK_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.YELLOW_GEM.get(), PA_Blocks.YELLOW_GEM_BLOCK.get());
    }
}
