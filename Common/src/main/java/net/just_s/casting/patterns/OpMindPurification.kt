package net.just_s.casting.patterns

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.asActionResult
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getPlayer
import at.petrak.hexcasting.api.casting.iota.Iota
import net.just_s.HexxyAttributesMod

class OpMindPurification : ConstMediaAction {
    /**
     * The number of arguments from the stack that this action requires.
     */
    override val argc = 1

    /**
     * The method called when this Action is actually executed. Accepts the [args]
     * that were on the stack (there will be [argc] of them), and the [env],
     * which contains things like references to the caster, the ServerLevel,
     * methods to determine whether locations and entities are in ambit, etc.
     * Returns the list of iotas that should be added back to the stack as the
     * result of this action executing.
     */
    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val player = args.getPlayer(0, argc)

        val feebleValue = player.getAttributeValue(HexxyAttributesMod.FEEBLE_MIND)
        // Return TRUE if player is SMART, lore reasons blablabla
        return (feebleValue <= 0).asActionResult
    }
}