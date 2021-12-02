package com.skizzium.projectapple.item;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.RegistryObject;

public class ThemeableSpawnEgg extends ForgeSpawnEggItem {
    public ThemeableSpawnEgg(RegistryObject<? extends EntityType<? extends Mob>> entity, int defaultPrimaryColor, int defaultSecondaryColor, Properties itemProperties) {
        super(entity, defaultPrimaryColor, defaultSecondaryColor, itemProperties);
    }

    @Override
    public String getDescriptionId() {
        return ProjectApple.getThemedDescriptionId(super.getDescriptionId());
    }
}
