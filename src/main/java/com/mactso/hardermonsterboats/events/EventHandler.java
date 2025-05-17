package com.mactso.hardermonsterboats.events;

import com.mactso.hardermonsterboats.Main;
import com.mactso.hardermonsterboats.config.MyConfig;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.event.entity.EntityMountEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(bus = Bus.GAME, modid = Main.MODID)
public class EventHandler {

	@SubscribeEvent
    public static void onTarget(LivingDamageEvent.Pre event)
    {
    	
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
    
    
	
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onMountEvent(EntityMountEvent event) {

		if (event.getEntityBeingMounted() instanceof Boat boat) {
			if (event.getEntity() instanceof Monster me) {

				String meRN = EntityType.getKey(me.getType()).toString();

				if (!MyConfig.isWillMonsterNotHitBoat(meRN)) {
					// boat.hurt(me.damageSources().generic(), 6.0f);
					Level level = me.level();
					if (level instanceof ServerLevel slevel) {
						boat.hurtServer(slevel, me.damageSources().generic(), 6.0f);
						slevel.playSound(null, me, SoundEvents.TURTLE_EGG_CRACK, SoundSource.HOSTILE, 0.5f, 0.5f);
					} else {
						boat.hurtClient(me.damageSources().generic());
					}
				    
				}

				if (MyConfig.isWillMonsterMountBoat(meRN)) {
					return;
				} else {
					event.setCanceled(true);
					return;
				}
			}
		}
	}
}
