package net.wiremc.database;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public final record MongoConnection(String username, String password, String database, String host, int port) {

    public MongoCredential credential() {
        return MongoCredential.createCredential(this.username, database, password.toCharArray());
    }

    public ServerAddress address() {
        return new ServerAddress(this.host, this.port);
    }

}
