package org.DOM.fabric;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import static org.w3c.dom.Node.ELEMENT_NODE;

public class FabricLinesFromXML {
    public static List<String> fabricLines(NodeList nodeList){
        List<String> lines = new ArrayList<>();

        for(int i = 0; i < nodeList.getLength(); i++)
        {
            if (nodeList.item(i).getNodeType() != ELEMENT_NODE) continue;
            lines.add(nodeList.item(i).getTextContent());
        }
        return lines;
    }
}
