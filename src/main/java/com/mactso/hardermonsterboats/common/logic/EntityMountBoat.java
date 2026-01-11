package com.mactso.hardermonsterboats.common.logic;

import com.mactso.hardermonsterboats.modloader.config.MyConfig;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;

public final class EntityMountBoat {

    private EntityMountBoat() {}

    /**
     * Returns true if the mount should be canceled.
     */
    public static boolean handleEntityMountBoat(Entity entity, Entity mountedEntity) {

        // Only care about Monster mounting a Boat
    	// might check animals too (on a config parm) later on tho.
        if (!(entity instanceof Monster monster)) return false;
        if (!(mountedEntity instanceof Boat boat)) return false;

        String monsterId = EntityType.getKey(monster.getType()).toString();

        // Damage boat if configured
        if (!MyConfig.isWillMonsterNotHitBoat(monsterId)) {
            Level level = monster.level();

            if (level instanceof ServerLevel serverLevel) {
                boat.hurtServer(serverLevel, monster.damageSources().generic(), 6.0f);
                serverLevel.playSound(
                        null,
                        monster,
                        SoundEvents.TURTLE_EGG_CRACK,
                        SoundSource.HOSTILE,
                        0.5f,
                        0.5f
                );
            } else {
                boat.hurtClient(monster.damageSources().generic());
            }
        }

        // Decide if mount should be canceled
        return !MyConfig.isWillMonsterMountBoat(monsterId);
    }
}