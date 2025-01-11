package net.just_s.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.just_s.HexxyAttributesModAbstractions;

import java.nio.file.Path;

public class HexxyAttributesModAbstractionsImpl {
    /**
     * This is the actual implementation of {@link HexxyAttributesModAbstractions#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
	
    public static void initPlatformSpecific() {
        HexxyAttributesModConfigFabric.init();
    }
}
