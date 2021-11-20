package com.skizzium.projectapple.entity.boss.skizzik;

import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStages;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraftforge.entity.PartEntity;

public class SkizzikPart extends PartEntity<Skizzik> {
    public final Skizzik skizzik;
    public final String name;
    public final SkizzikStages<?> despawnStage;
    private final EntityDimensions size;

    public SkizzikPart(Skizzik skizzik, String name, float width, float height) {
        this(skizzik, name, null, width, height);
    }
    
    public SkizzikPart(Skizzik skizzik, String name, SkizzikStages<?> despawnStage, float width, float height) {
        super(skizzik);
        this.skizzik = skizzik;
        this.name = name;
        this.despawnStage = despawnStage;
        this.size = EntityDimensions.scalable(width, height);
        this.refreshDimensions();
    }
    
    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {}

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.despawnStage != null) {
            return this.despawnStage.getId() >= this.skizzik.stageManager.getCurrentStage().getStage().getId() && this.skizzik.hurt(this, source, amount);
        }
        return this.skizzik.hurt(this, source, amount);
    }
    
    @Override
    public boolean is(Entity entity) {
        return this == entity || this.skizzik == entity;
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return this.size;
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }
}
