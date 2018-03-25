package fr.univ.amu.Data;

import fr.univ.amu.Network_Call.Geocodeur;
import fr.univ.amu.Object_Structure.Coordonée;

import java.util.ArrayList;
import java.util.Map;

public class CoordinateInserter {
    public static void CreeColonnesCoordonnée(){
        DbDonateur.AjouterColonnes("Latitudes");// Crée la colonnes Latitudes dans Donateurs
        DbDonateur.AjouterColonnes("Longitudes");// Crée la colonnes Longitudes dans Donateurs
    }

    public static void getAndInsertGPS(Map<String,ArrayList<String>> lesadresses){
        for (Map.Entry<String,ArrayList<String>> uneAdresse : lesadresses.entrySet()){
            if(AdrChecker.CheckIfCoordonateExist(uneAdresse.getKey()) != null){
                Coordonée myLatLong = AdrChecker.CheckIfCoordonateExist(uneAdresse.getKey());
                DbDonateur.modifyLatLong(myLatLong.getLat(),myLatLong.getLongitude(),uneAdresse.getValue());
            }else
            {
                String adresse = new String();
                for(String a : uneAdresse.getValue()){
                    adresse += " " + a;
                }
                Coordonée myLatLongFromServ = Geocodeur.getCoordonéeFromAdr(adresse);
                DbDonateur.modifyLatLong(myLatLongFromServ.getLat(),myLatLongFromServ.getLongitude(),uneAdresse.getValue());
            }
        }
    }
}
