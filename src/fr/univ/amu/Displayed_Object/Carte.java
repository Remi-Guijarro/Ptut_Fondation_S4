package fr.univ.amu.Displayed_Object;

import java.io.File;

public class Carte {
    private String source;

    public Carte() {
        this.source = new File("src/fr/univ/amu/ressources/map.html").toURI().toString();
    }

    public String getSource() {
        return this.source;
    }
}
