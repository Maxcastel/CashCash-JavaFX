package com.cashcash.entities;

/**
 * Représente une famille de produits.
 */
public class Famille {

    // Attributs
    private int code;
    private String libelle;

    /**
     * Constructeur d'une famille de produits.
     *
     * @param code    Le code de la famille
     * @param libelle Le libellé de la famille
     */
    public Famille(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    /**
     * Renvoie le code de la famille.
     *
     * @return Le code de la famille
     */
    public int getCodeFamille() {
        return code;
    }

    /**
     * Définit le code de la famille.
     *
     * @param codeFamille Le code de la famille à définir
     */
    public void setCodeFamille(int codeFamille) {
        this.code = codeFamille;
    }

    /**
     * Renvoie le libellé de la famille.
     *
     * @return Le libellé de la famille
     */
    public String getLibelleFamille() {
        return libelle;
    }

    /**
     * Définit le libellé de la famille.
     *
     * @param libelleFamille Le libellé de la famille à définir
     */
    public void setLibelleFamille(String libelleFamille) {
        this.libelle = libelleFamille;
    }

    /**
     * Renvoie une représentation textuelle de la famille.
     *
     * @return Une chaîne représentant la famille
     */
    @Override
    public String toString() {
        return "Famille [codeFamille=" + code + ", libelleFamille=" + libelle + "]";
    }
}
