package com.cashcash.entities;

public class Famille {

    //Attributes
    private int code;
    private String libelle;

    //Constructor
    public Famille(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    //Getters and setters
    public int getCodeFamille() {
        return code;
    }

    public void setCodeFamille(int codeFamille) {
        this.code = codeFamille;
    }

    public String getLibelleFamille() {
        return libelle;
    }

    public void setLibelleFamille(String libelleFamille) {
        this.libelle = libelleFamille;
    }

    //toString
    @Override
    public String toString() {
        return "Famille [codeFamille=" + code + ", libelleFamille=" + libelle + "]";
    }
    
}
