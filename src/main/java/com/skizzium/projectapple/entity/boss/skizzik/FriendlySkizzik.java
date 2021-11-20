package com.skizzium.projectapple.entity.boss.skizzik;

import com.google.common.collect.ImmutableList;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.ai.FriendlySkizzoReattachGoal;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.friendly.FriendlySkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.util.FriendlySkizzikGoalController;
import com.skizzium.projectapple.gui.FriendlySkizzikMenu;
import com.skizzium.projectapple.init.PA_ClientHelper;
import com.skizzium.projectapple.init.PA_GUI;
import com.skizzium.projectapple.init.PA_PacketRegistry;
import com.skizzium.projectapple.init.PA_Tags;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.init.item.PA_Items;
import com.skizzium.projectapple.item.Gem;
import com.skizzium.projectapple.network.FriendlySkizzikOpenScreenPacket;
import com.skizzium.projectlib.entity.BossEntity;
import com.skizzium.projectlib.gui.PL_BossEvent;
import com.skizzium.projectlib.gui.PL_ServerBossEvent;
import com.skizzium.projectlib.gui.minibar.Minibar;
import com.skizzium.projectlib.gui.minibar.ServerMinibar;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundAddMobPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.fmllegacy.network.NetworkDirection;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent;

public class FriendlySkizzik extends Monster implements BossEntity, ContainerListener, RangedAttackMob, IAnimatable {
    private static final EntityDataAccessor<Integer> DATA_ADDED_GEMS = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> DATA_BOTTOM_RIB = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_RIGHT_RIBS = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_LEFT_RIBS = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final List<EntityDataAccessor<Integer>> DATA_RIBS = ImmutableList.of(DATA_RIGHT_RIBS, DATA_LEFT_RIBS);
    private static final EntityDataAccessor<Boolean> DATA_COMMAND_BLOCK = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> DATA_ADDED_HEADS = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_DETACHED_HEADS = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_RIDDEN_HEADS = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    
    private static final EntityDataAccessor<Boolean> DATA_CONVERTED = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.BOOLEAN);
    
    private static final EntityDataAccessor<Integer> DATA_TARGET_A = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_B = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_C = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_D = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_E = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final List<EntityDataAccessor<Integer>> DATA_TARGETS = ImmutableList.of(DATA_TARGET_A, DATA_TARGET_B, DATA_TARGET_C, DATA_TARGET_D, DATA_TARGET_E);

    private EnumSet<Gem.GemType> addedGems = EnumSet.noneOf(Gem.GemType.class);
    
    private EnumSet<FriendlySkizzik.Ribs> rightRibs = EnumSet.noneOf(FriendlySkizzik.Ribs.class);
    private EnumSet<FriendlySkizzik.Ribs> leftRibs = EnumSet.noneOf(FriendlySkizzik.Ribs.class);
    
    private EnumSet<FriendlySkizzik.Heads> addedHeads = EnumSet.noneOf(FriendlySkizzik.Heads.class);
    private EnumSet<FriendlySkizzik.Heads> detachedHeads = EnumSet.noneOf(FriendlySkizzik.Heads.class);
    private EnumSet<FriendlySkizzik.Heads> riddenHeads = EnumSet.noneOf(FriendlySkizzik.Heads.class);
    
    // All the animation-related variables below are getting reset once the animations end
    private boolean commandBlockAnimation = false;
    
    private boolean bottomRibAnimation = false; // If true, the animation for the bottom rib appearing will play
    private FriendlySkizzik.Ribs ribAnimation = null; // The rib to play the animation of
    private FriendlySkizzik.RibSide ribSideAnimation = null; // The side of the rib you want to play the animation of
    
    public Heads renderHeadOnly = null;

    private final float[] xRotHeads = new float[4];
    private final float[] yRotHeads = new float[4];

    private final float[] xRotHeads1 = new float[4];
    private final float[] yRotHeads1 = new float[4];

    private float skullCooldown = 0.5F;
    private boolean canDetach = true;
    
    private final int[] nextHeadUpdate = new int[4];
    
    private final AnimationFactory factory = new AnimationFactory(this);
    private final FriendlySkizzikGoalController goalController;

    private final FriendlySkizzikPart[] parts;
    public final FriendlySkizzikHeadPart topLeftHead;
    public final FriendlySkizzikHeadPart topRightHead;
    public final FriendlySkizzikHeadPart bottomLeftHead;
    public final FriendlySkizzikHeadPart bottomRightHead;
    public final FriendlySkizzikPart centerHead;
    public final FriendlySkizzikPart commandBlockPart;
    public final FriendlySkizzikPart bodyPart;
    
    private static final TargetingConditions TARGETING_CONDITIONS = TargetingConditions.forCombat().range(20.0D).selector(PA_Entities.FRIENDLY_SKIZZIK_SELECTOR);
    public final PL_ServerBossEvent bossBar = new PL_ServerBossEvent(this, this.getDisplayName(), new PL_BossEvent.BossEventProperties().updateProgressAutomatically(true).color(PL_BossEvent.PL_BossBarColor.AQUA));
    
    private SimpleContainer inventory;
    private LazyOptional<?> itemHandler = null;
    
    public FriendlySkizzik(EntityType<? extends FriendlySkizzik> entity, Level world) {
        super(entity, world);
        this.setHealth(this.getMaxHealth());
        this.getNavigation().setCanFloat(true);
        this.xpReward = 0;
        this.goalController = new FriendlySkizzikGoalController(this);

        this.topLeftHead = new FriendlySkizzikHeadPart(this, "topLeftHead", Heads.TOP_LEFT_HEAD, 0.75F, 0.75F);
        this.topRightHead = new FriendlySkizzikHeadPart(this, "topRightHead", Heads.TOP_RIGHT_HEAD, 0.75F, 0.75F);
        this.bottomLeftHead = new FriendlySkizzikHeadPart(this, "bottomLeftHead", Heads.BOTTOM_LEFT_HEAD, 0.75F, 0.75F);
        this.bottomRightHead = new FriendlySkizzikHeadPart(this, "bottomRightHead", Heads.BOTTOM_RIGHT_HEAD, 0.75F, 0.75F);
        this.centerHead = new FriendlySkizzikPart(this, "centerHead", 1.0F, 1.0F);
        this.commandBlockPart = new FriendlySkizzikPart(this, "commandBlock", 0.93F, 0.93F);
        this.bodyPart = new FriendlySkizzikPart(this, "body", 0.43F, 1.865F);
        this.parts = new FriendlySkizzikPart[]{this.topLeftHead, this.topRightHead, this.bottomLeftHead, this.bottomRightHead, this.centerHead, this.commandBlockPart, this.bodyPart};

        this.createInventory();
    }

    @Override
    protected Component getTypeName() {
        return new TranslatableComponent(ProjectApple.getThemedDescriptionId(super.getType().getDescriptionId()));
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    protected boolean canRide(Entity entity) {
        return false;
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return 2.45F;
    }

    @Override
    public void startSeenByPlayer(ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
    }

    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        this.bossBar.setName(new TextComponent(this.getDisplayName().getString()));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WITHER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.WITHER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WITHER_DEATH;
    }

//    @Override
//    public EntityDimensions getDimensions(Pose pose) {
//        return this.stageManager.getCurrentStage().hitbox();
//    }

    @Override
    public boolean isPersistenceRequired() {
        return true;
    }

    @Override
    public boolean causeFallDamage(float f0, float f1, DamageSource source) {
        return false;
    }

    @Override
    public boolean addEffect(MobEffectInstance effect, @Nullable Entity entity) {
        return false;
    }

    @Override
    public void makeStuckInBlock(BlockState state, Vec3 vector) {}

    public static boolean canDestroy(BlockState state) {
        return !state.isAir() && !state.is(BlockTags.WITHER_IMMUNE); //BlockTags.WITHER_IMMUNE.contains(state.getBlock());
    }

    public static AttributeSupplier.Builder buildAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1000.0D)
                .add(Attributes.ARMOR, 0.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(Attributes.FLYING_SPEED, 0.6D);
    }

    public boolean isBottomRibPlaced() {
        return this.entityData.get(DATA_BOTTOM_RIB);
    }

    public void setBottomRibPlaced(boolean flag) {
        this.entityData.set(DATA_BOTTOM_RIB, flag);
    }
    
    public boolean isCommandBlockPlaced() {
        return this.entityData.get(DATA_COMMAND_BLOCK);
    }

    public void setCommandBlockPlaced(boolean flag) {
        this.entityData.set(DATA_COMMAND_BLOCK, flag);
    }
    
    public boolean isConverted() {
        return this.entityData.get(DATA_CONVERTED);
    }

    public void setConverted(boolean flag) {
        this.entityData.set(DATA_CONVERTED, flag);
    }
    
    public int getAlternativeTarget(int head) {
        return this.entityData.get(DATA_TARGETS.get(head));
    }

    public void setAlternativeTarget(int head, int target) {
        this.entityData.set(DATA_TARGETS.get(head), target);
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public PartEntity<?>[] getParts() {
        return this.parts;
    }

    @Override
    public void recreateFromPacket(ClientboundAddMobPacket packet) {
        super.recreateFromPacket(packet);
        PartEntity<?>[] parts = this.getParts();

        for(int i = 0; i < parts.length; ++i) {
            parts[i].setId(i + 1 + packet.getId());
        }
    }
    
    @Override
    public boolean canBeAffected(MobEffectInstance effect) {
        return false;
    }

    @Override
    protected void doPush(Entity entity) {
        if (this.isPushable() && !(entity instanceof FriendlySkizzo)) {
            super.doPush(entity);
        }
    }

    @Override
    public boolean isPushable() {
        return super.isPushable();
    }
    
    private <E extends IAnimatable> PlayState ambient(AnimationEvent<E> event) {
        if (renderHeadOnly == null) {
            if (this.isConverted()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.friendly_skizzik.end_convert"));
                if (event.getController().getAnimationState() == AnimationState.Stopped) {
                    this.setConverted(false);
                }
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.friendly_skizzik.body_movement"));
            return PlayState.CONTINUE;
        }
        else {
            return PlayState.STOP;
        }
    }

    private <E extends IAnimatable> PlayState commandBlock(AnimationEvent<E> event) {
        if (this.commandBlockAnimation) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.friendly_skizzik.add_command_block"));
            
            if (event.getController().getAnimationState() == AnimationState.Stopped) {
                this.commandBlockAnimation = false;
            }
            
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState ribAnimation(AnimationEvent<E> event) {
        if (event.getController().getName().equals("bottom_rib") && this.bottomRibAnimation) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.friendly_skizzik.add_bottom_rib"));

            if (event.getController().getAnimationState() == AnimationState.Stopped) {
                this.bottomRibAnimation = false;
            }
        }

        if (this.ribAnimation != null && this.ribSideAnimation != null) {
            if (event.getController().getName().equals(String.format("%s_%s_rib", ribAnimation.name().toLowerCase(), ribSideAnimation.name().toLowerCase()))) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation(String.format("animation.friendly_skizzik.add_%s_%s_rib", this.ribAnimation.name().toLowerCase(), this.ribSideAnimation.name().toLowerCase())));

                if (event.getController().getAnimationState() == AnimationState.Stopped) {
                    this.ribAnimation = null;
                    this.ribSideAnimation = null;
                }
            }
        }
        
        return PlayState.CONTINUE;
    }
    
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "ambient", 0, this::ambient));
        data.addAnimationController(new AnimationController(this, "command_block", 0, this::commandBlock));
        data.addAnimationController(new AnimationController(this, "bottom_rib", 0, this::ribAnimation));
        for (RibSide side : RibSide.values()) {
            for (Ribs rib : Ribs.values()) {
                data.addAnimationController(new AnimationController(this, String.format("%s_%s_rib", rib.name().toLowerCase(), side.name().toLowerCase()), 0, this::ribAnimation));
            }
        }
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected int getInventorySize() {
        return 2;
    }

    public boolean hasInventoryChanged(Container container) {
        return this.inventory != container;
    }

    public void openInventory(Player player) {
        if (!this.level.isClientSide && (!this.isVehicle() || this.hasPassenger(player))) {
            ((ServerPlayer) player).nextContainerCounter();
            PA_PacketRegistry.INSTANCE.sendTo(new FriendlySkizzikOpenScreenPacket(((ServerPlayer) player).containerCounter, this.inventory.getContainerSize(), this.getId()), ((ServerPlayer) player).connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
            
            player.containerMenu = new FriendlySkizzikMenu(((ServerPlayer) player).containerCounter, player.getInventory(), this.inventory, this);
            ((ServerPlayer) player).initMenu(player.containerMenu);
            MinecraftForge.EVENT_BUS.post(new PlayerContainerEvent.Open(player, player.containerMenu));
        }
    }
    
    private SlotAccess createEquipmentSlotAccess(final int slot, final Predicate<ItemStack> predicate) {
        return new SlotAccess() {
            public ItemStack get() {
                return FriendlySkizzik.this.inventory.getItem(slot);
            }

            public boolean set(ItemStack itemstack) {
                if (!predicate.test(itemstack)) {
                    return false;
                } else {
                    FriendlySkizzik.this.inventory.setItem(slot, itemstack);
                    FriendlySkizzik.this.updateContainerEquipment();
                    return true;
                }
            }
        };
    }

    public SlotAccess getSlot(int slot) {
        int i = slot - 400;
        if (i >= 0 && i < 2 && i < this.inventory.getContainerSize()) {
            if (i == 0) {
                return this.createEquipmentSlotAccess(i, (predicate) -> predicate.isEmpty() || predicate.is(Items.SADDLE));
            }

//            if (i == 1) {
//                if (!this.canWearArmor()) {
//                    return SlotAccess.NULL;
//                }
//
//                return this.createEquipmentSlotAccess(i, (predicate) -> predicate.isEmpty() || this.isArmor(predicate));
//            }
        }

        int j = slot - 500 + 2;
        return j >= 2 && j < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, j) : super.getSlot(slot);
    }
    
    protected void createInventory() {
        SimpleContainer oldContainer = this.inventory;
        this.inventory = new SimpleContainer(this.getInventorySize());
        if (oldContainer != null) {
            oldContainer.removeListener(this);
            int i = Math.min(oldContainer.getContainerSize(), this.inventory.getContainerSize());

            for(int j = 0; j < i; ++j) {
                ItemStack itemstack = oldContainer.getItem(j);
                if (!itemstack.isEmpty()) {
                    this.inventory.setItem(j, itemstack.copy());
                }
            }
        }

        this.inventory.addListener(this);
        this.updateContainerEquipment();
        this.itemHandler = LazyOptional.of(() -> new InvWrapper(this.inventory));
    }

    protected void updateContainerEquipment() {
//        if (!this.level.isClientSide) {
//            this.setFlag(4, !this.inventory.getItem(0).isEmpty());
//        }
    }

    @Override
    public void containerChanged(Container container) {
//        boolean flag = this.isSaddled();
        this.updateContainerEquipment();
//        if (this.tickCount > 20 && !flag && this.isSaddled()) {
//            this.playSound(SoundEvents.HORSE_SADDLE, 0.5F, 1.0F);
//        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (this.isAlive() && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && itemHandler != null)
            return itemHandler.cast();
        return super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        if (itemHandler != null) {
            LazyOptional<?> oldHandler = itemHandler;
            itemHandler = null;
            oldHandler.invalidate();
        }
    }

    public float getHeadXRot(int head) {
        return this.xRotHeads[head];
    }
    
    public float getHeadYRot(int head) {
        return this.yRotHeads[head];
    }

    public void setHeadXRot(Heads head, float value) {
        this.xRotHeads[head.ordinal()] = value;
    }

    public void setHeadYRot(Heads head, float value) {
        this.yRotHeads[head.ordinal()] = value;
    }

    public double getHeadX(int head) {
        if (head <= 0) {
            return this.getX();
        }
        else {
            float f = (this.yBodyRot + (float)(180 * (head - 1))) * ((float)Math.PI / 180F);
            float f1 = Mth.cos(f);

            if (head == 3 && this.getAddedHeads().size() - this.getDetachedHeads().size() == 3) {
                return this.getX() - f1 * -0.063F;
            }
            
            return this.getX() + (double)f1;
        }
    }

    public double getHeadY(int head) {
        int addedHeads = this.getAddedHeads().size() - this.getDetachedHeads().size();

        if (head == 3 && addedHeads == 3) {
            return this.getY() + 3.198D;
        }
        else if (head == 1 && addedHeads == 2) {
            return this.getY() + 1.948D;
        }
        else if (head == 1 && addedHeads == 1) {
            return this.getY() + 3.135D;
        }
        
        return head == 1 ? this.getY() + 1.7D : 
               head == 2 ? this.getY() + 1.822D :
               head == 3 ? this.getY() + 3.073D :
               head == 4 ? this.getY() + 3.199D :
               this.getY() + 2.01D ;
    }

    public double getHeadZ(int head) {
        float f = (this.yBodyRot + (float)(180 * (head - 1))) * ((float)Math.PI / 180F);
        float f1 = Mth.sin(f);
        int addedHeads = this.getAddedHeads().size() - this.getDetachedHeads().size();

        if (head == 3 && addedHeads == 3) {
            return this.getZ() + f1 * -0.25D;
        }
        else if (head == 1 && addedHeads == 1) {
            return this.getZ() + f1 * 0.688D;
        }
        
        return head == 1 ? this.getZ() + (double)f1 * 1.187D :
               head == 2 ? this.getZ() + (double)f1 * 1.125D :
               head == 3 ? this.getZ() + (double)f1 * 1.06D :
               head == 4 ? this.getZ() + (double)f1 * 0.812D :
               this.getZ();
    }

    private float rotlerp(float f1, float f2, float f3) {
        float f = Mth.wrapDegrees(f2 - f1);
        if (f > f3) {
            f = f3;
        }

        if (f < -f3) {
            f = -f3;
        }

        return f1 + f;
    }

    public void addGem(Gem.GemType gem) {
        this.entityData.set(DATA_ADDED_GEMS, this.entityData.get(DATA_ADDED_GEMS) | 1 << gem.ordinal());
    }

    public Set<Gem.GemType> getGems() {
        return this.addedGems;
    }

    private void updateAddedGems() {
        var set = EnumSet.noneOf(Gem.GemType.class);
        int addedGems = this.entityData.get(DATA_ADDED_GEMS);
        for (Gem.GemType gem : Gem.GemType.values()) {
            if ((addedGems & (1 << gem.ordinal())) != 0) {
                set.add(gem);
            }
        }
        this.addedGems = set;
    }

    public void addRib(FriendlySkizzik.RibSide side, FriendlySkizzik.Ribs rib) {
        this.entityData.set(DATA_RIBS.get(side.ordinal()), this.entityData.get(DATA_RIBS.get(side.ordinal())) | 1 << rib.ordinal());
        this.bottomRibAnimation = false;
        this.ribAnimation = rib;
        this.ribSideAnimation = side;
    }

    public Set<FriendlySkizzik.Ribs> getRibs(FriendlySkizzik.RibSide side) {
        if (side == RibSide.LEFT) {
            return this.leftRibs;
        }
        return this.rightRibs;
    }

    private void updateRightRibs() {
        var set = EnumSet.noneOf(FriendlySkizzik.Ribs.class);
        int ribs = this.entityData.get(DATA_RIGHT_RIBS);
        for (FriendlySkizzik.Ribs rib : FriendlySkizzik.Ribs.values()) {
            if ((ribs & (1 << rib.ordinal())) != 0) {
                set.add(rib);
            }
        }
        this.rightRibs = set;
    }

    private void updateLeftRibs() {
        var set = EnumSet.noneOf(FriendlySkizzik.Ribs.class);
        int ribs = this.entityData.get(DATA_LEFT_RIBS);
        for (FriendlySkizzik.Ribs rib : FriendlySkizzik.Ribs.values()) {
            if ((ribs & (1 << rib.ordinal())) != 0) {
                set.add(rib);
            }
        }
        this.leftRibs = set;
    }
    
    public void addHead(FriendlySkizzik.Heads head) {
        this.entityData.set(DATA_ADDED_HEADS, this.entityData.get(DATA_ADDED_HEADS) | 1 << head.ordinal());
    }

    public void removeHead(FriendlySkizzik.Heads head) {
        if (this.addedHeads.contains(head)) {
            for (ServerMinibar bar : this.bossBar.getMinibars()) {
                if (bar.getEntity() instanceof FriendlySkizzikHeadPart && ((FriendlySkizzikHeadPart) bar.getEntity()).headType == head) {
                    this.bossBar.removeMinibar(bar);
                }
            }
            
            this.entityData.set(DATA_ADDED_HEADS, this.entityData.get(DATA_ADDED_HEADS) & ~(1 << head.ordinal()));
        }
    }

    public Set<FriendlySkizzik.Heads> getAddedHeads() {
        return this.addedHeads;
    }

    private void updateAddedHeads() {
        var set = EnumSet.noneOf(FriendlySkizzik.Heads.class);
        int addedHeads = this.entityData.get(DATA_ADDED_HEADS);
        for (FriendlySkizzik.Heads head : FriendlySkizzik.Heads.values()) {
            if ((addedHeads & (1 << head.ordinal())) != 0) {
                set.add(head);
            }
        }
        this.addedHeads = set;
    }

    public void reattachHead(FriendlySkizzik.Heads head) {
        if (this.detachedHeads.contains(head)) {
            this.entityData.set(DATA_DETACHED_HEADS, this.entityData.get(DATA_DETACHED_HEADS) & ~(1 << head.ordinal()));
            
            for (ServerMinibar minibar : this.bossBar.getMinibars()) {
                if (minibar.getEntity() != this.getHeadFromEnum(head)) {
                    minibar.setEntity(this.getHeadFromEnum(head));
                }
            }
        }
    }

    public void detachHead(FriendlySkizzik.Heads head) {
        this.entityData.set(DATA_DETACHED_HEADS, this.entityData.get(DATA_DETACHED_HEADS) | 1 << head.ordinal());
    }

    public Set<FriendlySkizzik.Heads> getDetachedHeads() {
        return this.detachedHeads;
    }

    private void updateDetachedHeads() {
        var set = EnumSet.noneOf(FriendlySkizzik.Heads.class);
        int detachedHeads = this.entityData.get(DATA_DETACHED_HEADS);
        for (FriendlySkizzik.Heads head : FriendlySkizzik.Heads.values()) {
            if ((detachedHeads & (1 << head.ordinal())) != 0) {
                set.add(head);
            }
        }
        this.detachedHeads = set;
    }

    public void stopRidingHead(FriendlySkizzik.Heads head) {
        if (this.riddenHeads.contains(head))
            this.entityData.set(DATA_RIDDEN_HEADS, this.entityData.get(DATA_RIDDEN_HEADS) & ~(1 << head.ordinal()));
    }

    public void rideHead(FriendlySkizzik.Heads head) {
        this.entityData.set(DATA_RIDDEN_HEADS, this.entityData.get(DATA_RIDDEN_HEADS) | 1 << head.ordinal());
    }

    public Set<FriendlySkizzik.Heads> getRiddenHeads() {
        return this.riddenHeads;
    }

    private void updateRiddenHeads() {
        var set = EnumSet.noneOf(FriendlySkizzik.Heads.class);
        int riddenHeads = this.entityData.get(DATA_RIDDEN_HEADS);
        for (FriendlySkizzik.Heads head : FriendlySkizzik.Heads.values()) {
            if ((riddenHeads & (1 << head.ordinal())) != 0) {
                set.add(head);
            }
        }
        this.riddenHeads = set;
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (key.equals(DATA_ADDED_GEMS)) {
            this.updateAddedGems();
        }
        
        if (key.equals(DATA_RIGHT_RIBS)) {
            this.updateRightRibs();
        }
        if (key.equals(DATA_LEFT_RIBS)) {
            this.updateLeftRibs();
        }
        
        if (key.equals(DATA_ADDED_HEADS)) {
            this.updateAddedHeads();
        }
        if (key.equals(DATA_DETACHED_HEADS)) {
            this.updateDetachedHeads();
        }
        if (key.equals(DATA_RIDDEN_HEADS)) {
            this.updateRiddenHeads();
        }
    }
    
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(DATA_ADDED_GEMS, 0);

        this.entityData.define(DATA_BOTTOM_RIB, false);
        this.entityData.define(DATA_RIGHT_RIBS, 0);
        this.entityData.define(DATA_LEFT_RIBS, 0);
        this.entityData.define(DATA_COMMAND_BLOCK, false);
        
        this.entityData.define(DATA_ADDED_HEADS, 0);
        this.entityData.define(DATA_DETACHED_HEADS, 0);
        this.entityData.define(DATA_RIDDEN_HEADS, 0);

        this.entityData.define(DATA_CONVERTED, false);
        
        this.entityData.define(DATA_TARGET_A, 0);
        this.entityData.define(DATA_TARGET_B, 0);
        this.entityData.define(DATA_TARGET_C, 0);
        this.entityData.define(DATA_TARGET_D, 0);
        this.entityData.define(DATA_TARGET_E, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);

        ListTag gemNBTList = new ListTag();
        
        ListTag rightRibNBTList = new ListTag();
        ListTag leftRibNBTList = new ListTag();
        
        ListTag headNBTList = new ListTag();
        ListTag detachedNBTList = new ListTag();
        ListTag riddenNBTList = new ListTag();
        
        for (Gem.GemType gem : this.getGems()) {
            gemNBTList.add(StringTag.valueOf(gem.name().toLowerCase()));
        }
        
        for (FriendlySkizzik.Ribs rib : this.getRibs(FriendlySkizzik.RibSide.RIGHT)) {
            rightRibNBTList.add(StringTag.valueOf(rib.name().toLowerCase()));
        }
        for (FriendlySkizzik.Ribs rib : this.getRibs(FriendlySkizzik.RibSide.LEFT)) {
            leftRibNBTList.add(StringTag.valueOf(rib.name().toLowerCase()));
        }
        
        for (FriendlySkizzik.Heads head : this.getAddedHeads()) {
            headNBTList.add(StringTag.valueOf(head.name().toLowerCase()));
        }
        for (FriendlySkizzik.Heads head : this.getDetachedHeads()) {
            detachedNBTList.add(StringTag.valueOf(head.name().toLowerCase()));
        }
        for (FriendlySkizzik.Heads head : this.getRiddenHeads()) {
            riddenNBTList.add(StringTag.valueOf(head.name().toLowerCase()));
        }
        
        nbt.put("Gems", gemNBTList);

        nbt.putBoolean("BottomRib", this.isBottomRibPlaced());
        nbt.put("RightRibs", rightRibNBTList);
        nbt.put("LeftRibs", leftRibNBTList);
        nbt.putBoolean("CommandBlock", this.isCommandBlockPlaced());
        
        nbt.put("Heads", headNBTList);
        nbt.put("DetachedHeads", detachedNBTList);
        nbt.put("RiddenHeads", riddenNBTList);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);

        this.setBottomRibPlaced(nbt.getBoolean("BottomRib"));
        this.setCommandBlockPlaced(nbt.getBoolean("CommandBlock"));
        
        int addedGems = 0;
        for (Tag nbtTag : nbt.getList("Gems", Tag.TAG_STRING)) {
            addedGems |= 1 << Gem.GemType.valueOf(nbtTag.getAsString().toUpperCase()).ordinal();
        }
        
        int rightRibs = 0;
        for (Tag nbtTag : nbt.getList("RightRibs", Tag.TAG_STRING)) {
            rightRibs |= 1 << FriendlySkizzik.Ribs.valueOf(nbtTag.getAsString().toUpperCase()).ordinal();
        }
        int leftRibs = 0;
        for (Tag nbtTag : nbt.getList("LeftRibs", Tag.TAG_STRING)) {
            leftRibs |= 1 << FriendlySkizzik.Ribs.valueOf(nbtTag.getAsString().toUpperCase()).ordinal();
        }
        
        int addedHeads = 0;
        for (Tag nbtTag : nbt.getList("Heads", Tag.TAG_STRING)) {
            addedHeads |= 1 << FriendlySkizzik.Heads.valueOf(nbtTag.getAsString().toUpperCase()).ordinal();
        }
        int detachedHeads = 0;
        for (Tag nbtTag : nbt.getList("DetachedHeads", Tag.TAG_STRING)) {
            detachedHeads |= 1 << FriendlySkizzik.Heads.valueOf(nbtTag.getAsString().toUpperCase()).ordinal();
        }
        int riddenHeads = 0;
        for (Tag nbtTag : nbt.getList("RiddenHeads", Tag.TAG_STRING)) {
            riddenHeads |= 1 << FriendlySkizzik.Heads.valueOf(nbtTag.getAsString().toUpperCase()).ordinal();
        }
        
        this.entityData.set(DATA_ADDED_GEMS, addedGems);
        
        this.entityData.set(DATA_RIGHT_RIBS, rightRibs);
        this.entityData.set(DATA_LEFT_RIBS, leftRibs);
        
        this.entityData.set(DATA_ADDED_HEADS, addedHeads);
        this.entityData.set(DATA_DETACHED_HEADS, detachedHeads);
        this.entityData.set(DATA_RIDDEN_HEADS, riddenHeads);
        
        if (this.hasCustomName()) {
            this.bossBar.setName(this.getDisplayName());
        }

        for (FriendlySkizzikPart part : this.parts) {
            if (part instanceof FriendlySkizzikHeadPart && this.getAddedHeads().contains(((FriendlySkizzikHeadPart) part).headType)) {
                if (!this.getDetachedHeads().contains(((FriendlySkizzikHeadPart) part).headType))
                    this.bossBar.addMinibar(new ServerMinibar(part, new Minibar.MinibarProperties().updateProgressAutomatically(false).color(PL_BossEvent.PL_BossBarColor.BLUE)));
                else
                    this.bossBar.addMinibar(new ServerMinibar(FriendlySkizzo.getSkizzoWithHead(this.level, ((FriendlySkizzikHeadPart) part).headType), new Minibar.MinibarProperties().updateProgressAutomatically(false).color(PL_BossEvent.PL_BossBarColor.BLUE)));
            }
        }

        this.updateContainerEquipment();
    }

    @Override
    public void positionRider(Entity passenger) {
        if (this.hasPassenger(passenger)) {
            Vec3 vec3 = new Vec3(this.getHeadX(this.getPassengers().indexOf(passenger)), this.getHeadY(this.getPassengers().indexOf(passenger)), this.getHeadZ(this.getPassengers().indexOf(passenger)));
            passenger.setPos(vec3.x, vec3.y, vec3.z);
        }
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengers().size() < 5;
    }

    @Nullable
    @Override
    public Entity getControllingPassenger() {
        return this.getFirstPassenger();
    }

    @Override
    public boolean canBeControlledByRider() {
        return this.getControllingPassenger() instanceof LivingEntity;
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity entity) {
        if (this.getPassengers().size() <= 0) {
            this.setNoGravity(false);
        }
        
        if (this.level.isClientSide) {
            OverlayRegistry.enableOverlay(PA_GUI.HEAD_STATUS, false);
        }
        
        this.skullCooldown = 0.5F;
        return super.getDismountLocationForPassenger(entity);
    }

    private void doPlayerRide(FriendlySkizzikPart part, Player player) {
        if (part instanceof FriendlySkizzikHeadPart) {
            if (!level.isClientSide) {
                if (this.getAddedHeads().size() > this.getPassengers().size() - 1) {
                    player.setYRot(part.getYRot());
                    player.setXRot(part.getXRot());
                    player.startRiding(part);
                    this.rideHead(((FriendlySkizzikHeadPart) part).headType);
                    this.setNoGravity(true);
                }
            }
        }
        else if (this.getPassengers().size() == 0) {
            if (!this.level.isClientSide) {
                player.setYRot(this.getYRot());
                player.setXRot(this.getXRot());
                player.startRiding(this);
                this.setNoGravity(true);
            }
            else {
                OverlayRegistry.enableOverlay(PA_GUI.HEAD_STATUS, true);
            }
        }
    }

    public FriendlySkizzikHeadPart getHeadFromEnum(Heads head) {
        for (FriendlySkizzikPart part : this.parts) {
            if (part instanceof FriendlySkizzikHeadPart && ((FriendlySkizzikHeadPart) part).headType == head) {
                return (FriendlySkizzikHeadPart) part;
            }
        }
        return null;
    }

    public InteractionResult mobInteract(FriendlySkizzikPart part,  Player player, InteractionHand hand) {
        Item item = player.getItemInHand(hand).getItem();
        if (player.isSecondaryUseActive()) {
            this.openInventory(player);
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }
        
        if (part == this.centerHead && item instanceof Gem && PA_Tags.Items.SKIZZIK_BASE_GEMS.contains(item)) {
            if (!this.getGems().contains(((Gem) item).getType()) && !this.level.isClientSide) {
                this.addGem(((Gem) item).getType());
            }
            return InteractionResult.sidedSuccess(player.level.isClientSide);
        }
        else if (item == PA_Items.SMALL_FRIENDLY_SKIZZIK_HEAD_WITH_GEMS.get()) {
            if (this.getAddedHeads().size() < 4) {
                this.bossBar.addMinibar(new ServerMinibar(this.getHeadFromEnum(FriendlySkizzik.Heads.values()[this.getAddedHeads().size()]), new Minibar.MinibarProperties().color(PL_BossEvent.PL_BossBarColor.BLUE)));
                this.addHead(FriendlySkizzik.Heads.values()[this.getAddedHeads().size()]);
            }
            return InteractionResult.sidedSuccess(player.level.isClientSide);
        }
        else if (part == this.bodyPart && PA_Tags.Items.FRIENDLY_SKIZZIK_RIBS.contains(item)) {
            if (item == PA_Items.FRIENDLY_SKIZZIK_BOTTOM_RIB.get() && !this.isBottomRibPlaced()) {
                this.setBottomRibPlaced(true);
                this.bottomRibAnimation = true;
            }
            else if (item == PA_Items.FRIENDLY_SKIZZIK_BIG_RIB.get()) {
                if (!this.getRibs(RibSide.RIGHT).contains(Ribs.MIDDLE)) {
                    this.addRib(RibSide.RIGHT, Ribs.MIDDLE);
                }
                else if (!this.getRibs(RibSide.LEFT).contains(Ribs.MIDDLE)) {
                    this.addRib(RibSide.LEFT, Ribs.MIDDLE);
                }
            }
            else if (item == PA_Items.FRIENDLY_SKIZZIK_RIB.get()) {
                if (!this.getRibs(RibSide.RIGHT).contains(Ribs.BOTTOM)) {
                    this.addRib(RibSide.RIGHT, Ribs.BOTTOM);
                }
                else if (!this.getRibs(RibSide.RIGHT).contains(Ribs.TOP)) {
                    this.addRib(RibSide.RIGHT, Ribs.TOP);
                }
                else if (!this.getRibs(RibSide.LEFT).contains(Ribs.BOTTOM)) {
                    this.addRib(RibSide.LEFT, Ribs.BOTTOM);
                }
                else if (!this.getRibs(RibSide.LEFT).contains(Ribs.TOP)) {
                    this.addRib(RibSide.LEFT, Ribs.TOP);
                }
            }
            
            return InteractionResult.sidedSuccess(player.level.isClientSide);
        }
        else if (part == this.bodyPart && item == PA_Blocks.COMMAND_BLOCK.get().asItem()) {
            if (!this.isCommandBlockPlaced() && this.isBottomRibPlaced() && this.getRibs(RibSide.RIGHT).size() == 3 && this.getRibs(RibSide.LEFT).size() == 3) {
                this.setCommandBlockPlaced(true);
                this.commandBlockAnimation = true;
            }
            return InteractionResult.sidedSuccess(player.level.isClientSide);
        }
        
        this.doPlayerRide(part, player);
        return super.mobInteract(player, hand);
    }

    @Override
    public void travel(Vec3 pos) {
        if (this.isAlive()) {
            if (this.isVehicle() && this.canBeControlledByRider()) {
                LivingEntity livingentity = (LivingEntity)this.getControllingPassenger();

                this.setRot(this.getYRot(), this.getXRot());
                this.setXRot(livingentity.getXRot() * 0.5F);
                this.setYRot(livingentity.getYRot());

                this.yRotO = this.getYRot();
                this.yHeadRot = livingentity.yHeadRot;
                this.yBodyRot = this.getYRot();

                float moveX = livingentity.xxa * 0.5F;
                double moveY;
                float moveZ = livingentity.zza;
                if (moveZ <= 0.0F) {
                    moveZ *= 0.25F;
                }

                this.flyingSpeed = this.getSpeed() * 0.5F;
                if (this.isControlledByLocalInstance()) {
                    if (PA_ClientHelper.getClient().options.keyJump.isDown()) {
                        moveY = 0.8F;
                    }
                    else if (PA_ClientHelper.getClient().options.keySprint.isDown()) {
                        moveY = -0.8F;
                    }
                    else {
                        moveY = 0.0F;
                    }

                    this.setSpeed((float)this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    this.setDeltaMovement(this.getDeltaMovement().scale(0.9F));
                    super.travel(new Vec3(moveX, moveY, moveZ));
                }
                else if (livingentity instanceof Player) {
                    this.setDeltaMovement(Vec3.ZERO);
                }

                this.calculateEntityAnimation(this, false);
                this.tryCheckInsideBlocks();
            }
            else {
                this.flyingSpeed = 0.02F;
                super.travel(pos);
            }
        }
    }
    
    @Override
    public void performRangedAttack(LivingEntity entity, float f) {
        this.performRangedAttack(0, entity);
    }

    private void performRangedAttack(int head, LivingEntity entity) {
        this.performRangedAttack(head, entity.getX(), entity.getY() + (double)entity.getEyeHeight() * 0.5D, entity.getZ());
    }

    private void performRangedAttack(int head, double x, double y, double z) {
        if (!this.isSilent()) {
            this.level.levelEvent(null, 1024, this.blockPosition(), 0);
        }

        double headX = this.getHeadX(head);
        double headY = this.getHeadY(head);
        double headZ = this.getHeadZ(head);

        double targetX = x - headX;
        double targetY = y - headY;
        double targetZ = z - headZ;

        FriendlySkizzikSkull skull = new FriendlySkizzikSkull(this.level, this, targetX, targetY, targetZ);

        skull.setPosRaw(headX, headY, headZ);
        this.level.addFreshEntity(skull);
    }

    private void performRangedAttack(Player player) {
        if (!this.isSilent()) {
            this.level.levelEvent(null, 1024, this.blockPosition(), 0);
        }

        float xRot = player.getXRot();
        float yRot = player.getYRot();
        float zRot = 0.0F;
        
        float x = -Mth.sin(yRot * ((float)Math.PI / 180F)) * Mth.cos(xRot * ((float)Math.PI / 180F));
        float y = -Mth.sin((xRot + zRot) * ((float)Math.PI / 180F));
        float z = Mth.cos(yRot * ((float)Math.PI / 180F)) * Mth.cos(xRot * ((float)Math.PI / 180F));

        FriendlySkizzikSkull skull = new FriendlySkizzikSkull(this.level, player, x, y, z);
        skull.setPosRaw(this.getX(), this.getEyeY() + (player.getEyeY() - this.getEyeY()), this.getZ());
        this.level.addFreshEntity(skull);
    }

    public void killAllSkizzies(Level world, boolean skizziesOnly) {
        if (world instanceof ServerLevel) {
            LevelEntityGetter<Entity> entityGetter = ((ServerLevel) world).getEntities();
            Iterable<Entity> entities = entityGetter.getAll();
            for (Entity entity : entities) {
                if (entity instanceof FriendlySkizzie) {
                    if (((FriendlySkizzie) entity).getOwner() == this) {
                        entity.kill();
                    }
                }
                else if (!skizziesOnly && entity instanceof FriendlySkizzo) {
                    if (((FriendlySkizzo) entity).getOwner() == this) {
                        entity.kill();
                    }
                }
            }
        }
    }

    public void changeHeadAttachment(int index) {
        if (this.getAddedHeads().contains(FriendlySkizzik.Heads.values()[index])) {
            if (!this.getDetachedHeads().contains(FriendlySkizzik.Heads.values()[index])) {
                this.detachHead(FriendlySkizzik.Heads.values()[index]);

                FriendlySkizzo skizzo = (FriendlySkizzo) PA_Entities.FRIENDLY_SKIZZO.get().spawn((ServerLevel) this.level, null, null, new BlockPos(this.getHeadX(index + 1), this.getHeadY(index + 1), this.getHeadZ(index + 1)), MobSpawnType.MOB_SUMMONED, true, true);
                //skizzo.setYBodyRot(skizzik.getHeadXRot(id - 2));
                //skizzo.setYHeadRot(skizzik.getHeadYRot(id - 2));
                skizzo.setTarget((LivingEntity) this.level.getEntity(this.getAlternativeTarget(index)));
                skizzo.setOwner(this);
                skizzo.setHead(index + 1);
                for (ServerMinibar minibar : this.bossBar.getMinibars()) {
                    if (minibar.getEntity() == this.getHeadFromEnum(FriendlySkizzik.Heads.values()[index])) {
                        minibar.setEntity(skizzo);
                    }
                }

                if (this.getPassengers().size() >= index + 2) {
                    Entity passanger = this.getPassengers().get(index + 1);
                    if (passanger != null) {
                        passanger.stopRiding();
                        passanger.startRiding(skizzo);
                    }
                }
            }
            else {
                LevelEntityGetter<Entity> entityGetter = ((ServerLevel) this.level).getEntities();
                Iterable<Entity> entities = entityGetter.getAll();
                for (Entity entity : entities) {
                    if (entity instanceof FriendlySkizzo) {
                        if (((FriendlySkizzo) entity).getOwner() == this && ((FriendlySkizzo) entity).getHead() - 1 == index) {
                            ((FriendlySkizzo) entity).goalSelector.addGoal(1, new FriendlySkizzoReattachGoal((FriendlySkizzo) entity));
                        }
                    }
                }
            }
        }
    }
    
    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnReason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag nbt) {
        if (spawnReason == MobSpawnType.CONVERSION) {
            this.setConverted(true);
        }
        
        Explosion.BlockInteraction explosion = getMobGriefingEvent(this.level, this) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 3.0F, false, explosion);
        
        return super.finalizeSpawn(level, difficulty, spawnReason, spawnData, nbt);
    }

    @Override
    public void baseTick() {
        super.baseTick();

        this.refreshDimensions();
//        this.stageManager.updateStage();

//        int currentStageId = this.stageManager.getCurrentStage().getStage().getId();
//        this.activeHeads = currentStageId == 1 ? 4 :
//                                currentStageId == 2 ? 3 :
//                                        currentStageId == 3 ? 2 :
//                                                currentStageId == 4 ? 1 : 0;
//        this.activeHeads = 4;
        Level world = this.level;

//        this.getAttributes().getInstance(Attributes.ARMOR).setBaseValue(this.stageManager.getCurrentStage().armorValue());
//        this.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(this.stageManager.getCurrentStage().maxHealth());
//
//        if (!this.isInvul()) {
//            this.bossBar.setName(this.stageManager.getCurrentStage().displayName());
//            this.bossBar.setColor(this.stageManager.getCurrentStage().barColor());
//        }
//        else {
//            this.bossBar.setName(new TextComponent(String.format("%s - %s", this.getDisplayName().getString(), new TranslatableComponent("entity.skizzik.skizzik.invulnerable").getString())));
//            this.bossBar.setColor(PA_BossEvent.PA_BossBarColor.AQUA);
//        }
//        this.bossBar.setOverlay(this.stageManager.getCurrentStage().barOverlay());

//        if (world instanceof ServerLevel) {
//            if (ProjectApple.holiday == 1 && !(this.stageManager.getCurrentStage() instanceof SkizzikFinishHim)) {
//                ((ServerLevel) world).setDayTime(18000);
//            }
//            else {
//                if (this.stageManager.getCurrentStage() instanceof SkizzikStage3) {
//                    ((ServerLevel) world).setDayTime(13000);
//                }
//                else if (this.stageManager.getCurrentStage() instanceof SkizzikStage4 || this.stageManager.getCurrentStage() instanceof SkizzikStage5) {
//                    ((ServerLevel) world).setDayTime(18000);
//                }
//            }
//        }
    }

    public void kill() {
        this.remove(Entity.RemovalReason.KILLED);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
    }
    
    public boolean hurt(FriendlySkizzikPart part, DamageSource source, float amount) {
        Entity entity = source.getEntity();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Level world = this.level;

        if ((!(entity instanceof Player) && entity instanceof LivingEntity)) {
            return false;
        }

//        if (SkizzikStages.isImmune(this, source)) {
//            return false;
//        }
//        else {
//            if (this.destroyBlocksTicks <= 0) {
//                this.destroyBlocksTicks = 35;
//            }
        
            if (world instanceof ServerLevel) {
                LightningBolt[] lightnings = {EntityType.LIGHTNING_BOLT.create(world), EntityType.LIGHTNING_BOLT.create(world), EntityType.LIGHTNING_BOLT.create(world), EntityType.LIGHTNING_BOLT.create(world)};

                lightnings[0].moveTo(Vec3.atBottomCenterOf(new BlockPos(x, y, z + 100)));
                lightnings[1].moveTo(Vec3.atBottomCenterOf(new BlockPos(x, y, z - 100)));
                lightnings[2].moveTo(Vec3.atBottomCenterOf(new BlockPos(x + 100, y, z)));
                lightnings[3].moveTo(Vec3.atBottomCenterOf(new BlockPos(x - 100, y, z)));

                for (LightningBolt lightning : lightnings) {
                    lightning.setVisualOnly(true);
                    world.addFreshEntity(lightning);
                }
            }

            if (part instanceof FriendlySkizzikHeadPart && amount != 0.000042069F) {
                return ((FriendlySkizzikHeadPart) part).damage(source, amount);
            }
            return super.hurt(source, amount);
//        }
    }
    
    public void tickPartOffset(FriendlySkizzikPart part, double offsetX, double offsetY, double offsetZ) {
        this.tickPart(part, this.getX() + offsetX, this.getY() + offsetY, this.getZ() + offsetZ);
    }

    public void tickPart(FriendlySkizzikPart part, double x, double y, double z) {
        if (part instanceof FriendlySkizzikHeadPart) {
            if (this.getAddedHeads().contains(((FriendlySkizzikHeadPart) part).headType) && !this.getDetachedHeads().contains(((FriendlySkizzikHeadPart) part).headType)) {
                part.setPos(x, y, z);
            }
            else {
                part.setPos(this.parts[4].xo, this.parts[4].yo + 0.05, this.parts[4].zo);
            }
        }
        else if (part == this.commandBlockPart) {
            if (this.isCommandBlockPlaced()) {
                part.setPos(x, y, z);
            }
            else {
                part.setPos(this.parts[4].xo, this.parts[4].yo + 0.05, this.parts[4].zo);
            }
        }
        else {
            part.setPos(x, y, z);
        }
    }
    
    private void tickParts() {
        float f = this.yBodyRot * ((float)Math.PI / 180F);
        float f1 = Mth.cos(f);
        float f2 = Mth.sin(f);

        this.tickPart(this.topLeftHead, this.getHeadX(4), this.getHeadY(4), this.getHeadZ(4));
        this.tickPart(this.topRightHead, this.getHeadX(3), this.getHeadY(3), this.getHeadZ(3));
        this.tickPart(this.bottomLeftHead, this.getHeadX(2), this.getHeadY(2), this.getHeadZ(2));
        this.tickPart(this.bottomRightHead, this.getHeadX(1), this.getHeadY(1), this.getHeadZ(1));
        this.tickPart(this.centerHead, this.getHeadX(0), this.getHeadY(0), this.getHeadZ(0));
        this.tickPartOffset(this.commandBlockPart, -f2 * 0.63F, 0.87F, f1);
        this.tickPartOffset(this.bodyPart, -0.062F, 0.0F, 0.0F);
    }

    @Override
    public void aiStep() {
        this.tickParts();
        
        Vec3[] vec3 = new Vec3[this.parts.length];
        for(int i = 0; i < this.parts.length; ++i) {
            vec3[i] = new Vec3(this.parts[i].getX(), this.parts[i].getY(), this.parts[i].getZ());

            this.parts[i].xo = vec3[i].x;
            this.parts[i].yo = vec3[i].y;
            this.parts[i].zo = vec3[i].z;
            this.parts[i].xOld = vec3[i].x;
            this.parts[i].yOld = vec3[i].y;
            this.parts[i].zOld = vec3[i].z;
        }
        
        if (this.getFirstPassenger() == null) {
            Vec3 vector = this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D);
            if (!this.level.isClientSide && this.getAlternativeTarget(0) > 0) {
                Entity entity = this.level.getEntity(this.getAlternativeTarget(0));
                if (entity != null) {
                    double vectorY = vector.y;
                    if (this.getY() < entity.getY()) {
                        vectorY = Math.max(0.0D, vectorY);
                        vectorY = vectorY + (0.3D - vectorY * (double) 0.6F);
                    }
    
                    vector = new Vec3(vector.x, vectorY, vector.z);
                    Vec3 vector1 = new Vec3(entity.getX() - this.getX(), 0.0D, entity.getZ() - this.getZ());
                    if (vector1.horizontalDistanceSqr() > 9.0D) {
                        Vec3 vector2 = vector1.normalize();
                        vector = vector.add(vector2.x * 0.3D - vector.x * 0.6D, 0.0D, vector2.z * 0.3D - vector.z * 0.6D);
                    }
                }
            }
    
            this.setDeltaMovement(vector);
            if (vector.horizontalDistanceSqr() > 0.05D) {
                this.setYRot((float) Mth.atan2(vector.z, vector.x) * (180F / (float) Math.PI) - 90.0F);
            }
        }

        super.aiStep();

        for (int i = 0; i < this.getAddedHeads().size(); ++i) {
            this.yRotHeads1[i] = this.yRotHeads[i];
            this.xRotHeads1[i] = this.xRotHeads[i];
        }

        for (int j = 0; j < this.getAddedHeads().size(); ++j) {
            int k = this.getAlternativeTarget(j + 1);
            Entity entity1 = null;
            if (k > 0) {
                entity1 = this.level.getEntity(k);
            }

            if (entity1 != null) {
                double headX = this.getHeadX(j + 1);
                double headY = this.getHeadY(j + 1);
                double headZ = this.getHeadZ(j + 1);

                double entityX = entity1.getX() - headX;
                double entityY = entity1.getEyeY() - headY;
                double entityZ = entity1.getZ() - headZ;

                double d7 = Math.sqrt(entityX * entityX + entityZ * entityZ);

                float f = (float) (Mth.atan2(entityZ, entityX) * (double) (180F / (float) Math.PI)) - 90.0F;
                float f1 = (float) (-(Mth.atan2(entityY, d7) * (double) (180F / (float) Math.PI)));

                this.xRotHeads[j] = this.rotlerp(this.xRotHeads[j], f1, 40.0F);
                this.yRotHeads[j] = this.rotlerp(this.yRotHeads[j], f, 10.0F);
            }
            else {
                this.yRotHeads[j] = this.rotlerp(this.yRotHeads[j], this.yBodyRot, 10.0F);
            }
        }

        for(int l = 0; l < this.getAddedHeads().size() + 1; ++l) {
            double headX = this.getHeadX(l);
            double heaxY = this.getHeadY(l);
            double headZ = this.getHeadZ(l);
            if (l == 0) {
                this.level.addParticle(ParticleTypes.DRIPPING_WATER, headX + this.random.nextGaussian() * (double)0.5F, heaxY + this.random.nextGaussian() * (double)0.5F, headZ + this.random.nextGaussian() * (double)0.5F, 0.0D, 0.0D, 0.0D);
            }
            else {
                this.level.addParticle(ParticleTypes.DRIPPING_WATER, headX + this.random.nextGaussian() * (double) 0.3F, heaxY + this.random.nextGaussian() * (double) 0.3F, headZ + this.random.nextGaussian() * (double) 0.3F, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();

//        if (this.spawnSkizzieTicks <= 0) {
//            this.spawnSkizzieTicks = 60;
//        }

        if (this.isVehicle()) {
            this.goalController.addDefaultGoals();
        }
        else {
            Options options = PA_ClientHelper.getClient().options;
            if (skullCooldown <= 0.0F && options.keyUse.isDown()) {
                this.performRangedAttack(PA_ClientHelper.getClient().player);
                skullCooldown = 0.5F;
            }

            if (canDetach && PA_ClientHelper.keybinds.keyDetachHead.isDown()) {
                int i = 0;
                for (KeyMapping key : options.keyHotbarSlots) {
                    if (i < 4 && key.isDown()) {
                        this.changeHeadAttachment(i);
                        canDetach = false; // This is needed in order to prevent the user from holding the keybind
                    }
                    i += 1;
                }
            }
            else if (!PA_ClientHelper.keybinds.keyDetachHead.isDown()) {
                this.canDetach = true;
            }

            skullCooldown -= 0.1F;
        }

        for (int headIndex = 1; headIndex < this.getAddedHeads().size() + 1; ++headIndex) {
            if (this.tickCount >= this.nextHeadUpdate[headIndex - 1]) {
                this.nextHeadUpdate[headIndex - 1] = this.tickCount + 10 + this.random.nextInt(10);

                int alternativeTarget = this.getAlternativeTarget(headIndex);

                if (alternativeTarget > 0) {
                    Entity target = this.level.getEntity(alternativeTarget);
                    if (target != null && target.isAlive() && !(this.distanceToSqr(target) > 900.0D) && this.hasLineOfSight(target)) {
                        if (target instanceof Player && ((Player) target).getAbilities().invulnerable) {
                            this.setAlternativeTarget(headIndex, 0);
                        } else {
                            this.performRangedAttack(headIndex + 1, target.getX(), target.getY() + (double) target.getEyeHeight() * 0.5D, target.getZ());
                            this.nextHeadUpdate[headIndex - 1] = this.tickCount + 40 + this.random.nextInt(20);
                        }
                    } else {
                        this.setAlternativeTarget(headIndex, 0);
                    }
                } else {
                    List<LivingEntity> list = this.level.getNearbyEntities(LivingEntity.class, TARGETING_CONDITIONS, this, this.getBoundingBox().inflate(20.0D, 8.0D, 20.0D));

                    for (int i = 0; i < 10 && !list.isEmpty(); ++i) {
                        LivingEntity target = list.get(this.random.nextInt(list.size()));
                        if (target != this && target.isAlive() && this.hasLineOfSight(target)) {
                            if (target instanceof Player) {
                                if (!((Player) target).getAbilities().invulnerable) {
                                    this.setAlternativeTarget(headIndex, target.getId());
                                }
                            } else {
                                this.setAlternativeTarget(headIndex, target.getId());
                            }
                            break;
                        }
                        list.remove(target);
                    }
                }
            }
        }
        
        if (this.getTarget() != null) {
            this.setAlternativeTarget(0, this.getTarget().getId());
        }
        else {
            this.setAlternativeTarget(0, 0);
        }

        for (ServerMinibar bar : this.bossBar.getMinibars()) {
            if (bar.getEntity() instanceof FriendlySkizzikHeadPart) {
                if (this.getAddedHeads().contains(((FriendlySkizzikHeadPart) bar.getEntity()).headType) && !this.getDetachedHeads().contains(((FriendlySkizzikHeadPart) bar.getEntity()).headType))
                    bar.setProgress(((FriendlySkizzikHeadPart) bar.getEntity()).getHealth() / 150.0F);
            }
        }
    }

    @Override
    public void die(DamageSource source) {
        this.killAllSkizzies(this.level, false);
        for (Heads head : this.addedHeads) {
            FriendlySkizzo skizzo = (FriendlySkizzo) PA_Entities.FRIENDLY_SKIZZO.get().spawn((ServerLevel) this.level, null, null, new BlockPos(this.getHeadX(head.ordinal() + 1), this.getHeadY(head.ordinal() + 1), this.getHeadZ(head.ordinal() + 1)), MobSpawnType.MOB_SUMMONED, true, true);
            skizzo.setTarget((LivingEntity) this.level.getEntity(this.getAlternativeTarget(head.ordinal())));
        }
        
        super.die(source);
    }

    @Override
    public PL_ServerBossEvent getBossBar() {
        return this.bossBar;
    }

    public enum RibSide {
        RIGHT,
        LEFT
    }
    
    public enum Ribs {
        TOP,
        MIDDLE,
        BOTTOM
    }
    
    public enum Heads {
        BOTTOM_RIGHT_HEAD,
        BOTTOM_LEFT_HEAD,
        TOP_RIGHT_HEAD,
        TOP_LEFT_HEAD
    }
}
