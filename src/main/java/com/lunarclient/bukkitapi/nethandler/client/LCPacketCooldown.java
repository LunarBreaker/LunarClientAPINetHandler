package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor
public class LCPacketCooldown extends LCPacket {

    @Getter
    private String message;
    @Getter
    private long durationMs;
    @Getter
    private int iconId;
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeString(this.message);
        buf.buf().writeLong(this.durationMs);
        buf.buf().writeInt(this.iconId);
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.message = buf.readString();
        this.durationMs = buf.buf().readLong();
        this.iconId = buf.buf().readInt();
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleCooldown(this);
    }

}
