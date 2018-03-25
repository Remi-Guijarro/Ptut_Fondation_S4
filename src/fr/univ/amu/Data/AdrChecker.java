package fr.univ.amu.Data;

import fr.univ.amu.Object_Structure.Coordonée;

import java.util.Map;

public class AdrChecker {
    public static Coordonée CheckIfCoordonateExist(String adresseFormaté){
       Map <String, Coordonée>  map = DbAdrToGPS.getAll();
        for (Map.Entry<String,Coordonée> e : map.entrySet()){
            if(e.getKey() == adresseFormaté){
                return e.getValue();
            }
        }
        return null;
    }
}
