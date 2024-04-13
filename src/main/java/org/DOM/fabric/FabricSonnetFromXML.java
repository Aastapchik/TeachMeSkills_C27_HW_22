package org.DOM.fabric;

import org.DOM.DOMParser.OpenXMLFile;
import org.model.Author;
import org.model.Sonnet;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.Const.Const;
import java.util.List;

import static org.w3c.dom.Node.ELEMENT_NODE;

public class FabricSonnetFromXML {

    public static Sonnet fabricSonnetFromXML(String pathToXML) {
        Document doc;
        try {
            doc = OpenXMLFile.openFile(pathToXML);
        } catch (Exception e) {
            System.out.println("Error with exception " + e.toString());
            return null;
        }

        Node sonnetTag = doc.getFirstChild();
        NodeList sonnetChildren = sonnetTag.getChildNodes();
        Node authorNode = null;
        Node linesNode = null;
        String title = null;
        for (int i = 0; i < sonnetChildren.getLength(); i++) {
            if (sonnetChildren.item(i).getNodeType() != ELEMENT_NODE) continue;

            switch (sonnetChildren.item(i).getNodeName()) {

                case Const.TAG_NAME_AUTHOR: {
                    authorNode = sonnetChildren.item(i);
                    break;
                }
                case Const.TAG_NAME_TITLE: {
                    title = sonnetChildren.item(i).getTextContent();
                    break;
                }
                case Const.TAG_NAME_LINES: {
                    linesNode = sonnetChildren.item(i);
                    break;
                }

            }

        }

        if (authorNode == null || linesNode == null) return null;

        Author author = FabricAuthorFromXML.fabricAuthor(authorNode.getChildNodes());
        List<String> lines = FabricLinesFromXML.fabricLines(linesNode.getChildNodes());
        return new Sonnet(author, title, lines);

    }
}
