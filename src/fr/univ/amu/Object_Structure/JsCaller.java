package fr.univ.amu.Object_Structure;

import javafx.scene.web.WebEngine;

public class JsCaller {
    public static void afficherDonateur(double latitude,double longitude,WebEngine engine)
    {
        engine.executeScript("document.placeMarkerDB("+latitude+","+longitude+")");                     // Appel de la fonction qui permet d'ajouter des marker
    }
}
