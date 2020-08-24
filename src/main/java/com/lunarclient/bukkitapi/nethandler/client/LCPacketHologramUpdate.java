package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor
public class LCPacketHologramUpdate extends LCPacket {

    @Getter
    private UUID uuid;
    @Getter
    private List<String> lines;
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeUUID(this.uuid);
        buf.writeVarInt(this.lines.size());
        for (String s : this.lines) {
            buf.writeString(s);
        }
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.uuid = buf.readUUID();
        int linesSize = buf.readVarInt();
        this.lines = new ArrayList<>(linesSize);
        for (int i = 0; i < linesSize; ++i) {
            this.lines.add(buf.readString());
        }
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleUpdateHologram(this);
    }

}