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

public class MaterielsController implements Initializable {

    MaterielRepository materielRepository = new MaterielRepository();

    @FXML
    private TableView<Materiel> tv_contrat_materiels;

    @FXML
    private TableColumn<Materiel, Integer> tc_num;

    @FXML
    private TableColumn<Materiel, String> tc_label;

    @FXML
    private TableColumn<Materiel, String> tc_emplacement;

    @FXML
    private TableColumn<Materiel, LocalDate> tc_installationdate;

    @FXML
    private Label label_titre;

    @FXML
    private Button btn_create;

    @FXML
    private Client selectedClient;
    

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;

        loadMaterielData();
    }


    // affiche chaque ligne de la table qui n'ont pas encore un contrat, 
    // on utilise un fonction GetAllMaterielsOfClient pour récupérer les informations de la table materiels

    
    @Override
    public void initialize(URL location, ResourceBundle resources){

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

        tv_contrat_materiels.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    void onAjouteButtonClick(MouseEvent event) throws IOException, SQLException {
        ObservableList<Materiel> selectedMateriels = tv_contrat_materiels.getSelectionModel().getSelectedItems();

        if (!selectedMateriels.isEmpty()) {

            GestionMateriels gm = new GestionMateriels(new BDD());
            boolean aUnContrat = selectedClient.aUnContratMaintenance();
            ContratMaintenance unContrat = gm.createContratMaintenance(selectedClient);

            for (Materiel materiel : selectedMateriels) {

                // On affecte le matériel au contrat de maintenance
                gm.setMaterielToContrat(materiel, unContrat);
            }
    
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

            // On actualise les matériels
            loadMaterielData();
        } else {
            Alert a = new Alert(AlertType.WARNING);
            a.setHeaderText(null);
            a.setContentText("Aucun matériel sélectionné !");
            a.show();
        }
       
     }
    

    private void loadMaterielData() {
        if (selectedClient != null) {
            ObservableList<Materiel> materielList = new BDD().getMaterielForClient(selectedClient.getNumClient(), false);
            tv_contrat_materiels.setItems(materielList);

            label_titre.setText("Matériels du client " + selectedClient.getRaisonSociale());
            btn_create.setText(selectedClient.aUnContratMaintenance() ? "Ajouter au contrat" : "Créer un contrat");
        } else {
            System.out.println("selectedClient is null");
        }
    }



}
