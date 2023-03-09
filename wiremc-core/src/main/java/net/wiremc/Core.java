package net.wiremc;

import lombok.Getter;
import org.atomic.commons.Bootstrap;
import org.atomic.commons.futures.IFutureFactory;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Core extends JavaPlugin {

    private final Core instance = new Core();

    /* future factory to handle async tasks */
    private final IFutureFactory futureFactory = IFutureFactory.Companion.getFutureFactory();

    @Override
    public void onEnable() {
        Bootstrap.Companion.enable();

    }

    @Override
    public void onDisable() {

    }

}
