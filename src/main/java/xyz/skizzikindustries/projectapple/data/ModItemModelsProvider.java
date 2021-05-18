package xyz.skizzikindustries.projectapple.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.skizzikindustries.projectapple.ProjectApple;

public class ModItemModelsProvider extends ItemModelProvider {
    public ModItemModelsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("command_block", modLoc("block/command_block"));
        withExistingParent("deactivated_command_block", modLoc("block/deactivated_command_block"));
        withExistingParent("broken_command_block", modLoc("block/broken_command_block"));

        withExistingParent("skizzik_flesh_block", modLoc("block/skizzik_flesh_block"));

        withExistingParent("candy_leaves", modLoc("block/candy_leaves"));
        withExistingParent("candy_log", modLoc("block/candy_log"));
        withExistingParent("candy_nylium", modLoc("block/candy_nylium"));
        withExistingParent("candyrack", modLoc("block/candyrack"));

        withExistingParent("rainbow_ore", modLoc("block/rainbow_ore"));

        withExistingParent("rainbow_gem_block", modLoc("block/rainbow_gem_block"));
        withExistingParent("black_gem_block", modLoc("block/black_gem_block"));
        withExistingParent("blue_gem_block", modLoc("block/blue_gem_block"));
        withExistingParent("brown_gem_block", modLoc("block/brown_gem_block"));
        withExistingParent("yellow_gem_block", modLoc("block/yellow_gem_block"));
        withExistingParent("orange_gem_block", modLoc("block/orange_gem_block"));
        withExistingParent("green_gem_block", modLoc("block/green_gem_block"));
        withExistingParent("pink_gem_block", modLoc("block/pink_gem_block"));

        ModelFile generated = getExistingFile(mcLoc("item/generated"));

        builder(generated, "skizzik_bone");
        builder(generated, "raw_skizzik_flesh");
        builder(generated, "skizzik_flesh");

        builder(generated, "skizzik_flesh_cap");
        builder(generated, "skizzik_flesh_tunic");
        builder(generated, "skizzik_flesh_pants");
        builder(generated, "skizzik_flesh_boots");

        builder(generated, "candianite_nugget");
        builder(generated, "candianite_ingot");

        builder(generated, "candianite_horse_armor");
        builder(generated, "candianite_helmet");
        builder(generated, "candianite_chestplate");
        builder(generated, "candianite_leggings");
        builder(generated, "candianite_boots");

        builder(generated, "platinum_nugget");
        builder(generated, "platinum_ingot");

        builder(generated, "rainbow_sword");

        builder(generated, "rainbow_gem");
        builder(generated, "black_gem");
        builder(generated, "blue_gem");
        builder(generated, "brown_gem");
        builder(generated, "yellow_gem");
        builder(generated, "orange_gem");
        builder(generated, "green_gem");
        builder(generated, "pink_gem");
    }

    private ItemModelBuilder builder(ModelFile generated, String name) {
        return getBuilder(name).parent(generated).texture("layer0", "item/" + name);
    }
}
