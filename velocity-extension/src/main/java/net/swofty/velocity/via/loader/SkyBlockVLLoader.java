package net.swofty.velocity.via.loader;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.api.protocol.version.VersionProvider;
import net.raphimc.vialoader.impl.viaversion.VLLoader;


public class SkyBlockVLLoader extends VLLoader {
    @Override
    public void load() {
        Via.getManager().getProviders().use(VersionProvider.class, userConnection -> userConnection.isClientSide() ? ProtocolVersion.v1_20_3 : ProtocolVersion.v1_13_2);
    }
}
