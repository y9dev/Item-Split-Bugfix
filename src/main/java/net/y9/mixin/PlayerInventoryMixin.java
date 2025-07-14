package net.y9.mixin;

import net.minecraft.item.ItemStack;
import net.y9.ItemSplitBugfix;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
    @Inject(method = "addStack(ILnet/minecraft/item/ItemStack;)I", at = @At("HEAD"))
    public void add(int slot, ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        PlayerInventory inv = ((PlayerInventory)(Object)this);
        if (slot == inv.selectedSlot) {
            ItemSplitBugfix.fixSplitBug(stack);
            ((PlayerInventory)(Object)this).player.playerScreenHandler.sendContentUpdates();
        }
    }
}
