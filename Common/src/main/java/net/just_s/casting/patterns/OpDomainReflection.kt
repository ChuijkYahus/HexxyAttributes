package net.just_s.casting.patterns

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import net.just_s.HexxyAttributesMod

class OpDomainReflection : ConstMediaAction {
    override val argc = 0

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        ctx.assertEntityInRange(ctx.caster)
        val ambitRadius = ctx.caster.getAttributeValue(HexxyAttributesMod.AMBIT_RADIUS)
        return ambitRadius.asActionResult
    }
}