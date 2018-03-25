package fr.univ.amu.Data;

import fr.univ.amu.Network_Call.Geocodeur;
import fr.univ.amu.Object_Structure.Address;
import fr.univ.amu.Object_Structure.Coordonée;

import java.util.ArrayList;
import java.util.Map;

public class CoordinateInserter {
    public static void CreeColonnesCoordonnée(){
        DbDonateur.AjouterColonnes("Latitudes");// Crée la colonnes Latitudes dans Donateurs
        DbDonateur.AjouterColonnes("Longitudes");// Crée la colonnes Longitudes dans Donateurs
    }

    public static void getAndInsertGPS(Map<String,Address> monAdr){
        for (Map.Entry<String,Address> uneAdresse : monAdr.entrySet()){
            if(AdrChecker.CheckIfCoordonateExist(uneAdresse.getKey()) != null){
                Coordonée myLatLong = AdrChecker.CheckIfCoordonateExist(uneAdresse.getKey());
                DbDonateur.modifyLatLong(myLatLong.getLat(),myLatLong.getLongitude(),uneAdresse.getValue());
            }else
            {
                Address ObjetAdr = uneAdresse.getValue();

                Coordonée myLatLongFromServ = Geocodeur.getCoordonéeFromAdr(ObjetAdr.getAdr() + " " + ObjetAdr.getCodePostal() + " " + ObjetAdr.getVille());
                DbDonateur.modifyLatLong(myLatLongFromServ.getLat(),myLatLongFromServ.getLongitude(),ObjetAdr);
                DbAdrToGPS.insertTuple(uneAdresse.getKey(),myLatLongFromServ.getLat(),myLatLongFromServ.getLongitude());
            }
        }
    }
}
