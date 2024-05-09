package com.cashcash.controllers;

import java.io.IOException;

import com.cashcash.App;
import com.cashcash.entities.Client;
import com.cashcash.repositories.ClientRepository;

import javafx.fxml.FXML;

public class PrimaryController {

    private ClientRepository clientRepository = new ClientRepository();

    @FXML
    private void switchToClients() throws IOException {
        App.setRoot("secondary");

        // for (Client client : clientRepository.getAllClients()) {
        //     System.out.println(client.getNumClient());
        // }
        Client client = clientRepository.getClientByNum(2);
        System.out.println(client.getRaisonSociale());
    }
}
