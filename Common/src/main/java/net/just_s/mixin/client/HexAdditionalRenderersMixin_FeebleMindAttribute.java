package net.just_s.mixin.client;

import at.petrak.hexcasting.api.misc.DiscoveryHandlers;
import at.petrak.hexcasting.client.HexAdditionalRenderers;
import net.just_s.HexxyAttributesMod;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HexAdditionalRenderers.class)
public class HexAdditionalRenderersMixin_FeebleMindAttribute {
    @Redirect(
            method = "tryRenderScryingLensOverlay",
            at = @At(value = "INVOKE", target = "Lat/petrak/hexcasting/api/misc/DiscoveryHandlers;hasLens(Lnet/minecraft/entity/player/PlayerEntity;)Z")
    )
    private static boolean hexxyattributes$lens_renderer(PlayerEntity player) {
        if (player.getAttributeValue(HexxyAttributesMod.FEEBLE_MIND) <= 0) {
            return DiscoveryHandlers.hasLens(player);
        }
        return false;
    }
}