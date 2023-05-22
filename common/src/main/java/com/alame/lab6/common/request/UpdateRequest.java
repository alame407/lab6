package com.alame.lab6.common.request;

import com.alame.lab6.common.data.Coordinates;
import com.alame.lab6.common.data.FormOfEducation;
import com.alame.lab6.common.data.Person;
import com.alame.lab6.common.data.Semester;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;
import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

/**
 * request for updating fields of element by id
 */
public class UpdateRequest extends AbstractRequest{
    private final int id;
    private final String name;
    private final Coordinates coordinates;
    private final int studentsCount;
    private final long expelledStudents;
    private final FormOfEducation formOfEducation;
    private final Semester semester;
    private final Person groupAdmin;
    public UpdateRequest(int id, String name, Coordinates coordinates, int studentsCount, long expelledStudents,
                         FormOfEducation formOfEducation, Semester semester, Person groupAdmin){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.studentsCount = studentsCount;
        this.expelledStudents = expelledStudents;
        this.formOfEducation = formOfEducation;
        this.semester = semester;
        this.groupAdmin = groupAdmin;
    }

    /**
     * updating fields of element by id
     * @return generated response
     */
    @Override
    public Response<String> handle() {
        try {
            getServer().update(id, name, coordinates, studentsCount, expelledStudents, formOfEducation, semester, groupAdmin);
            return new Response<>(ResponseStatus.SUCCESS, "Команда выполнена успешно", null);
        }
        catch (IncorrectElementFieldException e){
            return new Response<>(ResponseStatus.FAIL, null, e.getMessage());
        }
    }
}
