package xyz.skizzikindustries.projectapple.effect.functions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

import java.util.Map;

public class CorruptionPotionExpire {
    public static void execute(Map<String, Object> dependencies) {
        Entity entity = (Entity) dependencies.get("entity");
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).hurt(new DamageSource("corrupted").bypassArmor(), (float) 1000);
        }
    }

}
