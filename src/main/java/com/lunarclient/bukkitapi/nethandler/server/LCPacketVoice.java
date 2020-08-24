package com.lunarclient.bukkitapi.nethandler.server;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.client.LCNetHandlerClient;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor
public class LCPacketVoice extends LCPacket {

    @Getter
    private Set<UUID> uuids;
    @Getter
    private byte[] data;
    
    @Override
    public void write(ByteBufWrapper b) {
        b.writeVarInt(this.uuids.size());
        this.uuids.forEach(b::writeUUID);
        this.writeBlob(b, this.data);
    }
    
    @Override
    public void read(ByteBufWrapper b) {
        this.uuids = new HashSet<>();
        for (int size = b.readVarInt(), i = 0; i < size; ++i) {
            this.uuids.add(b.readUUID());
        }
        this.data = this.readBlob(b);
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleVoice(this);
    }
    
    @Deprecated
    public UUID getUuid() {
        return new ArrayList<>(this.uuids).get(0);
    }

}
