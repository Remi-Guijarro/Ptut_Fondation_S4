package fr.univ.amu.Data;

import java.sql.*;

public class DbDonateur {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:donateurs;create= true";

    private static Connection connection;

    public static void GetConnected(){
        try {
            connection = DriverManager.getConnection(JDBC_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection != null){
            System.out.println("Connecté à la base de donnée Donateurs");
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
            System.out.println(concatQuery);
            connection.createStatement().execute(concatQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertTuple(String[] arrayDonateurs){
        try {
            String query = "INSERT INTO Donateurs VALUES( ";
            for (String mot : arrayDonateurs){
                query += "'"+mot+"'" + " ,";
            }
            String concatQuery = query.substring(0, query.length() - 2);
            concatQuery += ")";
            System.out.println(concatQuery);
            connection.createStatement().execute(concatQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayAll(){
        try {
            String query = "SELECT * FROM Donateurs";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println(resultSet.getString("CODE") + "  " + resultSet.getString("VILLE") + "  " + resultSet.getString("MONEY"));
            }
            System.out.println("terminé");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void supprTable(){
        String querySuppr = "DROP TABLE Donateurs";
        try {
            Statement statementSuppr = connection.createStatement();
            statementSuppr.execute(querySuppr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
