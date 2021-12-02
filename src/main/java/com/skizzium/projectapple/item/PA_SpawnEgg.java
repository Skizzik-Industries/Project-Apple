package com.skizzium.projectapple.item;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.RegistryObject;
import org.antlr.v4.runtime.misc.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_SpawnEgg extends SpawnEggItem {
    private static final List<PA_SpawnEgg> UNADDED_EGGS = new ArrayList<>(1);
    private final Lazy<? extends EntityType<?>> supplier;

    public PA_SpawnEgg(RegistryObject<? extends EntityType<?>> supplier, int primaryColor, int secondaryColor, Properties properties) {
        super(null, primaryColor, secondaryColor, properties);
        this.supplier = Lazy.of(supplier);
        UNADDED_EGGS.add(this);
    }

    @Override
    public @NotNull EntityType<?> getType(@Nullable CompoundTag compoundNBT) {
        return supplier.get();
    }

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            try {
                Map<EntityType<?>, SpawnEggItem> spawnEggItemMap = ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "f_43201_");
                Minecraft minecraft = Minecraft.getInstance();
                for (PA_SpawnEgg spawnEggItem : UNADDED_EGGS) {
                    if (spawnEggItemMap != null) {
                        spawnEggItemMap.put(spawnEggItem.supplier.get(), spawnEggItem);
                    }
                    minecraft.getItemColors().register((color1, color2) -> spawnEggItem.getColor(color2), spawnEggItem);
                }
            } catch (Exception e) {
                ProjectApple.LOGGER.warn("Unable to access SpawnEggItem.BY_ID: " + e);
            }
        });
    }
}