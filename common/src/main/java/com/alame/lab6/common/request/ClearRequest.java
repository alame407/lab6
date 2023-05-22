package com.alame.lab6.common.request;

import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

/**
 * clear collection request
 */
public class ClearRequest extends AbstractRequest{
    /**
     * clear collection
     * @return generated response
     */
    @Override
    public Response<String> handle() {
        getServer().clear();
        return new Response<>(ResponseStatus.SUCCESS, "Команда выполнена успешно", null);
    }
}
