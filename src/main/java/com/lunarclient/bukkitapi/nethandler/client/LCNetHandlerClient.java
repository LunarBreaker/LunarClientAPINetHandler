package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.server.LCPacketVoice;
import com.lunarclient.bukkitapi.nethandler.server.LCPacketVoiceChannel;
import com.lunarclient.bukkitapi.nethandler.server.LCPacketVoiceChannelRemove;
import com.lunarclient.bukkitapi.nethandler.server.LCPacketVoiceChannelUpdate;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;

public interface LCNetHandlerClient extends LCNetHandler {

    void handleBossBar(LCPacketBossBar p0);
    
    void handleCooldown(LCPacketCooldown p0);
    
    void handleGhost(LCPacketGhost p0);
    
    void handleAddHologram(LCPacketHologram p0);
    
    void handleRemoveHologram(LCPacketHologramRemove p0);
    
    void handleUpdateHologram(LCPacketHologramUpdate p0);
    
    void handleOverrideNametags(LCPacketNametagsOverride p0);
    
    void handleNametagsUpdate(LCPacketNametagsUpdate p0);
    
    void handleNotification(LCPacketNotification p0);
    
    void handleServerRule(LCPacketServerRule p0);
    
    void handleServerUpdate(LCPacketServerUpdate p0);
    
    void handleStaffModState(LCPacketStaffModState p0);
    
    void handleTeammates(LCPacketTeammates p0);
    
    void handleTitle(LCPacketTitle p0);
    
    void handleUpdateWorld(LCPacketUpdateWorld p0);
    
    void handleWorldBorder(LCPacketWorldBorder p0);
    
    void handleWorldBorderCreateNew(LCPacketWorldBorderCreateNew p0);
    
    void handleWorldBorderRemove(LCPacketWorldBorderRemove p0);
    
    void handleWorldBorderUpdate(LCPacketWorldBorderUpdate p0);
    
    void handleWorldBorderUpdateNew(LCPacketWorldBorderUpdateNew p0);
    
    void handleVoice(LCPacketVoice p0);
    
    void handleVoiceChannels(LCPacketVoiceChannel p0);
    
    void handleVoiceChannelUpdate(LCPacketVoiceChannelUpdate p0);
    
    void handleVoiceChannelDelete(LCPacketVoiceChannelRemove p0);
    
    void handleModSettings(LCPacketModSettings p0);

}
