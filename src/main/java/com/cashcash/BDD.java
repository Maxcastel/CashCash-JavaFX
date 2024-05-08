package com.cashcash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cashcash.entities.Client;
import com.cashcash.entities.ContratMaintenance;

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

    public void closeConnection() {
        try {
            if (connexion != null) {
                connexion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BDD() {
        this.connexion = this.getConnection();
    }

    public void close() {
        this.closeConnection();
    }

    public ResultSet ChargerDepuisBase(String id, String nomClasse) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        switch (nomClasse) {
            case "Client":
                try {
                    ps = this.connexion.prepareStatement("SELECT * FROM client WHERE client_num = " + id);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        System.out.println("Client charge depuis la BDD");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case "Agence":

                try {
                    ps = this.connexion.prepareStatement("SELECT * FROM agence WHERE agence_num = " + id);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        System.out.println("Agence " + id + " charge depuis la BDD");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case "ContratMaintenance":
                try {
                    ps = this.connexion.prepareStatement("SELECT * FROM contratmaintenance WHERE contrat_num = " + id);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        System.out.println("ContratMaintenance " + id + " charge depuis la BDD");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case "Intervention":

                try {
                    ps = this.connexion.prepareStatement("SELECT * FROM intervention WHERE intervention_id = " + id);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        System.out.println("Intervention " + id + " charge depuis la BDD");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        
            case "Materiel":

                try {
                    ps = this.connexion.prepareStatement("SELECT * FROM materiel WHERE materiel_num_serie = " + id);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        System.out.println("Materiel "+ id + " charge depuis la BDD");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

        }
        return rs;
    }

            



}
