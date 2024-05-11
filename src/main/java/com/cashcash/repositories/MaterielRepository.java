package com.cashcash.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cashcash.BDD;
import com.cashcash.entities.Famille;
import com.cashcash.entities.Materiel;
import com.cashcash.entities.TypeMateriel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Gère l'accès aux données des matériels.
 */
public class MaterielRepository {

    /**
     * Récupère tous les matériels d'un client à partir de son numéro.
     *
     * @param numClient Le numéro du client dont les matériels doivent être récupérés
     * @return Une liste de tous les matériels du client spécifié
     */
    public ArrayList<Materiel> getAllMaterielsOfClient(int numClient) {
        ArrayList<Materiel> materielsOfclient = new ArrayList<Materiel>(); 

        try {
            BDD bdd = new BDD();

            PreparedStatement ps = bdd.getConnection().prepareStatement("SELECT m.materiel_num_serie, m.materiel_date_vente, m.materiel_date_installation, m.materiel_prix_vente, m.materiel_emplacement, m.client_num, m.contrat_num, m.materiel_type_id, mt.materiel_type_id, mt.materiel_type_reference, mt.materiel_type_libelle, mt.famille_id, f.famille_id, f.famille_libelle FROM materiel m, materiel_type mt, famille f WHERE mt.materiel_type_id = m.materiel_type_id AND mt.famille_id = f.famille_id AND m.client_num = ?");
            ps.setInt(1, numClient);
            ResultSet rs1 = ps.executeQuery();

            while (rs1.next()) {
                TypeMateriel typeMateriel = new TypeMateriel(
                    rs1.getString("materiel_type_reference"), 
                    rs1.getString("materiel_type_libelle"),
                    new Famille(rs1.getInt("famille_id"), rs1.getString("famille_libelle"))
                );
                Materiel materiel = new Materiel(
                    rs1.getInt("materiel_num_serie"), 
                    rs1.getDate("materiel_date_vente"), 
                    rs1.getDate("materiel_date_installation"),
                    rs1.getDouble("materiel_prix_vente"), 
                    rs1.getString("materiel_emplacement"), 
                    typeMateriel, 
                    rs1.getInt("contrat_num")
                );
                materielsOfclient.add(materiel);
            }
        } catch (Exception e) {
            e.printStackTrace();    
        }

        return materielsOfclient;
    }

    /**
     * Récupère les matériels d'un client en fonction de leur contrat.
     *
     * @param numClient Le numéro du client dont les matériels doivent être récupérés
     * @param contrat   true si les matériels doivent être liés à un contrat, false sinon
     * @return Une liste des matériels du client spécifié en fonction de leur contrat
     */
    public ArrayList<Materiel> getMaterielsOfClientByContrat(int numClient, boolean contrat) {
        ArrayList<Materiel> materielsOfclient = new ArrayList<Materiel>();
    
        try {
            for (Materiel materiel : getAllMaterielsOfClient(numClient)) {
                if (contrat) {
                    if (materiel.getContratNum() != 0) {
                        materielsOfclient.add(materiel);
                    }
                } else {
                    if (materiel.getContratNum() == 0) {
                        materielsOfclient.add(materiel);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();    
        }

        return materielsOfclient;
    }
}
