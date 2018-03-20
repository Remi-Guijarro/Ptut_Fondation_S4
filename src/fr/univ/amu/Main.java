package fr.univ.amu;

import fr.univ.amu.Data.CsvReader;
import fr.univ.amu.Data.DbAdrToGPS;
import fr.univ.amu.Data.DbDonateur;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Gestion Donateurs");
        primaryStage.setScene(new Scene(root, 1200, 600));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("apparaitre une pop-up qui dit : attention vos données ne seront pas sauvegardés");
                System.out.println("Destruction de la table Donateurs");
                DbDonateur.supprTable();

            }
        });
    }


    public static void main(String[] args) {
        DbDonateur.GetConnected();
        CsvReader csvReader = new CsvReader("/home/remi/IdeaProjects/GeolocalisationDonS4/src/fr/univ/amu/ressources/data.csv");
        csvReader.getHeader();
        csvReader.readFromFile();
        DbDonateur.displayAll();
        //DbAdrToGPS.GetConnected();
        //DbAdrToGPS.createTable();
        //DbAdrToGPS.insertTuple("45 avenue du sangloer , 21600 Une ville en France","1.5678","3.7654");
        //DbAdrToGPS.displayAll();
        launch(args);
    }
}
