package com.lunarclient.bukkitapi.nethandler.server;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor @Getter
public class LCPacketStaffModStatus extends LCPacket {

    private final Set<String> enabled;
    
    public LCPacketStaffModStatus() {
        this.enabled = new HashSet<>();
    }
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeVarInt(this.enabled.size());
        this.enabled.forEach(buf::writeString);
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        for (int size = buf.readVarInt(), i = 0; i < size; ++i) {
            this.enabled.add(buf.readString());
        }
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerServer)handler).handleStaffModStatus(this);
    }

}
