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

@AllArgsConstructor @NoArgsConstructor @Getter
public class LCPacketVoiceMute extends LCPacket {

    private UUID muting;
    private int volume;
    
    public LCPacketVoiceMute(UUID muting) {
        this(muting, 0);
    }
    
    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeUUID(this.muting);
        b.writeVarInt(this.volume);
    }
    
    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.muting = b.readUUID();
        this.volume = b.readVarInt();
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerServer)handler).handleVoiceMute(this);
    }

}
