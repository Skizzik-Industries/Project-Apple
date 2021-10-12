package com.skizzium.projectapple.entity.boss.skizzik;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.resources.ResourceLocation;

public class SkizzikGem {
    private final GemType gem;
    private boolean isAvailable;
    
    public SkizzikGem(GemType gem) {
        this.gem = gem;
    }

    public GemType getGemType() {
        return gem;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public enum GemType {
        BLACK("black"),
        BLUE("blue"),
        BROWN("brown"),
        GREEN("green"),
        LIME_1("lime_1"),
        LIME_2("lime_2"),
        ORANGE("orange"),
        PINK("pink"),
        YELLOW("yellow");
        
        private final String name;
        private final ResourceLocation location;

        GemType(String name) {
            this.name = name;
            this.location = new ResourceLocation(ProjectApple.MOD_ID, String.format("textures/entity/friendly_skizzik/gems/%s_gem.png", name));
        }

        public String getName() {
            return this.name;
        }
        
        public ResourceLocation getLocation() {
            return this.location;
        }

        public static SkizzikGem.GemType byName(String name) {
            SkizzikGem.GemType[] values = SkizzikGem.GemType.values();
            int length = values.length;

            for (GemType gemTypes : values) {
                if (gemTypes.name.equals(name)) {
                    return gemTypes;
                }
            }

            return BLACK;
        }
    }
}
