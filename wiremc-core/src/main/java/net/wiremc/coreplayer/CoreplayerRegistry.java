package net.wiremc.coreplayer;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.UUID;

public interface CoreplayerRegistry {

    public LinkedHashMap<UUID, Coreplayer> getCashedCoreplayers();

    public Coreplayer getCoreplayerByUUID(UUID uniqueID);

    public Coreplayer getCoreplayerByName(String name);

    public Coreplayer getCoreplayerByBukkitPlayer(Player player);

    public int count();

    public int initialCapacity();

    public MongoCollection<Document> getDatabaseCollection();

}
