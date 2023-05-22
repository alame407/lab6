package com.alame.lab6.common.request;

/**
 * abstract request that realize setter and getter for server
 */
public abstract class AbstractRequest implements Request{
    private ClientServerInterface server;

    /**
     * set server for request
     * @param server - server to set
     */
    @Override
    public void setServer(ClientServerInterface server) {
        this.server = server;
    }

    /**
     * get server of request
     * @return server of request
     */
    public ClientServerInterface getServer() {
        return server;
    }
}
