package org.runner;

import org.DOM.fabric.FabricSonnetFromXML;
import org.SAX.Parser;
import org.model.Sonnet;
import org.DOM.writter.Writter;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        System.out.print("Введи название XML-документа: ");
        Scanner scanner = new Scanner(System.in);
        String pathToXML = scanner.nextLine();
        System.out.println("Введи 1, чтобы использовать DOM-парсер. \nВведи 2, чтобы использовать SAX-парсер.");
        int a = scanner.nextInt();
        Sonnet sonnet = null;
        if(a == 1) sonnet = FabricSonnetFromXML.fabricSonnetFromXML(pathToXML + ".xml");
        if(a == 2) sonnet = Parser.parse(pathToXML + ".xml");
        if(sonnet == null) {
            System.out.println("XML-file не найден. ");
            return;
        }
        Writter.writter(sonnet);

    }
}
