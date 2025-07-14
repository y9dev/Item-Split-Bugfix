package net.y9.mixin;

import net.minecraft.item.ItemStack;
import net.y9.ItemSplitBugfix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@Inject(method = "copy", at = @At("HEAD"))
	public void copy(CallbackInfoReturnable<ItemStack> cir) {
		ItemSplitBugfix.fixSplitBug((ItemStack)(Object)this);
	}
	@Inject(method = "split", at = @At("HEAD"))
	public void split(CallbackInfoReturnable<ItemStack> cir) {
		ItemSplitBugfix.fixSplitBug((ItemStack)(Object)this);
	}
	@Inject(method = "canCombine", at = @At("HEAD"))
	private static void canCombine(ItemStack stack, ItemStack otherStack, CallbackInfoReturnable<Boolean> cir) {
		ItemSplitBugfix.fixSplitBug(stack);
		ItemSplitBugfix.fixSplitBug(otherStack);
	}
	@Inject(method = "getCount", at = @At("HEAD"))
	public void getCount(CallbackInfoReturnable<Integer> cir) {
		ItemSplitBugfix.fixSplitBug((ItemStack)(Object)this);
	}
	@Inject(method = "setCount", at = @At("HEAD"))
	public void setCount(int count, CallbackInfo ci) {
		ItemSplitBugfix.fixSplitBug((ItemStack)(Object)this);
	}
}