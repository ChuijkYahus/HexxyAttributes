package net.just_s;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HexxyAttributesMod implements ModInitializer {
	public static final String MOD_ID = "hexxyattributes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final EntityAttribute FEEBLE_MIND = createAttribute(
			"player.feeble_mind",
			0.0D, 0.0D, 1.0D
	);
	public static final EntityAttribute MEDIA_CONSUMPTION_MODIFIER = createAttribute(
			"player.media_consumption_modifier",
			1.0D, 0.0D, Double.MAX_VALUE
	);

	private static void register(String id, EntityAttribute attribute) {
		Registry.register(Registries.ATTRIBUTE, new Identifier(MOD_ID, id), attribute);
	}

	private static EntityAttribute createAttribute(final String name, double base, double min, double max) {
		return new ClampedEntityAttribute("attribute.name.generic." + MOD_ID + '.' + name, base, min, max).setTracked(true);
	}

	@Override
	public void onInitialize() {
		register("feeble_mind", FEEBLE_MIND);
		register("media_consumption_modifier", MEDIA_CONSUMPTION_MODIFIER);

		LOGGER.info("hexxy attributes here");
	}
}