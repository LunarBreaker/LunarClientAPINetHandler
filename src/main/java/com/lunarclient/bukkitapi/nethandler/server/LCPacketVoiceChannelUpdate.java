package com.lunarclient.bukkitapi.nethandler.server;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.client.LCNetHandlerClient;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor @Getter
public class LCPacketVoiceChannelUpdate extends LCPacket {

    public int status;
    private UUID channelUuid;
    private UUID uuid;
    private String name;
    
    @Override
    public void write(ByteBufWrapper b) {
        b.writeVarInt(this.status);
        b.writeUUID(this.channelUuid);
        b.writeUUID(this.uuid);
        b.writeString(this.name);
    }
    
    @Override
    public void read(ByteBufWrapper b) {
        this.status = b.readVarInt();
        this.channelUuid = b.readUUID();
        this.uuid = b.readUUID();
        this.name = b.readString();
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleVoiceChannelUpdate(this);
    }

}
