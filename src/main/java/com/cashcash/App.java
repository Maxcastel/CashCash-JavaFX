package com.cashcash;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe principale de l'application JavaFX.
 */
public class App extends Application {

    private static Scene scene;

    /**
     * Méthode principale qui lance l'application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Chargement du fichier FXML de la fenêtre de connexion
        scene = new Scene(loadFXML("connexion"), 720, 480);
        // Configuration de la fenêtre principale
        stage.setTitle("CashCash");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Permet de changer la racine de la scène avec le contenu FXML spécifié.
     *
     * @param fxml Le nom du fichier FXML à charger
     * @throws IOException Si une erreur de chargement du fichier FXML survient
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Charge le fichier FXML spécifié et retourne son contenu en tant que Parent.
     *
     * @param fxml Le nom du fichier FXML à charger
     * @return Le contenu du fichier FXML sous forme de Parent
     * @throws IOException Si une erreur de chargement du fichier FXML survient
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Méthode principale qui lance l'application.
     */
    public static void main(String[] args) {
        launch();
    }

}
