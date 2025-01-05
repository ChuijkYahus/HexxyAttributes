package net.just_s.power;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.calio.data.SerializableData;
import net.just_s.HexxyOriginsMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

/*
 * Actually you can disable casting by setting player's attributes hexcasting:grid_zoom and hexcasting:scry_sight
 * to 0. But if you do that, they will still be able to open Staff's GUI. If any other addon modifies those attributes,
 * your "silly" player will be able to cast.
 *
 * So, if you want to ABSOLUTELY disable hex for an origin, use this power. If you want to give player ability to
 * change his status ingame, use origins:modify_attribute.
 * */
public class IncomprehensiblePower extends Power {
    public IncomprehensiblePower(PowerType<?> type, LivingEntity entity) {
        super(type, entity);
    }

    public static PowerFactory createFactory() {
        return new PowerFactory<>(Identifier.of(HexxyOriginsMod.MOD_ID, "incomprehensible"),
                new SerializableData(),
                data -> IncomprehensiblePower::new);
    }
}
