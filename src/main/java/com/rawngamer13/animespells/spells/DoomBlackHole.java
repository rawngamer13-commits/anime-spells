package com.rawngamer13.animespells.spells;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import electroblob.wizardry.util.SpellModifiers;

public class DoomBlackHole extends Entity {
    private static final String NBT_DAMAGE_TIME = "damageTime";
    private static final String NBT_CASTER_ID = "casterId";
    
    private EntityPlayer caster;
    private int damageTime = 0;
    private float radius = 8f;
    private float maxDuration = 200f;
    private float damage = 15f;
    private int age = 0;

    public DoomBlackHole(World worldIn) {
        super(worldIn);
        this.setSize(1f, 1f);
    }

    public DoomBlackHole(World worldIn, Vec3d pos, EntityPlayer caster, SpellModifiers modifiers) {
        this(worldIn);
        this.setPosition(pos.x, pos.y, pos.z);
        this.caster = caster;
        this.radius = 8f * modifiers.get(SpellModifiers.POTENCY);
        this.maxDuration = 200 * modifiers.get(SpellModifiers.DURATION);
        this.damage = 15f * modifiers.get(SpellModifiers.POTENCY);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        age++;

        if (age > maxDuration) {
            this.setDead();
            return;
        }

        if (!this.world.isRemote) {
            // Atraer entidades
            java.util.List<Entity> entities = this.world.getEntitiesWithinAABBExcludingEntity(
                this,
                this.getEntityBoundingBox().expand(radius, radius, radius)
            );

            for (Entity entity : entities) {
                if (entity instanceof EntityLivingBase && entity != caster) {
                    EntityLivingBase living = (EntityLivingBase) entity;
                    Vec3d direction = new Vec3d(
                        this.posX - entity.posX,
                        this.posY - entity.posY,
                        this.posZ - entity.posZ
                    ).normalize();

                    // Atracción
                    double force = 0.3f * (1 - (age / maxDuration));
                    entity.motionX += direction.x * force;
                    entity.motionY += direction.y * force;
                    entity.motionZ += direction.z * force;

                    // Daño progresivo
                    damageTime++;
                    if (damageTime >= 20) { // Daño cada segundo
                        living.attackEntityFrom(
                            net.minecraft.util.DamageSource.MAGIC,
                            damage
                        );
                        damageTime = 0;
                    }
                }
            }
        } else {
            // Efectos visuales en cliente
            spawnParticles();
        }
    }

    private void spawnParticles() {
        // Partículas del agujero negro (bordes rojos)
        for (int i = 0; i < 5; i++) {
            double angle = Math.random() * Math.PI * 2;
            double distance = Math.random() * radius;
            double x = this.posX + Math.cos(angle) * distance;
            double y = this.posY + (Math.random() - 0.5f) * radius;
            double z = this.posZ + Math.sin(angle) * distance;

            this.world.spawnParticle(
                net.minecraft.client.particle.EnumParticleTypes.REDSTONE,
                x, y, z,
                Math.random() * 0.5 - 0.25,
                Math.random() * 0.5 - 0.25,
                Math.random() * 0.5 - 0.25
            );
        }
    }

    @Override
    protected void entityInit() {
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        if (compound.hasKey(NBT_DAMAGE_TIME)) {
            this.damageTime = compound.getInteger(NBT_DAMAGE_TIME);
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger(NBT_DAMAGE_TIME, damageTime);
    }
}
