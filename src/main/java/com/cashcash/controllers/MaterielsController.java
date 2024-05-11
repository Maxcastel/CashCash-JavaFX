package com.cashcash.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import com.cashcash.entities.Client;
import com.cashcash.entities.ContratMaintenance;
import com.cashcash.entities.GestionMateriels;
import com.cashcash.BDD;
import com.cashcash.entities.Materiel;
import com.cashcash.entities.TypeMateriel;
import com.cashcash.repositories.MaterielRepository;

import javafx.fxml.Initializable;
import java.net.URL;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import com.cashcash.App;

/**
 * Contrôleur pour la gestion des matériels.
 */
public class MaterielsController implements Initializable {

    /** Référence vers le repository des matériels */
    private MaterielRepository materielRepository = new MaterielRepository();

    /** Tableau affichant les matériels */
    @FXML
    private TableView<Materiel> tv_contrat_materiels;

    /** Colonne du numéro de série */
    @FXML
    private TableColumn<Materiel, Integer> tc_num;

    /** Colonne du label du matériel */
    @FXML
    private TableColumn<Materiel, String> tc_label;

    /** Colonne de l'emplacement du matériel */
    @FXML
    private TableColumn<Materiel, String> tc_emplacement;

    /** Colonne de la date d'installation */
    @FXML
    private TableColumn<Materiel, LocalDate> tc_installationdate;

    /** Label du titre */
    @FXML
    private Label label_titre;

    /** Bouton de création */
    @FXML
    private Button btn_create;

    /** Client sélectionné */
    @FXML
    private Client selectedClient;
    
    /**
     * Définit le client sélectionné.
     * 
     * @param selectedClient Le client sélectionné
     */
    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;

        loadMaterielData();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){

        // Initialisation des colonnes de la table
        tc_num.setCellValueFactory(new PropertyValueFactory<Materiel, Integer>("numSerie"));
        tc_label.setCellValueFactory(cellData -> {
            Materiel materiel = cellData.getValue();
            if (materiel.getLeType() != null) {
                return new SimpleStringProperty(materiel.getLeType().getLibelleTypeMateriel());
            } else {
                return new SimpleStringProperty("null"); 
            }
        });
        tc_emplacement.setCellValueFactory(new PropertyValueFactory<Materiel, String>("emplacement"));
        tc_installationdate.setCellValueFactory(new PropertyValueFactory<Materiel, LocalDate>("dateInstallation"));

        // Permettre la sélection multiple dans la table
        tv_contrat_materiels.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


    @FXML
    void onAjouteButtonClick(MouseEvent event) throws IOException, SQLException {
        // Récupération des matériels sélectionnés dans la table
        ObservableList<Materiel> selectedMateriels = tv_contrat_materiels.getSelectionModel().getSelectedItems();

        if (!selectedMateriels.isEmpty()) {

            // Gestionnaire des matériels
            GestionMateriels gm = new GestionMateriels(new BDD());
            boolean aUnContrat = selectedClient.aUnContratMaintenance();
            // Création ou récupération d'un contrat de maintenance pour le client sélectionné
            ContratMaintenance unContrat = gm.createContratMaintenance(selectedClient);

            // Affectation des matériels au contrat de maintenance
            for (Materiel materiel : selectedMateriels) {
                gm.setMaterielToContrat(materiel, unContrat);
            }
    
            // Affichage d'une alerte avec les détails du contrat et des matériels affectés
            Alert a = new Alert(AlertType.INFORMATION);
            a.setHeaderText(null);

            String textAlert;            
            if (aUnContrat) {
                textAlert = "Matériel(s) ajouté(s) au contrat de maintenance n°" + unContrat.getNumContrat() + ".\n";
            } else {
                textAlert = "Contrat de maintenance n°" + unContrat.getNumContrat() + " créé avec succès.\n";
            }

            textAlert += "- Matériels affectés :\n";             
            for (Materiel materiel : selectedMateriels) {
                textAlert += "  - Matériel n°" + materiel.getNumSerie() + "\n";
            }

            a.setContentText(textAlert);
            a.show();

            // Actualisation des matériels affichés
            loadMaterielData();
        } else {
            // Alerte si aucun matériel sélectionné
            Alert a = new Alert(AlertType.WARNING);
            a.setHeaderText(null);
            a.setContentText("Aucun matériel sélectionné !");
            a.show();
        }
       
     }
    

    // Chargement des données des matériels
    private void loadMaterielData() {
        if (selectedClient != null) {
            ObservableList<Materiel> materielList = new BDD().getMaterielForClient(selectedClient.getNumClient(), false);
            tv_contrat_materiels.setItems(materielList);

            // Mise à jour du titre et du texte du bouton en fonction de la présence d'un contrat
            label_titre.setText("Matériels du client " + selectedClient.getRaisonSociale());
            btn_create.setText(selectedClient.aUnContratMaintenance() ? "Ajouter au contrat" : "Créer un contrat");
        } else {
            System.out.println("selectedClient is null");
        }
    }
}
