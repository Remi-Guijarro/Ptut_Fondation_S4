package fr.univ.amu.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvReader {
    private String filename;
    private Scanner inputStream;

    public CsvReader(String Filename){
        this.filename = Filename;
        File file = new File(this.filename);
        try{
            this.inputStream = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String[] getHeader(){
        String completeEntete;
        completeEntete = inputStream.next();
        String[] entete;
        entete = completeEntete.split(",");
        System.out.println("Entete  = " + completeEntete);
        DbDonateur.createTable(entete);
        return entete;
    }

    public String[] readFromFile(){
        String data;
        data = inputStream.next();
        String[] values = new String[0];
        while (inputStream.hasNext()){
            data = inputStream.next();
            values = data.split(",");
            for (String valeur : values){
                System.out.print(valeur+"||");
            }
            System.out.println();
            DbDonateur.insertTuple(values);
        }
        inputStream.close();
        return values;
    }
}
