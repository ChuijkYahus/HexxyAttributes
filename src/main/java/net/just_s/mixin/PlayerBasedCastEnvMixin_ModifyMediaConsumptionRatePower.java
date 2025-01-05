package net.just_s.mixin;

import at.petrak.hexcasting.api.addldata.ADMediaHolder;
import at.petrak.hexcasting.api.casting.eval.env.PlayerBasedCastEnv;
import at.petrak.hexcasting.api.utils.MediaHelper;
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.component.PowerHolderComponentImpl;
import io.github.apace100.apoli.power.ModifyAttributePower;
import net.just_s.HexxyOriginsMod;
import net.just_s.power.ModifyMediaConsumptionRatePower;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = PlayerBasedCastEnv.class, remap = false)
public abstract class PlayerBasedCastEnvMixin_ModifyMediaConsumptionRatePower {
    @Shadow @Final protected ServerPlayerEntity caster;

    @Redirect(
            method = "extractMediaFromInventory",
            at = @At(value = "INVOKE", target = "Lat/petrak/hexcasting/api/utils/MediaHelper;extractMedia(Lat/petrak/hexcasting/api/addldata/ADMediaHolder;JZZ)J")
    )
    private long hexxyorigins$modify_media_consumption_from_items(ADMediaHolder holder, long cost, boolean drainForBatteries, boolean simulate) {
        // I am not that good in computer math, but this should ease the loss of data between casting long to float
        long concatenated_cost = cost;
        long pow = 0;
        do {
            if (concatenated_cost % 10 != 0) {
                break;
            }
            concatenated_cost /= 10;
            pow++;
        } while (concatenated_cost != 0);

        long base_ten_modifier = 1;
        while (pow-- != 0) {
            base_ten_modifier *= 10;
        }

        float modified_concatenated_cost = PowerHolderComponent.modify(
                this.caster,
                ModifyMediaConsumptionRatePower.class,
                (float) concatenated_cost
        );
        long modified_cost = (long)(modified_concatenated_cost * base_ten_modifier);

        long expected = MediaHelper.extractMedia(holder, cost, drainForBatteries, true);
        // actually drain amount of media we want
        MediaHelper.extractMedia(holder, modified_cost, drainForBatteries, false);
        // return what hex expects to see
        return expected;
    }

    @Redirect(
            method = "extractMediaFromInventory",
            at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(DD)D")
    )
    private double hexxyorigins$modify_media_consumption_from_health(double cost_to_health_ratio, double half_a_heart) {
        double modified_cost_to_health_ratio = PowerHolderComponent.modify(
                this.caster,
                ModifyMediaConsumptionRatePower.class,
                cost_to_health_ratio
        );
        return Math.max(modified_cost_to_health_ratio, half_a_heart);
    }
}
