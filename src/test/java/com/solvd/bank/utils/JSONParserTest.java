package com.solvd.bank.utils;

import com.solvd.bank.models.Passport;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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


    @Test
    public void testSerialize() {
        LOGGER.info("call testSerialize()");
        JSONParser.serialize(passport, path);
        List<Character> characters = new ArrayList<>();
        try(FileInputStream inputStream = new FileInputStream(path)) {
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
        Assert.assertEquals(result, json);
    }

    @Test
    public void testDeserialize() {
        LOGGER.info("call testDeserialize()");
        Passport testPassport = (Passport)JSONParser.deserialize(new Passport(), path);
        Assert.assertEquals(testPassport, passport);

    }

}