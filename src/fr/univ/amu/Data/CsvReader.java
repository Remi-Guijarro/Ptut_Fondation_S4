package fr.univ.amu.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvReader {
    private String filename;
    private File csvFile;
    private String[] enteteFichier;

    public CsvReader(String Filename){
        this.filename = Filename;
        csvFile = new File(this.filename);
    }

    public void getHeader() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(this.csvFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String completeEntete;
        completeEntete = scanner.next();
        String[] entete;
        entete = completeEntete.split(",");
        System.out.println("Entete  = " + completeEntete);
        this.enteteFichier = entete;
    }

    public String[] readFromFile(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(this.csvFile);
            scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        String[] values = null;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            values = line.split(",");
            DbDonateur.insertTuple(values);
        }
        scanner.close();
        return values;
    }

    public String[] getEnteteFichier() {
        return this.enteteFichier;
    }
}
