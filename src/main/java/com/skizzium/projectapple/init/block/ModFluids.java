package com.skizzium.projectapple.init.block;

import com.skizzium.projectapple.block.MapleSyrup;
import com.skizzium.projectapple.init.Register;
import net.minecraft.fluid.*;
import net.minecraftforge.fml.RegistryObject;

public class ModFluids {
    public static final RegistryObject<FlowingFluid> MAPLE_SYRUP = Register.FLUIDS.register("maple_syrup", () -> new MapleSyrup.Source(MapleSyrup.createProperties()));
    public static final RegistryObject<FlowingFluid> FLOWING_MAPLE_SYRUP = Register.FLUIDS.register("flowing_maple_syrup", () -> new MapleSyrup.Flowing(MapleSyrup.createProperties()));

    public static void register(){}
}
