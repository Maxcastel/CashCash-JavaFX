package com.cashcash.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cashcash.BDD;
import com.cashcash.Fichier;
import com.cashcash.entities.Client;
import com.cashcash.entities.ContratMaintenance;
import com.cashcash.entities.Materiel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientRepository {

    MaterielRepository materielRepository = new MaterielRepository();

    public ObservableList<Client> getAllClients() {
        ObservableList<Client> clients = FXCollections.observableArrayList(); 

        try{
            Connection conn = new BDD().getConnection();
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM client");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ArrayList<Materiel> materielsOfclient = materielRepository.getAllMaterielsOfClient(rs.getInt("client_num"));

                PreparedStatement ps2 = conn.prepareStatement("SELECT contrat_num, contrat_date_signature, contrat_date_echeance FROM contratmaintenance WHERE client_num = ?");
                ps2.setInt(1, rs.getInt("client_num"));
                ResultSet rs2 = ps2.executeQuery();

                ContratMaintenance contratMaintenance = null;
                if (rs2.next()) {
                    contratMaintenance = new ContratMaintenance(
                            rs2.getInt("contrat_num"), 
                            rs2.getDate("contrat_date_signature"), 
                            rs2.getDate("contrat_date_echeance")
                        );
                }

                clients.add(
                    new Client(
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
                    )
                );
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
                ArrayList<Materiel> materielsOfclient = materielRepository.getAllMaterielsOfClient(numClient);

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

    public String xmlClient(Client Client) throws IOException {


        String xmlMatTotal = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n" + "<listeMateriel>" + "\n"
                + "<materiels idClient=\"" + Client.getNumClient() + "\">\n";

        // Sous contrat
        xmlMatTotal += "\t<souContrat>\n";
        ContratMaintenance contratMaintenance = Client.getLeContrat();
        if (contratMaintenance != null) {
            int jourRestant = contratMaintenance.getJoursRestants();
            for (Materiel materiel : materielRepository.getMaterielsOfClientByContrat(Client.getNumClient(), true)) {
                xmlMatTotal += materiel.xmlMateriel(jourRestant) + "\n";
            }
        }

        xmlMatTotal += "\t</sousContrat>\n";

        // Hors contrat
        xmlMatTotal += "\t<horsContrat>\n";
        for (Materiel materiel : materielRepository.getMaterielsOfClientByContrat(Client.getNumClient(), false) ) {
            xmlMatTotal += materiel.xmlMateriel() + "\n";
        }
        xmlMatTotal += "\t</horsContrat>\n";

        xmlMatTotal += "</listeMateriel>";

        Fichier fichierDesMateriels = new Fichier();
        fichierDesMateriels.ouvrir("materielsClient" + Client.getNumClient() + ".xml", "w");
        fichierDesMateriels.ecrire(xmlMatTotal);
        fichierDesMateriels.fermer();

        return xmlMatTotal;
    }
    
}
