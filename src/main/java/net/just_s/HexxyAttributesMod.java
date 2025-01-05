package net.just_s;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class HexxyAttributesMod implements ModInitializer {
	public static final String MOD_ID = "hexxyattributes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Consider this as a boolean attribute, like hexcasting:scry_sight.
	// If player has Feeble Mind, he will not be able to open Staff's casting GUI.
	// Players with feeble minds won't be able to use Scrying lenses, too.
	// They are still able to use artifacts tho!!
	public static final EntityAttribute FEEBLE_MIND = createAttribute(
			"player.feeble_mind",
			0.0D, 0.0D, 1.0D
	);
	// This sets the multiplying modifier:
	// Imagine cost for the cast is 100 media points.
	// The player with modifier of 0.5 will only pay 50 media points.
	// On the other hand, player with modifier 1.7 will have to pay 170 media points.
	// This also applies to taken health.
	public static final EntityAttribute MEDIA_CONSUMPTION_MODIFIER = createAttribute(
			"player.media_consumption_modifier",
			1.0D, 0.0D, Double.MAX_VALUE
	);

	// Those two attributes are self-explanatory.
	public static final EntityAttribute AMBIT_RADIUS = createAttribute(
			"player.ambit_radius",
			32.0D, 0.0D, Double.MAX_VALUE
	);
	public static final EntityAttribute SENTINEL_RADIUS = createAttribute(
			"player.sentinel_radius",
			16.0D, 0.0D, Double.MAX_VALUE
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
		register("ambit_radius", AMBIT_RADIUS);
		register("sentinel_radius", SENTINEL_RADIUS);

		LOGGER.info("hexxy attributes here");
	}

	// this method is used in mixin, moved code here for easier development in the future.
	public static void addHexxyAttributes(final CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
		info.getReturnValue()
				.add(HexxyAttributesMod.FEEBLE_MIND)
				.add(HexxyAttributesMod.MEDIA_CONSUMPTION_MODIFIER)
				.add(HexxyAttributesMod.AMBIT_RADIUS)
				.add(HexxyAttributesMod.SENTINEL_RADIUS);
	}
}