package fr.univ.amu.Data;

import java.sql.*;

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

    public static void displayAll(String[] ArrayEntete) {
        try {
            String query = "SELECT * FROM Donateurs";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("\tAffichage des tuples :\t\n");
            while (resultSet.next()){
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
