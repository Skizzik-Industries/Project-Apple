package com.skizzium.projectapple.init;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.ResourceLocationException;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_Config {
    public static class ClientOptions {
        public final ForgeConfigSpec.BooleanValue discordRPC;

        ClientOptions(ForgeConfigSpec.Builder builder) {
            builder.comment("Client Options").push("worldGen");

            this.discordRPC = builder
                    .comment("Determines whether or not to display information about your current boss fight via Discord's rich presence (DiscordRPC).")
                    .define("enableDiscordRPC", true);

            builder.pop();
        }
    }
    
    public static class Blocks {
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> corruptionImmuneBlocks;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> rainbowSwordImmuneBlocks;

        Blocks(ForgeConfigSpec.Builder builder) {
            builder.comment("Block Settings").push("blocks");

            this.corruptionImmuneBlocks = builder
                    .comment("Not Functional Yet!", "Add any blocks you want to be immune from being turned into Corrupted Blocks here.", "Please note that Rainbow Sword immune blocks are also Corruption immune, so no need to add them here.")
                    .defineList("corruptionImmuneBlocks", Collections.emptyList(), id -> {
                        try {
                            ResourceLocation.tryParse(id.toString());
                            return true;
                        }
                        catch(ResourceLocationException e) {
                            return false;
                        }
                    });

            this.rainbowSwordImmuneBlocks = builder
                    .comment("Not Functional Yet!", "Add any blocks you want to be immune from being teleported by a Rainbow Sword here.")
                    .defineList("rainbowSwordImmuneBlocks", Collections.emptyList(), id -> {
                        try {
                            ResourceLocation.tryParse(id.toString());
                            return true;
                        }
                        catch(ResourceLocationException e) {
                            return false;
                        }
                    });

            builder.pop();
        }
    }

    public static class Entities {
        public final ForgeConfigSpec.BooleanValue allowSkizzieConversion;
        public final ForgeConfigSpec.IntValue skizzieStatueChances;
        public final ForgeConfigSpec.BooleanValue allowCorruptedSkizzieAbility;
        public final ForgeConfigSpec.BooleanValue convertWithDragonEgg;

        Entities(ForgeConfigSpec.Builder builder) {
            builder.comment("Entity Settings").push("entities");

            this.allowSkizzieConversion = builder
                    .comment("Determines whether or not Skizzies can be converted.")
                    .define("allowSkizzieConversion", true);
            this.skizzieStatueChances = builder
                    .comment("Determines the chance of a Skizzie turning into a statue (in percentages) when converted between normal and friendly. Set to 0% to disable.")
                    .defineInRange("skizzieStatueChances", 30, 0, 100);
            this.allowCorruptedSkizzieAbility = builder
                    .comment("Determines whether or not Corrupted Skizzies will turn blocks under them into Corrupted Blocks.")
                    .define("allowCorruptedSkizzieAbility", true);
            this.convertWithDragonEgg = builder
                    .comment("Determines whether the Skizzik will require a Dragon Egg when first converted. If disabled, a nether star will always be needed.")
                    .define("convertWithDragonEgg", true);

            builder.pop();
        }
    }
    
    public static class Client {
        public final ClientOptions clientOptions;

        public Client(ForgeConfigSpec.Builder builder) {
            builder.push("client");

            this.clientOptions = new ClientOptions(builder);

            builder.pop();
        }
    }

    public static class Common {
        public final Blocks blocks;
        public final Entities entities;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("common");

            this.blocks = new Blocks(builder);
            this.entities = new Entities(builder);

            builder.pop();
        }
    }

    public static class Server {
        public final WorldGen worldGen;

        public Server(ForgeConfigSpec.Builder builder) {
            builder.push("server");

            this.worldGen = new WorldGen(builder);

            builder.pop();
        }
    }

    public static final ForgeConfigSpec clientSpec;
    public static final Client clientInstance;

    public static final ForgeConfigSpec commonSpec;
    public static final Common commonInstance;

    public static final ForgeConfigSpec serverSpec;
    public static final Server serverInstance;

    static {
        final Pair<Client, ForgeConfigSpec> clientPair = new ForgeConfigSpec.Builder().configure(Client::new);
        clientSpec = clientPair.getRight();
        clientInstance = clientPair.getLeft();
        
        final Pair<Common, ForgeConfigSpec> commonPair = new ForgeConfigSpec.Builder().configure(Common::new);
        commonSpec = commonPair.getRight();
        commonInstance = commonPair.getLeft();

        final Pair<Server, ForgeConfigSpec> serverPair = new ForgeConfigSpec.Builder().configure(Server::new);
        serverSpec = serverPair.getRight();
        serverInstance = serverPair.getLeft();
    }
}
