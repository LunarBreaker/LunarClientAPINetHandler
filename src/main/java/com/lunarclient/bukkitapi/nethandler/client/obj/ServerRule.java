package com.lunarclient.bukkitapi.nethandler.client.obj;

import lombok.Getter;

@Getter
public enum ServerRule {
    COMPETITIVE_GAME("competitiveGame", Boolean.class),
    LEGACY_ENCHANTING("legacyEnchanting", Boolean.class),
    MINIMAP_STATUS("minimapStatus", String.class),
    SERVER_HANDLES_WAYPOINTS("serverHandlesWaypoints", Boolean.class),
    SHADERS_DISABLED("shadersDisabled", Boolean.class),
    VOICE_ENABLED("voiceEnabled", Boolean.class),
    LEGACY_COMBAT("legacyCombat", Boolean.class);

    private final String id;
    private final Class type;

    ServerRule(String id, Class type) {
        this.id = id;
        this.type = type;
    }

    public static ServerRule getRule(String id) {
        for (ServerRule existing : values()) {
            if (existing.id.equals(id)) {
                return existing;
            }
        }
        return null;
    }
    
}
