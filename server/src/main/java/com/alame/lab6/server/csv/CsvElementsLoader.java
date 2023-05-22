package com.alame.lab6.server.csv;

import com.alame.lab6.common.data.Coordinates;
import com.alame.lab6.common.data.Person;
import com.alame.lab6.common.data.StudyGroup;
import com.alame.lab6.common.data.StudyGroupValidator;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;
import com.alame.lab6.common.exceptions.IncorrectKeyException;
import com.alame.lab6.common.parsers.CoordinatesParser;
import com.alame.lab6.common.parsers.KeyParser;
import com.alame.lab6.common.parsers.PersonParser;
import com.alame.lab6.common.parsers.StudyGroupParser;
import com.alame.lab6.common.request.ClientServerInterface;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.TreeMap;

/**
 * class for read element of collection from csv file
 */
public class CsvElementsLoader {
    /**
     * headers of csv file
     */
    private final static String[] HEADERS = { "key", "id", "name", "Coordinates x", "Coordinates y", "creationDate",
            "studentsCount", "expelledStudents", "formOfEducation", "semesterEnum", "groupAdmin name",
            "groupAdmin birthday", "groupAdmin eyeColor", "groupAdmin hairColor", "groupAdmin nationality"};
    /**
     * field that store collection
     */
    private final ClientServerInterface server;
    /**
     * field for reading file
     */
    private final Reader reader;
    private final StudyGroupParser studyGroupParser;
    private final PersonParser personParser;
    private final CoordinatesParser coordinatesParser;
    private final KeyParser keyParser;
    private final StudyGroupValidator studyGroupValidator;
    public CsvElementsLoader(Reader reader, ClientServerInterface server, StudyGroupParser studyGroupParser, PersonParser personParser, CoordinatesParser coordinatesParser, KeyParser keyParser, StudyGroupValidator studyGroupValidator){
        this.reader = reader;
        this.server = server;
        this.studyGroupParser = studyGroupParser;
        this.personParser = personParser;
        this.coordinatesParser = coordinatesParser;
        this.keyParser = keyParser;
        this.studyGroupValidator = studyGroupValidator;
    }

    /**
     * load elements from file
     * @throws IOException if something goes wrong with file
     * @throws IncorrectElementFieldException if element field is incorrect
     * @throws IncorrectKeyException if key is incorrect
     */
    public void load() throws IOException, IncorrectElementFieldException, IncorrectKeyException {
        Map<String, StudyGroup> elements = new TreeMap<>();
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .setNullString("")
                .setDelimiter(",")
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(reader);

        for (CSVRecord record : records) {
            String key = record.get("key");
            String id = record.get("id");
            String name = record.get("name");
            String coordinatesX = record.get("Coordinates x");
            String coordinatesY = record.get("Coordinates y");
            String creationDate = record.get("creationDate");
            String studentsCount = record.get("studentsCount");
            String expelledStudents = record.get("expelledStudents");
            String formOfEducation = record.get("formOfEducation");
            String semesterEnum = record.get("semesterEnum");
            String groupAdminName = record.get("groupAdmin name");
            String groupAdminBirthday = record.get("groupAdmin birthday");
            String groupAdminEyeColor = record.get("groupAdmin eyeColor");
            String groupAdminHairColor = record.get("groupAdmin hairColor");
            String groupAdminNationality = record.get("groupAdmin nationality");
            Coordinates coordinates = new Coordinates(coordinatesParser.parseX(coordinatesX),
                    coordinatesParser.parseY(coordinatesY));
            Person groupAdmin = new Person(personParser.parseName(groupAdminName),
                    personParser.parseBirthday(groupAdminBirthday),
                    personParser.parseEyesColor(groupAdminEyeColor), personParser.parseHairColor(groupAdminHairColor),
                    personParser.parseCountry(groupAdminNationality));
            StudyGroup studyGroup = new StudyGroup(studyGroupParser.parseId(id),studyGroupParser.parseName(name),
                    coordinates, studyGroupParser.parseCreationDate(creationDate),
                    studyGroupParser.parseStudentsCount(studentsCount),
                    studyGroupParser.parseExpelledStudents(expelledStudents),
                    studyGroupParser.parseFormOfEducation(formOfEducation),
                    studyGroupParser.parseSemester(semesterEnum), groupAdmin);
            elements.put(keyParser.parseKey(key), studyGroup);
        }
        if (!(studyGroupValidator.validCollectionId(elements.values())))
            throw new IncorrectElementFieldException("id должны быть различны");
        server.putAll(elements);
    }

    /**
     * close reader
     */
    public void close() throws IOException{
        reader.close();
    }
}
