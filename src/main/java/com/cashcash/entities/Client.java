package com.cashcash.entities;

import java.util.ArrayList;

/**
 * La classe Client représente un client de l'entreprise.
 */
public class Client {

    private String raisonSociale, siren, codeApe, adresse, telClient, email;
    private int numClient, dureeDeplacement, distanceKm;
    
    private ArrayList<Materiel> lesMateriels;
    private ContratMaintenance leContrat;
    
    /**
     * Constructeur de la classe Client.
     * 
     * @param numClient Le numéro du client.
     * @param raisonSociale La raison sociale du client.
     * @param siren Le numéro SIREN du client.
     * @param codeApe Le code APE du client.
     * @param adresse L'adresse du client.
     * @param telClient Le numéro de téléphone du client.
     * @param email L'email du client.
     * @param dureeDeplacement La durée de déplacement du client.
     * @param distanceKm La distance en kilomètres du client.
     * @param lesMateriels La liste des matériels du client.
     * @param leContrat Le contrat de maintenance du client.
     */
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
        this.lesMateriels = new ArrayList<Materiel>(lesMateriels);
        this.leContrat = leContrat;  
    }

    // Méthodes demandées

    /**
     * Retourne l'ensemble des matériels du client.
     * 
     * @return La liste des matériels du client.
     */
    public ArrayList<Materiel> getMateriels() {
        return lesMateriels;
    }

    /**
     * Retourne la liste des matériels du client sous contrat de maintenance.
     * 
     * @return La liste des matériels sous contrat de maintenance.
     */
    public ArrayList<Materiel> getMaterielsSousContrat() {
        ArrayList<Materiel> lesMaterielsSousContrat = new ArrayList<Materiel>();
        if (leContrat.estValide()) {
            lesMaterielsSousContrat = leContrat.getLesMaterielsAssures();
        }
        return lesMaterielsSousContrat;
    }

    /**
     * Vérifie si le client est assuré.
     * 
     * @return True si le client est assuré, sinon False.
     */
    public boolean estAssure() {
        return leContrat.estValide();
    }

    /**
     * Vérifie si le client a un contrat de maintenance.
     * 
     * @return True si le client a un contrat de maintenance, sinon False.
     */
    public boolean aUnContratMaintenance() {
        return leContrat != null;
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
