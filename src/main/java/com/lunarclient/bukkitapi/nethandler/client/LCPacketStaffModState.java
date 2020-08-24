package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor
public class LCPacketStaffModState extends LCPacket {

    @Getter
    private String mod;
    @Getter
    private boolean state;
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeString(this.mod);
        buf.buf().writeBoolean(this.state);
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.mod = buf.readString();
        this.state = buf.buf().readBoolean();
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleStaffModState(this);
    }
}
