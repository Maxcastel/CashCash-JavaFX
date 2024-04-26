package com.cashcash;

import java.util.ArrayList;

public class Client {

    private String raisonSociale, siren, codeApe, adresse, telClient, email;
    private int numClient, dureeDeplacement, distanceKm;
    
    private ArrayList<Materiel> lesMateriels;
    private ContratMaintenance leContrat;
    
    public Client(int numClient, String raisonSociale, String siren, String codeApe, String adresse,
            String telClient, String email, int dureeDeplacement, int distanceKm, ArrayList<Materiel> lesMateriels,
            ContratMaintenance leContrat) {
        this.numClient = numClient;
        this.raisonSociale = raisonSociale;
        this.siren = siren;
        this.codeApe = codeApe;
        this.adresse = adresse;
        this.telClient = telClient;
        this.email = email;
        this.dureeDeplacement = dureeDeplacement;
        this.distanceKm = distanceKm;
        this.lesMateriels = lesMateriels;
        this.leContrat = leContrat;
    }

    public int getNumClient() {
        return numClient;
    }

    public void setNumClient(int numClient) {
        this.numClient = numClient;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getCodeApe() {
        return codeApe;
    }

    public void setCodeApe(String codeApe) {
        this.codeApe = codeApe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelClient() {
        return telClient;
    }

    public void setTelClient(String telClient) {
        this.telClient = telClient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDureeDeplacement() {
        return dureeDeplacement;
    }

    public void setDureeDeplacement(int dureeDeplacement) {
        this.dureeDeplacement = dureeDeplacement;
    }

    public int getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(int distanceKm) {
        this.distanceKm = distanceKm;
    }

    public ArrayList<Materiel> getMateriels() {
        return lesMateriels;
    }

    public void setMateriels(ArrayList<Materiel> lesMateriels) {
        this.lesMateriels = lesMateriels;
    }

    public ContratMaintenance getLeContrat() {
        return leContrat;
    }

    public void setLeContrat(ContratMaintenance leContrat) {
        this.leContrat = leContrat;
    } 

    
}
