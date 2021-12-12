package com.skizzium.projectapple.entity.boss.skizzik.util.stage;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzo;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.Skizzie;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStageManager;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.init.network.PA_PacketRegistry;
import com.skizzium.projectapple.network.RPCPacket;
import com.skizzium.projectlib.gui.PL_BossEvent;
import com.skizzium.projectlib.gui.minibar.Minibar;
import com.skizzium.projectlib.gui.minibar.ServerMinibar;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraftforge.network.PacketDistributor;

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
    public PL_BossEvent.PL_BossBarColor barColor() {
        return ProjectApple.holiday == 1 ? PL_BossEvent.PL_BossBarColor.ORANGE : PL_BossEvent.PL_BossBarColor.RED;
    }

    @Override
    public PL_BossEvent.PL_BossBarOverlay barOverlay() {
        return PL_BossEvent.PL_BossBarOverlay.NOTCHED_5;
    }

    @Override
    public String rpcStageDetails() {
        return String.format("Stage %s of 5", skizzik.stageManager.getCurrentStage().getStage().getId());
    }

    @Override
    public String rpcImageKey() {
        return "skizzik";
    }

    @Override
    public String rpcImageText() {
        return "Skizzik";
    }

    @Override
    public String textureLocation() {
        return String.format("%s/%s", skizzik.getTranslationKey().getString().toLowerCase(), skizzik.getTranslationKey().getString().toLowerCase());
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
    public int safeLightningTicks() {
        return 0;
    }
    
    @Override
    public int lightningTicks() {
        return 0;
    }

    @Override
    public int skizzieSpawnTicks() {
        return 60;
    }

    @Override
    public int destroyBlocksTicks() {
        return 35;
    }

    @Override
    public float eyeHeight() {
        return 2.45F;
    }

    @Override
    public float floatY() {
        return 0;
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
                            Skizzo skizzo = (Skizzo) PA_Entities.SKIZZO.get().spawn((ServerLevel) world, null, null, new BlockPos(this.getHeadX(id - 1), this.getHeadY(id - 1), this.getHeadZ(id - 1)), MobSpawnType.MOB_SUMMONED, true, true);
                            skizzo.setXRot(skizzik.getHeadXRot(id - 2));
                            skizzo.setYRot(skizzik.getHeadYRot(id - 2));
                            
                            skizzo.setTarget((LivingEntity) skizzik.level.getEntity(skizzik.getAlternativeTarget(id - 1)));
                            skizzo.setOwner(skizzik);
                            skizzik.setInvul(!skizzik.getStageDebug());
                            skizzik.bossBar.addMinibar(new ServerMinibar(skizzo, new Minibar.MinibarProperties().color(PL_BossEvent.PL_BossBarColor.RED)));
                        }
                    }
                }
            }
        }
    }

    public void addGoals() {
        skizzik.goalSelector.removeAllGoals();
        skizzik.targetSelector.removeAllGoals();

        skizzik.targetSelector.addGoal(1, skizzik.hurtGoal);
        //skizzik.targetSelector.addGoal(2, skizzik.attackFSkizzikGoal);
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
    public double getHeadX(int head) {
        if (head <= 0) {
            return skizzik.getX();
        }
        else {
            float f = (skizzik.yBodyRot + (float)(180 * (head - 1))) * ((float)Math.PI / 180F);
            float f1 = Mth.cos(f);

            return skizzik.getX() + (double)f1;
        }
    }

    @Override
    public double getHeadY(int head) {
        return head == 1 ? skizzik.getY() + 1.7D :
               head == 2 ? skizzik.getY() + 1.822D : 
               head == 3 ? skizzik.getY() + 3.073D :
               head == 4 ? skizzik.getY() + 3.199D :
               skizzik.getY() + 2.01D ;
    }

    @Override
    public double getHeadZ(int head) {
        float f = (skizzik.yBodyRot + (float)(180 * (head - 1))) * ((float)Math.PI / 180F);
        float f1 = Mth.sin(f);
        return head == 1 ? skizzik.getZ() + (double)f1 * 1.187D :
               head == 2 ? skizzik.getZ() + (double)f1 * 1.125D :
               head == 3 ? skizzik.getZ() + (double)f1 * 1.06D :
               head == 4 ? skizzik.getZ() + (double)f1 * 0.812D :
               skizzik.getZ();
    }

    /**
     * Used to change the positions of the different parts. 
     * If calling the super, you need to set your new properties after the super call in order for them to be applied.
     * Otherwise, they'll get reset by defaults.
     */
    @Override
    public void tickParts() {
        float f = skizzik.yBodyRot * ((float)Math.PI / 180F);
        float f1 = Mth.cos(f);
        float f2 = Mth.sin(f);

        skizzik.tickPart(skizzik.topLeftHead, this.getHeadX(4), this.getHeadY(4), this.getHeadZ(4));
        skizzik.tickPart(skizzik.topRightHead, this.getHeadX(3), this.getHeadY(3), this.getHeadZ(3));
        skizzik.tickPart(skizzik.bottomLeftHead, this.getHeadX(2), this.getHeadY(2), this.getHeadZ(2));
        skizzik.tickPart(skizzik.bottomRightHead, this.getHeadX(1), this.getHeadY(1), this.getHeadZ(1));
        skizzik.tickPart(skizzik.centerHead, this.getHeadX(0), this.getHeadY(0), this.getHeadZ(0));
        skizzik.tickPartOffset(skizzik.commandBlockPart, -f2 * 0.63F, 0.87F, f1);
        skizzik.tickPartOffset(skizzik.bodyPart, -0.062F, 0.0F, 0.0F);
    }

    @Override
    public void tick() {
        boolean hasAliveSkizzo = false;
        Level world = skizzik.level;

        if (!world.isClientSide && skizzik.tickCount % 100 == 0) {
            // The RPC needs to be reloaded every 5 seconds due to issues with the begin method, causing the RPC to not update / lack behind
            PA_PacketRegistry.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this.skizzik), new RPCPacket(RPCPacket.RPCAction.RELOAD, this.skizzik.getId()));
        }

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
