package net.wiremc;

import lombok.Getter;
import net.wiremc.commons.coreplayer.SimpleCoreplayerRegistry;
import net.wiremc.commons.database.SimpleDatabaseBootstrap;
import net.wiremc.coreplayer.CoreplayerRegistry;
import net.wiremc.database.DatabaseBootstrap;
import net.wiremc.database.DatabaseTerminal;
import net.wiremc.database.MongoConnection;
import org.atomic.commons.Bootstrap;
import org.atomic.commons.futures.IFutureFactory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Getter
public final class Core extends JavaPlugin {

    @Getter
    private static final Core instance = new Core();

    /* future factory to handle async tasks */
    private final IFutureFactory futureFactory = IFutureFactory.Companion.getFutureFactory();
    private final MongoConnection connection = new MongoConnection("network", "1234", "core_db", "127.0.0.1", 27017);
    private final DatabaseBootstrap bootstrap = new SimpleDatabaseBootstrap();
    private final DatabaseTerminal databaseTerminal = this.bootstrap
            .connect(this.connection)
            .get()
            .orElseThrow(NullPointerException::new);
    private final byte threadCount = (byte) Runtime.getRuntime().availableProcessors();
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(threadCount-2, threadCount, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    private final CoreplayerRegistry coreplayerRegistry = new SimpleCoreplayerRegistry();

    @Override
    public void onEnable() {
        Bootstrap.Companion.enable();

    }

    @Override
    public void onDisable() {

        this.databaseTerminal.getMongoClient().close();
    }

}
