package net.wiremc.coreplayer;

import java.util.UUID;
import java.util.concurrent.Future;

public interface Coreplayer {

    public CoreplayerObject get();

    public Future<Coreplayer> push();

    public UUID getUniqueID();

}
