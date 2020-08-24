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
public class LCPacketNametagsOverride extends LCPacket {

    @Getter
    private UUID player;
    @Getter
    private List<String> tags;
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeUUID(this.player);
        buf.buf().writeBoolean(this.tags != null);
        if (this.tags != null) {
            buf.writeVarInt(this.tags.size());
            for (String tag : this.tags) {
                buf.writeString(tag);
            }
        }
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.player = buf.readUUID();
        if (buf.buf().readBoolean()) {
            int tagsSize = buf.readVarInt();
            this.tags = new ArrayList<>(tagsSize);
            for (int i = 0; i < tagsSize; ++i) {
                this.tags.add(buf.readString());
            }
        }
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleOverrideNametags(this);
    }

}
