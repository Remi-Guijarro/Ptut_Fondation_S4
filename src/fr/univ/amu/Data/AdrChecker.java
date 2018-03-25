package fr.univ.amu.Data;

import fr.univ.amu.Object_Structure.Coordonée;

import java.util.Map;

public class AdrChecker {
    public static Boolean CheckIfCoordonateExist(String adresseFormaté){
       Map <String, Coordonée>  map = DbAdrToGPS.getAll();
        for (Map.Entry<String,Coordonée> e : map.entrySet()){
            if(e.getKey().equals(adresseFormaté)){
                //return e.getValue();
                return true;
            }
        }
        //return null;
        return false;
    }

    public static Coordonée GetCoordonate(String adresseFormaté){
        Map <String, Coordonée>  map = DbAdrToGPS.getAll();
        for (Map.Entry<String,Coordonée> e : map.entrySet()){
            String str = e.getKey();
            if(str.equals(adresseFormaté)){
                Coordonée c = e.getValue();
                System.out.println(c.getLat() +"   " + c.getLongitude());
                return c;
            }
        }
        return new Coordonée(0,0);
    }
}
