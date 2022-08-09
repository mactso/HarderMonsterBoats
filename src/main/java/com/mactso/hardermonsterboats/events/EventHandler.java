package com.mactso.hardermonsterboats.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mactso.hardermonsterboats.Main;
import com.mactso.hardermonsterboats.config.MyConfig;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(bus = Bus.FORGE, modid = Main.MODID)
public class EventHandler {
	private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void onTarget(LivingDamageEvent event)
    {
    	
        LivingEntity e = event.getEntityLiving();
        if (e.getVehicle() instanceof Boat) {
			String meRN = e.getType().getRegistryName().toString();
			if (!MyConfig.isWillMonsterNotLeaveBoat(meRN)) {
	        	e.stopRiding();
			}

        }
        
    }
    
    
	
	@SubscribeEvent(priority = EventPriority.LOW)
	public void onMountEvent(EntityMountEvent event) {

		if (event.getEntityBeingMounted() instanceof Boat boat) {
			if (event.getEntity() instanceof Monster me) {

				String meRN = me.getType().getRegistryName().toString();

				if (!MyConfig.isWillMonsterNotHitBoat(meRN)) {
					boat.hurt(DamageSource.GENERIC, 6.0f);
				}

				if (!MyConfig.isWillMonsterMountBoat(meRN)) {
					if (event.isCancelable()) {
						event.setCanceled(true);
						return;
					}
				}
			}
		}
	}
}
