package net.just_s.power;

import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.ValueModifyingPower;
import net.minecraft.entity.LivingEntity;

public class ModifyMediaConsumptionRatePower extends ValueModifyingPower {
    public ModifyMediaConsumptionRatePower(PowerType<?> type, LivingEntity entity) {
        super(type, entity);
    }
}
