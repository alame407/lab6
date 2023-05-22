package com.alame.lab6.common.request;

import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

/**
 * request for remove key from collection
 */
public class RemoveKeyRequest extends AbstractRequest{
    private final String key;

    public RemoveKeyRequest(String key) {
        this.key = key;
    }

    /**
     * remove key from collection
     * @return generated response
     */
    @Override
    public Response<String> handle() {
        getServer().removeKey(key);
        return new Response<>(ResponseStatus.SUCCESS, "Команда выполнена успешно", null);
    }
}
