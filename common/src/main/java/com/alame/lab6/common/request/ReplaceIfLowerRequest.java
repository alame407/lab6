package com.alame.lab6.common.request;

import com.alame.lab6.common.data.StudyGroup;
import com.alame.lab6.common.exceptions.CollectionFullException;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;
import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

/**
 * request for replace element by key if element less than given
 */
public class ReplaceIfLowerRequest extends AbstractRequest{
    private final StudyGroup studyGroup;
    private final String key;

    public ReplaceIfLowerRequest(StudyGroup studyGroup, String key) {
        this.studyGroup = studyGroup;
        this.key = key;
    }

    /**
     * replace element by key if element less than given
     * @return generated response
     */
    @Override
    public Response<String> handle() {
        try {
            studyGroup.setId(getServer().getNextId());
            studyGroup.setCreationDate(getServer().getCreationDate());
            getServer().replaceIfLower(key, studyGroup);
            return new Response<>(ResponseStatus.SUCCESS, "Команда выполнена успешно", null);
        }
        catch (IncorrectElementFieldException | CollectionFullException e){
            return new Response<>(ResponseStatus.FAIL, null, e.getMessage());
        }
    }
}
