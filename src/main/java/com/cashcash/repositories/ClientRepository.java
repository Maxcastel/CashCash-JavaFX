package com.cashcash.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cashcash.BDD;
import com.cashcash.entities.Client;
import com.cashcash.entities.ContratMaintenance;
import com.cashcash.entities.Materiel;

public class ClientRepository {

    MaterielRepository materielRepository = new MaterielRepository();

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();

        try{
            Connection conn = new BDD().getConnection();
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM client");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setNumClient(rs.getInt("client_num"));
                clients.add(client);
            }
        }
        catch (Exception e) {
			e.printStackTrace();	
		}

        return clients;
    }

    public Client getClientByNum(int numClient) {
        Client client = null;

        try{
            Connection conn = new BDD().getConnection();

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM client WHERE client_num = ?");
            ps.setInt(1, numClient);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ArrayList<Materiel> materielsOfclient = materielRepository.getMaterielsOfClient(numClient);

                PreparedStatement ps2 = conn.prepareStatement("SELECT contrat_num, contrat_date_signature, contrat_date_echeance FROM contratmaintenance WHERE client_num = ?");
                ps2.setInt(1, numClient);
                ResultSet rs2 = ps2.executeQuery();

                ContratMaintenance contratMaintenance = null;
                while (rs2.next()) {
                    contratMaintenance = new ContratMaintenance(
                            rs2.getInt("contrat_num"), 
                            rs2.getDate("contrat_date_signature"), 
                            rs2.getDate("contrat_date_echeance")
                        );
                }

                client = new Client(
                    rs.getInt("client_num"),
                    rs.getString("client_raison_sociale"),
                    rs.getString("client_num_SIREN"),
                    rs.getString("client_code_APE"),
                    rs.getString("client_adresse"),
                    rs.getString("client_téléphone"),
                    rs.getString("client_email"),
                    rs.getInt("duree_deplacement"),
                    rs.getInt("nbkm_agence_client"),
                    materielsOfclient,
                    contratMaintenance
                );
            }
        }
        catch (Exception e) {
			e.printStackTrace();	
		}

        return client;
    }
    
}
