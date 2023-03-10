package net.wiremc;

import lombok.Getter;
import net.wiremc.commons.database.SimpleDatabaseBootstrap;
import net.wiremc.database.DatabaseBootstrap;
import net.wiremc.database.DatabaseTerminal;
import net.wiremc.database.MongoConnection;
import org.atomic.commons.Bootstrap;
import org.atomic.commons.futures.IFutureFactory;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Core extends JavaPlugin {

    private final Core instance = new Core();

    /* future factory to handle async tasks */
    private final IFutureFactory futureFactory = IFutureFactory.Companion.getFutureFactory();
    private final MongoConnection connection = new MongoConnection("network", "1234", "core_db", "127.0.0.1", 27017);
    private final DatabaseBootstrap bootstrap = new SimpleDatabaseBootstrap();
    private final DatabaseTerminal databaseTerminal = this.bootstrap
            .connect(this.connection)
            .get()
            .orElseThrow(NullPointerException::new);

    @Override
    public void onEnable() {
        Bootstrap.Companion.enable();

    }

    @Override
    public void onDisable() {

        this.databaseTerminal.getMongoClient().close();
    }

}
