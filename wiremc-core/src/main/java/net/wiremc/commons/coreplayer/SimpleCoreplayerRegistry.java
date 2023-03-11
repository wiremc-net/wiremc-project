package net.wiremc.commons.coreplayer;

import com.google.common.collect.Maps;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import net.wiremc.Core;
import net.wiremc.coreplayer.Coreplayer;
import net.wiremc.coreplayer.CoreplayerObject;
import net.wiremc.coreplayer.CoreplayerRegistry;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class SimpleCoreplayerRegistry implements CoreplayerRegistry {

    private final int initializeCapacity = Bukkit.getMaxPlayers() / 2;
    private final LinkedHashMap<UUID, Coreplayer> linkedCoreplayers = Maps.newLinkedHashMapWithExpectedSize(this.initializeCapacity);

    @Override
    public LinkedHashMap<UUID, Coreplayer> getCashedCoreplayers() {
        return this.linkedCoreplayers;
    }

    @Override
    public Coreplayer getCoreplayerByUUID(UUID uniqueID) {
        final var item = this.linkedCoreplayers.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(uniqueID))
                .findFirst()
                .orElseGet(() -> {
                    final var object = new CoreplayerObject(this.getDatabaseCollection().find(Filters.eq("uniqueID", uniqueID.toString())).first());
                    final var coreplayer = new SimpleCoreplayer(uniqueID, object);
                    SimpleCoreplayerRegistry.this.linkedCoreplayers.put(uniqueID, coreplayer);
                    return new Map.Entry<>() {
                        @Override public Coreplayer getValue() { return coreplayer; }

                        @Override public UUID getKey() {return null;}

                        @Override public Coreplayer setValue(Coreplayer value) {
                            return null;
                        }
                    };
                });
        return item.getValue();
    }

    @Override
    public Coreplayer getCoreplayerByName(String name) {
        final var player = Bukkit.getOfflinePlayer(name);
        return getCoreplayerByUUID(player.getUniqueId());
    }

    @Override
    public Coreplayer getCoreplayerByBukkitPlayer(Player player) {
        return getCoreplayerByUUID(player.getUniqueId());
    }

    @Override
    public int count() {
        return this.linkedCoreplayers.size();
    }

    @Override
    public int initialCapacity() {
        return this.initializeCapacity;
    }

    @Override
    public MongoCollection<Document> getDatabaseCollection() {
        return Core.getInstance().getDatabaseTerminal().getCoreDatabase().getCollection("core_players_db");
    }
}
