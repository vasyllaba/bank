package com.solvd.bank.utils;

import com.solvd.bank.models.Client;
import com.solvd.bank.models.Department;
import com.solvd.bank.models.Passport;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JSONParserTest {
    Passport passport;
    String json;
    String result;
    private final String path = "src/main/resources/test/passportSerializeTest.json";
    private final String serializePath = "src/main/resources/test/serializeTest.json";
    private static final Logger LOGGER = Logger.getLogger(JSONParserTest.class);

    @BeforeMethod
    public void setUp() {
        LOGGER.info("call setUp()");
        Date date = new Date(1674562027048l);
        passport = new Passport();
        passport.setPassportImage("/mock/path");
        passport.setPassportNumber("KK1002LL");
        passport.setDate_of_birth(date);
        passport.setFirstName("Super");
        passport.setLastName("Tester");

        json = """
        {"date_of_birth":"24-07-2023","id":null,"firstName":"Super","lastName":"Tester","passportNumber":"KK1002LL","passportImage":"/mock/path"}""";
    }
    @DataProvider(name = "serializeDataProvider")
    public Object[][] createSerializeData(){
        Date date = new Date(1674562027048l);
        passport = new Passport();
        passport.setPassportImage("/mock/path");
        passport.setPassportNumber("KK1002LL");
        passport.setDate_of_birth(date);
        passport.setFirstName("Super");
        passport.setLastName("Tester");

        json = """
        {"date_of_birth":"24-07-2023","id":null,"firstName":"Super","lastName":"Tester","passportNumber":"KK1002LL","passportImage":"/mock/path"}""";

        return new Object[][]{
                {passport, json},
                {new Client("+00044412211", "myMyEmail@gmail.com", "1111"),
                """
                    {"id":null,"passportId":null,"mobile":"+00044412211","email":"myMyEmail@gmail.com","password":"1111","role":null,"passport":null}"""
                },
                {new Department("TestAutomation"),"{\"id\":null,\"name\":\"TestAutomation\"}"}
        };
    }


    @Test(dataProvider = "serializeDataProvider", description = "Serialize data and compare two string")
    public void testSerialize(Object model, String expected) {
        LOGGER.info("call testSerialize()");
        JSONParser.serialize(model, serializePath);
        List<Character> characters = new ArrayList<>();
        try(FileInputStream inputStream = new FileInputStream(serializePath)) {
            StringBuilder sb = new StringBuilder();
            int i=-1;
            while((i=inputStream.read())!=-1){
                characters.add((char)i);
                sb.append((char)i);
            }
            result = sb.toString();
        } catch (IOException e) {
            LOGGER.error(e);
        }
        Assert.assertEquals(result, expected);
    }

    @Test(description = "deserialize class from file and compare class objects")
    public void testDeserialize() {
        LOGGER.info("call testDeserialize()");
        Passport testPassport = (Passport)JSONParser.deserialize(new Passport(), path);
        Assert.assertEquals(testPassport, passport);

    }

}