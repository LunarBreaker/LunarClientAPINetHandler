package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor
public class LCPacketUpdateWorld extends LCPacket {

    @Getter
    private String world;
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeString(this.world);
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.world = buf.readString();
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleUpdateWorld(this);
    }

}
