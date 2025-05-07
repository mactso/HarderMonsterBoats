package com.mactso.hardermonsterboats.events;

import com.mactso.hardermonsterboats.config.MyConfigs;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.vehicle.Boat;
//import net.minecraftforge.event.entity.EntityMountEvent;
//import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class EventHandler {

	public static void onEntityActuallyHurt(LivingEntity e) {

		if ((e instanceof ServerPlayer))
			return;

		if (!(e.getVehicle() instanceof Boat))
			return;

		String meRN = EntityType.getKey(e.getType()).toString();
		if (!MyConfigs.isWillMonsterNotLeaveBoat(meRN)) {
			e.stopRiding();
		}

	}

	public static boolean canEntityMountBoat(Entity boat, Entity e) {

	
		if (!(e instanceof Monster me))
			return true;

		String meRN = EntityType.getKey(me.getType()).toString();

		if (!MyConfigs.isWillMonsterNotHitBoat(meRN)) {
			boat.hurt(me.damageSources().generic(), 6.0f);
		}

		if (MyConfigs.isWillMonsterMountBoat(meRN)) {
			return true;
		}
		return false;
	}

}
