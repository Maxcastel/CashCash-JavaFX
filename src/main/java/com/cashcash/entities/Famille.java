package com.cashcash.entities;

public class Famille {

    //Attributes
    private String codeFamille, libelleFamille;

    //Constructor
    public Famille(String codeFamille, String libelleFamille) {
        this.codeFamille = codeFamille;
        this.libelleFamille = libelleFamille;
    }

    //Getters and setters
    public String getCodeFamille() {
        return codeFamille;
    }

    public void setCodeFamille(String codeFamille) {
        this.codeFamille = codeFamille;
    }

    public String getLibelleFamille() {
        return libelleFamille;
    }

    public void setLibelleFamille(String libelleFamille) {
        this.libelleFamille = libelleFamille;
    }

    //toString
    @Override
    public String toString() {
        return "Famille [codeFamille=" + codeFamille + ", libelleFamille=" + libelleFamille + "]";
    }
    
}
