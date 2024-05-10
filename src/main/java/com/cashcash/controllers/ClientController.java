package com.cashcash.controllers;

import com.cashcash.BDD;
import com.cashcash.entities.Client;
import com.cashcash.entities.GestionMateriels;
import com.cashcash.repositories.ClientRepository;

import java.io.IOException;
import java.net.URL;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class ClientController implements Initializable {

    ClientRepository clientRepository = new ClientRepository();
    
    @FXML
    private TableView<Client> tv_listeClients;

    @FXML
    private TableColumn<Client, Integer> tc_id;


    @FXML
    private TableColumn<Client, String> tc_raisonSociale;

    @FXML
    private TableColumn<Client, Integer> tc_telephone;

    @FXML
    private TableColumn<Client, String> tc_email;

    @FXML
    private Label label_title;

    @FXML
    private Button btn_relanceClients;

    ObservableList<Client> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        tc_id.setCellValueFactory(new PropertyValueFactory<Client, Integer>("numClient"));
		tc_raisonSociale.setCellValueFactory(new PropertyValueFactory<Client, String>("raisonSociale"));
        tc_telephone.setCellValueFactory(new PropertyValueFactory<Client, Integer>("telClient"));
        tc_email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        
		list = clientRepository.getAllClients();
		tv_listeClients.setItems(list);

        addXMLButtonToTable();
        addContratButtonToTable();
	}

      /**
       * Cette fonction est un gestionnaire d'événements pour l'événement "OnClickRelanceClients".
       * Elle récupère une liste de numéros de clients provenant de la base de données qui ont un contrat de maintenance
       * qui expire dans les 3 mois à venir, et génère un rapport client PDF pour chaque client.
       *
       * @param  event  l'événement ActionEvent déclenché par l'action de l'utilisateur
       */
    @FXML
    void OnClickRelanceClients(ActionEvent event) {
        BDD conn = new BDD();
		GestionMateriels gm = new GestionMateriels(conn);
		
		try {
			PreparedStatement ps = conn.getConnection().prepareStatement("SELECT c.client_num FROM client c, contratmaintenance cm WHERE c.client_num=cm.client_num AND (DATEDIFF(cm.contrat_date_echeance, NOW())/30) <= 3");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
                Client client = gm.getClient(rs.getInt("client_num"));
                gm.pdfClient(client);
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
    }

    /**
     * Ajoute un bouton "Générer XML" à chaque ligne de la vue de tableau. Lorsque le bouton est cliqué, il récupère le client associé à la ligne, génère un fichier XML pour le client et affiche une alerte avec un message de succès.
     *
     * @throws Exception si il y a une erreur lors de la génération du fichier XML
     */

    private void addXMLButtonToTable() {
        TableColumn<Client, Void> colBtn = new TableColumn<Client, Void>("Genener XML");
    
        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
            @Override
            public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
                final TableCell<Client, Void> cell = new TableCell<Client, Void>() {

                    private final Button btn = new Button("XML");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Client client = getTableView().getItems().get(getIndex());

                            try {
                                clientRepository.xmlClient(client);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                                                   
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setHeaderText(null);
                            a.setContentText("Fichier XML pour le client " + client.getRaisonSociale() + " créé.");
                            a.show();
                        });
                    }

                    /**
                     * Met à jour l'élément affiché dans la cellule de la table.
                     *
                     * @param  item   l'élément à afficher (ignoré dans cette implémentation)
                     * @param  empty  indique si la cellule est vide ou non
                     */

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        tv_listeClients.getColumns().add(colBtn);
    }

    /**
    * Ajoute un bouton "Contrat" à chaque ligne de la table pour afficher les matériels non en contrat pour le client correspondant.
    *
    * @param  param  l'objet TableColumn représentant la colonne à laquelle le bouton sera ajouté
    * @return        l'objet TableCell représentant la cellule du bouton
    */
    
    private void addContratButtonToTable() {
        TableColumn<Client, Void> colBtn = new TableColumn("Ajouter aux Contrat");

        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
            @Override
            public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
                final TableCell<Client, Void> cell = new TableCell<Client, Void>() {

                    private final Button btn = new Button("Contrat");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Client client = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cashcash/contrat_materiels.fxml"));
                                Parent root = loader.load();                                
                                
                                MaterielsController materielController = loader.getController();                           
                                materielController.setSelectedClient(client);

                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.setTitle("Matériels - " + client.getRaisonSociale());
                                stage.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        tv_listeClients.getColumns().add(colBtn);
    }


}