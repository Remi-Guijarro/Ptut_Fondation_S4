package fr.univ.amu;

import fr.univ.amu.Data.CsvReader;
import fr.univ.amu.Data.DbDonateur;
import fr.univ.amu.Network_Call.Geocodeur;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;

public class Main extends Application {

    public static void main(String[] args) {
        DbDonateur.GetConnected();
        CsvReader csvReader = new CsvReader("src/fr/univ/amu/ressources/data.csv");
        csvReader.getHeader();
        DbDonateur.createTable(csvReader.getEnteteFichier());
        //System.out.println(csvReader.getEnteteFichier());
        csvReader.readFromFile();
        DbDonateur.displayAll(csvReader.getEnteteFichier());
        //DbAdrToGPS.GetConnected();
        //DbAdrToGPS.createTable();
        //DbAdrToGPS.insertTuple("45 avenue du sangloer , 21600 Une ville en France","1.5678","3.7654");
        //DbAdrToGPS.displayAll();
        Geocodeur.getCoordonéeFromAdr(" La ciotat");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Gestion Donateurs");

        final WebView webView = new WebView();
        AnchorPane.setTopAnchor(webView, 0d);

        Label cptDonateurs = new Label();

        final WebEngine webengine = webView.getEngine();

        AnchorPane.setLeftAnchor(webView, 0d);
        AnchorPane.setBottomAnchor(webView, 0d);
        AnchorPane.setRightAnchor(webView, 0d);

        // Barre sur la gauche de l'écran : zoom.
        /*final Button zoomInButton = new Button("+");
        zoomInButton.setDisable(true);
        zoomInButton.setStyle("-fx-background-radius: 20, 19, 18, 17;");
        zoomInButton.setPrefSize(28, 28);
        zoomInButton.setOnAction(actionEvent -> webView.getEngine().executeScript("document.zoomIn();"));
        final Button zoomOutButton = new Button("-");
        zoomOutButton.setDisable(true);
        zoomOutButton.setStyle("-fx-background-radius: 20, 19, 18, 17;");
        zoomOutButton.setPrefSize(28, 28);
        zoomOutButton.setOnAction(actionEvent -> webView.getEngine().executeScript("document.zoomOut();"));
        final VBox leftControls = new VBox(zoomInButton, zoomOutButton);
        leftControls.setSpacing(6);
        AnchorPane.setTopAnchor(leftControls, 50d);
        AnchorPane.setLeftAnchor(leftControls, 6d);*/


        // Assemblage.
       /* final AnchorPane rooty = new AnchorPane();
        rooty.getChildren().setAll(webView,leftControls);

        webView.getEngine().getLoadWorker().stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) -> {
            final boolean disabled = newValue != Worker.State.SUCCEEDED;
            zoomInButton.setDisable(disabled);
            zoomOutButton.setDisable(disabled);
        });*/

        //webView.getEngine().load(carte.getSource());

        final BorderPane background = new BorderPane();

        final ComboBox region = new ComboBox();
        region.getItems().addAll(
                "Alsace",
                "Aquitaine",
                "Auvergne",
                "Basse Normandie",
                "Bourgogne",
                "Bretagne",
                "Centre-Val de Loire",
                "Champagne-Ardenne",
                "Corse",
                "Franche-Comte",
                "Haute Normandie",
                "Ile-de-France",
                "Languedoc-Roussillon",
                "Limousin",
                "Lorraine",
                "Midi-Pyrenees",
                "Nord-Pas-de-Calais",
                "Pays de la Loire",
                "Picardie",
                "Poitou-Charantes",
                "PACA",
                "Rhone-Alpes"
        );

        region.valueProperty().addListener(observable -> webView.getEngine().executeScript("document.goToLocation('" + region.getValue().toString() + "')"));

        Menu ajouter = new Menu("");
        Label labelAjout = new Label("Ajouter");
        ajouter.setGraphic(labelAjout);
        labelAjout.setTextFill(Paint.valueOf("white"));
        MenuItem ajoutDon = new MenuItem("Ajouter Un Nouveau Donnateur");
        ajouter.getItems().addAll(ajoutDon);


        ajoutDon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Stage donnateur = new Stage();
                donnateur.setAlwaysOnTop(true);
                BorderPane fond = new BorderPane();

                Label labelnom = new Label("Nom : ");
                TextField nom = new TextField();
                nom.setPromptText("Nom");

                Label labelprenom = new Label("Prénom : ");
                labelprenom.setPadding(new Insets(10, 0, 0, 0));
                TextField prenom = new TextField();
                prenom.setPromptText("Prénom");

                Label labelAge = new Label("Age : ");
                labelAge.setPadding(new Insets(10, 0, 0, 0));
                TextField age = new TextField();
                age.setPromptText("Age");

                Label labeladr = new Label("Adresse : ");
                labeladr.setPadding(new Insets(10, 0, 0, 0));
                TextField adr = new TextField();
                adr.setPromptText("Adresse");

                Label labelVille = new Label("Ville : ");
                labelVille.setPadding(new Insets(10, 0, 0, 0));
                TextField ville = new TextField();
                ville.setPromptText("Ville");

                Label labelcodePostal = new Label("Code postal : ");
                labelcodePostal.setPadding(new Insets(10, 0, 0, 0));
                TextField codePostal = new TextField();
                codePostal.setPromptText("Code postal");

                Label labelMontantDon = new Label("Montant du don : ");
                labelMontantDon.setPadding(new Insets(10, 0, 0, 0));
                TextField montantDon = new TextField();
                montantDon.setPromptText("Montant du don");


                Button annulerDonateur = new Button("Annuler");
                Button validerDonateur = new Button("Valider");
                annulerDonateur.setPadding(new Insets(10, 10, 10, 10));
                validerDonateur.setPadding(new Insets(10, 10, 10, 10));
                final HBox boutonsDonateur = new HBox(20, annulerDonateur, validerDonateur);
                boutonsDonateur.setAlignment(Pos.CENTER);
                boutonsDonateur.setPadding(new Insets(20, 0, 0, 0));

                annulerDonateur.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        donnateur.close();
                    }
                });


                VBox contenu = new VBox(labelnom, nom, labelprenom, prenom, labelAge, age, labeladr, adr, labelVille, ville, labelcodePostal, codePostal, labelMontantDon, montantDon, boutonsDonateur);
                fond.setCenter(contenu);
                fond.setPadding(new Insets(20, 20, 20, 20));
                Scene scenedon = new Scene(fond, 300, 450);
                donnateur.setScene(scenedon);
                donnateur.setResizable(false);
                donnateur.setTitle("Ajouter un Donateur");
                donnateur.show();
            }
        });


        Menu afficher = new Menu("Affichage");
        MenuItem zoomp = new MenuItem("Zoom +");
        MenuItem zoomm = new MenuItem("Zoom -");
        afficher.getItems().addAll(zoomp, new SeparatorMenuItem(), zoomm);

        Menu aide = new Menu("");
        Label aideLabel = new Label("Aide");
        aideLabel.setTextFill(Paint.valueOf("white"));
        aide.setGraphic(aideLabel);
        MenuItem docu = new MenuItem("Documentation");
        aide.getItems().addAll(docu);

        docu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                BorderPane root = new BorderPane();
                WebView test = new WebView();
                String documentation = new File("src/main/ressources/documentation.html").toURI().toString();
                test.getEngine().load(documentation);
                root.setCenter(test);
                stage.setScene(new Scene(root));
                stage.setAlwaysOnTop(true);
                stage.show();
            }
        });


        MenuBar menuBar = new MenuBar(ajouter, aide);

        menuBar.setUseSystemMenuBar(true);

        Label textRegion = new Label("Region :");
        textRegion.setTextFill(Paint.valueOf("white"));
        Label textFiltre = new Label("Filtres :");
        textFiltre.setTextFill(Paint.valueOf("white"));

        TextField ageMin = new TextField();
        ageMin.setPromptText("Age min");
        TextField ageMax = new TextField();
        ageMax.setPromptText("Age max");
        ageMin.setPrefWidth(80);
        ageMax.setPrefWidth(80);
        HBox filtreAge = new HBox(ageMin, ageMax);
        filtreAge.setSpacing(5);


        TextField donMin = new TextField();
        donMin.setPromptText("Don min");
        TextField donMax = new TextField();
        donMax.setPromptText("Don max");
        donMin.setPrefWidth(80);
        donMax.setPrefWidth(80);
        HBox filtreDon = new HBox(donMin, donMax);
        filtreDon.setSpacing(5);

        TextField dept = new TextField();
        dept.setPromptText("Num departement");
        dept.setPrefWidth(165);
        HBox filtreDept = new HBox(dept);
        filtreDept.setSpacing(5);

        Button validerAll = new Button("Valider");

        final Label erreur = new Label(" ");
        erreur.setTextFill(Color.RED);
        erreur.setAlignment(Pos.CENTER);


        HBox hboxErreur = new HBox(erreur);
        hboxErreur.setAlignment(Pos.CENTER);

        VBox commands = new VBox(30, textRegion, region, textFiltre, filtreAge, filtreDon, filtreDept, validerAll, hboxErreur);
        HBox horizon = new HBox(commands);


        commands.setStyle("-fx-background-color: #0d3a6d");
        commands.setPadding(new Insets(20, 50, 5, 50));


        background.setCenter(horizon);
        background.setTop(menuBar);
        menuBar.setStyle("-fx-background-color: #0d3a6d");


        background.setRight(commands);


        primaryStage.setResizable(true);
        primaryStage.setAlwaysOnTop(true);
        Scene scene = new Scene(background, 1200, 800);
        scene.setFill(Paint.valueOf("grey"));
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("apparaitre une pop-up qui dit : attention vos données ne seront pas sauvegardés");
                System.out.println("Destruction de la table Donateurs\n");
                DbDonateur.supprTable();

            }
        });
    }
}
