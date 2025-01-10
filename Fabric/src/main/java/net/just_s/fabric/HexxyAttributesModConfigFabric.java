package net.just_s.fabric;

import at.petrak.hexcasting.api.misc.MediaConstants;
import dev.architectury.platform.Platform;
import net.just_s.HexxyAttributesMod;
import net.just_s.api.config.HexxyAttributesModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.EnvType;

@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal"})
@Config(name = HexxyAttributesMod.MOD_ID)
public class HexxyAttributesModConfigFabric extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("server")
    @ConfigEntry.Gui.TransitiveObject
    public final Server server = new Server();

    public static void init() {
        AutoConfig.register(HexxyAttributesModConfigFabric.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        var instance = AutoConfig.getConfigHolder(HexxyAttributesModConfigFabric.class).getConfig();

        // Needed for logical server in singleplayer, do not access server configs from client code
        HexxyAttributesModConfig.setHexxyConfig(instance.server);
    }

    @Config(name = "server")
    private static class Server implements ConfigData, HexxyAttributesModConfig.ServerConfigAccess {

        @ConfigEntry.Gui.CollapsibleObject
        private Costs costs = new Costs();

        @Override
        public void validatePostLoad() throws ValidationException {
            this.costs.mindPurificationCost = HexxyAttributesModConfig.bound(this.costs.mindPurificationCost, DEF_MIN_COST, DEF_MAX_COST);
            this.costs.domainReflectionCost = HexxyAttributesModConfig.bound(this.costs.domainReflectionCost, DEF_MIN_COST, DEF_MAX_COST);
            this.costs.sentinelDomainReflectionCost = HexxyAttributesModConfig.bound(this.costs.sentinelDomainReflectionCost, DEF_MIN_COST, DEF_MAX_COST);
            this.costs.mediaReflectionCost = HexxyAttributesModConfig.bound(this.costs.mediaReflectionCost, DEF_MIN_COST, DEF_MAX_COST);
        }

        @Override
        public int getMindPurificationCost() {
            return (int) (costs.mindPurificationCost * MediaConstants.DUST_UNIT);
        }

        @Override
        public int getDomainReflectionCost() {
            return (int) (costs.domainReflectionCost * MediaConstants.DUST_UNIT);
        }

        @Override
        public int getSentinelDomainReflectionCost() {
            return (int) (costs.sentinelDomainReflectionCost * MediaConstants.DUST_UNIT);
        }

        @Override
        public int getMediaReflectionCost() {
            return (int) (costs.mediaReflectionCost * MediaConstants.DUST_UNIT);
        }

        static class Costs {
            // costs of actions
            double mindPurificationCost = DEFAULT_MIND_PURIFICATION_COST;
            double domainReflectionCost = DEFAULT_DOMAIN_REFLECTION_COST;
            double sentinelDomainReflectionCost = DEFAULT_SENTINEL_DOMAIN_REFLECTION_COST;
            double mediaReflectionCost = DEFAULT_MEDIA_REFLECTION_COST;
        }
    }
}