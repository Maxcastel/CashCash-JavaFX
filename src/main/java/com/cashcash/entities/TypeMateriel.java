package com.cashcash.entities;

/**
 * Représente un type de matériel.
 */
public class TypeMateriel {
    
    // Attributs
    private String referenceInterne, libelleTypeMateriel;
    private Famille laFamille;
    
    /**
     * Constructeur pour un type de matériel.
     *
     * @param referenceInterne      La référence interne du type de matériel
     * @param libelleTypeMateriel  Le libellé du type de matériel
     * @param laFamille            La famille à laquelle le type de matériel appartient
     */
    public TypeMateriel(String referenceInterne, String libelleTypeMateriel, Famille laFamille) { 
        this.referenceInterne = referenceInterne;
        this.libelleTypeMateriel = libelleTypeMateriel;
        this.laFamille = laFamille;
    } 
    
    // Getters & Setters
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

    // Méthodes
    @Override
    public String toString() {
        return "TypeMateriel [referenceInterne=" + referenceInterne + ", libelleTypeMateriel=" + libelleTypeMateriel
                + ", laFamille=" + laFamille + "]";
    }
}
