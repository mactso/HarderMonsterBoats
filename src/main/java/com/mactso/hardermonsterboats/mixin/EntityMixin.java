package com.mactso.hardermonsterboats.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mactso.hardermonsterboats.events.EventHandler;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.boat.Boat;


@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(method = "startRiding", at = @At("HEAD"), cancellable = true)
    private void onStartRiding(Entity vehicle, CallbackInfoReturnable<Boolean> cir) {
        if (!(vehicle instanceof Boat)) {
            return;
        }

        if (!EventHandler.canEntityMountBoat(vehicle, (Entity)(Object)this)) {
            cir.setReturnValue(false);
        }
    }
}
