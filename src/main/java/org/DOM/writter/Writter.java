package org.DOM.writter;

import org.model.Sonnet;

import java.io.PrintWriter;

public class Writter {

    public static void writter(Sonnet sonnet)
    {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(sonnet.getAuthor().getFirstName() + "_" + sonnet.getAuthor().getLastName() + "_" + sonnet.getTitle() + ".txt", "UTF-8");
            for(int i = 0; i < sonnet.getLines().size(); i++){
                writer.println(sonnet.getLines().get(i));
            }
        }
        catch (Exception e){
            System.out.println("Error with " + e.toString());
        }
        finally {
            writer.close();
        }
    }
}
