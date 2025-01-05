package net.just_s.mixin;

import at.petrak.hexcasting.api.addldata.ADMediaHolder;
import at.petrak.hexcasting.api.casting.eval.env.PlayerBasedCastEnv;
import at.petrak.hexcasting.api.utils.MediaHelper;
import net.just_s.HexxyAttributesMod;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = PlayerBasedCastEnv.class, remap = false)
public abstract class PlayerBasedCastEnvMixin_MediaConsumptionModifierAttribute {
    @Shadow @Final protected ServerPlayerEntity caster;

    @Redirect(
            method = "extractMediaFromInventory",
            at = @At(value = "INVOKE", target = "Lat/petrak/hexcasting/api/utils/MediaHelper;extractMedia(Lat/petrak/hexcasting/api/addldata/ADMediaHolder;JZZ)J")
    )
    private long hexxyattributes$modify_media_consumption_from_items(ADMediaHolder holder, long cost, boolean drainForBatteries, boolean simulate) {
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

        double modified_concatenated_cost =
                concatenated_cost * this.caster.getAttributeValue(HexxyAttributesMod.MEDIA_CONSUMPTION_MODIFIER);

        long modified_cost = 1;
        do {
            if (Double.compare(modified_concatenated_cost % 1, 0d) == 0) {
                modified_cost = (long)modified_concatenated_cost;
                break;
            }
            modified_concatenated_cost *= 10;
        } while (--pow != 0);
        while (pow-- != 0) {
            modified_cost *= 10;
        }
        if (modified_cost == 1) {
            modified_cost = (long)modified_concatenated_cost;
        }

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
    private double hexxyattributes$modify_media_consumption_from_health(double cost_to_health_ratio, double half_a_heart) {
        double modified_cost_to_health_ratio =
                cost_to_health_ratio * this.caster.getAttributeValue(HexxyAttributesMod.MEDIA_CONSUMPTION_MODIFIER);
        return Math.max(modified_cost_to_health_ratio, half_a_heart);
    }
}
