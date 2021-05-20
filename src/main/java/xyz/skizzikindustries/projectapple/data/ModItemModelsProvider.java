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

        withExistingParent("candianite_ore", modLoc("block/candianite_ore"));

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

        skullBuilder("skizzik_head");
        skullBuilder("skizzik_wall_head");

        defaultBuilder(generated, "skizzik_bone");
        defaultBuilder(generated, "raw_skizzik_flesh");
        defaultBuilder(generated, "skizzik_flesh");

        defaultBuilder(generated, "skizzik_flesh_cap");
        defaultBuilder(generated, "skizzik_flesh_tunic");
        defaultBuilder(generated, "skizzik_flesh_pants");
        defaultBuilder(generated, "skizzik_flesh_boots");

        defaultBuilder(generated, "candy_stick");

        defaultBuilder(generated, "candianite_nugget");
        defaultBuilder(generated, "candianite_ingot");

        defaultBuilder(generated, "candianite_sword");
        defaultBuilder(generated, "candianite_pickaxe");
        defaultBuilder(generated, "candianite_axe");
        defaultBuilder(generated, "candianite_shovel");
        defaultBuilder(generated, "candianite_hoe");

        defaultBuilder(generated, "candianite_horse_armor");
        defaultBuilder(generated, "candianite_helmet");
        defaultBuilder(generated, "candianite_chestplate");
        defaultBuilder(generated, "candianite_leggings");
        defaultBuilder(generated, "candianite_boots");

        defaultBuilder(generated, "platinum_nugget");
        defaultBuilder(generated, "platinum_ingot");

        defaultBuilder(generated, "rainbow_sword");

        defaultBuilder(generated, "rainbow_gem");
        defaultBuilder(generated, "black_gem");
        defaultBuilder(generated, "blue_gem");
        defaultBuilder(generated, "brown_gem");
        defaultBuilder(generated, "yellow_gem");
        defaultBuilder(generated, "orange_gem");
        defaultBuilder(generated, "green_gem");
        defaultBuilder(generated, "pink_gem");
    }

    private ItemModelBuilder defaultBuilder(ModelFile generated, String name) {
        return getBuilder(name).parent(generated).texture("layer0", "item/" + name);
    }

    private ItemModelBuilder skullBuilder(String name) {
        return getBuilder(name).texture("parent", "item/template_skull");
    }
}
