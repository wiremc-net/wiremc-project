package net.wiremc.coreplayer;

import org.bson.Document;

import java.util.Map;

public final class CoreplayerObject extends Document {

    public CoreplayerObject() {
    }

    public CoreplayerObject(String key, Object value) {
        super(key, value);
    }

    public CoreplayerObject(Map<String, Object> map) {
        super(map);
    }

}
