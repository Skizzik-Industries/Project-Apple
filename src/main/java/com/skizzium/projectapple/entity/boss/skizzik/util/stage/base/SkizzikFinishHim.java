package com.skizzium.projectapple.entity.boss.skizzik.util.stage.base;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.block.SkizzikLootBag;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStageManager;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStages;
import com.skizzium.projectapple.entity.boss.skizzik.util.stage.AbstractPassiveSkizzikStage;
import com.skizzium.projectapple.init.effects.PA_Effects;
import com.skizzium.projectapple.init.PA_SoundEvents;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.item.PA_Items;
import com.skizzium.projectapple.item.LootBagLocator;
import com.skizzium.projectlib.gui.PL_BossEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SkizzikFinishHim extends AbstractPassiveSkizzikStage {
    public SkizzikFinishHim(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public TextComponent displayName() {
        if (skizzik.isConverting() && skizzik.hasEffect(PA_Effects.CONVERSION.get())) {
            if ((skizzik.getEffect(PA_Effects.CONVERSION.get()).getDuration() / 5) % 8 == 0) {
                return new TextComponent(new TranslatableComponent(ProjectApple.getThemedDescriptionId("entity.skizzik.friendly_skizzik")).getString());
            }
            else {
                return new TextComponent(skizzik.getDisplayName().getString());
            }
        }
        return new TextComponent(String.format("%s - %s", skizzik.getDisplayName().getString(), new TranslatableComponent("entity.skizzik.skizzik.finish_him").getString()));
    }

    @Override
    public PL_BossEvent.PL_BossBarColor barColor() {
        if (skizzik.isConverting() && skizzik.hasEffect(PA_Effects.CONVERSION.get())) {
            return (skizzik.getEffect(PA_Effects.CONVERSION.get()).getDuration() / 5) % 8 == 0 ? PL_BossEvent.PL_BossBarColor.AQUA : ProjectApple.holiday == 1 ? PL_BossEvent.PL_BossBarColor.ORANGE : PL_BossEvent.PL_BossBarColor.RED;
        }
        return super.barColor();
    }
    
    @Override
    public int transitionTime() {
        return 16;
    }
    
    @Override
    public String textureLocation() {
        if (skizzik.isConverting() && skizzik.hasEffect(PA_Effects.CONVERSION.get())) {
            return (skizzik.getEffect(PA_Effects.CONVERSION.get()).getDuration() / 5) % 8 == 0 ? String.format("friendly_%s/friendly_%s", skizzik.getTranslationKey().getString().toLowerCase(), skizzik.getTranslationKey().getString().toLowerCase()) : String.format("%s/%s_finish-him", skizzik.getTranslationKey().getString().toLowerCase(), skizzik.getTranslationKey().getString().toLowerCase());
        }
        return String.format("%s/%s_finish-him", skizzik.getTranslationKey().getString().toLowerCase(), skizzik.getTranslationKey().getString().toLowerCase());
    }

    @Override
    public int maxStageHealth() {
        return 20;
    }

    @Override
    public int maxHealth() {
        return skizzik.isConverting() ? 1020 : this.maxStageHealth();
    }

    @Override
    public float eyeHeight() {
        return 2.25F;
    }

    @Override
    public EntityDimensions hitbox() {
        return new EntityDimensions(1.2F, 2.868F, true);
    }
    
    private BlockState getBagState() {
        BlockState state = PA_Blocks.SKIZZIK_LOOT_BAG.get().defaultBlockState();
        if (ProjectApple.holiday > 0) {
            return state.setValue(SkizzikLootBag.HOLIDAY, ProjectApple.holiday);
        }
        return state;
    }
    
    @Override
    public void begin(SkizzikStageManager stageManager) {
        super.begin(stageManager);

        Level world = skizzik.level;
        if (world instanceof ServerLevel) {
            world.setBlock(new BlockPos(skizzik.getX(), skizzik.getY(), skizzik.getZ()), this.getBagState(), 2);
            ((ServerLevel) world).setDayTime(1000);
            
            for (ServerPlayer player : skizzik.bossBar.getPlayers()) {
                if (!skizzik.level.isClientSide) {
                    ItemStack itemstack = new ItemStack(PA_Items.LOOT_BAG_LOCATOR.get());
                    LootBagLocator.bindLootBag(true, skizzik.getLevel().dimension(), skizzik.blockPosition(), itemstack.getOrCreateTag());
                    if (!player.getInventory().add(itemstack)) {
                        player.drop(itemstack, false);
                    }
                }
            }
        }

        skizzik.killAllSkizzies(world, false);
        skizzik.level.playSound(null, skizzik.getX(), skizzik.getY(), skizzik.getZ(), PA_SoundEvents.FINISH_HIM_LAZY.get(), SoundSource.HOSTILE, 10000.0F, 1.0F);
    }

    @Override
    public void addGoals() {
        skizzik.goalSelector.removeAllGoals();
        skizzik.targetSelector.removeAllGoals();

        skizzik.goalSelector.addGoal(1, skizzik.avoidPlayerGoal);
        skizzik.goalSelector.addGoal(2, skizzik.panicGoal);
        skizzik.goalSelector.addGoal(3, skizzik.avoidWaterGoal);
        skizzik.goalSelector.addGoal(4, skizzik.lookGoal);
        skizzik.goalSelector.addGoal(5, skizzik.lookRandomlyGoal);
    }
    
    @Override
    public double getHeadY(int head) {
        if (head == 0) {
            return skizzik.getY() + 1.867D;
        }
        return super.getHeadY(head);
    }

    @Override
    public SkizzikStages<? extends SkizzikStageInterface> getStage() {
        return SkizzikStages.FINISH_HIM;
    }
}
