package xyz.skizzikindustries.projectapple;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import xyz.skizzikindustries.projectapple.init.Register;

@Mod(ProjectApple.MOD_ID)
public class ProjectApple
{
    public static final String MOD_ID = "skizzik";

    public ProjectApple() {
        Register.register();
        MinecraftForge.EVENT_BUS.register(this);
    }
}
