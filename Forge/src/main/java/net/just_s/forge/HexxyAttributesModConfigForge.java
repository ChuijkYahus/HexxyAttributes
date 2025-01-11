package net.just_s.forge;

import at.petrak.hexcasting.api.misc.MediaConstants;
import net.just_s.api.config.HexxyAttributesModConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class HexxyAttributesModConfigForge {

    public static void init() {
        Pair<Server, ForgeConfigSpec> serverConfig = (new ForgeConfigSpec.Builder()).configure(Server::new);
        HexxyAttributesModConfig.setHexxyConfig(serverConfig.getLeft());
        ModLoadingContext mlc = ModLoadingContext.get();
        mlc.registerConfig(ModConfig.Type.SERVER, serverConfig.getRight());
    }

    public static class Server implements HexxyAttributesModConfig.ServerConfigAccess {
        // costs of actions
        private static ForgeConfigSpec.DoubleValue mindPurificationCost;
        private static ForgeConfigSpec.DoubleValue domainReflectionCost;
        private static ForgeConfigSpec.DoubleValue sentinelDomainReflectionCost;
        private static ForgeConfigSpec.DoubleValue mediaReflectionCost;

        public Server(ForgeConfigSpec.Builder builder) {
            builder.translation("text.autoconfig.hexxyattributes.option.server.costs").push("costs");

            mindPurificationCost = builder.translation("text.autoconfig.hexxyattributes.option.server.costs.mindPurificationCost").defineInRange("mindPurificationCost", DEFAULT_MIND_PURIFICATION_COST, DEF_MIN_COST, DEF_MAX_COST);
            domainReflectionCost = builder.translation("text.autoconfig.hexxyattributes.option.server.costs.domainReflectionCost").defineInRange("domainReflectionCost", DEFAULT_DOMAIN_REFLECTION_COST, DEF_MIN_COST, DEF_MAX_COST);
            sentinelDomainReflectionCost = builder.translation("text.autoconfig.hexxyattributes.option.server.costs.sentinelDomainReflectionCost").defineInRange("sentinelDomainReflectionCost", DEFAULT_SENTINEL_DOMAIN_REFLECTION_COST, DEF_MIN_COST, DEF_MAX_COST);
            mediaReflectionCost = builder.translation("text.autoconfig.hexxyattributes.option.server.costs.mediaReflectionCost").defineInRange("mediaReflectionCost", DEFAULT_MEDIA_REFLECTION_COST, DEF_MIN_COST, DEF_MAX_COST);

            builder.pop();
        }

        @Override
        public int getMindPurificationCost() {
            return (int) (mindPurificationCost.get() * MediaConstants.DUST_UNIT);
        }

        @Override
        public int getDomainReflectionCost() {
            return (int) (domainReflectionCost.get() * MediaConstants.DUST_UNIT);
        }

        @Override
        public int getSentinelDomainReflectionCost() {
            return (int) (sentinelDomainReflectionCost.get() * MediaConstants.DUST_UNIT);
        }

        @Override
        public int getMediaReflectionCost() {
            return (int) (mediaReflectionCost.get() * MediaConstants.DUST_UNIT);
        }
    }
}