package net.just_s.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.just_s.HexxyAttributesModClient;

/**
 * Fabric client loading entrypoint.
 */
public class HexxyAttributesModClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HexxyAttributesModClient.init();
    }
}
