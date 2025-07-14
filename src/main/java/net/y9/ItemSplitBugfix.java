package net.y9;

import net.fabricmc.api.ModInitializer;

import net.minecraft.item.ItemStack;

public class ItemSplitBugfix implements ModInitializer {
	@Override
	public void onInitialize() {

	}
	public static void fixSplitBug(ItemStack stack) {
		if (stack.getNbt() != null && stack.getNbt().isEmpty()) {
			stack.setNbt(null);
		}
	}
}