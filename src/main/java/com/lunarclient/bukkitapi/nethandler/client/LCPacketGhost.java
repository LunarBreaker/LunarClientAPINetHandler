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

@AllArgsConstructor @NoArgsConstructor @Getter
public class LCPacketGhost extends LCPacket {

    private List<UUID> addGhostList;
    private List<UUID> removeGhostList;
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeVarInt(this.addGhostList.size());
        this.addGhostList.forEach(buf::writeUUID);
        buf.writeVarInt(this.removeGhostList.size());
        this.removeGhostList.forEach(buf::writeUUID);
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        int addListSize = buf.readVarInt();
        this.addGhostList = new ArrayList<>(addListSize);
        for (int i = 0; i < addListSize; ++i) {
            this.addGhostList.add(buf.readUUID());
        }
        int removeListSize = buf.readVarInt();
        this.removeGhostList = new ArrayList<>(removeListSize);
        for (int j = 0; j < removeListSize; ++j) {
            this.removeGhostList.add(buf.readUUID());
        }
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleGhost(this);
    }
}
