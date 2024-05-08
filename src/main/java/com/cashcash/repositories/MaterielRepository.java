package com.cashcash.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cashcash.BDD;
import com.cashcash.entities.Client;
import com.cashcash.entities.Materiel;
import com.cashcash.entities.TypeMateriel;

public class MaterielRepository {

    public ArrayList<Materiel> getMaterielsOfClient(int numClient) {
        ArrayList<Materiel> materielsOfclient = new ArrayList<>();

        try{
            BDD bdd = new BDD();

            PreparedStatement ps = bdd.getConnection().prepareStatement("SELECT m.materiel_num_serie, m.materiel_date_vente, m.materiel_date_installation, m.materiel_prix_vente, m.materiel_emplacement, m.client_num, m.contrat_num, m.materiel_type_id, mt.materiel_type_id, mt.materiel_type_reference, mt.materiel_type_libelle FROM materiel m, materiel_type mt WHERE mt.materiel_type_id = m.materiel_type_id AND m.client_num = ?");
            ps.setInt(1, numClient);
            ResultSet rs1 = ps.executeQuery();

            while (rs1.next()) {
                TypeMateriel typeMateriel = new TypeMateriel(
                    rs1.getString("internalRef"), 
                    rs1.getString("label")
                    //new Famille(rs1.getString("code"), rs1.getString("libelle"))
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

                //TypeMateriel tm = new TypeMateriel(rs1.getString("internalRef"), rs1.getString("label"), new Famille(rs1.getString("code"), rs1.getString("libelle")));
            }
        }
        catch (Exception e) {
			e.printStackTrace();	
		}

        return materielsOfclient;
    }
    
}
