package fr.univ.amu.Object_Structure;

public class Address {
    private String Adr;
    private String CodePostal;
    private String Ville;

    public Address(String a,String cp,String v){
        this.Adr = a;
        this.CodePostal = cp;
        this.Ville = v;
    }

    public void setAdr(String adr) {
        Adr = adr;
    }

    public String getAdr() {
        return Adr;
    }

    public void setCodePostal(String codePostal) {
        CodePostal = codePostal;
    }

    public String getCodePostal() {
        return CodePostal;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getVille() {
        return Ville;
    }
}
