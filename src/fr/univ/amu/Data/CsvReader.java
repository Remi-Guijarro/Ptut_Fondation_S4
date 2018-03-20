package fr.univ.amu.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvReader {
    private String filename;
    private Scanner inputStream;
    public String[] enteteFichier;

    public CsvReader(String Filename){
        this.filename = Filename;
        File file = new File(this.filename);
        try{
            this.inputStream = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getHeader() {
        String completeEntete;
        completeEntete = inputStream.next();
        String[] entete;
        entete = completeEntete.split(",");
        System.out.println("Entete  = " + completeEntete);
        this.enteteFichier = entete;
    }

    public String[] readFromFile(){
        String data;
        String[] values = new String[0];
        while (inputStream.hasNext()){
            data = inputStream.next();
            values = data.split(",");
            DbDonateur.insertTuple(values);
        }
        inputStream.close();
        return values;
    }

    public String[] getEnteteFichier() {
        return this.enteteFichier;
    }
}
