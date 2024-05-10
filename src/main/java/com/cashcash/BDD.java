package com.cashcash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cashcash.entities.Client;
import com.cashcash.entities.ContratMaintenance;
import com.cashcash.entities.GestionMateriels;
import com.cashcash.entities.Materiel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BDD {

    /* bdd hebergée */
    private static final String DB_URL = "jdbc:mysql://185.207.226.14:3306/CashCash";
    private static final String DB_USERNAME = "CashCash";
    private static final String DB_PASSWORD = "DT56gK_*p%-3ol2H";

    /* en local */
    // private static final String DB_URL = "jdbc:mysql://localhost:3306/cashcash";
    // private static final String DB_USERNAME = "root";
    // private static final String DB_PASSWORD = "";

    private Connection connexion;

    public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connexion = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);		
            System.out.println("Connecté");
        } 
        catch (Exception e) {
			e.printStackTrace();
		}
		return connexion;
	}


	/* pas besoin car j'ai déjà fait 
	la fonction getAllClients 
	dans ClientRepository */

    public static ObservableList<Client> getDataClients() {
		BDD conn = new BDD();
		GestionMateriels gm = new GestionMateriels(conn);
		ObservableList<Client> list = FXCollections.observableArrayList();
		
		try {
			PreparedStatement ps = conn.getConnection().prepareStatement("SELECT * FROM client");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				list.add(gm.getClient(rs.getInt("client_num")));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return list;
	}
	

	/* pas besoin car j'ai déjà fait 
	la fonction getMaterielsOfClient 
	dans MaterielRepository */
	/* il faut juste que dans la fonction
	je verifie si les materiels sont dans
	le contrat */

    public static ObservableList<Materiel> getMaterielForClient(int id, boolean contrat) {
		BDD conn = new BDD();
		GestionMateriels gm = new GestionMateriels(conn);
		ObservableList<Materiel> list = FXCollections.observableArrayList();
		
		try {
			for (Materiel unMateriel : gm.getMateriels(id)) {
				if (contrat && unMateriel.getContratNum() != 0) {
					list.add(unMateriel);
				} else if (!contrat && unMateriel.getContratNum() == 0){
					list.add(unMateriel);
				}
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
