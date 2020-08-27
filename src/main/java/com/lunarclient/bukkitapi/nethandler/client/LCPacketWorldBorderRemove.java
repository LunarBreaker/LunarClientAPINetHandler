package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor @Getter
public class LCPacketWorldBorderRemove extends LCPacket {

    private String id;
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeString(this.id);
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.id = buf.readString();
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleWorldBorderRemove(this);
    }

}
