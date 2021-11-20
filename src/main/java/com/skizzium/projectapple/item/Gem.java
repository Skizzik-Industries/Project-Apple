package com.skizzium.projectapple.item;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class Gem extends Item {
    private final GemType type;
    
    public Gem(GemType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    public GemType getType() {
        return type;
    }

    public enum GemType {
        BLACK,
        BLUE,
        GREEN,
        ORANGE,
        PINK,
        YELLOW;
        
        public ResourceLocation getLocation() {
            if (ProjectApple.holiday == 1) {
                return new ResourceLocation(ProjectApple.MOD_ID, String.format("textures/entity/holidays/spooktober/friendly_spookzik/gems/%s_gem.png", this.name().toLowerCase()));
            }
            return new ResourceLocation(ProjectApple.MOD_ID, String.format("textures/entity/friendly_skizzik/gems/%s_gem.png", this.name().toLowerCase()));
        }
    }
}
