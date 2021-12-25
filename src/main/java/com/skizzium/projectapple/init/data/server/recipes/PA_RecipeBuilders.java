package com.skizzium.projectapple.init.data.server.recipes;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.item.PA_Items;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class PA_RecipeBuilders {
    public static String getItemName(ItemLike item) {
        return Registry.ITEM.getKey(item.asItem()).getPath();
    }

    public static String getHasName(ItemLike item) {
        return "has_" + getItemName(item);
    }

    public static ResourceLocation getFinalName(String name) {
        return new ResourceLocation(ProjectApple.MOD_ID, name);
    }

    public static String getConversionRecipeName(ItemLike p_176518_, ItemLike p_176519_) {
        return getItemName(p_176518_) + "_from_" + getItemName(p_176519_);
    }

    protected static InventoryChangeTrigger.TriggerInstance has(ItemLike item) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(item).build());
    }

    public static InventoryChangeTrigger.TriggerInstance inventoryTrigger(ItemPredicate... predicates) {
        return new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, predicates);
    }

    public static void ribRecipes(Consumer<FinishedRecipe> recipe, ItemLike flesh, ItemLike rib, ItemLike bottomRib, ItemLike bigRib, String group) {
        baseRibRecipe(flesh, rib, group).pattern("BBF").pattern("FBF").pattern("FBF").save(recipe);
        baseRibRecipe(flesh, bottomRib, group).pattern("FFB").pattern("BBB").pattern("FFF").save(recipe);
        baseRibRecipe(flesh, bigRib, group).pattern("BBB").pattern("FBB").pattern("FBB").save(recipe);
    }

    public static ShapedRecipeBuilder baseRibRecipe(ItemLike flesh, ItemLike rib, String group) {
        return ShapedRecipeBuilder.shaped(rib).define('B', PA_Items.SKIZZIK_BONE.get()).define('F', flesh).group(group).unlockedBy(getHasName(flesh), has(flesh));
    }

    public static void fleshBlockRecipes(Consumer<FinishedRecipe> recipe, ItemLike flesh, ItemLike block) {
        ShapelessRecipeBuilder.shapeless(flesh, 9).requires(block).unlockedBy(getHasName(block), has(block)).save(recipe, getFinalName(getItemName(flesh) + "_from_block"));
        ShapedRecipeBuilder.shaped(block).define('F', flesh).define('N', PA_Items.PLATINUM_NUGGET.get()).define('P', ItemTags.PLANKS).pattern("NFN").pattern("FPF").pattern("NFN").unlockedBy(getHasName(flesh), has(flesh)).save(recipe);
    }

    public static void smallHeadWithGemsRecipe(Consumer<FinishedRecipe> recipe, ItemLike head, ItemLike result) {
        ShapelessRecipeBuilder.shapeless(result, 1).requires(head).requires(PA_Items.BROWN_GEM.get()).requires(PA_Items.GREEN_GEM.get(), 2).group("skizzik_head").unlockedBy(getHasName(head), has(head)).save(recipe);
    }

    public static void headWithGemsRecipe(Consumer<FinishedRecipe> recipe, ItemLike head, ItemLike result) {
        ShapelessRecipeBuilder.shapeless(result, 1)
                .requires(head)
                .requires(PA_Items.BLACK_GEM.get())
                .requires(PA_Items.BLUE_GEM.get())
                .requires(PA_Items.YELLOW_GEM.get())
                .requires(PA_Items.ORANGE_GEM.get())
                .requires(PA_Items.GREEN_GEM.get())
                .requires(PA_Items.PINK_GEM.get())
                .group("skizzik_head").unlockedBy(getHasName(head), has(head)).save(recipe);
    }
    
    public static void gemRecipe(Consumer<FinishedRecipe> recipe, ItemLike output) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(PA_Items.RAINBOW_GEM.get()), output, 3).unlockedBy(getHasName(PA_Items.RAINBOW_GEM.get()), has(PA_Items.RAINBOW_GEM.get())).save(recipe);
    }

    public static void gemBlockRecipes(Consumer<FinishedRecipe> recipe, ItemLike item, ItemLike block) {
        nineBlockStorageRecipes(recipe, item, block, getItemName(block), "gem_block", getItemName(item) + "_from_block", "gem");
    }

    public static void nuggetRecipes(Consumer<FinishedRecipe> recipe, ItemLike nugget, ItemLike ingot) {
        nineBlockStorageRecipesWithCustomPacking(recipe, nugget, ingot, getItemName(ingot) + "_from_nuggets", getItemName(ingot));
    }

    public static void ingotSmeltingRecipes(Consumer<FinishedRecipe> recipe, ItemLike ingot, ItemLike ore) {
        ingotSmeltingRecipes(recipe, ingot, ore, 200, 100, 0.7F);
    }
    
    public static void ingotSmeltingRecipes(Consumer<FinishedRecipe> recipe, ItemLike ingot, ItemLike ore, int smeltingTime, int blastingTime, float exp) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ore), ingot, exp, smeltingTime).unlockedBy(getHasName(ore), has(ore)).save(recipe, getFinalName(getItemName(ingot) + "_from_smelting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ore), ingot, exp, blastingTime).unlockedBy(getHasName(ore), has(ore)).save(recipe, getFinalName(getItemName(ingot) + "_from_blasting"));
    }
    
    public static void toolSet(Consumer<FinishedRecipe> recipe, ItemLike material, ItemLike axe, ItemLike hoe, ItemLike pickaxe, ItemLike shovel, ItemLike sword) {
        ShapedRecipeBuilder.shaped(axe).define('#', Items.STICK).define('X', material).pattern("XX").pattern("X#").pattern(" #").unlockedBy(getHasName(material), has(material)).save(recipe);
        ShapedRecipeBuilder.shaped(hoe).define('#', Items.STICK).define('X', material).pattern("XX").pattern(" #").pattern(" #").unlockedBy(getHasName(material), has(material)).save(recipe);
        ShapedRecipeBuilder.shaped(pickaxe).define('#', Items.STICK).define('X', material).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy(getHasName(material), has(material)).save(recipe);
        ShapedRecipeBuilder.shaped(shovel).define('#', Items.STICK).define('X', material).pattern(" X").pattern(" #").pattern(" #").unlockedBy(getHasName(material), has(material)).save(recipe);
        ShapedRecipeBuilder.shaped(sword).define('#', Items.STICK).define('X', material).pattern(" X").pattern(" X").pattern(" #").unlockedBy(getHasName(material), has(material)).save(recipe);
    }

    public static void armorSet(Consumer<FinishedRecipe> recipe, ItemLike material, ItemLike helmet, ItemLike chestplate, ItemLike leggings, ItemLike boots) {
        ShapedRecipeBuilder.shaped(helmet).define('X', material).pattern("XXX").pattern("X X").unlockedBy(getHasName(material), has(material)).save(recipe);
        ShapedRecipeBuilder.shaped(chestplate).define('X', material).pattern("X X").pattern("XXX").pattern("XXX").unlockedBy(getHasName(material), has(material)).save(recipe);
        ShapedRecipeBuilder.shaped(leggings).define('X', material).pattern("XXX").pattern("X X").pattern("X X").unlockedBy(getHasName(material), has(material)).save(recipe);
        ShapedRecipeBuilder.shaped(boots).define('X', material).pattern("X X").pattern("X X").unlockedBy(getHasName(material), has(material)).save(recipe);
    }
    
    public static void stonecutterRecipe(Consumer<FinishedRecipe> recipe, ItemLike input, ItemLike output) {
        stonecutterRecipe(recipe, input, output, 1);
    }

    public static void stonecutterRecipe(Consumer<FinishedRecipe> recipe, ItemLike input, ItemLike output, int count) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), output, count).unlockedBy(getHasName(input), has(input)).save(recipe, getConversionRecipeName(output, input) + "_stonecutting");
    }

    public static void nineBlockStorageRecipes(Consumer<FinishedRecipe> recipe, ItemLike item, ItemLike storage) {
        nineBlockStorageRecipes(recipe, item, storage, getItemName(storage), null, getItemName(item), null);
    }

    public static void nineBlockStorageRecipesWithCustomPacking(Consumer<FinishedRecipe> recipe, ItemLike item, ItemLike storage, String packingName, String packingGroup) {
        nineBlockStorageRecipes(recipe, item, storage, packingName, packingGroup, getItemName(item), null);
    }

    public static void nineBlockStorageRecipesRecipesWithCustomUnpacking(Consumer<FinishedRecipe> recipe, ItemLike item, ItemLike storage, String unpackingName, String unpackingGroup) {
        nineBlockStorageRecipes(recipe, item, storage, getItemName(storage), null, unpackingName, unpackingGroup);
    }

    public static void nineBlockStorageRecipes(Consumer<FinishedRecipe> recipe, ItemLike item, ItemLike storage, String packingName, @Nullable String packingGroup, String unpackingName, @Nullable String unpackingGroup) {
        ShapelessRecipeBuilder.shapeless(item, 9).requires(storage).group("skizzik_" + unpackingGroup).unlockedBy(getHasName(storage), has(storage)).save(recipe, getFinalName(unpackingName));
        ShapedRecipeBuilder.shaped(storage).define('#', item).pattern("###").pattern("###").pattern("###").group("skizzik_" + packingGroup).unlockedBy(getHasName(item), has(item)).save(recipe, getFinalName(packingName));
    }
}
