package com.cashcash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cashcash.entities.Client;
import com.cashcash.entities.GestionMateriels;
import com.cashcash.entities.Materiel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe pour la gestion de la connexion à la base de données et la récupération des données.
 */
public class BDD {

    /* Informations de connexion pour la base de données hébergée */
    private static final String DB_URL = "jdbc:mysql://185.207.226.14:3306/CashCash";
    private static final String DB_USERNAME = "CashCash";
    private static final String DB_PASSWORD = "DT56gK_*p%-3ol2H";

    /* Informations de connexion pour la base de données en local */
    // private static final String DB_URL = "jdbc:mysql://localhost:3306/cashcash";
    // private static final String DB_USERNAME = "root";
    // private static final String DB_PASSWORD = "";

    private Connection connexion;

    /**
     * Méthode pour établir la connexion à la base de données.
     * 
     * @return La connexion établie
     */
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexion = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connecté à la base de données");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connexion;
    }

    /**
     * Méthode pour récupérer la liste des clients depuis la base de données.
     * 
     * @return Une liste observable des clients
     */
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Méthode pour récupérer la liste des matériels pour un client donné.
     * 
     * @param id      L'identifiant du client
     * @param contrat Indicateur si on souhaite les matériels sous contrat ou non
     * @return Une liste observable des matériels du client
     */
    public static ObservableList<Materiel> getMaterielForClient(int id, boolean contrat) {
        BDD conn = new BDD();
        GestionMateriels gm = new GestionMateriels(conn);
        ObservableList<Materiel> list = FXCollections.observableArrayList();

        try {
            for (Materiel unMateriel : gm.getMateriels(id)) {
                if (contrat && unMateriel.getContratNum() != 0) {
                    list.add(unMateriel);
                } else if (!contrat && unMateriel.getContratNum() == 0) {
                    list.add(unMateriel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
