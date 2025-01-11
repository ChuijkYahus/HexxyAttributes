package net.just_s.casting.patterns

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getPlayer
import at.petrak.hexcasting.api.spell.iota.Iota
import net.just_s.HexxyAttributesMod

class OpMindPurification : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val player = args.getPlayer(0, argc)

        val feebleValue = player.getAttributeValue(HexxyAttributesMod.FEEBLE_MIND)
        // Return TRUE if player is SMART, lore reasons blablabla
        return (feebleValue <= 0).asActionResult
    }
}