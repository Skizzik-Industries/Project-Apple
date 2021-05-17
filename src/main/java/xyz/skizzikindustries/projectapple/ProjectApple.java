package xyz.skizzikindustries.projectapple;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.skizzikindustries.projectapple.init.Register;

@Mod(ProjectApple.MOD_ID)
public class ProjectApple
{
    public static final String MOD_ID = "skizzik";

    public static final Logger LOGGER = LogManager.getLogger();

    public ProjectApple() {
        Register.register();
        MinecraftForge.EVENT_BUS.register(this);
    }
}
