package com.rawngamer13.animespells;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

import com.rawngamer13.animespells.spells.DoomBlackHole;
import com.rawngamer13.animespells.event.EntityRegisterHandler;

@Mod(
    modid = AnimespellsMod.MODID,
    name = AnimespellsMod.NAME,
    version = AnimespellsMod.VERSION,
    dependencies = "required-after:wizardry"
)
public class AnimespellsMod {
    public static final String MODID = "animespells";
    public static final String NAME = "Anime Spells";
    public static final String VERSION = "1.0.0";

    public static Logger logger;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.info("Anime Spells initializing...");
        
        // Registrar entidades
        EntityRegistry.registerModEntity(
            DoomBlackHole.class,
            "doom_black_hole",
            0,
            MODID,
            64,
            10,
            true
        );
        
        SpellRegistry.registerSpells();
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        logger.info("Anime Spells initialized successfully!");
        
        // Registrar event handlers
        if (event.getSide() == Side.CLIENT) {
            net.minecraftforge.fml.common.FMLCommonHandler.instance().bus().register(
                new com.rawngamer13.animespells.event.SkyRenderHandler()
            );
        }
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        logger.info("Anime Spells post-initialized!");
    }
}
