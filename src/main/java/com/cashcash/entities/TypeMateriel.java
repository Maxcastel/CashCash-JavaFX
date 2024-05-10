package com.cashcash.entities;

public class TypeMateriel {
    // Attributes
    private String referenceInterne, libelleTypeMateriel;
    private Famille laFamille;
    
    // Constructor
    public TypeMateriel(String referenceInterne, String libelleTypeMateriel, Famille laFamille) { 
        this.referenceInterne = referenceInterne;
        this.libelleTypeMateriel = libelleTypeMateriel;
        this.laFamille = laFamille;
    } 
    
    // Getters and Setters
    public String getReferenceInterne() {
        return referenceInterne;
    }

    public void setReferenceInterne(String referenceInterne) {
        this.referenceInterne = referenceInterne;
    }

    public String getLibelleTypeMateriel() {
        return libelleTypeMateriel;
    }

    public void setLibelleTypeMateriel(String libelleTypeMateriel) {
        this.libelleTypeMateriel = libelleTypeMateriel;
    }

    public Famille getLaFamille() {
        return laFamille;
    }

    public void setLaFamille(Famille laFamille) {
        this.laFamille = laFamille;
    }

    // Methods
    @Override
    public String toString() {
        return "TypeMateriel [referenceInterne=" + referenceInterne + ", libelleTypeMateriel=" + libelleTypeMateriel
                + ", laFamille=" + laFamille + "]";
    }

}
