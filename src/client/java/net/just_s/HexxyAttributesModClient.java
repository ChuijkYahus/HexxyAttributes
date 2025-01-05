package net.just_s;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

public class HexxyAttributesModClient implements ClientModInitializer {
	public static MinecraftClient MC = MinecraftClient.getInstance();

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}