package com.alame.lab6.common.request;

import com.alame.lab6.common.data.StudyGroup;
import com.alame.lab6.common.exceptions.CollectionIsEmptyException;
import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

/**
 * request for get max study group by creation date
 */
public class GetMaxByCreationDateRequest extends AbstractRequest{
    /**
     * get max study group by creation date
     * @return generated response
     */
    @Override
    public Response<StudyGroup> handle() {
        try {
            StudyGroup studyGroup = getServer().getMaxByCreationDate();
            return new Response<>(ResponseStatus.SUCCESS, studyGroup, null);
        }
        catch (CollectionIsEmptyException e){
            return new Response<>(ResponseStatus.FAIL, null, e.getMessage());
        }
    }
}
