package fr.univ.amu.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvReader {
    private String filename;
    private Scanner inputStream;
    private String[] enteteFichier;
    private ArrayList<String> query;

    public CsvReader(String Filename){
        this.filename = Filename;
        File file = new File(this.filename);
        this.query = new ArrayList<String>();
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
        String line;
        String[] values = null;
        while (inputStream.hasNextLine()) {
            line = inputStream.nextLine();
            values = line.split(",");
            //  System.out.println(values.toString());
            DbDonateur.insertTuple(values);
        }
        inputStream.close();
        return values;
    }

    public String[] getEnteteFichier() {
        return this.enteteFichier;
    }
}
