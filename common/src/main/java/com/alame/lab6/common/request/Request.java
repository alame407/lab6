package com.alame.lab6.common.request;

import com.alame.lab6.common.response.Response;

import java.io.Serializable;

/**
 * interface for all request to server
 */
public interface Request extends Serializable {
    /**
     * handle this request and generate response
     * @return generated response
     */
    Response<?> handle();

    /**
     * set server for request
     * @param clientServerInterface - server to set
     */
    void setServer(ClientServerInterface clientServerInterface);
}
