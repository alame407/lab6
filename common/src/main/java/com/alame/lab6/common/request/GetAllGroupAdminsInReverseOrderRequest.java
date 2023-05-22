package com.alame.lab6.common.request;

import com.alame.lab6.common.data.Person;
import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

import java.util.List;

/**
 * request for get all group admins of study groups in reverse order
 */
public class GetAllGroupAdminsInReverseOrderRequest extends AbstractRequest{
    /**
     * get all group admins of study groups in reverse order
     * @return generated response
     */
    @Override
    public Response<List<Person>> handle() {
        List<Person> personList = getServer().getAllGroupAdminsInReverseOrder();
        return new Response<>(ResponseStatus.SUCCESS, personList, null);
    }
}
