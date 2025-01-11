package net.just_s;

import dev.architectury.registry.registries.DeferredRegister;
import net.just_s.registry.HexxyAttributesPatternRegistry;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is effectively the loading entrypoint for most of your code, at least
 * if you are using Architectury as intended.
 */
public class HexxyAttributesMod {
    public static final String MOD_ID = "hexxyattributes";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final DeferredRegister<EntityAttribute> ATTRIBUTES = DeferredRegister.create(MOD_ID, Registry.ATTRIBUTE_KEY);

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

    private static EntityAttribute createAttribute(final String name, double base, double min, double max) {
        return new ClampedEntityAttribute("attribute.name.generic." + MOD_ID + '.' + name, base, min, max).setTracked(true);
    }

    private static void register(String id, EntityAttribute attribute) {
        //Registry.register(Registries.ATTRIBUTE, new Identifier(MOD_ID, id), attribute);
        ATTRIBUTES.register(id, () -> attribute);
    }

    public static void init() {
        HexxyAttributesModAbstractions.initPlatformSpecific();
        HexxyAttributesPatternRegistry.init();

        register("feeble_mind", FEEBLE_MIND);
        register("media_consumption_modifier", MEDIA_CONSUMPTION_MODIFIER);
        register("ambit_radius", AMBIT_RADIUS);
        register("sentinel_radius", SENTINEL_RADIUS);

        ATTRIBUTES.register();

        LOGGER.info("hexxy attributes here");
    }

    /**
     * Shortcut for identifiers specific to this mod.
     */
    public static Identifier id(String string) {
        return new Identifier(MOD_ID, string);
    }
}
