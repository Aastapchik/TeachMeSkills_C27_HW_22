package org.SAX;

import org.model.Author;
import org.model.Sonnet;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.Const.Const;

import java.util.ArrayList;
import java.util.List;

public class SAXParserHandler extends DefaultHandler {
    private String currentTagName;
    Sonnet sonnet = new Sonnet();
    Author author = new Author();
    private boolean isAuthor = false;
    private boolean isLines = false;
    private int count = 0;
    List<String> lines = new ArrayList<>();

    public Sonnet getSonnet() {
        return sonnet;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTagName = qName;
        if (currentTagName.equals(Const.TAG_NAME_AUTHOR)) isAuthor = true;
        if (currentTagName.equals(Const.TAG_NAME_LINES)) isLines = true;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        currentTagName = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentTagName == null) return;
        if (currentTagName.equals(Const.TAG_NAME_TITLE)) sonnet.setTitle(new String(ch, start, length));
        if (isAuthor) {
            if (currentTagName.equals(Const.TAG_NAME_FIRST_NAME))
                author.setFirstName(new String(ch, start, length));
            else if (currentTagName.equals(Const.TAG_NAME_LAST_NAME))
                author.setLastName(new String(ch, start, length));
            else if (currentTagName.equals(Const.TAG_NAME_NATIONALITY))
                author.setNationality(new String(ch, start, length));
            else if (currentTagName.equals(Const.TAG_NAME_YEAR_OF_BIRTH))
                author.setYearOfBirth(Integer.parseInt(new String(ch, start, length)));
            else if (currentTagName.equals(Const.TAG_NAME_YEAR_OF_DEATH))
                author.setYearOfDeath(Integer.parseInt(new String(ch, start, length)));
            count++;
            if (count == 5) {
                sonnet.setAuthor(author);
                isAuthor = false;
                count = 0;
            }
        }
        if (isLines) {
            lines.add(new String(ch, start, length));
            count++;
            if (count == 15) {
                sonnet.setLines(lines);
                isLines = false;
                count = 0;
            }
        }


    }
}
