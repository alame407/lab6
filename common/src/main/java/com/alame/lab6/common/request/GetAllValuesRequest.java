package com.alame.lab6.common.request;

import com.alame.lab6.common.data.StudyGroup;
import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;
import java.util.List;

/**
 * request for get all study groups
 */
public class GetAllValuesRequest extends AbstractRequest{
    /**
     * get all study groups
     * @return generated response
     */
    @Override
    public Response<List<StudyGroup>> handle() {
        return new Response<>(ResponseStatus.SUCCESS, getServer().getAllValues(), null);
    }
}
