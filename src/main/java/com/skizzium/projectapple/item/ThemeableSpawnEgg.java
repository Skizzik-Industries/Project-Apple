package com.skizzium.projectapple.item;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ThemeableSpawnEgg extends PA_SpawnEgg {
    public ThemeableSpawnEgg(RegistryObject<? extends EntityType<?>> entity, int defaultPrimaryColor, int defaultSecondaryColor, Properties itemProperties) {
        super(entity, defaultPrimaryColor, defaultSecondaryColor, itemProperties);
    }

    @Override
    public String getDescriptionId() {
        return ProjectApple.getThemedDescriptionId(super.getDescriptionId());
    }
}
