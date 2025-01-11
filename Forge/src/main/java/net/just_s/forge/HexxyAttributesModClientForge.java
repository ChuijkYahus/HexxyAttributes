package net.just_s.forge;

import net.just_s.HexxyAttributesModClient;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Forge client loading entrypoint.
 */
public class HexxyAttributesModClientForge {
    public static void init(FMLClientSetupEvent event) {
        HexxyAttributesModClient.init();
    }
}
