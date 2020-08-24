package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor
public class LCPacketServerUpdate extends LCPacket {

    @Getter
    private String server;
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeString(this.server);
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.server = buf.readString();
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleServerUpdate(this);
    }

}
