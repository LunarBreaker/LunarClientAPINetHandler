package com.lunarclient.bukkitapi.nethandler;

import com.lunarclient.bukkitapi.nethandler.client.*;
import com.lunarclient.bukkitapi.nethandler.server.LCPacketVoice;
import com.lunarclient.bukkitapi.nethandler.server.LCPacketVoiceChannel;
import com.lunarclient.bukkitapi.nethandler.server.LCPacketVoiceChannelRemove;
import com.lunarclient.bukkitapi.nethandler.server.LCPacketVoiceChannelUpdate;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import com.lunarclient.bukkitapi.nethandler.shared.LCPacketEmoteBroadcast;
import com.lunarclient.bukkitapi.nethandler.shared.LCPacketWaypointAdd;
import com.lunarclient.bukkitapi.nethandler.shared.LCPacketWaypointRemove;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class LCPacket {

    private static final Map<Class, Integer> classToId;
    private static final Map<Integer, Class> idToClass;
    @Getter
    private Object attachment;
    
    public static LCPacket handle(byte[] data) {
        return handle(data, null);
    }
    
    public static LCPacket handle(byte[] data, Object attachment) {
        ByteBufWrapper wrappedBuffer = new ByteBufWrapper(Unpooled.wrappedBuffer(data));
        int packetId = wrappedBuffer.readVarInt();
        Class packetClass = LCPacket.idToClass.get(packetId);
        if (packetClass != null) {
            try {
                LCPacket packet = (LCPacket) packetClass.newInstance();
                packet.attach(attachment);
                packet.read(wrappedBuffer);
                return packet;
            }
            catch (IOException | InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    public static byte[] getPacketData(LCPacket packet) {
        return getPacketBuf(packet).array();
    }
    
    public static ByteBuf getPacketBuf(LCPacket packet) {
        ByteBufWrapper wrappedBuffer = new ByteBufWrapper(Unpooled.buffer());
        wrappedBuffer.writeVarInt(LCPacket.classToId.get(packet.getClass()));
        try {
            packet.write(wrappedBuffer);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return wrappedBuffer.buf();
    }
    
    private static void addPacket(int id, Class clazz) {
        if (LCPacket.classToId.containsKey(clazz)) {
            throw new IllegalArgumentException("Duplicate packet class (" + clazz.getSimpleName() + "), already used by " + LCPacket.classToId.get(clazz));
        }
        if (LCPacket.idToClass.containsKey(id)) {
            throw new IllegalArgumentException("Duplicate packet ID (" + id + "), already used by " + LCPacket.idToClass.get(id).getSimpleName());
        }
        LCPacket.classToId.put(clazz, id);
        LCPacket.idToClass.put(id, clazz);
    }
    
    public abstract void write(ByteBufWrapper p0) throws IOException;
    
    public abstract void read(ByteBufWrapper p0) throws IOException;
    
    public abstract void process(LCNetHandler p0);
    
    public <T> void attach(T obj) {
        this.attachment = obj;
    }
    
    protected void writeBlob(ByteBufWrapper b, byte[] bytes) {
        b.buf().writeShort(bytes.length);
        b.buf().writeBytes(bytes);
    }
    
    protected byte[] readBlob(ByteBufWrapper b) {
        short key = b.buf().readShort();
        if (key < 0) {
            System.out.println("Key was smaller than nothing!  Weird key!");
            return null;
        }
        byte[] blob = new byte[key];
        b.buf().readBytes(blob);
        return blob;
    }
    
    static {
        classToId = new HashMap<>();
        idToClass = new HashMap<>();
        addPacket(0, LCPacketClientVoice.class);
        addPacket(1, LCPacketVoiceChannelSwitch.class);
        addPacket(2, LCPacketVoiceMute.class);
        addPacket(3, LCPacketCooldown.class);
        addPacket(4, LCPacketHologram.class);
        addPacket(5, LCPacketHologramUpdate.class);
        addPacket(6, LCPacketHologramRemove.class);
        addPacket(7, LCPacketNametagsOverride.class);
        addPacket(8, LCPacketNametagsUpdate.class);
        addPacket(9, LCPacketNotification.class);
        addPacket(10, LCPacketServerRule.class);
        addPacket(11, LCPacketServerUpdate.class);
        addPacket(12, LCPacketStaffModState.class);
        addPacket(13, LCPacketTeammates.class);
        addPacket(14, LCPacketTitle.class);
        addPacket(15, LCPacketUpdateWorld.class);
        addPacket(16, LCPacketVoice.class);
        addPacket(17, LCPacketVoiceChannel.class);
        addPacket(18, LCPacketVoiceChannelRemove.class);
        addPacket(19, LCPacketVoiceChannelUpdate.class);
        addPacket(20, LCPacketWorldBorder.class);
        addPacket(21, LCPacketWorldBorderRemove.class);
        addPacket(22, LCPacketWorldBorderUpdate.class);
        addPacket(23, LCPacketWaypointAdd.class);
        addPacket(24, LCPacketWaypointRemove.class);
        addPacket(25, LCPacketGhost.class);
        addPacket(26, LCPacketEmoteBroadcast.class);
        addPacket(28, LCPacketBossBar.class);
        addPacket(29, LCPacketWorldBorderCreateNew.class);
        addPacket(30, LCPacketWorldBorderUpdateNew.class);
        addPacket(31, LCPacketModSettings.class);
    }

}
