package com.rawngamer13.animespells.spells;

import electroblob.wizardry.spell.Spell;
import electroblob.wizardry.util.SpellModifiers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SpellDoom extends Spell {

    public SpellDoom() {
        super("doom", SpellType.PROJECTILE, "animespells");
        this.hasCastParticles = true;
        this.soundValues(1, 1.1f, 0.1f);
    }

    @Override
    public boolean cast(World world, EntityPlayer caster, Vec3d origin, Vec3d direction, float ticksInUse, int duration, SpellModifiers modifiers) {
        if (!world.isRemote) {
            // Crear el agujero negro
            DoomBlackHole blackHole = new DoomBlackHole(world, origin, caster, modifiers);
            world.spawnEntity(blackHole);
        }
        return true;
    }

    @Override
    public boolean canBeCastBy(EntityLivingBase entity) {
        return entity instanceof EntityPlayer;
    }

    @Override
    public float getManaRequirement() {
        return 200f;
    }

    @Override
    public int getCooldown() {
        return 60; // 3 segundos
    }

    @Override
    public float getDamage(SpellModifiers modifiers) {
        return 15f * modifiers.get(SpellModifiers.POTENCY);
    }

    @Override
    public int getDuration(SpellModifiers modifiers) {
        return (int)(200 * modifiers.get(SpellModifiers.DURATION));
    }
}
