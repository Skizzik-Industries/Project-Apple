package xyz.skizzikindustries.projectapple.init.block;

import net.minecraft.fluid.*;
import net.minecraftforge.fml.RegistryObject;
import xyz.skizzikindustries.projectapple.block.MapleSyrup;
import xyz.skizzikindustries.projectapple.init.Register;

public class ModFluids {
    public static final RegistryObject<FlowingFluid> MAPLE_SYRUP = Register.FLUIDS.register("maple_syrup", () -> new MapleSyrup.Source(MapleSyrup.createProperties()));
    public static final RegistryObject<FlowingFluid> FLOWING_MAPLE_SYRUP = Register.FLUIDS.register("flowing_maple_syrup", () -> new MapleSyrup.Flowing(MapleSyrup.createProperties()));

    public static void register(){}
}
