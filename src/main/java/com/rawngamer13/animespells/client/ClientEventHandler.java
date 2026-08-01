package com.rawngamer13.animespells.client;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.client.event.RenderPlayerEvent;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEventHandler {
    
    @SubscribeEvent
    public static void onClientTick(net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent event) {
        // Client tick events
    }
}
