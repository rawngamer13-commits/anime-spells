package com.rawngamer13.animespells.event;

import com.rawngamer13.animespells.spells.DoomBlackHole;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber
public class EntityRegisterHandler {
    
    public static void registerEntities() {
        EntityRegistry.registerModEntity(
            DoomBlackHole.class,
            "doom_black_hole",
            0,
            "animespells",
            64,
            10,
            true
        );
    }
}
