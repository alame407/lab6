package com.alame.lab6.common.request;

import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

/**
 * request for get info about collection
 */
public class GetInfoRequest extends AbstractRequest{
    /**
     * get info about collection
     * @return generated response
     */
    @Override
    public Response<String> handle() {
        return new Response<>(ResponseStatus.SUCCESS, getServer().info(), null);
    }
}
