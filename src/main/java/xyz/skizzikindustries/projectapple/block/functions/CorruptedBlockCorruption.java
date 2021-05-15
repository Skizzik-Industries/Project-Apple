package xyz.skizzikindustries.projectapple.block.functions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.GameType;
import xyz.skizzikindustries.projectapple.effect.CorruptionPotion;

import java.util.Map;

public class CorruptedBlockCorruption {
    /* public static void execute(Map<String, Object> dependencies) {
        Entity entity = (Entity) dependencies.get("entity");
        if ((new Object() {
            public boolean checkGamemode(Entity _ent) {
                if (_ent instanceof ServerPlayerEntity) {
                    return ((ServerPlayerEntity) _ent).intera.getGameType() == GameType.CREATIVE;
                } else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
                    NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
                            .getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
                    return _npi != null && _npi.getGameType() == GameType.CREATIVE;
                }
                return false;
            }
        }.checkGamemode(entity))) {
            if (entity instanceof LivingEntity)
                ((LivingEntity) entity).addPotionEffect(new EffectInstance(CorruptionPotion.potion, (int) 1200, (int) 1));
        }
    } */
}
