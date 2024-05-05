package com.cashcash.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cashcash.entities.PersistanceSQL;
import com.cashcash.entities.Client;

public class ClientRepository {

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();

        try{
            PersistanceSQL bdd = new PersistanceSQL("185.207.226.14", 3306, "CashCash");
            
            PreparedStatement ps = bdd.prepareStatement("SELECT * FROM client");
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
    
}
