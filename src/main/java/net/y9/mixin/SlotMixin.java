package net.y9.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.y9.ItemSplitBugfix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public class SlotMixin {
    @Inject(method = "onQuickTransfer", at = @At("HEAD"))
    public void onQuickTransfer(ItemStack newItem, ItemStack original, CallbackInfo ci) {
        ItemSplitBugfix.fixSplitBug(newItem);
        ItemSplitBugfix.fixSplitBug(original);
        update(((Slot)(Object)this).inventory);
    }
    @Inject(method = "insertStack(Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/item/ItemStack;", at = @At("HEAD"))
    public void insertStack(ItemStack stack, int count, CallbackInfoReturnable<ItemStack> cir) {
        ItemSplitBugfix.fixSplitBug(stack);
        update(((Slot)(Object)this).inventory);
    }
    @Inject(method = "setStack", at = @At("HEAD"))
    public void insertStack(ItemStack stack, CallbackInfo ci) {
        ItemSplitBugfix.fixSplitBug(stack);
        update(((Slot)(Object)this).inventory);
    }

    private void update(Inventory inventory) {
        if (inventory instanceof PlayerInventory playerInventory) {
            PlayerEntity player = playerInventory.player;
            player.playerScreenHandler.sendContentUpdates();
        }
    }
}
