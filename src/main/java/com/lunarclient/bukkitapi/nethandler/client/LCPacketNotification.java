package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor
public class LCPacketNotification extends LCPacket {

    @Getter
    private String message;
    @Getter
    private long durationMs;
    @Getter
    private String level;
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeString(this.message);
        buf.buf().writeLong(this.durationMs);
        buf.writeString(this.level);
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.message = buf.readString();
        this.durationMs = buf.buf().readLong();
        this.level = buf.readString();
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient) handler).handleNotification(this);
    }

}
