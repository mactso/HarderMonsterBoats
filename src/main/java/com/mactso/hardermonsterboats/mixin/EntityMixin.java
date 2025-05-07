package com.mactso.hardermonsterboats.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mactso.hardermonsterboats.events.EventHandler;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;

@Mixin(Entity.class)
public abstract class EntityMixin {
    
    @Inject(method = "startRiding(Lnet/minecraft/world/entity/Entity;Z)Z", at = @At("HEAD"), cancellable = true)
    private void isBoatMountable(Entity vehicle, boolean force, CallbackInfoReturnable<Boolean> cir) {
    	if (!(vehicle instanceof Boat)) {
            return;
    	}
    	
        if (!(EventHandler.canEntityMountBoat(vehicle, (Entity) (Object) this))) {
//            System.out.println("Blocked entity from entering boat: " + this.getClass().getSimpleName());
            cir.setReturnValue(false); // Prevent the entity from mounting the boat
        }
    }
}
