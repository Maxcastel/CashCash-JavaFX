package com.cashcash.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ContratMaintenance {

    private String numContrat;
    private LocalDate dateSignature, dateEcheance;

    private ArrayList<Materiel> lesMaterielsAssures;

    public ContratMaintenance(String numContrat, LocalDate dateSignature, LocalDate dateEcheance,
            ArrayList<Materiel> lesMaterielsAssures) {
        this.numContrat = numContrat;
        this.dateSignature = dateSignature;
        this.dateEcheance = dateEcheance;
        this.lesMaterielsAssures = new ArrayList<Materiel>();
    }

    public int getJoursRestants() {
        LocalDate now = LocalDate.now();
        long joursRestants = dateEcheance.until(now, ChronoUnit.DAYS);
        return Math.toIntExact(joursRestants);
    }

    public boolean estValide() {
        LocalDate now = LocalDate.now();
        return now.isAfter(dateSignature) && now.isBefore(dateEcheance);
    }

    public void ajouteMateriel(Materiel unMateriel) {
        if(dateSignature.isBefore(unMateriel.getDateInstallation())) {
            lesMaterielsAssures.add(unMateriel);
        }
    }
    

    public String getNumContrat() {
        return numContrat;
    }

    public void setNumContrat(String numContrat) {
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
