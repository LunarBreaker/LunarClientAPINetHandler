package com.lunarclient.bukkitapi.nethandler.shared;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor
public class LCPacketWaypointRemove extends LCPacket {

    @Getter
    private String name;
    @Getter
    private String world;
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeString(this.name);
        buf.writeString(this.world);
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.name = buf.readString();
        this.world = buf.readString();
    }
    
    @Override
    public void process(LCNetHandler handler) {
        handler.handleRemoveWaypoint(this);
    }

}
