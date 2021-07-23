package com.skizzium.projectapple.sound;

import com.skizzium.projectapple.entity.Skizzik;
import com.skizzium.projectapple.init.PA_SoundEvents;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzikMusic extends AbstractTickableSoundInstance {
    private final Skizzik skizzik;
    private float pitch = 0.0F;

    public SkizzikMusic(Skizzik skizzik) {
        super(PA_SoundEvents.MUSIC_SKIZZIK_LAZY.get(), SoundSource.HOSTILE);
        this.skizzik = skizzik;
        this.delay = 0;
        this.volume = 0.0F;
        this.x = (float)skizzik.getX();
        this.y = (float)skizzik.getY();
        this.z = (float)skizzik.getZ();
    }

    public boolean canPlaySound() {
        return !this.skizzik.isSilent();
    }

    public boolean canStartSilent() {
        return true;
    }

    public void tick() {
        if (!this.skizzik.isAlive()) {
            this.stop();
        }
        else {
            this.looping = true;
            this.x = (float) this.skizzik.getX();
            this.y = (float) this.skizzik.getY();
            this.z = (float) this.skizzik.getZ();
            //if (!Minecraft.getInstance().getSoundManager().isActive(this)) {
                this.pitch = 1;
                this.volume = 1;
            //}
        }
    }
}
