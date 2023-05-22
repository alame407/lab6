package com.alame.lab6.common.request;

import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

/**
 * request for check if key represented in collection
 */
public class KeyExistRequest extends AbstractRequest{
    private final String key;

    public KeyExistRequest(String key) {
        this.key = key;
    }

    /**
     * request for check if key represented in collection
     * @return generated response
     */
    @Override
    public Response<Boolean> handle() {
        return new Response<>(ResponseStatus.SUCCESS, getServer().keyExist(key), null);
    }
}
