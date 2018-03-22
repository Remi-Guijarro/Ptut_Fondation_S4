package fr.univ.amu.Network_Call;

import fr.univ.amu.Object_Structure.Coordonée;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;


public class Geocodeur {
    private String str;

    public static Coordonée getCoordonéeFromAdr(String adresse) {
        try {
            String adrGeocodeur = "http://photon.komoot.de/api/?q=" + URLEncoder.encode(adresse, "UTF-8");
            try {
                URL geoCodeur = new URL(adrGeocodeur);
                // read from the URL
                Scanner scan = new Scanner(geoCodeur.openStream());

                String str = new String();
                while (scan.hasNext())
                    str += scan.nextLine();
                scan.close();
                JSONObject obj = new JSONObject(str);
                // get the first result
                JSONObject res = obj.getJSONArray("features").getJSONObject(0);
                JSONObject loc = res.getJSONObject("geometry");
                JSONArray coordonne = loc.getJSONArray("coordinates");
                Coordonée result = new Coordonée(coordonne.getDouble(1), coordonne.getDouble(0));
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new Coordonée(0,0);
    }
}
