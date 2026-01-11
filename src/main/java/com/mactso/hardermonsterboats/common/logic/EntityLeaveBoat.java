package com.mactso.hardermonsterboats.common.logic;

import com.mactso.hardermonsterboats.modloader.config.MyConfig;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.Boat;

public class EntityLeaveBoat {

    private EntityLeaveBoat() {} // no instantiation

    public static void handleEntityDamaged(LivingEntity entity) {

        if (entity.level().isClientSide()) {
            return;
        }

        // Ignore players
        if (entity instanceof ServerPlayer) {
            return;
        }

        // Only care about mobs riding boats
        if (!(entity.getVehicle() instanceof Boat)) {
            return;
        }

        String entityRegistryName = EntityType.getKey(entity.getType()).toString();

        if (!MyConfig.isWillMonsterNotLeaveBoat(entityRegistryName)) {
            entity.stopRiding();
        }
    }
    
}
