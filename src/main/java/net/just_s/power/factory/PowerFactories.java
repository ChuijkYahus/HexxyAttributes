package net.just_s.power.factory;

import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.power.ModifyAirSpeedPower;
import io.github.apace100.apoli.power.ValueModifyingPower;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.power.factory.PowerFactorySupplier;
import io.github.apace100.apoli.registry.ApoliRegistries;
import net.just_s.HexxyOriginsMod;
import net.just_s.power.IncomprehensiblePower;
import net.just_s.power.ModifyMediaConsumptionRatePower;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class PowerFactories {
    public static void register() {
        register(IncomprehensiblePower::createFactory);
        register(() -> ValueModifyingPower.createValueModifyingFactory(
                ModifyMediaConsumptionRatePower::new,
                Identifier.of(HexxyOriginsMod.MOD_ID, "modify_media_consumption_rate")
        ));
    }

    private static void register(PowerFactory<?> powerFactory) {
        Registry.register(ApoliRegistries.POWER_FACTORY, powerFactory.getSerializerId(), powerFactory);
    }

    private static void register(PowerFactorySupplier<?> factorySupplier) {
        register(factorySupplier.createFactory());
    }
}
