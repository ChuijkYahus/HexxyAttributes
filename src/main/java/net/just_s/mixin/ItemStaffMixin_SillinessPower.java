package net.just_s.mixin;

import at.petrak.hexcasting.common.items.ItemStaff;
import net.just_s.HexxyOriginsMod;
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
public class ItemStaffMixin_SillinessPower {
	@Inject(at = @At("HEAD"), method = "use", cancellable = true)
	private void init(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
		if (HexxyOriginsMod.hasSilliness(player)) {
			cir.setReturnValue(TypedActionResult.fail(player.getStackInHand(hand)));
		}
	}
}