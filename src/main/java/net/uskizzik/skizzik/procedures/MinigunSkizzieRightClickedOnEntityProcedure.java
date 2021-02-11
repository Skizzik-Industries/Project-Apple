package net.uskizzik.skizzik.procedures;

import net.uskizzik.skizzik.entity.WitchSkizzieEntity;
import net.uskizzik.skizzik.entity.SkizzieEntity;
import net.uskizzik.skizzik.entity.KaBoomSkizzieEntity;
import net.uskizzik.skizzik.entity.FriendlyMinigunSkizzieEntity;
import net.uskizzik.skizzik.entity.CorruptedSkizzieEntity;
import net.uskizzik.skizzik.block.StoneSkizzieBlock;
import net.uskizzik.skizzik.block.SkizzikHeadBlock;
import net.uskizzik.skizzik.block.CorruptedBlockBlock;
import net.uskizzik.skizzik.SkizzikModElements;
import net.uskizzik.skizzik.SkizzikMod;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.Minecraft;

import java.util.Map;

@SkizzikModElements.ModElement.Tag
public class MinigunSkizzieRightClickedOnEntityProcedure extends SkizzikModElements.ModElement {
	public MinigunSkizzieRightClickedOnEntityProcedure(SkizzikModElements instance) {
		super(instance, 150);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SkizzikMod.LOGGER.warn("Failed to load dependency entity for procedure MinigunSkizzieRightClickedOnEntity!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				SkizzikMod.LOGGER.warn("Failed to load dependency sourceentity for procedure MinigunSkizzieRightClickedOnEntity!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SkizzikMod.LOGGER.warn("Failed to load dependency x for procedure MinigunSkizzieRightClickedOnEntity!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SkizzikMod.LOGGER.warn("Failed to load dependency y for procedure MinigunSkizzieRightClickedOnEntity!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SkizzikMod.LOGGER.warn("Failed to load dependency z for procedure MinigunSkizzieRightClickedOnEntity!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SkizzikMod.LOGGER.warn("Failed to load dependency world for procedure MinigunSkizzieRightClickedOnEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(world.isRemote()))) {
			if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == new ItemStack(Items.WATER_BUCKET, (int) (1)).getItem())) {
				if ((new Object() {
					public boolean checkGamemode(Entity _ent) {
						if (_ent instanceof ServerPlayerEntity) {
							return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
						} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
							NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
									.getPlayerInfo(((ClientPlayerEntity) _ent).getGameProfile().getId());
							return _npi != null && _npi.getGameType() == GameType.CREATIVE;
						}
						return false;
					}
				}.checkGamemode(sourceentity))) {
					if (world instanceof ServerWorld) {
						Entity entityToSpawn = new FriendlyMinigunSkizzieEntity.CustomEntity(FriendlyMinigunSkizzieEntity.entity, (World) world);
						entityToSpawn.setLocationAndAngles(x, y, z, (float) (entity.rotationYaw), (float) (entity.rotationPitch));
						entityToSpawn.setRenderYawOffset((float) (entity.rotationYaw));
						if (entityToSpawn instanceof MobEntity)
							((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
									world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED, (ILivingEntityData) null,
									(CompoundNBT) null);
						world.addEntity(entityToSpawn);
					}
					if (world instanceof ServerWorld) {
						LightningBoltEntity _ent = EntityType.LIGHTNING_BOLT.create((World) world);
						_ent.moveForced(Vector3d.copyCenteredHorizontally(new BlockPos((int) x, (int) y, (int) z)));
						_ent.setEffectOnly(false);
						((World) world).addEntity(_ent);
					}
					if (!entity.world.isRemote())
						entity.remove();
				} else {
					if ((Math.random() < 0.3)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), StoneSkizzieBlock.block.getDefaultState(), 3);
						if (!entity.world.isRemote())
							entity.remove();
					} else {
						if (world instanceof ServerWorld) {
							Entity entityToSpawn = new FriendlyMinigunSkizzieEntity.CustomEntity(FriendlyMinigunSkizzieEntity.entity, (World) world);
							entityToSpawn.setLocationAndAngles(x, y, z, (float) (entity.rotationYaw), (float) (entity.rotationPitch));
							entityToSpawn.setRenderYawOffset((float) (entity.rotationYaw));
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
										world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED,
										(ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
						if (world instanceof ServerWorld) {
							LightningBoltEntity _ent = EntityType.LIGHTNING_BOLT.create((World) world);
							_ent.moveForced(Vector3d.copyCenteredHorizontally(new BlockPos((int) x, (int) y, (int) z)));
							_ent.setEffectOnly(false);
							((World) world).addEntity(_ent);
						}
						if (!entity.world.isRemote())
							entity.remove();
						if (sourceentity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(Items.WATER_BUCKET, (int) (1));
							((PlayerEntity) sourceentity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
									((PlayerEntity) sourceentity).container.func_234641_j_());
						}
					}
				}
			} else {
				if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(Items.GUNPOWDER, (int) (1)).getItem())) {
					if ((new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((ClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.CREATIVE;
							}
							return false;
						}
					}.checkGamemode(sourceentity))) {
						if (world instanceof ServerWorld) {
							Entity entityToSpawn = new KaBoomSkizzieEntity.CustomEntity(KaBoomSkizzieEntity.entity, (World) world);
							entityToSpawn.setLocationAndAngles(x, y, z, (float) (entity.rotationYaw), (float) (entity.rotationPitch));
							entityToSpawn.setRenderYawOffset((float) (entity.rotationYaw));
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
										world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED,
										(ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
						if (world instanceof ServerWorld) {
							LightningBoltEntity _ent = EntityType.LIGHTNING_BOLT.create((World) world);
							_ent.moveForced(Vector3d.copyCenteredHorizontally(new BlockPos((int) x, (int) y, (int) z)));
							_ent.setEffectOnly(false);
							((World) world).addEntity(_ent);
						}
						if (!entity.world.isRemote())
							entity.remove();
					} else {
						if (world instanceof ServerWorld) {
							Entity entityToSpawn = new KaBoomSkizzieEntity.CustomEntity(KaBoomSkizzieEntity.entity, (World) world);
							entityToSpawn.setLocationAndAngles(x, y, z, (float) (entity.rotationYaw), (float) (entity.rotationPitch));
							entityToSpawn.setRenderYawOffset((float) (entity.rotationYaw));
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
										world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED,
										(ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
						if (world instanceof ServerWorld) {
							LightningBoltEntity _ent = EntityType.LIGHTNING_BOLT.create((World) world);
							_ent.moveForced(Vector3d.copyCenteredHorizontally(new BlockPos((int) x, (int) y, (int) z)));
							_ent.setEffectOnly(false);
							((World) world).addEntity(_ent);
						}
						if (!entity.world.isRemote())
							entity.remove();
						if (sourceentity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(Items.GUNPOWDER, (int) (1));
							((PlayerEntity) sourceentity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
									((PlayerEntity) sourceentity).container.func_234641_j_());
						}
					}
				} else {
					if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getItem() == new ItemStack(CorruptedBlockBlock.block, (int) (1)).getItem())) {
						if ((new Object() {
							public boolean checkGamemode(Entity _ent) {
								if (_ent instanceof ServerPlayerEntity) {
									return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
								} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
									NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
											.getPlayerInfo(((ClientPlayerEntity) _ent).getGameProfile().getId());
									return _npi != null && _npi.getGameType() == GameType.CREATIVE;
								}
								return false;
							}
						}.checkGamemode(sourceentity))) {
							if (world instanceof ServerWorld) {
								Entity entityToSpawn = new CorruptedSkizzieEntity.CustomEntity(CorruptedSkizzieEntity.entity, (World) world);
								entityToSpawn.setLocationAndAngles(x, y, z, (float) (entity.rotationYaw), (float) (entity.rotationPitch));
								entityToSpawn.setRenderYawOffset((float) (entity.rotationYaw));
								if (entityToSpawn instanceof MobEntity)
									((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
											world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED,
											(ILivingEntityData) null, (CompoundNBT) null);
								world.addEntity(entityToSpawn);
							}
							if (world instanceof ServerWorld) {
								LightningBoltEntity _ent = EntityType.LIGHTNING_BOLT.create((World) world);
								_ent.moveForced(Vector3d.copyCenteredHorizontally(new BlockPos((int) x, (int) y, (int) z)));
								_ent.setEffectOnly(false);
								((World) world).addEntity(_ent);
							}
							if (!entity.world.isRemote())
								entity.remove();
						} else {
							if (world instanceof ServerWorld) {
								Entity entityToSpawn = new CorruptedSkizzieEntity.CustomEntity(CorruptedSkizzieEntity.entity, (World) world);
								entityToSpawn.setLocationAndAngles(x, y, z, (float) (entity.rotationYaw), (float) (entity.rotationPitch));
								entityToSpawn.setRenderYawOffset((float) (entity.rotationYaw));
								if (entityToSpawn instanceof MobEntity)
									((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
											world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED,
											(ILivingEntityData) null, (CompoundNBT) null);
								world.addEntity(entityToSpawn);
							}
							if (world instanceof ServerWorld) {
								LightningBoltEntity _ent = EntityType.LIGHTNING_BOLT.create((World) world);
								_ent.moveForced(Vector3d.copyCenteredHorizontally(new BlockPos((int) x, (int) y, (int) z)));
								_ent.setEffectOnly(false);
								((World) world).addEntity(_ent);
							}
							if (!entity.world.isRemote())
								entity.remove();
							if (sourceentity instanceof PlayerEntity) {
								ItemStack _stktoremove = new ItemStack(CorruptedBlockBlock.block, (int) (1));
								((PlayerEntity) sourceentity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
										((PlayerEntity) sourceentity).container.func_234641_j_());
							}
						}
					} else {
						if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getItem() == new ItemStack(Items.POTION, (int) (1)).getItem())) {
							if ((new Object() {
								public boolean checkGamemode(Entity _ent) {
									if (_ent instanceof ServerPlayerEntity) {
										return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
									} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
										NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
												.getPlayerInfo(((ClientPlayerEntity) _ent).getGameProfile().getId());
										return _npi != null && _npi.getGameType() == GameType.CREATIVE;
									}
									return false;
								}
							}.checkGamemode(sourceentity))) {
								if (world instanceof ServerWorld) {
									Entity entityToSpawn = new WitchSkizzieEntity.CustomEntity(WitchSkizzieEntity.entity, (World) world);
									entityToSpawn.setLocationAndAngles(x, y, z, (float) (entity.rotationYaw), (float) (entity.rotationPitch));
									entityToSpawn.setRenderYawOffset((float) (entity.rotationYaw));
									if (entityToSpawn instanceof MobEntity)
										((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
												world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED,
												(ILivingEntityData) null, (CompoundNBT) null);
									world.addEntity(entityToSpawn);
								}
								if (world instanceof ServerWorld) {
									LightningBoltEntity _ent = EntityType.LIGHTNING_BOLT.create((World) world);
									_ent.moveForced(Vector3d.copyCenteredHorizontally(new BlockPos((int) x, (int) y, (int) z)));
									_ent.setEffectOnly(false);
									((World) world).addEntity(_ent);
								}
								if (!entity.world.isRemote())
									entity.remove();
							} else {
								if (world instanceof ServerWorld) {
									Entity entityToSpawn = new WitchSkizzieEntity.CustomEntity(WitchSkizzieEntity.entity, (World) world);
									entityToSpawn.setLocationAndAngles(x, y, z, (float) (entity.rotationYaw), (float) (entity.rotationPitch));
									entityToSpawn.setRenderYawOffset((float) (entity.rotationYaw));
									if (entityToSpawn instanceof MobEntity)
										((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
												world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED,
												(ILivingEntityData) null, (CompoundNBT) null);
									world.addEntity(entityToSpawn);
								}
								if (world instanceof ServerWorld) {
									LightningBoltEntity _ent = EntityType.LIGHTNING_BOLT.create((World) world);
									_ent.moveForced(Vector3d.copyCenteredHorizontally(new BlockPos((int) x, (int) y, (int) z)));
									_ent.setEffectOnly(false);
									((World) world).addEntity(_ent);
								}
								if (!entity.world.isRemote())
									entity.remove();
								if (sourceentity instanceof PlayerEntity) {
									ItemStack _stktoremove = new ItemStack(Items.POTION, (int) (1));
									((PlayerEntity) sourceentity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
											((PlayerEntity) sourceentity).container.func_234641_j_());
								}
							}
						} else {
							if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getItem() == new ItemStack(SkizzikHeadBlock.block, (int) (1)).getItem())) {
								if ((new Object() {
									public boolean checkGamemode(Entity _ent) {
										if (_ent instanceof ServerPlayerEntity) {
											return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
										} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
											NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
													.getPlayerInfo(((ClientPlayerEntity) _ent).getGameProfile().getId());
											return _npi != null && _npi.getGameType() == GameType.CREATIVE;
										}
										return false;
									}
								}.checkGamemode(sourceentity))) {
									if (world instanceof ServerWorld) {
										Entity entityToSpawn = new SkizzieEntity.CustomEntity(SkizzieEntity.entity, (World) world);
										entityToSpawn.setLocationAndAngles(x, y, z, (float) (entity.rotationYaw), (float) (entity.rotationPitch));
										entityToSpawn.setRenderYawOffset((float) (entity.rotationYaw));
										if (entityToSpawn instanceof MobEntity)
											((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
													world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED,
													(ILivingEntityData) null, (CompoundNBT) null);
										world.addEntity(entityToSpawn);
									}
									if (world instanceof ServerWorld) {
										LightningBoltEntity _ent = EntityType.LIGHTNING_BOLT.create((World) world);
										_ent.moveForced(Vector3d.copyCenteredHorizontally(new BlockPos((int) x, (int) y, (int) z)));
										_ent.setEffectOnly(false);
										((World) world).addEntity(_ent);
									}
									if (!entity.world.isRemote())
										entity.remove();
								} else {
									if (world instanceof ServerWorld) {
										Entity entityToSpawn = new SkizzieEntity.CustomEntity(SkizzieEntity.entity, (World) world);
										entityToSpawn.setLocationAndAngles(x, y, z, (float) (entity.rotationYaw), (float) (entity.rotationPitch));
										entityToSpawn.setRenderYawOffset((float) (entity.rotationYaw));
										if (entityToSpawn instanceof MobEntity)
											((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
													world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED,
													(ILivingEntityData) null, (CompoundNBT) null);
										world.addEntity(entityToSpawn);
									}
									if (world instanceof ServerWorld) {
										LightningBoltEntity _ent = EntityType.LIGHTNING_BOLT.create((World) world);
										_ent.moveForced(Vector3d.copyCenteredHorizontally(new BlockPos((int) x, (int) y, (int) z)));
										_ent.setEffectOnly(false);
										((World) world).addEntity(_ent);
									}
									if (!entity.world.isRemote())
										entity.remove();
									if (sourceentity instanceof PlayerEntity) {
										ItemStack _stktoremove = new ItemStack(SkizzikHeadBlock.block, (int) (1));
										((PlayerEntity) sourceentity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
												((PlayerEntity) sourceentity).container.func_234641_j_());
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
