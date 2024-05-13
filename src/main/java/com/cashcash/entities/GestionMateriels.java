package com.cashcash.entities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.cashcash.BDD;
import com.cashcash.Fichier;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Gère les opérations liées aux matériels et aux contrats de maintenance.
 */
public class GestionMateriels {

    private BDD bdd;

    /**
     * Initialise un objet GestionMateriels avec une connexion à la base de données.
     *
     * @param bdd La connexion à la base de données.
     */
    public GestionMateriels(BDD bdd) {
        this.bdd = bdd;
    }

    /**
     * Récupère les matériels associés à un client à partir de la base de données.
     *
     * @param idClient L'identifiant du client.
     * @return Une liste d'objets Materiel associés au client.
     */
    public ArrayList<Materiel> getMateriels(int idClient) {
        Connection conn = bdd.getConnection();
        ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
        try {
            PreparedStatement ps1 = conn.prepareStatement(
                    "SELECT m.contrat_num, m.materiel_num_serie, m.materiel_date_vente, m.materiel_date_installation, m.materiel_prix_vente, m.materiel_emplacement, mt.materiel_type_reference, mt.materiel_type_libelle, f.famille_id, f.famille_libelle FROM materiel m, materiel_type mt, famille f WHERE mt.materiel_type_id = m.materiel_type_id AND mt.famille_id = f.famille_id AND m.client_num = ?");
            ps1.setInt(1, idClient);
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {
                TypeMateriel tm = new TypeMateriel(rs1.getString("materiel_type_reference"), rs1.getString("materiel_type_libelle"), new Famille(rs1.getInt("famille_id"), rs1.getString("famille_libelle")));
                Materiel m = new Materiel(rs1.getInt("materiel_num_serie"), rs1.getDate("materiel_date_vente"), rs1.getDate("materiel_date_installation"),
                        rs1.getDouble("materiel_prix_vente"), rs1.getString("materiel_emplacement"), tm, rs1.getInt("contrat_num"));
                lesMateriels.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lesMateriels;
    }

    /**
     * Associe un matériel à un contrat de maintenance.
     *
     * @param materiel Le matériel à associer au contrat.
     * @param contrat  Le contrat de maintenance auquel associer le matériel.
     */
    public void setMaterielToContrat(Materiel materiel, ContratMaintenance contrat) {

        try {
            Connection conn = bdd.getConnection();

            // On met à jour le numéro du contrat du matériel
            PreparedStatement ps = conn.prepareStatement("UPDATE materiel SET contrat_num = ? WHERE materiel_num_serie = ?");
            ps.setInt(1, contrat.getNumContrat());
            ps.setInt(2, materiel.getNumSerie());
            ps.executeUpdate();

            // On affecte le numéro de contrat au matériel
            materiel.setContratNum(contrat.getNumContrat());

            // On ajoute le matériel au contrat
            contrat.ajouteMateriel(materiel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Crée un nouveau contrat de maintenance pour un client s'il n'en a pas déjà un.
     *
     * @param client Le client pour lequel créer le contrat de maintenance.
     * @return Le nouveau contrat de maintenance créé, ou le contrat de maintenance déjà existant.
     * @throws SQLException En cas d'erreur lors de l'interaction avec la base de données.
     */
    public ContratMaintenance createContratMaintenance(Client client) throws SQLException {
        ContratMaintenance unContrat = null;
        if (!client.aUnContratMaintenance()) {
            Connection conn = bdd.getConnection();

            Date signatureDate = new Date();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(signatureDate);
            calendar.add(Calendar.YEAR, 1);
            Date dueDate = calendar.getTime();

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO contratmaintenance(contrat_date_signature, contrat_date_echeance, client_num) VALUES(?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new java.sql.Date(signatureDate.getTime()));
            ps.setDate(2, new java.sql.Date(dueDate.getTime()));
            ps.setInt(3, client.getNumClient());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int contratId = rs.getInt(1);

            unContrat = new ContratMaintenance(contratId, new java.sql.Date(signatureDate.getTime()),
                    new java.sql.Date(dueDate.getTime()));
            client.setLeContrat(unContrat);
        } else {
            unContrat = client.getLeContrat();
        }

        return unContrat;
    }

    /**
     * Récupère les informations d'un client à partir de la base de données.
     *
     * @param id L'identifiant du client.
     * @return Le client correspondant à l'identifiant spécifié.
     */
    public Client getClient(int numClient) {
        Connection conn = bdd.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM client WHERE client_num = ?");
            ps.setInt(1, numClient);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int clientNum = rs.getInt("client_num");

                // On parcours les matériels du client que l'on parcours
                ArrayList<Materiel> lesMateriels = getMateriels(clientNum);

                // Contrat de maintenance
                PreparedStatement ps2 = conn.prepareStatement(
                        "SELECT contrat_num, contrat_date_signature, contrat_date_echeance FROM contratmaintenance WHERE client_num = ?");
                ps2.setInt(1, clientNum);
                ResultSet rs2 = ps2.executeQuery();

                ContratMaintenance cm = null;
                while (rs2.next()) {
                    cm = new ContratMaintenance(rs2.getInt("contrat_num"), rs2.getDate("contrat_date_signature"), rs2.getDate("contrat_date_echeance"));
                }

                Client unClient = new Client(
                    rs.getInt("client_num"),
                    rs.getString("client_raison_sociale"),
                    rs.getString("client_num_SIREN"),
                    rs.getString("client_code_APE"),
                    rs.getString("client_adresse"),
                    rs.getString("client_téléphone"),
                    rs.getString("client_email"),
                    rs.getInt("duree_deplacement"),
                    rs.getInt("nbkm_agence_client"),
                    lesMateriels,
                    cm
                );

                return unClient;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //JE L'AI FAIT DANS CLIENT REPOSITORY
    /**
     * Génère une représentation XML des matériels d'un client et enregistre le fichier XML.
     *
     * @param unClient Le client pour lequel générer la représentation XML des matériels.
     * @return La représentation XML des matériels du client.
     * @throws IOException En cas d'erreur lors de la lecture ou de l'écriture du fichier XML.
     */
    public String xmlClient(Client unClient) throws IOException {

        String xmlMatTotal = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n" + "<listeMateriel>" + "\n"
                + "<materiels idClient=\"" + unClient.getNumClient() + "\">\n";

        // Sous contrat
        xmlMatTotal += "\t<souContrat>\n";
        ContratMaintenance ct = unClient.getLeContrat();
        if (ct != null) {
            int jourRestant = ct.getJoursRestants();
            for (Materiel materiel : bdd.getMaterielForClient(unClient.getNumClient(), true)) {
                xmlMatTotal += materiel.xmlMateriel(jourRestant) + "\n";
            }
        }

        xmlMatTotal += "\t</sousContrat>\n";

        // Hors contrat
        xmlMatTotal += "\t<horsContrat>\n";
        for (Materiel materiel : bdd.getMaterielForClient(unClient.getNumClient(), false)) {
            xmlMatTotal += materiel.xmlMateriel() + "\n";
        }
        xmlMatTotal += "\t</horsContrat>\n";

        xmlMatTotal += "</listeMateriel>";

        Fichier fichierDesMateriels = new Fichier();
        fichierDesMateriels.ouvrir("materielsClient" + unClient.getNumClient() + ".xml", "w");
        fichierDesMateriels.ecrire(xmlMatTotal);
        fichierDesMateriels.fermer();

        return xmlMatTotal;
    }

     /**
     * Génère une représentation PDF et enregistre le fichier PDF.
     *
     * @param client Le client pour lequel générer la représentation PDF.
     * @return La représentation PDF du message de relance du client.
     */
    public void pdfClient(Client client) {
        String space = "\n\n\n\n\n\n\n";
        String sp = "\n\n";
        String text = "\tNous vous informons que votre contrat avec CashCash arrivera à expiration le " + client.getLeContrat().getDateEcheance() + ". \nVeuillez envisager de renouveler votre contrat pour continuer à profiter de nos services.\nPour toute question ou assistance, n'hésitez pas à nous contacter.\n\nCordialement,";
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("relancecli" + client.getNumClient() + ".pdf"));
            document.open();
            document.add(new Paragraph(client.getRaisonSociale() + "\nID => " + client.getNumClient() + "\nMail => " + client.getEmail()));
            document.add(new Paragraph(space + "Sujet: Relance contrat de maintenance" + sp + text));
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
