package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.*;

@AllArgsConstructor @NoArgsConstructor @Getter
public class LCPacketNametagsUpdate extends LCPacket {

    private Map<UUID, List<String>> playersMap;
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeVarInt((this.playersMap == null) ? -1 : this.playersMap.size());
        if (this.playersMap != null) {
            this.playersMap.forEach((uuid, tags) -> {
                buf.writeUUID(uuid);
                buf.writeVarInt(tags.size());
                tags.forEach(buf::writeString);
            });
        }
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        int playersMapSize = buf.readVarInt();
        if (playersMapSize != -1) {
            this.playersMap = new HashMap<>();
            for (int i = 0; i < playersMapSize; ++i) {
                UUID uuid = buf.readUUID();
                int tagsSize = buf.readVarInt();
                List<String> tags = new ArrayList<>(tagsSize);
                for (int j = 0; j < tagsSize; ++j) {
                    tags.add(buf.readString());
                }
                this.playersMap.put(uuid, tags);
            }
        }
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleNametagsUpdate(this);
    }

}
