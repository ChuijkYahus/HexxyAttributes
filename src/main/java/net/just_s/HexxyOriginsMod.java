package net.just_s;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.origins.component.OriginComponent;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.registry.ModComponents;
import net.fabricmc.api.ModInitializer;

import net.just_s.power.IncomprehensiblePower;
import net.just_s.power.factory.PowerFactories;
import net.minecraft.entity.player.PlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class HexxyOriginsMod implements ModInitializer {
	public static final String MOD_ID = "hexxyorigins";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		PowerFactories.register();
		LOGGER.info("hexxy origins here");
	}

	public static boolean hasSilliness(PlayerEntity player) {
		OriginComponent originComponent = ModComponents.ORIGIN.get(player);
		for (Map.Entry<OriginLayer, Origin> map: originComponent.getOrigins().entrySet()) {
			Origin origin = map.getValue();
			for (PowerType<?> powerType : origin.getPowerTypes()) {
				Power power = powerType.get(player);
				if (power == null) {
					continue;
				}

				if (power instanceof IncomprehensiblePower) {
					return true;
				}
			}
		}
		return false;
	}
}