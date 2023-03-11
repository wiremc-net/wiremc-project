package net.wiremc.coreplayer;

import java.util.UUID;

public interface Coreplayer {

    public CoreplayerObject get();

    public Coreplayer push();

    public UUID getUniqueID();

}
