package org.SAX;

import org.model.Sonnet;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Parser {
    public static Sonnet parse(String path) {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParserHandler handler = new SAXParserHandler();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (Exception ignored) {
        }

        File file = new File(path);

        try {
            parser.parse(file, handler);
        } catch (Exception ignored) {
        }


        return handler.getSonnet();
    }
}
