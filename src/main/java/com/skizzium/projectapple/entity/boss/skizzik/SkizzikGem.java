package com.skizzium.projectapple.entity.boss.skizzik;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class SkizzikGem {
    private final GemType gem;
    private boolean isPlaced;
    
    public SkizzikGem(GemType gem) {
        this.gem = gem;
    }

    public GemType getGemType() {
        return gem;
    }
    
    public boolean isPlaced() {
        return isPlaced;
    }

    public SkizzikGem setPlaced(boolean placed) {
        isPlaced = placed;
        return this;
    }

    public CompoundTag toNbt() {
        CompoundTag tag = new CompoundTag();
        tag.putString("GemType", gem.name());
        tag.putBoolean("IsPlaced", isPlaced);
        return tag;
    }

    public static SkizzikGem fromNbt(CompoundTag nbt) {
        return new SkizzikGem(SkizzikGem.GemType.valueOf(nbt.getString("GemType"))).setPlaced(nbt.getBoolean("IsGemPlaced"));
    }

    public enum GemType {
        BLACK("black"),
        BLUE("blue"),
        GREEN("green"),
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
