package net.wiremc.database;

import com.mongodb.MongoClientSettings;
import org.atomic.commons.futures.IFuturePromise;

public interface DatabaseBootstrap {

    public IFuturePromise<DatabaseTerminal> connect(MongoConnection connectionInfo);

    public MongoClientSettings defaults(MongoConnection connectionInfo);

}
