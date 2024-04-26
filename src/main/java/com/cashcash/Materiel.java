package com.cashcash;

import java.time.LocalDate;

public class Materiel {

    private int numSerie;
    private LocalDate dateVente;
    private LocalDate dateInstallation;
    private double prixVente;
    private String emplacement;

    private TypeMateriel leType;

    public Materiel(int numSerie, LocalDate dateVente, LocalDate dateInstallation, double prixVente, String emplacement,
            TypeMateriel leType) {
        this.numSerie = numSerie;
        this.dateVente = dateVente;
        this.dateInstallation = dateInstallation;
        this.prixVente = prixVente;
        this.emplacement = emplacement;
        this.leType = leType;
    }
    
    public String xmlMateriel() {
        String codeXMLMateriel = "<materiel numSerie=\"" + numSerie +"\">\n";
    
        codeXMLMateriel += "<type refInterne=\"" + leType.getReferenceInterne() + "\" libelle=\"" + leType.getLibelleTypeMateriel() + "\" />\n";
        codeXMLMateriel += "<famille codeFamille=\"" + leType.getLaFamille().getCodeFamille() + "\" libelle=\"" + leType.getLaFamille().getLibelleFamille() + "\" />\n";
        codeXMLMateriel += "<date_vente>" + dateVente.toString() + "</date_vente>\n";
        codeXMLMateriel += "<date_installation>" + dateInstallation.toString() + "</date_installation>\n";
        codeXMLMateriel += "<prix_vente>" + prixVente + "</prix_vente>\n";
        codeXMLMateriel += "<emplacement>" + emplacement + "</emplacement>\n";
        codeXMLMateriel += "<nbJourAvantEcheance>94</nbJourAvantEcheance>\n";

        codeXMLMateriel += "</materiel>";

        return codeXMLMateriel;
    }

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

    @Override
    public String toString() {
        return "Materiel [numSerie=" + numSerie + ", dateVente=" + dateVente + ", dateInstallation=" + dateInstallation
                + ", prixVente=" + prixVente + ", emplacement=" + emplacement + ", leType=" + leType + "]";
    }
}
