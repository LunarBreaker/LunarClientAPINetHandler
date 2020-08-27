package com.lunarclient.bukkitapi.nethandler.client.obj;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ModSettings {

    public static Gson GSON;
    @Getter private final Map<String, ModSetting> modSettings;
    
    public ModSettings() {
        this.modSettings = new HashMap<>();
    }
    
    public ModSettings addModSetting(String modId, ModSetting setting) {
        this.modSettings.put(modId, setting);
        return this;
    }
    
    public ModSetting getModSetting(String modId) {
        return this.modSettings.get(modId);
    }
    
    static {
        GSON = new Gson();
    }

    @AllArgsConstructor @NoArgsConstructor @Getter
    public static class ModSetting {

        private boolean enabled;
        private Map<String, Object> properties;
        
        @Override
        public String toString() {
            return "ModSetting{enabled=" + this.enabled + ", properties=" + this.properties + '}';
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            ModSetting that = (ModSetting)o;
            return this.enabled == that.enabled && Objects.equals(this.properties, that.properties);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.enabled, this.properties);
        }
    }
}
