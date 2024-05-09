package com.cashcash.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.cashcash.BDD;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private Button button_submit;

    @FXML
    private PasswordField txtfield_password;

    @FXML
    private TextField txtfield_username;

    @FXML
    void handleButtonAuth(MouseEvent event) throws IOException, SQLException {

        String username = txtfield_username.getText();
        String password = txtfield_password.getText();
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);

        // Vérification si les champs sont remplis
        if (!username.isEmpty() && !password.isEmpty()) {
            Connection conn = new BDD().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT password FROM utilisateur WHERE username = ? AND employe_num_matricule NOT IN (SELECT employe_num_matricule FROM technicien)");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            
            if (rs.next()) {
                String passwordBDD = rs.getString("password");
                
                if (BCrypt.checkpw(password, passwordBDD)) {
                        // Authentification reussie
                        alert.setAlertType(AlertType.INFORMATION);
                        alert.setContentText("Authentification reussie");
                        alert.show();
                        
                        //redirection vers le tableau de bord
                        com.cashcash.App.setRoot("listeClients");

                } else {
                    // Mot de passe incorrect
                    alert.setContentText("Utilisateur ou mot de passe incorrect");
                    alert.show();
                }
            
        } else {
            // Utilisateur inexistant
            alert.setContentText("Utilisateur ou mot de passe incorrect");
            alert.show();
        }
    } else {
        // Champs vides
        alert.setContentText("Veuillez remplir tous les champs");
        alert.show();
    }


    }
}
