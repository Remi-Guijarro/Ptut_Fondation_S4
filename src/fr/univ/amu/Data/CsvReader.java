package fr.univ.amu.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvReader {
    private String filename;
    private File csvFile;
    private String[] enteteFichier;

    public CsvReader(String Filename){                          // CONSTRUCTEUR
        this.filename = Filename;
        csvFile = new File(this.filename);
    }

    public void getHeader() {                                  // FONCTION QUI LIT LA PREMIERE LIGNE DU CSV ET INITIALISE LA VARIABLE ENTETE FICHIER AVEC LES VALEUR DE LA PREMMIERE LIGNE DU FICHIER
        Scanner scanner = null;
        try {
            scanner = new Scanner(this.csvFile);               // INITIALISATION DU SCANNER ( LECTEUR DE FICHIER )
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String ligneLu; // ligne lu
        ligneLu = scanner.next();
        String[] entete;
        entete = ligneLu.split(",");
        //System.out.println("Entete  = " + ligneLu);
        this.enteteFichier = entete;
        DbDonateur.setTailleEntete(entete.length);
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
            DbDonateur.insertTuple(values);                 // INSERTION DE LA LIGNE DANS LA BASE DE DONÉE
        }
        DbAdrToGPS.GetConnected();                                                                    // Connexion à la base de donnée des coordonées
        //DbAdrToGPS.createTable();
        CoordinateInserter.CreeColonnesCoordonnée();
        CoordinateInserter.getAndInsertGPS(DbDonateur.getAdrs());
        //DbAdrToGPS.displayAll();
       // DbDonateur.displayAll(enteteFichier);
        scanner.close();
        return values;
    }

    public String[] getEnteteFichier() {
        return this.enteteFichier;                          // RENVOI LA VARIABLE ENTETE DU FICHIER
    }
}
