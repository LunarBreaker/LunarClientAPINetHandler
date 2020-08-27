package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.server.LCNetHandlerServer;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor @Getter
public class LCPacketClientVoice extends LCPacket {

    private byte[] data;
    
    @Override
    public void write(ByteBufWrapper b) throws IOException {
        this.writeBlob(b, this.data);
    }
    
    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.data = this.readBlob(b);
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerServer)handler).handleVoice(this);
    }

}
