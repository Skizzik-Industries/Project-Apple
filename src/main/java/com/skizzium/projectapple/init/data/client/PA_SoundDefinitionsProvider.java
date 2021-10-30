package com.skizzium.projectapple.init.data.client;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.PA_SoundEvents;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class PA_SoundDefinitionsProvider extends SoundDefinitionsProvider {
    public PA_SoundDefinitionsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    private static ResourceLocation modLoc(String name) {
        return new ResourceLocation(ProjectApple.MOD_ID, name);
    }
    
    @Override
    public void registerSounds() {
        this.add(PA_SoundEvents.MUSIC_SKIZZIK.get(), SoundDefinitionsProvider.definition().with(SoundDefinitionsProvider.sound(modLoc("music/skizzik")).stream(true)));
        this.add(PA_SoundEvents.MUSIC_SPOOKZIK.get(), SoundDefinitionsProvider.definition().with(SoundDefinitionsProvider.sound(modLoc("music/spookzik")).stream(true)));

        this.add(PA_SoundEvents.FLESH_EQUIP.get(), SoundDefinitionsProvider.definition().with(SoundDefinitionsProvider.sound(modLoc("entity/skizzik_flesh_equip"))).subtitle("subtitles.entity.skizzik_flesh.equip"));
        this.add(PA_SoundEvents.FINISH_HIM.get(), SoundDefinitionsProvider.definition().with(SoundDefinitionsProvider.sound(modLoc("entity/skizzik_finish_him"))).subtitle("subtitles.entity.skizzik.finish_him"));

        this.add(PA_SoundEvents.DABABY.get(), SoundDefinitionsProvider.definition().with(SoundDefinitionsProvider.sound(modLoc("entity/dababy"))).subtitle("subtitles.entity.dababy"));
    }
}
