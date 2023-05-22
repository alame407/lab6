package com.alame.lab6.common.request;

import com.alame.lab6.common.data.StudyGroup;
import com.alame.lab6.common.exceptions.CollectionFullException;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;
import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

/**
 * request for insert pair key, element in collection
 */
public class InsertRequest extends AbstractRequest{
    StudyGroup studyGroup;
    String key;
    public InsertRequest(StudyGroup studyGroup, String key){
        this.studyGroup = studyGroup;
        this.key = key;
    }

    /**
     * insert pair key, element in collection
     * @return generated response
     */
    @Override
    public Response<String> handle() {
        try {
            studyGroup.setId(getServer().getNextId());
            studyGroup.setCreationDate(getServer().getCreationDate());
            getServer().insert(key, studyGroup);
            return new Response<>(ResponseStatus.SUCCESS, "Команда выполнена успешно", null);
        }catch (IncorrectElementFieldException | CollectionFullException e){
            return new Response<>(ResponseStatus.FAIL, null, e.getMessage());
        }

    }

}
