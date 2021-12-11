package com.skizzium.projectapple.init.block;

import com.skizzium.projectapple.block.MapleSyrup;
import com.skizzium.projectapple.init.PA_Registry;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.registries.RegistryObject;

public class PA_Fluids {
    public static final RegistryObject<FlowingFluid> MAPLE_SYRUP = PA_Registry.FLUIDS.register("maple_syrup", () -> new MapleSyrup.Source(MapleSyrup.createProperties()));
    public static final RegistryObject<FlowingFluid> FLOWING_MAPLE_SYRUP = PA_Registry.FLUIDS.register("flowing_maple_syrup", () -> new MapleSyrup.Flowing(MapleSyrup.createProperties()));

    public static void register(){}
}
