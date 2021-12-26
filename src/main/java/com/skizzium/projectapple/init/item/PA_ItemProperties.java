package com.skizzium.projectapple.init.item;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.item.LootBagLocator;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import javax.annotation.Nullable;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_ItemProperties {
    @SubscribeEvent
    public static void register(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(PA_Items.LOOT_BAG_LOCATOR.get(), new ResourceLocation("angle"), new ClampedItemPropertyFunction() {
                private final LocatorWobble wobble = new LocatorWobble();
                private final LocatorWobble wobbleRandom = new LocatorWobble();

                public float unclampedCall(ItemStack itemstack, @Nullable ClientLevel level, @Nullable LivingEntity livingentity, int i) {
                    Entity entity = livingentity != null ? livingentity : itemstack.getEntityRepresentation();
                    if (entity == null) {
                        return 0.0F;
                    }
                    else {
                        if (level == null && entity.level instanceof ClientLevel) {
                            level = (ClientLevel) entity.level;
                        }

                        BlockPos blockpos = this.getLootBagPosition(level, itemstack.getOrCreateTag());
                        long i1 = level.getGameTime();
                        if (blockpos != null && !(entity.position().distanceToSqr((double) blockpos.getX() + 0.5D, entity.position().y(), (double) blockpos.getZ() + 0.5D) < (double) 1.0E-5F)) {
                            boolean flag = livingentity instanceof Player && ((Player) livingentity).isLocalPlayer();
                            double d1 = 0.0D;
                            if (flag) {
                                d1 = livingentity.getYRot();
                            }
                            else if (entity instanceof ItemFrame) {
                                d1 = this.getFrameRotation((ItemFrame) entity);
                            }
                            else if (entity instanceof ItemEntity) {
                                d1 = 180.0F - ((ItemEntity) entity).getSpin(0.5F) / ((float) Math.PI * 2F) * 360.0F;
                            }
                            else if (livingentity != null) {
                                d1 = livingentity.yBodyRot;
                            }

                            d1 = Mth.positiveModulo(d1 / 360.0D, 1.0D);
                            double d2 = this.getAngleTo(Vec3.atCenterOf(blockpos), entity) / (double) ((float) Math.PI * 2F);
                            double d3;
                            if (flag) {
                                if (this.wobble.shouldUpdate(i1)) {
                                    this.wobble.update(i1, 0.5D - (d1 - 0.25D));
                                }

                                d3 = d2 + this.wobble.rotation;
                            }
                            else {
                                d3 = 0.5D - (d1 - 0.25D - d2);
                            }

                            return Mth.positiveModulo((float) d3, 1.0F);
                        }
                        else {
                            if (this.wobbleRandom.shouldUpdate(i1)) {
                                this.wobbleRandom.update(i1, Math.random());
                            }

                            double d0 = this.wobbleRandom.rotation + (double) ((float) this.hash(i) / 2.14748365E9F);
                            return Mth.positiveModulo((float) d0, 1.0F);
                        }
                    }
                }

                private int hash(int i) {
                    return i * 1327217883;
                }

                @Nullable
                private BlockPos getLootBagPosition(Level level, CompoundTag tag) {
                    boolean flag = tag.contains("LootBagPos");
                    boolean flag1 = tag.contains("LootBagDimension");
                    if (flag && flag1) {
                        Optional<ResourceKey<Level>> optional = LootBagLocator.getLootBagDimension(tag);
                        if (optional.isPresent() && level.dimension() == optional.get()) {
                            return NbtUtils.readBlockPos(tag.getCompound("LootBagPos"));
                        }
                    }

                    return null;
                }

                private double getFrameRotation(ItemFrame frame) {
                    Direction direction = frame.getDirection();
                    int i = direction.getAxis().isVertical() ? 90 * direction.getAxisDirection().getStep() : 0;
                    return Mth.wrapDegrees(180 + direction.get2DDataValue() * 90 + frame.getRotation() * 45 + i);
                }

                private double getAngleTo(Vec3 vec3, Entity entity) {
                    return Math.atan2(vec3.z() - entity.getZ(), vec3.x() - entity.getX());
                }
            });
        });
    }
    
    private static class LocatorWobble {
        double rotation;
        private double deltaRotation;
        private long lastUpdateTick;

        boolean shouldUpdate(long gameTime) {
            return this.lastUpdateTick != gameTime;
        }

        void update(long gameTime, double amount) {
            this.lastUpdateTick = gameTime;
            double d0 = amount - this.rotation;
            d0 = Mth.positiveModulo(d0 + 0.5D, 1.0D) - 0.5D;
            this.deltaRotation += d0 * 0.1D;
            this.deltaRotation *= 0.8D;
            this.rotation = Mth.positiveModulo(this.rotation + this.deltaRotation, 1.0D);
        }
    }
}
