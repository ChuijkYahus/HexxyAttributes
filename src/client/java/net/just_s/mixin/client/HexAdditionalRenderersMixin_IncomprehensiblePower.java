package net.just_s.mixin.client;

import at.petrak.hexcasting.client.render.HexAdditionalRenderers;
import net.just_s.HexxyOriginsMod;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HexAdditionalRenderers.class)
public class HexAdditionalRenderersMixin_IncomprehensiblePower {

    @Redirect(
            method = "tryRenderScryingLensOverlay",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getAttributeValue(Lnet/minecraft/entity/attribute/EntityAttribute;)D")
    )
    private static double hexcasting$test(ClientPlayerEntity instance, EntityAttribute entityAttribute) {
        if (HexxyOriginsMod.hasIncomprehensiblePower(instance)) {
            return 0;
        }
        return instance.getAttributeValue(entityAttribute);
    }
}
