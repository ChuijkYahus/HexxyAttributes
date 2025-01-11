package net.just_s.casting.patterns

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import net.just_s.HexxyAttributesMod

class OpMediaReflection : ConstMediaAction {
    override val argc = 0

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        ctx.assertEntityInRange(ctx.caster)
        val mediaModifier = ctx.caster.getAttributeValue(HexxyAttributesMod.MEDIA_CONSUMPTION_MODIFIER)
        return mediaModifier.asActionResult
    }
}