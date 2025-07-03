package com.mactso.hardermonsterboats.events;

import com.mactso.hardermonsterboats.Main;
import com.mactso.hardermonsterboats.config.MyConfig;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.listener.Priority;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(bus = Bus.FORGE, modid = Main.MODID)
public class EventHandler {

	static boolean CANCEL_EVENT = true;
	static boolean CONTINUE_EVENT = false;

	@SubscribeEvent
	public static boolean onTarget(LivingDamageEvent event) {

		LivingEntity e = event.getEntity();
		if (e instanceof ServerPlayer)
			return CONTINUE_EVENT;

		if (e.getVehicle() instanceof Boat) {
			String meRN = EntityType.getKey(e.getType()).toString();
			if (!MyConfig.isWillMonsterNotLeaveBoat(meRN)) {
				e.stopRiding();
			}

		}
		return CONTINUE_EVENT;

	}

	@SubscribeEvent(priority = Priority.LOW)
	public static boolean onMountEvent(EntityMountEvent event) {

		if (event.getEntityBeingMounted() instanceof Boat boat) {
			if (event.getEntity() instanceof Monster me) {

				String meRN = EntityType.getKey(me.getType()).toString();

				if (!MyConfig.isWillMonsterNotHitBoat(meRN)) {
					boat.hurt(me.damageSources().generic(), 6.0f);
				}

				if (!MyConfig.isWillMonsterMountBoat(meRN)) {
					return CANCEL_EVENT;
				}
			}
		}
		return CONTINUE_EVENT;
	}

}
