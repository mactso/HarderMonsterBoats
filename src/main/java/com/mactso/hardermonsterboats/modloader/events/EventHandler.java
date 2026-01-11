package com.mactso.hardermonsterboats.modloader.events;

import com.mactso.hardermonsterboats.common.logic.EntityLeaveBoat;
import com.mactso.hardermonsterboats.common.logic.EntityMountBoat;
import com.mactso.hardermonsterboats.modloader.main.Main;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityMountEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = Main.MODID)
public class EventHandler {

    @SubscribeEvent
	    public static void onTarget(LivingDamageEvent.Post event) {
    	
        LivingEntity entity = event.getEntity();
        EntityLeaveBoat.handleEntityDamaged(entity);
        
	    }
	
   
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onMountEvent(EntityMountEvent event) {
        Entity entity = event.getEntity();
        Entity entityBeingMounted = event.getEntityBeingMounted();
        boolean cancel = EntityMountBoat.handleEntityMountBoat(entity, entityBeingMounted);
        if (cancel) {
            event.setCanceled(true);
        }
    }

}
