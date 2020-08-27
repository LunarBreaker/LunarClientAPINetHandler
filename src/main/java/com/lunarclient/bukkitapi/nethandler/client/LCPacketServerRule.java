package com.lunarclient.bukkitapi.nethandler.client;

import com.lunarclient.bukkitapi.nethandler.ByteBufWrapper;
import com.lunarclient.bukkitapi.nethandler.LCPacket;
import com.lunarclient.bukkitapi.nethandler.client.obj.ServerRule;
import com.lunarclient.bukkitapi.nethandler.shared.LCNetHandler;
import lombok.Getter;

import java.io.IOException;

@Getter
public class LCPacketServerRule extends LCPacket {

    private ServerRule rule;
    private int intValue;
    private float floatValue;
    private boolean booleanValue;
    private String stringValue;
    
    public LCPacketServerRule() {
        this.stringValue = "";
    }
    
    public LCPacketServerRule(ServerRule rule, float value) {
        this.stringValue = "";
        this.rule = rule;
        this.floatValue = value;
    }
    
    public LCPacketServerRule(ServerRule rule, boolean value) {
        this.stringValue = "";
        this.rule = rule;
        this.booleanValue = value;
    }
    
    public LCPacketServerRule(ServerRule rule, int value) {
        this.stringValue = "";
        this.rule = rule;
        this.intValue = value;
    }
    
    public LCPacketServerRule(ServerRule rule, String value) {
        this.stringValue = "";
        this.rule = rule;
        this.stringValue = value;
    }
    
    @Override
    public void write(ByteBufWrapper buf) throws IOException {
        buf.writeString(this.rule.getId());
        buf.buf().writeBoolean(this.booleanValue);
        buf.buf().writeInt(this.intValue);
        buf.buf().writeFloat(this.floatValue);
        buf.writeString(this.stringValue);
    }
    
    @Override
    public void read(ByteBufWrapper buf) throws IOException {
        this.rule = ServerRule.getRule(buf.readString());
        this.booleanValue = buf.buf().readBoolean();
        this.intValue = buf.buf().readInt();
        this.floatValue = buf.buf().readFloat();
        this.stringValue = buf.readString();
    }
    
    @Override
    public void process(LCNetHandler handler) {
        ((LCNetHandlerClient)handler).handleServerRule(this);
    }

}
