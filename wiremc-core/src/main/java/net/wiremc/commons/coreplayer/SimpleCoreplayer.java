package net.wiremc.commons.coreplayer;

import com.mongodb.client.model.Filters;
import net.wiremc.Core;
import net.wiremc.coreplayer.Coreplayer;
import net.wiremc.coreplayer.CoreplayerObject;
import org.bson.Document;

import java.util.Objects;
import java.util.UUID;

public final class SimpleCoreplayer implements Coreplayer {

    private final UUID uniqueID;
    private final CoreplayerObject object;

    public SimpleCoreplayer(UUID uniqueID, CoreplayerObject object) {
        this.uniqueID = uniqueID;
        this.object = object;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SimpleCoreplayer) obj;
        return Objects.equals(this.uniqueID, that.uniqueID) &&
                Objects.equals(this.object, that.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueID, object);
    }

    @Override
    public String toString() {
        return "SimpleCoreplayer[" +
                "uniqueID=" + uniqueID + ", " +
                "object=" + object + ']';
    }

    @Override
    public CoreplayerObject get() {
        return this.object;
    }

    @Override
    public Coreplayer push() {
        Core.getInstance().getExecutor().submit(() -> {
            Core.getInstance()
                    .getCoreplayerRegistry()
                    .getDatabaseCollection()
                    .updateOne(
                            Filters.eq("uniqueID", this.uniqueID.toString()), new Document("$set", this.object)
                    );
        });
        return this;
    }

    @Override
    public UUID getUniqueID() {
        return this.uniqueID;
    }
}
