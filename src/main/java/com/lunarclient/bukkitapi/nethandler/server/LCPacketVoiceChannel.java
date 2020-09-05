package com.lunarclient.bukkitapi.nethandler.server;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.client.LCNetHandlerClient;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor @Getter
public class LCPacketVoiceChannel extends LCPacket {

    private UUID uuid;
    private String name;
    private Map<UUID, String> players;
    private Map<UUID, String> listening;
    
    @Override
    public void write(ByteBufWrapper b) {
        b.writeUUID(this.uuid);
        b.writeString(this.name);
        this.writeMap(b, this.players);
        this.writeMap(b, this.listening);
    }
    
    @Override
    public void read(ByteBufWrapper b) {
        this.uuid = b.readUUID();
        this.name = b.readString();
        this.players = this.readMap(b);
        this.listening = this.readMap(b);
    }
    
    private void writeMap(ByteBufWrapper b, Map<UUID, String> players) {
        b.writeVarInt(players.size());
        players.entrySet().forEach(player -> {
            b.writeUUID(player.getKey());
            b.writeString(player.getValue());
        });
    }
    
    private Map<UUID, String> readMap(ByteBufWrapper b) {
        int size = b.readVarInt();
        Map<UUID, String> players = new HashMap<UUID, String>();
        for (int i = 0; i < size; ++i) {
            UUID uuid = b.readUUID();
            String name = b.readString();
            players.put(uuid, name);
        }
        return players;
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleVoiceChannels(this);
    }

}
