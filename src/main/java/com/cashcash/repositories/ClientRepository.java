package com.cashcash.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cashcash.BDD;
import com.cashcash.entities.Client;

public class ClientRepository {

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

    // public Client getClientByNum(int numClient) {
    //     BDD bdd = new BDD();
    //     try{
    //         
            
    //         PreparedStatement ps = bdd.getConnection().prepareStatement("SELECT * FROM clients WHERE client_num = ?");
    //         ps.setInt(1, numClient);
    //         ResultSet rs = ps.executeQuery();

    //         while (rs.next()) {
    //             Client client = new Client(
        
    //                 rs.getInt("client_num"),
    //                 rs.getString("client_raison_sociale	"),
    //                 rs.getString("client_num_SIREN"),
    //                 rs.getString("client_code_APE"),
    //                 rs.getString("client_adresse"),
    //                 rs.getString("client_téléphone"),
    //                 rs.getString("client_email"),
    //                 // rs.getInt("duree_deplacement"),
    //                 // rs.getInt("nbkm_agence_client")
    //             );

    //             return client;
    //         }
    //     }
    //     catch (Exception e) {
	// 		e.printStackTrace();	
	// 	}
    // }
    
}
