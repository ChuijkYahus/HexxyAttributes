package net.just_s.forge;

import net.just_s.HexxyAttributesModAbstractions;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class HexxyAttributesModAbstractionsImpl {
    /**
     * This is the actual implementation of {@link HexxyAttributesModAbstractions#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
	
    public static void initPlatformSpecific() {
        HexxyAttributesModConfigForge.init();
    }
}
