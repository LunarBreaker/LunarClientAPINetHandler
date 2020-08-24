package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.server.LCNetHandlerServer;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor
public class LCPacketVoiceChannelSwitch extends LCPacket {

    @Getter
    private UUID switchingTo;
    
    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeUUID(this.switchingTo);
    }
    
    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.switchingTo = b.readUUID();
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerServer)handler).handleVoiceChannelSwitch(this);
    }

}
