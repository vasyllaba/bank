package com.solvd.bank.utils;

import com.solvd.bank.enums.Role;
import com.solvd.bank.models.Client;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class MySAXParser extends DefaultHandler {
    final String FILE_PATH = "src/main/java/com/solvd/bank/utils/Clients.xml";
    private static final Logger LOGGER = Logger.getLogger(MySAXParser.class);

    private String currentTag = "";
    private Client client;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        LOGGER.info("call startElement method");

        switch (qName) {
            case "clients":
                break;
            case "client":
                client = new Client();
                client.setId(Long.valueOf(attributes.getValue(0)));
                currentTag = "client";
                break;
            case "passport_id":
                currentTag = "passport_id";
                break;
            case "mobile":
                currentTag = "mobile";
                break;
            case "email":
                currentTag = "email";
                break;
            case "password":
                currentTag = "password";
                break;
            case "role":
                currentTag = "role";
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        LOGGER.info("call characters method");

        switch (currentTag) {
            case "passport_id":
                client.setPassportId(Long.valueOf(new String(ch, start, length)));
                break;
            case "mobile":
                client.setMobile(new String(ch, start, length));
                break;
            case "email":
                client.setEmail(new String(ch, start, length));
                break;
            case "password":
                client.setPassword(new String(ch, start, length));
                break;
            case "role":
                client.setRole(Role.valueOf(new String(ch, start, length)));
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        LOGGER.info("call endElement method");

        currentTag = "";
    }

    public Client getClient(){
        LOGGER.info("call getClient method");

        return client;
    }
}
