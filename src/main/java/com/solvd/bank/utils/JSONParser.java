package com.solvd.bank.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JSONParser {
    private static final Logger LOGGER = Logger.getLogger(JSONParser.class);
    private static ObjectMapper objectMapper;

    public static Object deserialize(Object o, String path){
        LOGGER.info("deserialize " + o + "from json, with path " + path);
        ObjectMapper om = getObjectMapper();
        try {
            o = om.readValue(new File(path), o.getClass());
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return o;
    }

    public static void serialize(Object o, String path){
        LOGGER.info("serialize " + o + "to json, with path " + path);
        ObjectMapper objectMapper = getObjectMapper();
        try {
            objectMapper.writeValue(new File(path), o);
        } catch (IOException e) {
            LOGGER.error(e);
        }

    }

    private static void setObjectMapper(){
        LOGGER.info("call setObjectMapper()");
        objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("MM-dd-yyy");
        objectMapper.setDateFormat(df);
    }

    private static ObjectMapper getObjectMapper() {
        LOGGER.info("call getObjectMapper()");
        if(objectMapper == null){
            setObjectMapper();
        }
        return objectMapper;
    }
}
