package com.cashcash.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ContratMaintenance {

    // Attributs
    private int numContrat;
    private LocalDate dateSignature, dateEcheance;

    private ArrayList<Materiel> lesMaterielsAssures;

    // Constructeur par détails
    public ContratMaintenance(int numContrat, LocalDate dateSignature, LocalDate dateEcheance) {
        this.numContrat = numContrat;
        this.dateSignature = dateSignature;
        this.dateEcheance = dateEcheance;
        this.lesMaterielsAssures = new ArrayList<Materiel>();
    }
    // Renvoie le nombre de jours avant que le contrat arrive à échéance
    public int getJoursRestants() {
        LocalDate now = LocalDate.now();
        long joursRestants = dateEcheance.until(now, ChronoUnit.DAYS);
        return Math.toIntExact(joursRestants);
    }
    // indique si le contrat est valide (la date du jour est entre la date de signature et la date d’échéance)
    public boolean estValide() {
        LocalDate now = LocalDate.now();
        return now.isAfter(dateSignature) && now.isBefore(dateEcheance);
    }
    // ajoute unMatériel à la collection lesMaterielsAssures si la date de signature du contrat est antérieure à la date d’installation du matériel

    public void ajouteMateriel(Materiel unMateriel) {
        if(dateSignature.isBefore(unMateriel.getDateInstallation())) {
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
