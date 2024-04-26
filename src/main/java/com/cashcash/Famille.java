package com.cashcash;

public class Famille {

    private String codeFamille, libelleFamille;

    public Famille(String codeFamille, String libelleFamille) {
        this.codeFamille = codeFamille;
        this.libelleFamille = libelleFamille;
    }

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

    @Override
    public String toString() {
        return "Famille [codeFamille=" + codeFamille + ", libelleFamille=" + libelleFamille + "]";
    }
    
}
