package net.just_s.mixin;

import at.petrak.hexcasting.common.items.ItemStaff;
import net.just_s.HexxyAttributesMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStaff.class)
public class ItemStaffMixin_FeebleMindAttribute {
    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    private void hexxyattributes$disableStaffIfIncomprehensible(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (player.getAttributeValue(HexxyAttributesMod.FEEBLE_MIND) <= 0.0) {
            return;
        }
        cir.setReturnValue(TypedActionResult.fail(player.getStackInHand(hand)));
    }
}