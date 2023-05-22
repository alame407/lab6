package com.alame.lab6.common.request;

import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

/**
 * request for delete all keys which less than iven
 */
public class RemoveLowerKeyRequest extends AbstractRequest{
    private final String key;

    public RemoveLowerKeyRequest(String key) {
        this.key = key;
    }

    /**
     * delete all keys which less than iven
     * @return generated response
     */
    @Override
    public Response<String> handle() {
        getServer().removeLowerKey(key);
        return new Response<>(ResponseStatus.SUCCESS, "Команда выполнена успешно", null);
    }
}
