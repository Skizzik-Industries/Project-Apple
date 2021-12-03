package com.skizzium.projectapple.init;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzo;
import com.skizzium.projectapple.network.SkizzoConnectionParticlesPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.phys.Vec3;

public class PA_PacketHandler {
    public static void handleSkizzoParticles(SkizzoConnectionParticlesPacket packet) {
        Skizzik skizzik = (Skizzik) Minecraft.getInstance().level.getEntity(packet.skizzikId);
        Skizzo skizzo = (Skizzo) Minecraft.getInstance().level.getEntity(packet.skizzoId);
        
        double distance = skizzo.distanceTo(skizzik);
        Vec3 point1 = skizzo.position().add(0, skizzo.getEyeHeight(), 0);
        Vec3 point2 = skizzik.position().add(0, skizzik.getEyeHeight(), 0);
        Vec3 vector = new Vec3(point2.x, point2.y, point2.z).subtract(point1).normalize().scale(0.5);
        
        for (double length = 0; length < distance; length += 0.5) {
            // Custom Color: new DustParticleOptions(new Vector3f(Vec3.fromRGB24(0x03FC03)), 1.0F)
            skizzo.level.addParticle(DustParticleOptions.REDSTONE, point1.x, point1.y, point1.z, 0.0D, 0.0D, 0.0D);
            point1 = point1.add(vector);
        }
    }
}
