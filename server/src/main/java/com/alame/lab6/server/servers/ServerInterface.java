package com.alame.lab6.server.servers;

import com.alame.lab6.common.request.ClientServerInterface;
import java.io.IOException;

/**
 * interface server for a server project
 */
public interface ServerInterface extends ClientServerInterface {
    /**
     * save collection
     * @throws IOException if something goes wrong saving
     */
    void save() throws IOException;
}
