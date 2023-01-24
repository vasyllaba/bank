package com.solvd.bank.utils;

import com.solvd.bank.enums.Role;
import com.solvd.bank.models.Client;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MySAXParser extends DefaultHandler {
    final String fileName = "src/main/java/com/solvd/bank/utils/Clients.xml";

    private String currentTag = "";
    private Client client;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
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
        currentTag = "";
    }

    public Client getClient(){
        return client;
    }
}
