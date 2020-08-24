package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor
public class LCPacketWorldBorderUpdateNew extends LCPacket {

    @Getter
    private String id;
    @Getter
    private double minX;
    @Getter
    private double minZ;
    @Getter
    private double maxX;
    @Getter
    private double maxZ;
    @Getter
    private int durationTicks;
    @Getter
    private boolean cancelsEntry;
    @Getter
    private boolean cancelsExit;
    @Getter
    private int color;
    
    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(this.id);
        b.buf().writeDouble(this.minX);
        b.buf().writeDouble(this.minZ);
        b.buf().writeDouble(this.maxX);
        b.buf().writeDouble(this.maxZ);
        b.buf().writeInt(this.durationTicks);
        b.buf().writeBoolean(this.cancelsEntry);
        b.buf().writeBoolean(this.cancelsExit);
        b.buf().writeInt(this.color);
    }
    
    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.id = b.readString();
        this.minX = b.buf().readDouble();
        this.minZ = b.buf().readDouble();
        this.maxX = b.buf().readDouble();
        this.maxZ = b.buf().readDouble();
        this.durationTicks = b.buf().readInt();
        this.cancelsEntry = b.buf().readBoolean();
        this.cancelsExit = b.buf().readBoolean();
        this.color = b.buf().readInt();
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleWorldBorderUpdateNew(this);
    }

}
