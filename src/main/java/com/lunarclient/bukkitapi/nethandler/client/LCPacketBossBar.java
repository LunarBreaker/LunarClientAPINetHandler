package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor
public class LCPacketBossBar extends LCPacket {

    @Getter
    private int action;
    @Getter
    private String text;
    @Getter
    private float health;
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeVarInt(this.action);
        if (this.action == 0) {
            buf.writeString(this.text);
            buf.buf().writeFloat(this.health);
        }
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.action = buf.readVarInt();
        if (this.action == 0) {
            this.text = buf.readString();
            this.health = buf.buf().readFloat();
        }
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleBossBar(this);
    }

}
