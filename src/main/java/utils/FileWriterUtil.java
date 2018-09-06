package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public final class FileWriterUtil {

    final static String FILEPATH = "build/reports/userpurchase.txt";

    public static void writeFile(String textToWrite){
       try{
           FileWriter fileWriter = new FileWriter(FILEPATH);
           PrintWriter printWriter = new PrintWriter(fileWriter);
           printWriter.print(textToWrite);
           printWriter.close();
       }
       catch (IOException e){
           e.printStackTrace();
       }
    }
}
