package fr.univ.amu.Data;

import java.sql.*;

public class DbAdrToGPS {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:GeoCodeur;create= true";

    private static Connection connection;

    public static void GetConnected(){
        try {
            connection = DriverManager.getConnection(JDBC_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection != null){
            System.out.println("Connecté à la base de donnée GeoCodeur Local");
        }
    }

    public static void createTable(){
        try {
            String query = "CREATE TABLE GeoCodeur (" +
                    "adr VARCHAR(300)," +
                    "lat VARCHAR(300)," +
                    "longi VARCHAR(300))";
            System.out.println(query);
            connection.createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertTuple(String adr,String lat,String longi){
        try {
            String query = "INSERT INTO GeoCodeur VALUES(" + "'" + adr + "'" + "," + "'" + lat + "'" + "," + "'" + longi + "')";
            System.out.println(query);
            connection.createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayAll(){
        try {
            String query = "SELECT * FROM GeoCodeur";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println(resultSet.getString("adr") + "  " + resultSet.getString("lat") + "  " + resultSet.getString("longi"));
            }
            System.out.println("terminé");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

