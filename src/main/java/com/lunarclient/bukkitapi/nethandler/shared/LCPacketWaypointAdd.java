package com.lunarclient.bukkitapi.nethandler.shared;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor
public class LCPacketWaypointAdd extends LCPacket {

    @Getter
    private String name;
    @Getter
    private String world;
    @Getter
    private int color;
    @Getter
    private int x;
    @Getter
    private int y;
    @Getter
    private int z;
    @Getter
    private boolean forced;
    @Getter
    private boolean visible;
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeString(this.name);
        buf.writeString(this.world);
        buf.buf().writeInt(this.color);
        buf.buf().writeInt(this.x);
        buf.buf().writeInt(this.y);
        buf.buf().writeInt(this.z);
        buf.buf().writeBoolean(this.forced);
        buf.buf().writeBoolean(this.visible);
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.name = buf.readString();
        this.world = buf.readString();
        this.color = buf.buf().readInt();
        this.x = buf.buf().readInt();
        this.y = buf.buf().readInt();
        this.z = buf.buf().readInt();
        this.forced = buf.buf().readBoolean();
        this.visible = buf.buf().readBoolean();
    }
    
    @Override
    public void process(LCNetHandler handler) {
        handler.handleAddWaypoint(this);
    }

}
