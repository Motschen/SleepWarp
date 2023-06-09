package me.thegiggitybyte.sleepwarp;

import me.thegiggitybyte.sleepwarp.config.JsonConfiguration;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.util.ActionResult;

public class SleepWarp {
    public static class Common implements ModInitializer {
        @Override
        public void onInitialize() {
            JsonConfiguration.getUserInstance();
            Commands.register();
            
            ServerTickEvents.END_WORLD_TICK.register(WarpDrive::engage);
            EntitySleepEvents.ALLOW_SLEEP_TIME.register((player, sleepingPos, vanillaResult) -> {
                if (!vanillaResult && (player.getWorld().getTimeOfDay() % 24000 > 12542))
                    return ActionResult.SUCCESS;
                else
                    return ActionResult.PASS;
            });
        }
    }
    
    public static class Client implements ClientModInitializer {
        @Override
        public void onInitializeClient() {
            // TODO: client-side third person animation.
        }
    }
}
