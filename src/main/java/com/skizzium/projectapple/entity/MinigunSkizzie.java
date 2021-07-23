package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.entity.ai.MinigunShootAttackGoal;
import net.minecraft.entity.*;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.RangedAttackMob;

public class MinigunSkizzie extends Skizzie implements RangedAttackMob {
    private final MinigunShootAttackGoal<MinigunSkizzie> shootGoal = new MinigunShootAttackGoal(this, 1.2D, 1, 15.0F);
    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false) {
        public void start() {
            super.start();
            MinigunSkizzie.this.setAggressive(true);
        }

        public void stop() {
            super.stop();
            MinigunSkizzie.this.setAggressive(false);
        }
    };

    public MinigunSkizzie(EntityType<? extends Skizzie> entity, Level world) {
        super(entity, world);
        this.reassessWeaponGoal();
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return 1.40F;
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(difficulty);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawn, @Nullable SpawnGroupData data, @Nullable CompoundTag nbt) {
        data = super.finalizeSpawn(world, difficulty, spawn, data, nbt);
        this.populateDefaultEquipmentSlots(difficulty);
        this.populateDefaultEquipmentEnchantments(difficulty);
        this.reassessWeaponGoal();

        /* if (this.getItemBySlot(EquipmentSlotType.HEAD).isEmpty()) {
            LocalDate localdate = LocalDate.now();
            int i = localdate.get(ChronoField.DAY_OF_MONTH);
            int j = localdate.get(ChronoField.MONTH_OF_YEAR);
            if (j == 10 && i == 31 && this.random.nextFloat() < 0.25F) {
                this.setItemSlot(EquipmentSlotType.HEAD, new ItemStack(this.random.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
                this.armorDropChances[EquipmentSlotType.HEAD.getIndex()] = 0.0F;
            }
        } */

        return data;
    }

    public void reassessWeaponGoal() {
        if (this.level != null && !this.level.isClientSide) {
            this.goalSelector.removeGoal(this.meleeGoal);
            this.goalSelector.removeGoal(this.shootGoal);
            ItemStack item = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, Items.BOW));

            if (item.getItem() == Items.BOW) {
                this.shootGoal.setMinAttackInterval(1);
                this.goalSelector.addGoal(4, this.shootGoal);
            }
            else {
                this.goalSelector.addGoal(4, this.meleeGoal);
            }
        }
    }

    @Override
    public void performRangedAttack(LivingEntity entity, float power) {
        ItemStack item = this.getProjectile(this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, Items.BOW)));
        AbstractArrow arrow = this.getArrow(item, power);

        if (this.getMainHandItem().getItem() instanceof net.minecraft.world.item.BowItem) {
            arrow = ((net.minecraft.world.item.BowItem) this.getMainHandItem().getItem()).customArrow(arrow);
        }

        double d0 = entity.getX() - this.getX();
        double d1 = entity.getY(0.3333333333333333D) - arrow.getY();
        double d2 = entity.getZ() - this.getZ();
        double d3 = Mth.sqrt(d0 * d0 + d2 * d2);

        arrow.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(11 * 4));
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(arrow);
    }

    protected AbstractArrow getArrow(ItemStack item, float damage) {
        return ProjectileUtil.getMobArrow(this, item, damage);
    }

    @Override
    public boolean canFireProjectileWeapon(ProjectileWeaponItem item) {
        return item == Items.BOW;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.reassessWeaponGoal();
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack item) {
        super.setItemSlot(slot, item);
        if (!this.level.isClientSide) {
            this.reassessWeaponGoal();
        }
    }
}
