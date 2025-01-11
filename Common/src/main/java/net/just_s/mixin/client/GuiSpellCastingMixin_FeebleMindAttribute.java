package net.just_s.mixin.client;

import at.petrak.hexcasting.client.gui.GuiSpellcasting;
import net.just_s.HexxyAttributesMod;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiSpellcasting.class)
public class GuiSpellCastingMixin_FeebleMindAttribute {
    @Inject(at = @At("HEAD"), method = "tick")
    private void hexxyattributes$closeSpellcastingGui(CallbackInfo info) {
        // Imagine other addons adding spell casting items like lmao
        // Ok fr tho, we want to ensure that @silliness power affects other ways of "casting on the fly".
        // This could be neglected by client (using cheats basically) but uuuugh idc
        if (MinecraftClient.getInstance().player == null) {
            return;
        }

        GuiSpellcasting ths = (GuiSpellcasting)(Object)this;
        if (MinecraftClient.getInstance().player.getAttributeValue(HexxyAttributesMod.FEEBLE_MIND) <= 0) {
            return;
        }
        ths.close();
    }
}