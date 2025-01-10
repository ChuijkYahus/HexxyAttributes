package net.just_s.registry

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.HexRegistries
import dev.architectury.registry.registries.DeferredRegister
import net.just_s.HexxyAttributesMod.MOD_ID
import net.just_s.casting.patterns.OpDomainReflection
import net.just_s.casting.patterns.OpMediaReflection
import net.just_s.casting.patterns.OpMindPurification
import net.just_s.casting.patterns.OpSentinelDomainReflection

object HexxyAttributesPatternRegistry {
    private val REGISTRY = DeferredRegister.create(MOD_ID, HexRegistries.ACTION)

    fun register() {
        REGISTRY.register()
    }

    val DOMAIN_REFLECTION = REGISTRY.register("domain_reflection") {
        ActionRegistryEntry(
            HexPattern.fromAngles("qaqeaa", HexDir.NORTH_EAST),
            OpDomainReflection()
        )
    }

    val SENTINEL_DOMAIN_REFLECTION = REGISTRY.register("sentinel_domain_reflection") {
        ActionRegistryEntry(
            HexPattern.fromAngles("aeawaeadaa", HexDir.EAST),
            OpSentinelDomainReflection()
        )
    }

    val MEDIA_REFLECTION = REGISTRY.register("media_reflection") {
        ActionRegistryEntry(
            HexPattern.fromAngles("wwaqwqeaa", HexDir.NORTH_EAST),
            OpMediaReflection()
        )
    }

    val MIND_PURIFICATION = REGISTRY.register("mind_purification") {
        ActionRegistryEntry(
            HexPattern.fromAngles("waaqa", HexDir.EAST),
            OpMindPurification()
        )
    }
}