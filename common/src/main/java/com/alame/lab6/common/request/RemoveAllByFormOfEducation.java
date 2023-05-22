package com.alame.lab6.common.request;

import com.alame.lab6.common.data.FormOfEducation;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;
import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

/**
 * request for remove all study groups by form of education
 */
public class RemoveAllByFormOfEducation extends AbstractRequest{
    private final FormOfEducation formOfEducation;

    public RemoveAllByFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    /**
     * remove all study groups by form of education
     * @return generated response
     */
    @Override
    public Response<String> handle() {
        try {
            getServer().removeAllByFormOfEducation(formOfEducation);
            return new Response<>(ResponseStatus.SUCCESS, "Команда выполнена успешно", null);
        }
        catch (IncorrectElementFieldException e){
            return new Response<>(ResponseStatus.FAIL, null, e.getMessage());
        }

    }
}
