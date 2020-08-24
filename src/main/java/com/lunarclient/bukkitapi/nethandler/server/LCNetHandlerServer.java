package com.lunarclient.bukkitapi.nethandler.server;

import com.lunarclient.bukkitapi.nethandler.client.LCPacketClientVoice;
import com.lunarclient.bukkitapi.nethandler.client.LCPacketVoiceChannelSwitch;
import com.lunarclient.bukkitapi.nethandler.client.LCPacketVoiceMute;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;

public interface LCNetHandlerServer extends LCNetHandler {

    void handleStaffModStatus(LCPacketStaffModStatus p0);
    
    void handleVoice(LCPacketClientVoice p0);
    
    void handleVoiceMute(LCPacketVoiceMute p0);
    
    void handleVoiceChannelSwitch(LCPacketVoiceChannelSwitch p0);

}
