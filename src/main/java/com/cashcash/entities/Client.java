package com.cashcash.entities;

import java.util.ArrayList;

public class Client {

    private String raisonSociale, siren, codeApe, adresse, telClient, email;
    private int numClient, dureeDeplacement, distanceKm;
    
    private ArrayList<Materiel> lesMateriels;
    private ContratMaintenance leContrat;

    public Client(){}
    
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
        this.lesMateriels = new ArrayList<Materiel>(lesMateriels); // Tous les matériels du client.
        this.leContrat = leContrat;  // peut être nul si le client ne possède pas de contrat

    }


    // Classe demander

    // Retourne l'ensemble des matériels du client
    public ArrayList<Materiel> getMateriels() {
        return lesMateriels;
    }

    // Retourne l'ensemble des matériels pour lequels le client a souscrit un contrat de maintenance qui est encore valide

    public ArrayList<Materiel> getMaterielsSousContrat() {
        ArrayList<Materiel> lesMaterielsSousContrat = new ArrayList<Materiel>();
        if (leContrat.estValide()) {
            lesMaterielsSousContrat = leContrat.getLesMaterielsAssures();
        }
        return lesMaterielsSousContrat;
    }

    // Retourne vrai si le client est assuré, faux sinon
    public boolean estAssure() {
        if (leContrat.estValide()) {
            return true;
        }else {
            return false;
        }
    }

    // Getters & Setters

    
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