package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.Getter;

import java.beans.ConstructorProperties;
import java.io.IOException;

@Getter
public class LCPacketWorldBorderCreateNew extends LCPacket {

    private String id;
    private String world;
    private boolean cancelsEntry;
    private boolean cancelsExit;
    private boolean canShrinkExpand;
    private int color;
    private double minX;
    private double minZ;
    private double maxX;
    private double maxZ;

    public LCPacketWorldBorderCreateNew() {
        this.color = -13421569;
    }

    @ConstructorProperties({ "id", "world", "cancelsEntry", "cancelsExit", "canShrinkExpand", "color", "minX", "minZ", "maxX", "maxZ" })
    public LCPacketWorldBorderCreateNew(String id, String world, boolean cancelsEntry, boolean cancelsExit, boolean canShrinkExpand, int color, double minX, double minZ, double maxX, double maxZ) {
        this.color = -13421569;
        this.id = id;
        this.world = world;
        this.cancelsEntry = cancelsEntry;
        this.cancelsExit = cancelsExit;
        this.canShrinkExpand = canShrinkExpand;
        this.color = color;
        this.minX = minX;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxZ = maxZ;
    }
    
    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.buf().writeBoolean(this.id != null);
        if (this.id != null) {
            b.writeString(this.id);
        }
        b.writeString(this.world);
        b.buf().writeBoolean(this.cancelsEntry);
        b.buf().writeBoolean(this.cancelsExit);
        b.buf().writeBoolean(this.canShrinkExpand);
        b.buf().writeInt(this.color);
        b.buf().writeDouble(this.minX);
        b.buf().writeDouble(this.minZ);
        b.buf().writeDouble(this.maxX);
        b.buf().writeDouble(this.maxZ);
    }
    
    @Override
    public void read(ByteBufWrapper b) throws IOException {
        if (b.buf().readBoolean()) {
            this.id = b.readString();
        }
        this.world = b.readString();
        this.cancelsEntry = b.buf().readBoolean();
        this.cancelsExit = b.buf().readBoolean();
        this.canShrinkExpand = b.buf().readBoolean();
        this.color = b.buf().readInt();
        this.minX = b.buf().readDouble();
        this.minZ = b.buf().readDouble();
        this.maxX = b.buf().readDouble();
        this.maxZ = b.buf().readDouble();
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleWorldBorderCreateNew(this);
    }

}
