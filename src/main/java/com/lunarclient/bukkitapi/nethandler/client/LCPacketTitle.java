package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor @Getter
public class LCPacketTitle extends LCPacket {

    private String type;
    private String message;
    private float scale;
    private long displayTimeMs;
    private long fadeInTimeMs;
    private long fadeOutTimeMs;
    
    public LCPacketTitle(String type, String message, long displayTimeMs, long fadeInTimeMs, long fadeOutTimeMs) {
        this(type, message, 1.0f, displayTimeMs, fadeInTimeMs, fadeOutTimeMs);
    }
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeString(this.type);
        buf.writeString(this.message);
        buf.buf().writeFloat(this.scale);
        buf.buf().writeLong(this.displayTimeMs);
        buf.buf().writeLong(this.fadeInTimeMs);
        buf.buf().writeLong(this.fadeOutTimeMs);
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.type = buf.readString();
        this.message = buf.readString();
        this.scale = buf.buf().readFloat();
        this.displayTimeMs = buf.buf().readLong();
        this.fadeInTimeMs = buf.buf().readLong();
        this.fadeOutTimeMs = buf.buf().readLong();
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleTitle(this);
    }

}
