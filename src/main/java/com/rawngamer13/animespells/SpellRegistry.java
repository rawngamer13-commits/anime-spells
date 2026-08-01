package com.rawngamer13.animespells;

import com.rawngamer13.animespells.spells.SpellDoom;
import electroblob.wizardry.registry.WizardryItems;
import electroblob.wizardry.spell.Spell;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SpellRegistry {
    public static SpellDoom DOOM;

    public static void registerSpells() {
        DOOM = new SpellDoom();
        Spell.registerSpell(DOOM);
        AnimespellsMod.logger.info("Registered Doom spell");
    }
}
