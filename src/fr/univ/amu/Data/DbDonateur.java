package fr.univ.amu.Data;

import fr.univ.amu.Network_Call.Geocodeur;
import fr.univ.amu.Object_Structure.Coordonée;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbDonateur {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";        // INITIALISATION DU DRIVER ACCES A LA BD
    private static final String JDBC_URL = "jdbc:derby:donateurs;create= true";

    private static Connection connection;

    public static void GetConnected(){                                  // FONCTION DE CONNECTION A LA BASE DE DONNÉE
        try {
            connection = DriverManager.getConnection(JDBC_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection != null){
            //System.out.println("\tConnecté à la base de donnée Donateurs\t\n");
        }
    }

    public static void createTable(String[] arrayEntete){
        try {
            String query = "CREATE TABLE Donateurs (";
            for (String mot : arrayEntete){
                query += mot +" varchar(300) " + ",";
            }
            String concatQuery = query.substring(0, query.length() - 2);
            concatQuery += ")";
            //System.out.println(concatQuery);
            connection.createStatement().execute(concatQuery);
            System.out.println("\ttable Donateurs crée\t\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertTuple(String[] arrayDonateurs){
        String query = "INSERT INTO Donateurs VALUES(";
        for (String mot : arrayDonateurs) {
            if (mot == "" || mot == " ") {
                query += "'" + "NULL" + "'" + " ,";
            } else {
                query += "'"+mot+"'" + " ,";
            }

        }
        String concatQuery = query.substring(0, query.length() - 2);
        concatQuery += ")";
        //System.out.println(concatQuery);
        if (concatQuery != "''") {
            try {
                connection.createStatement().execute(concatQuery);
            } catch (SQLException e) {
            }
        }
    }

    public static List<Coordonée> getCoordonnees() {
        try {
            String query = "SELECT ADR3, CPOST, VILLE FROM Donateurs";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Coordonée> lesCoordonnées = new ArrayList<Coordonée>();
            while (resultSet.next()){
                 lesCoordonnées.add(Geocodeur.getCoordonéeFromAdr(resultSet.getString("ADR3") + " " + resultSet.getString("CPOST") + " " + resultSet.getString("VILLE")));
            }
            return lesCoordonnées;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Coordonée>();
    }

    public static ArrayList<String> getAdrs(){
        try {
            String query = "SELECT ADR3, CPOST, VILLE FROM Donateurs";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ArrayList<String> adresses = new ArrayList<String>();
            while (resultSet.next()){
                adresses.add(resultSet.getString("ADR3")+" " + resultSet.getString("CPOST") + " " + resultSet.getString("VILLE"));
            }
            resultSet = statement.executeQuery(query);
            return adresses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    public static void trierParCP(String codePostal) {
        try {
            String query = "";
            if(codePostal.length() == 2) {
                query = "SELECT * FROM Donateurs WHERE CPOST LIKE '" + codePostal +"%'";
            } else if(codePostal.length() == 5) {
                query = "SELECT * FROM Donateurs WHERE CPOST = '" + codePostal +"'";
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("\tDonateurs du code postal "+ codePostal +" :\t\n");
            List<Coordonée> lesCoordonnées = new ArrayList<Coordonée>();
            while (resultSet.next()){
                System.out.println(resultSet.getString("PRENOM"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayAll(String[] ArrayEntete) {
        try {
            String query = "SELECT * FROM Donateurs";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("\tAffichage des tuples :\t\n");
            while (resultSet.next()){
                Geocodeur.getCoordonéeFromAdr(resultSet.getString("ADR3"));
                for (String data : ArrayEntete) {
                    System.out.print(resultSet.getString(data) + " ");

                }
                System.out.println();
            }
            System.out.println("\tterminé\t\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void supprTable(){
        String querySuppr = "DROP TABLE Donateurs";
        try {
            Statement statementSuppr = connection.createStatement();
            statementSuppr.execute(querySuppr);
            System.out.println("\tTable détruite\t\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
