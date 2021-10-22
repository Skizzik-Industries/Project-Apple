package com.skizzium.projectapple.entity.boss.skizzik.util.stage;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzo;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.Skizzie;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStageManager;
import com.skizzium.projectapple.gui.bossevent.PA_BossEvent;
import com.skizzium.projectapple.init.network.PA_PacketRegistry;
import com.skizzium.projectapple.init.PA_SoundEvents;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.network.BossMusicStartPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraftforge.fmllegacy.network.PacketDistributor;

public abstract class AbstractSkizzikStage implements SkizzikStageInterface {
    protected final Skizzik skizzik;
    public boolean hasGoals = false;
    
    public AbstractSkizzikStage(Skizzik skizzik) {
        this.skizzik = skizzik;
    }

    @Override
    public TextComponent displayName() {
        return new TextComponent(String.format("%s - %s %s", skizzik.getDisplayName().getString(), new TranslatableComponent("entity.skizzik.skizzik.stage").getString(), this.getStage().getId()));
    }

    @Override
    public PA_BossEvent.PA_BossBarColor barColor() {
        return ProjectApple.holiday == 1 ? PA_BossEvent.PA_BossBarColor.ORANGE : PA_BossEvent.PA_BossBarColor.RED;
    }

    @Override
    public PA_BossEvent.PA_BossBarOverlay barOverlay() {
        return PA_BossEvent.PA_BossBarOverlay.NOTCHED_5;
    }

    @Override
    public String textureLocation() {
        return String.format("%s/%s", skizzik.getTranslationKey().getString().toLowerCase(), skizzik.getTranslationKey().getString().toLowerCase());
    }

    @Override
    public String modelLocation() {
        String model;
        if (skizzik.isTransitioning()) {
            model = String.format("skizzik/skizzik_stage-%s", skizzik.stageManager.getPreviousStage().getStage().getId());
        }
        else {
            model = String.format("skizzik/skizzik_stage-%s", skizzik.stageManager.getCurrentStage().getStage().getId());
        }
        
        if (model.equals("skizzik/skizzik_stage-0")) {
            model = "skizzik/skizzik_sleeping";
        }
        else if (model.equals("skizzik/skizzik_stage-1")) {
            model = "skizzik/skizzik";
        }
        else if (model.equals("skizzik/skizzik_stage-6")) {
            model = "skizzik/skizzik_finish-him";
        }
        
        return model;
    }

    @Override
    public int armorValue() {
        return 4;
    }

    @Override
    public int maxHealth() {
        return 1020;
    }

    @Override
    public int skullLevel() {
        return 1;
    }

    @Override
    public int skizzieSpawnTicks() {
        return 60;
    }

    @Override
    public int destroyBlocksTick() {
        return 35;
    }

    @Override
    public float eyeHeight() {
        return 2.45F;
    }

    @Override
    public EntityDimensions hitbox() {
        return new EntityDimensions(2.5F, 4.0F, true);
    }

    @Override
    public boolean playMusic() {
        return true;
    }

    @Override
    public boolean attackStatically() {
        return !skizzik.getPreview() && !skizzik.isTransitioning();
    }

    @Override
    public boolean attackDirectly() {
        return !skizzik.getPreview() && !skizzik.isTransitioning() && !skizzik.isInvul();
    }

    @Override
    public void begin(SkizzikStageManager stageManager) {
        int id = this.getStage().getId();
        int previousId = stageManager.getPreviousStage().getStage().getId();
        Level world = skizzik.level;

        skizzik.setHealth(this.maxStageHealth());
        
        skizzik.setTransitionsTicks(this.transitionTime());
        skizzik.setTransitioning(true);
        
        skizzik.setEyeHeight(this.eyeHeight());
        skizzik.killAllSkizzies(world, true);
        
        if (world instanceof ServerLevel) {
            PA_PacketRegistry.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this.skizzik), new BossMusicStartPacket(ProjectApple.holiday == 1 ? PA_SoundEvents.MUSIC_SPOOKZIK_LAZY.get() : PA_SoundEvents.MUSIC_SKIZZIK_LAZY.get()));
        }

        if (!skizzik.getPreview()) {
            if (world instanceof ServerLevel) {
                if (id > 1 && id <= 5) {
                    int difference;

                    if (previousId == 0) {
                        difference = id - previousId - 1;
                    } else if (previousId == 6) {
                        difference = id - previousId + 1;
                    } else {
                        difference = id - previousId;
                    }

                    if (difference > 0) {
                        for (int i = 1; i <= difference; i++) {
                            Skizzo skizzo = (Skizzo) PA_Entities.SKIZZO.get().spawn((ServerLevel) world, null, null, new BlockPos(skizzik.getHeadX(id - 1), skizzik.getHeadY(id - 1), skizzik.getHeadZ(id - 1)), MobSpawnType.MOB_SUMMONED, true, true);
                            //skizzo.setYBodyRot(skizzik.getHeadXRot(id - 2));
                            //skizzo.setYHeadRot(skizzik.getHeadYRot(id - 2));
                            skizzo.setTarget((LivingEntity) skizzik.level.getEntity(skizzik.getAlternativeTarget(id - 1)));
                            skizzo.setOwner(skizzik);
                            skizzik.setInvul(!skizzik.getDebug());
                            //skizzos[i - 1] = skizzo;
                        }
                    }
                    /* else {
                        difference = Math.abs(difference);
                        for (int i = 1; i <= difference; i++) {
                            ((ServerLevel) world).removeEntity(skizzos[i]);
                        }
                    } */
                }
            }
        }
    }

    public void addGoals() {
        skizzik.goalSelector.removeAllGoals();
        skizzik.targetSelector.removeAllGoals();

        skizzik.targetSelector.addGoal(1, skizzik.hurtGoal);
        skizzik.targetSelector.addGoal(2, skizzik.attackFSkizzikGoal);
        skizzik.targetSelector.addGoal(3, skizzik.attackGoal);
        skizzik.goalSelector.addGoal(3, skizzik.rangedAttackGoal);
        skizzik.goalSelector.addGoal(5, skizzik.avoidWaterGoal);
        skizzik.goalSelector.addGoal(6, skizzik.lookGoal);
        skizzik.goalSelector.addGoal(7, skizzik.lookRandomlyGoal);
    }

    public void spawnSkizzie(Skizzie entity, double x, double y, double z, Level world) {
        if (world instanceof ServerLevel) {
            entity.setHoliday(ProjectApple.holiday);
            entity.moveTo(x, y, z);
            entity.finalizeSpawn((ServerLevel) world, world.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            entity.setOwner(skizzik);
            world.addFreshEntity(entity);
        }
    }

    @Override
    public void tick() {
        boolean hasAliveSkizzo = false;
        Level world = skizzik.level;

        if (skizzik.isInvul() && world instanceof ServerLevel) {
            LevelEntityGetter<Entity> entityGetter = ((ServerLevel) world).getEntities();
            Iterable<Entity> entities = entityGetter.getAll();
            for (Entity entity : entities) {
                if (entity instanceof Skizzo) {
                    if (((Skizzo) entity).getOwner() == skizzik) {
                        hasAliveSkizzo = true;
                    }
                }
            }

            if (!hasAliveSkizzo) {
                skizzik.setInvul(false);
            }
        }

        if (skizzik.level instanceof ServerLevel) {
            double originalKnockbackRes = skizzik.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getBaseValue();
            if (skizzik.getPreview() || skizzik.isTransitioning() || skizzik.isInvul() || skizzik.isConverting()) {
                skizzik.getAttributes().getInstance(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
                skizzik.goalSelector.removeAllGoals();
                skizzik.targetSelector.removeAllGoals();
                this.hasGoals = false;
            } 
            else {
                if (!this.hasGoals) {
                    skizzik.getAttributes().getInstance(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(originalKnockbackRes);
                    this.addGoals();
                    this.hasGoals = true;
                }
            }
        }
        
        if (skizzik.getTransitionTicks() > 0) {
            skizzik.setTransitionsTicks(skizzik.getTransitionTicks() - 1);
            if (skizzik.getTransitionTicks() - 1 == 0) {
                this.addGoals();
            }
        }
        else {
            skizzik.setTransitioning(false);
        }
    }

    @Override
    public void end(SkizzikStageManager stageManager) {}
}
