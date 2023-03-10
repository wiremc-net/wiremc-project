package net.wiremc.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public interface DatabaseTerminal {

    public MongoClient getMongoClient();

    public MongoConnection getMongoClientConnectionInfo();

    public MongoDatabase getCoreDatabase();

}
