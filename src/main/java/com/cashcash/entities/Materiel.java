package com.cashcash.entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Représente un matériel.
 */
public class Materiel {

    // Attributs
    private int numSerie;
    private LocalDate dateVente;
    private LocalDate dateInstallation;
    private double prixVente;
    private String emplacement;
    private int contratNum;
    private TypeMateriel leType;

    /**
     * Constructeur pour un matériel.
     *
     * @param numSerie         Le numéro de série du matériel
     * @param dateVente        La date de vente du matériel
     * @param dateInstallation La date d'installation du matériel
     * @param prixVente        Le prix de vente du matériel
     * @param emplacement      L'emplacement du matériel
     * @param leType           Le type de matériel
     * @param contratNum       Le numéro du contrat lié au matériel
     */
    public Materiel(int numSerie, Date dateVente, Date dateInstallation, double prixVente, String emplacement,
                    TypeMateriel leType, int contratNum) {
        this.numSerie = numSerie;
        this.dateVente = dateVente.toLocalDate();
        this.dateInstallation = dateInstallation.toLocalDate();
        this.prixVente = prixVente;
        this.emplacement = emplacement;
        this.leType = leType;
        this.contratNum = contratNum;
    }

    /**
     * Retourne la chaîne correspondant au code XML représentant le matériel.
     *
     * @param nbJourAvantEcheance Le nombre de jours avant l'échéance du contrat lié au matériel
     * @return La chaîne représentant le matériel en XML
     */
    public String xmlMateriel(int nbJourAvantEcheance) {
        String codeXMLMateriel = "\t<materiel numSerie=\"" + numSerie + "\">\n";

        codeXMLMateriel += "\t\t<famille codeFamille=\"" + leType.getLaFamille().getCodeFamille() + "\" libelle=\"" + leType.getLaFamille().getLibelleFamille() + "\" />\n";
        codeXMLMateriel += "\t\t<type refInterne=\"" + leType.getReferenceInterne() + "\" libelle=\"" + leType.getLibelleTypeMateriel() + "\" />\n";
        codeXMLMateriel += "\t\t<date_installation>" + dateInstallation.toString() + "</date_installation>\n";
        codeXMLMateriel += "\t\t<prix_vente>" + prixVente + "</prix_vente>\n";
        codeXMLMateriel += "\t\t<emplacement>" + emplacement + "</emplacement>\n";
        codeXMLMateriel += "\t\t<nbJourAvantEcheance>" + nbJourAvantEcheance + "</nbJourAvantEcheance>\n";

        codeXMLMateriel += "</materiel>";

        return codeXMLMateriel;
    }

    /**
     * Retourne la chaîne correspondant au code XML représentant le matériel.
     *
     * @return La chaîne représentant le matériel en XML
     */
    public String xmlMateriel() {
        String codeXMLMateriel = "\t<materiel numSerie=\"" + numSerie + "\">\n";

        codeXMLMateriel += "\t\t<type refInterne=\"" + leType.getReferenceInterne() + "\" libelle=\"" + leType.getLibelleTypeMateriel() + "\" />\n";
        codeXMLMateriel += "\t\t<famille codeFamille=\"" + leType.getLaFamille().getCodeFamille() + "\" libelle=\"" + leType.getLaFamille().getLibelleFamille() + "\" />\n";
        codeXMLMateriel += "\t\t<date_installation>" + dateInstallation.toString() + "</date_installation>\n";
        codeXMLMateriel += "\t\t<prix_vente>" + prixVente + "</prix_vente>\n";
        codeXMLMateriel += "\t\t<emplacement>" + emplacement + "</emplacement>\n";

        codeXMLMateriel += "\t</materiel>";

        return codeXMLMateriel;
    }

    // Getters & setters
    public int getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(int numSerie) {
        this.numSerie = numSerie;
    }

    public LocalDate getDateVente() {
        return dateVente;
    }

    public void setDateVente(LocalDate dateVente) {
        this.dateVente = dateVente;
    }

    public LocalDate getDateInstallation() {
        return dateInstallation;
    }

    public void setDateInstallation(LocalDate dateInstallation) {
        this.dateInstallation = dateInstallation;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public TypeMateriel getLeType() {
        return leType;
    }

    public void setLeType(TypeMateriel leType) {
        this.leType = leType;
    }

    public int getContratNum() {
        return contratNum;
    }

    public void setContratNum(int contratNum) {
        this.contratNum = contratNum;
    }

    // ToString
    @Override
    public String toString() {
        return "Materiel [numSerie=" + numSerie + ", dateVente=" + dateVente + ", dateInstallation=" + dateInstallation
                + ", prixVente=" + prixVente + ", emplacement=" + emplacement + ", leType=" + leType + "]";
    }
}
