package net.wiremc.commons.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import net.wiremc.database.DatabaseTerminal;
import net.wiremc.database.MongoConnection;

public class SimpleDatabaseTerminal implements DatabaseTerminal {

    private final MongoConnection mongoConnectionInfo;
    private final MongoClient client;
    private final MongoDatabase coreDatabase;

    public SimpleDatabaseTerminal(MongoConnection mongoConnectionInfo, MongoClient client) {
        this.mongoConnectionInfo = mongoConnectionInfo;
        this.client = client;
        this.coreDatabase = client.getDatabase("core_db");
    }

    @Override
    public MongoClient getMongoClient() {
        return this.client;
    }

    @Override
    public MongoConnection getMongoClientConnectionInfo() {
        return this.mongoConnectionInfo;
    }

    @Override
    public MongoDatabase getCoreDatabase() {
        return this.coreDatabase;
    }

}
