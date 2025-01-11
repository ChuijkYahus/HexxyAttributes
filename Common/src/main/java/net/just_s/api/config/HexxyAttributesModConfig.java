
package net.just_s.api.config;

import net.just_s.HexxyAttributesMod;

import java.util.Collection;

/**
 * Platform-agnostic class for statically accessing current config values.
 * If any of the config types (common, client, server) are not needed in your mod,
 * feel free to remove anything related to them in this class and platform-specific config implementations.
 */
public class HexxyAttributesModConfig {
    private static final ServerConfigAccess hexxyConfig = new ServerConfigAccess() {
        @Override
        public int getDomainReflectionCost() {
            throw new IllegalStateException("Attempted to access property of Config Object");
        }

        @Override
        public int getSentinelDomainReflectionCost() {
            throw new IllegalStateException("Attempted to access property of Config Object");
        }

        @Override
        public int getMindPurificationCost() {
            throw new IllegalStateException("Attempted to access property of Config Object");
        }

        @Override
        public int getMediaReflectionCost() {
            throw new IllegalStateException("Attempted to access property of Config Object");
        }
    };
    private static ServerConfigAccess server = hexxyConfig;

    public static ServerConfigAccess getHexxyConfig() {
        return server;
    }

    public static void setHexxyConfig(ServerConfigAccess server) {

        if (HexxyAttributesModConfig.server != hexxyConfig) {
            HexxyAttributesMod.LOGGER.warn("ServerConfigAccess was replaced! Old {} New {}", HexxyAttributesModConfig.server.getClass().getName(), server.getClass().getName());
        }
        HexxyAttributesModConfig.server = server;
    }

    public static int bound(int toBind, int lower, int upper) {
        return Math.min(Math.max(toBind, lower), upper);
    }

    public static double bound(double toBind, double lower, double upper) {
        return Math.min(Math.max(toBind, lower), upper);
    }

    public interface ServerConfigAccess {
        double DEF_MIN_COST = 0; //0.0001;
        double DEF_MAX_COST = 10_000.0;
        double DEFAULT_MIND_PURIFICATION_COST = 1.5;
        double DEFAULT_DOMAIN_REFLECTION_COST = 0;
        double DEFAULT_SENTINEL_DOMAIN_REFLECTION_COST = 0;
        double DEFAULT_MEDIA_REFLECTION_COST = 0;

        int getDomainReflectionCost();

        int getSentinelDomainReflectionCost();

        int getMindPurificationCost();

        int getMediaReflectionCost();
    }
}
