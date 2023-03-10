package net.wiremc.commons.database;

import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ClusterSettings;
import net.wiremc.database.DatabaseBootstrap;
import net.wiremc.database.DatabaseTerminal;
import net.wiremc.database.MongoConnection;
import org.atomic.commons.futures.IFutureFactory;
import org.atomic.commons.futures.IFuturePromise;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SimpleDatabaseBootstrap implements DatabaseBootstrap {

    @Override
    public IFuturePromise<DatabaseTerminal> connect(MongoConnection connectionInfo) {
        return IFutureFactory.getFutureFactory().execute(iFutureChannelContext -> new SimpleDatabaseTerminal(connectionInfo, MongoClients.create(defaults(connectionInfo))));
    }

    @Override
    public MongoClientSettings defaults(MongoConnection connectionInfo) {
        return MongoClientSettings.builder().credential(connectionInfo.credential()).applyToClusterSettings(new Block<>() {
            @Override
            public void apply(ClusterSettings.@NotNull Builder builder) {
                builder.hosts(List.of(connectionInfo.address()));
            }
        }).build();
    }

}
