package net.just_s.mixin;

import at.petrak.hexcasting.api.casting.eval.CastingEnvironment;
import net.just_s.HexxyAttributesMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CastingEnvironment.class)
public abstract class CastingEnvironmentMixin_MediaConsumptionModifier {
    @Shadow public abstract @Nullable LivingEntity getCastingEntity();

    @ModifyVariable(
            method = "extractMedia",
            at = @At("HEAD"), ordinal = 0,
            argsOnly = true, remap = false
    )
    private long hexxyattributes$modify_cost(long cost) {
        if (!(this.getCastingEntity() instanceof PlayerEntity player)) {
            return cost;
        }

        // i am not that bright in computer math, i just hope this might help with casting problem
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
                concatenated_cost * player.getAttributeValue(HexxyAttributesMod.MEDIA_CONSUMPTION_MODIFIER);

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
        return modified_cost;
    }
}
