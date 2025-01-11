package net.just_s.casting.patterns

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import net.just_s.HexxyAttributesMod

class OpSentinelDomainReflection : ConstMediaAction {
    override val argc = 0

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        ctx.assertEntityInRange(ctx.caster)
        val sentinelAmbitRadius = ctx.caster.getAttributeValue(HexxyAttributesMod.SENTINEL_RADIUS)
        return sentinelAmbitRadius.asActionResult
    }
}