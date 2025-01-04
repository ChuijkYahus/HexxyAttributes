package net.just_s.power;

import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.calio.data.SerializableData;
import net.just_s.HexxyOriginsMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class SillinessPower extends Power {
    public SillinessPower(PowerType<?> type, LivingEntity entity) {
        super(type, entity);
    }

    public static PowerFactory createFactory() {
        return new PowerFactory<>(Identifier.of(HexxyOriginsMod.MOD_ID, "silliness"),
                new SerializableData(),
                data -> SillinessPower::new);
    }
}
