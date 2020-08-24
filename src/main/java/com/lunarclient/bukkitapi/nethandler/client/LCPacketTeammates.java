package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor
public class LCPacketTeammates extends LCPacket {

    @Getter
    private UUID leader;
    @Getter
    private long lastMs;
    @Getter
    private Map<UUID, Map<String, Double>> players;
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.buf().writeBoolean(this.leader != null);
        if (this.leader != null) {
            buf.writeUUID(this.leader);
        }
        buf.buf().writeLong(this.lastMs);
        buf.writeVarInt(this.players.size());
        this.players.forEach((uuid, posMap) -> {
            buf.writeUUID(uuid);
            buf.writeVarInt(posMap.size());
            posMap.forEach((key, val) -> {
                buf.writeString(key);
                buf.buf().writeDouble(val);
            });
        });
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        if (buf.buf().readBoolean()) {
            this.leader = buf.readUUID();
        }
        this.lastMs = buf.buf().readLong();
        int playersSize = buf.readVarInt();
        this.players = new HashMap<>();
        for (int i = 0; i < playersSize; ++i) {
            UUID uuid = buf.readUUID();
            int posMapSize = buf.readVarInt();
            Map<String, Double> posMap = new HashMap<>();
            for (int j = 0; j < posMapSize; ++j) {
                String key = buf.readString();
                double val = buf.buf().readDouble();
                posMap.put(key, val);
            }
            this.players.put(uuid, posMap);
        }
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleTeammates(this);
    }

}
