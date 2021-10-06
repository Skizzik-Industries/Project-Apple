package com.skizzium.projectapple.util;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.BiomeDictionary;
import org.apache.commons.lang3.Range;

import java.util.List;

public class SafeSpotConditions {
    private int startPointMinDistance = 10_000;
    private int playerRespawnPointMinDistance = 10_000;
    private int zeroZeroMinDistance = 100_000;
    private Range<Integer> lightLevelRange = Range.between(0, 5);
    private Range<Integer> heightRange = Range.between(0, 64);
    private List<BiomeDictionary.Type> requiredBiomeTypes = List.of();
    private Biome.BiomeCategory biome = null;
    private DimensionType dimension = null;

    // The minimal distance between the safe spot and the original point where the entity started searching for one
    public SafeSpotConditions startPointMinDistance(int distance) {
        this.startPointMinDistance = distance;
        return this;
    }

    // The minimal distance between the safe spot and the nearest player respawn point (Spawn Point, Bed or Respawn Anchor)
    public SafeSpotConditions playerRespawnPointMinDistance(int distance) {
        this.playerRespawnPointMinDistance = distance;
        return this;
    }

    // The minimal distance between the safe spot and X0 Z0 (aka the middle of the world)
    public SafeSpotConditions zeroZeroMinDistance(int distance) {
        this.zeroZeroMinDistance = distance;
        return this;
    }
    
    public SafeSpotConditions lightLevelRange(Range<Integer> range) {
        this.lightLevelRange = range;
        return this;
    }

    public Range<Integer> getHeightRange() {
        return this.heightRange;
    }
    
    public SafeSpotConditions heightRange(Range<Integer> range) {
        this.heightRange = range;
        return this;
    }

    public SafeSpotConditions addBiomeTypeRequirement(BiomeDictionary.Type requirement) {
        this.requiredBiomeTypes.add(requirement);
        return this;
    }
    
    public SafeSpotConditions requiredBiomeTypes(List<BiomeDictionary.Type> list) {
        this.requiredBiomeTypes = list;
        return this;
    }

    public SafeSpotConditions biome(Biome.BiomeCategory biome) {
        this.biome = biome;
        return this;
    }

    public SafeSpotConditions dimension(DimensionType dimension) {
        this.dimension = dimension;
        return this;
    }
    
    public boolean test(Entity entity, Vec3 pos) {
        if (entity == null) {
            return false;
        }
        
        // Player Respawn Point Minimum Distance
        for (Player player : entity.level.players()) {
            if (player instanceof ServerPlayer) {
                if (((ServerPlayer) player).getRespawnDimension() == entity.level.dimension() && ((ServerPlayer) player).getRespawnPosition().distSqr(pos, true) < this.playerRespawnPointMinDistance) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
