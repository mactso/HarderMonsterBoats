package com.mactso.hardermonsterboats.modloader.events;

import com.mactso.hardermonsterboats.modloader.config.MyConfig;
import com.mactso.hardermonsterboats.modloader.main.Main;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityMountEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

/**
 * Handles gameplay modifications related to monsters interacting with boats.
 *
 * <p>This class subscribes to NeoForge entity events and applies custom logic
 * based on configuration rules. The behavior includes:
 *
 * <ul>
 *   <li>Allows some entities to get out of boats when they are hurt</li>
 *   <li>Allows monsters to resist mounting boats</li>
 *   <li>Allows configured monsters to break boats instead of mounting them.</li>
 * </ul>
 *
 * <p>All logic is executed server-side unless explicitly noted.
 *
 * <p>Performance considerations:
 * <ul>
 *   <li>No persistent allocations or caching are used</li>
 * </ul>
 */

@EventBusSubscriber(modid = Main.MODID)
public class EventHandler {

	/**
	 * Handles {@link LivingDamageEvent.Pre} allows entities to get out of boats when hurt.
	 *
	 * <p>If a configured non-player entity is in a boat and is hurt, it will get out.
	 *
	 * <p>This prevents players from "cheesing" monsters by using default boat mounting.
	 *
	 * @param event the damage event fired before damage is applied
	 */
	@SubscribeEvent
	public static void onTarget(LivingDamageEvent.Pre event) {

		LivingEntity e = event.getEntity();
		if (!(e instanceof ServerPlayer)) {
			if (e.getVehicle() instanceof Boat) {
				String meRN = EntityType.getKey(e.getType()).toString();
				if (!MyConfig.isWillMonsterNotLeaveBoat(meRN)) {
					e.stopRiding();
				}

			}
		}

	}

	/**
	 * Handles {@link EntityMountEvent} to control monster interactions with boats.
	 *
	 * <p>Depending on configuration, monsters attempting to mount a boat may:
	 *
	 * <ul>
	 *   <li>Break the boat instead of getting in it</li>
	 *   <li>Avoid getting in a boat</li>
	 *   <li>Otherwise: Follows default boat related behavior.</li>
	 * </ul>
	 *
	 * <p>This logic is executed only on the server side.
	 *
	 * @param event the mount event triggered when an entity attempts to mount another entity
	 */
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onMonsterMountingBoatEvent(EntityMountEvent event) {

		if (!(event.getEntityBeingMounted() instanceof Boat boat))
			return;

		if (!(event.getEntity() instanceof Monster me))
			return;

		if (me.level().isClientSide())
			return;

		String meRN = EntityType.getKey(me.getType()).toString();

		if (!MyConfig.isWillMonsterNotHitBoat(meRN)) {

			ServerLevel slevel = (ServerLevel) me.level();
			boat.hurtServer(slevel, me.damageSources().generic(), 6.0f);
			slevel.playSound(null, me, SoundEvents.TURTLE_EGG_CRACK, SoundSource.HOSTILE, 0.5f, 0.5f);

		}

		if (MyConfig.isWillMonsterMountBoat(meRN)) {
			return;
		} else {
			event.setCanceled(true);
			return;
		}

	}
}
