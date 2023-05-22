package com.alame.lab6.common.request;

import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

/**
 * request for check if id represented in collection
 */
public class IdExistRequest extends AbstractRequest{
    private final int id;

    public IdExistRequest(int id) {
        this.id = id;
    }

    /**
     * check if id represented in collection
     * @return generated response
     */
    @Override
    public Response<Boolean> handle() {
        return new Response<>(ResponseStatus.SUCCESS, getServer().idExist(id),null);
    }
}
