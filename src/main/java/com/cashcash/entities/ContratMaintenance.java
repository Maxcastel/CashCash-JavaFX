package com.cashcash.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Représente un contrat de maintenance pour assurer des matériels.
 */
public class ContratMaintenance {

    // Attributs
    private int numContrat;
    private LocalDate dateSignature, dateEcheance;
    private ArrayList<Materiel> lesMaterielsAssures;

    /**
     * Constructeur par détails pour un contrat de maintenance.
     *
     * @param numContrat    Le numéro du contrat
     * @param dateSignature La date de signature du contrat
     * @param dateEcheance  La date d'échéance du contrat
     */
    public ContratMaintenance(int numContrat, Date dateSignature, Date dateEcheance) {
        this.numContrat = numContrat;
        this.dateSignature = dateSignature.toLocalDate();
        this.dateEcheance = dateEcheance.toLocalDate();
        this.lesMaterielsAssures = new ArrayList<Materiel>();
    }

    /**
     * Renvoie le nombre de jours restants avant l'échéance du contrat.
     *
     * @return Le nombre de jours restants
     */
    public int getJoursRestants() {
        LocalDate now = LocalDate.now();
        long joursRestants = dateEcheance.until(now, ChronoUnit.DAYS);
        return Math.toIntExact(joursRestants);
    }

    /**
     * Indique si le contrat est valide (la date du jour est entre la date de signature et la date d’échéance).
     *
     * @return true si le contrat est valide, false sinon
     */
    public boolean estValide() {
        LocalDate now = LocalDate.now();
        return now.isAfter(dateSignature) && now.isBefore(dateEcheance);
    }

    /**
     * Ajoute un matériel à la collection des matériels assurés si la date de signature du contrat est antérieure à la date d’installation du matériel.
     *
     * @param unMateriel Le matériel à ajouter au contrat
     */
    public void ajouteMateriel(Materiel unMateriel) {
        if (dateSignature.isBefore(unMateriel.getDateInstallation())) {
            lesMaterielsAssures.add(unMateriel);
        }
    }

    // Getters & Setters

    public int getNumContrat() {
        return numContrat;
    }

    public void setNumContrat(int numContrat) {
        this.numContrat = numContrat;
    }

    public LocalDate getDateSignature() {
        return dateSignature;
    }

    public void setDateSignature(LocalDate dateSignature) {
        this.dateSignature = dateSignature;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public ArrayList<Materiel> getLesMaterielsAssures() {
        return lesMaterielsAssures;
    }

    public void setLesMaterielsAssures(ArrayList<Materiel> lesMaterielsAssures) {
        this.lesMaterielsAssures = lesMaterielsAssures;
    }
}
