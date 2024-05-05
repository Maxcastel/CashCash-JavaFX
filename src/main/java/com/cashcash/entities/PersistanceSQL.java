package com.cashcash.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersistanceSQL {

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    private Connection connection;

    // Constructeur
    public PersistanceSQL(String ipBase , int port , String nomBaseDonnee ) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+ipBase+":"+port+"/"+nomBaseDonnee, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connecté");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Construit un objet PersistanceSql. Cet objet permettra de charger les données depuis une base de données ou de les sauvegarder dans la base.



    public void RangerDansBase (Objet unObjet ){

    }
    // Stocke les données de l'objet dans la base de données.

    // Retourne l’objet de la classe NomClasse dont l’identifiant est "id". Cet objet est chargé depuis la base de données, 
    // ainsi que l’ensemble de ses objets liés (voir l’exemple d’utilisationci-dessous). 
    // Retourne NULL si aucun objet de cette classe ne possède cet identifiant.


    public Object chargerDepuisBase(String id, String nomClasse) throws SQLException {
        Object obj = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            if (nomClasse.equals("Client")) {
                ps = this.connection.prepareStatement("SELECT * FROM client WHERE client_num = ?");
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    // Créer un objet Client et initialiser ses attributs avec les données de la base de données
                    // par exemple :
                    // obj = new Client(rs.getString("client_num"), rs.getString("nom"), ...);
                }
            } else if (nomClasse.equals("Agence")) {
                // Ajoutez la logique pour charger depuis la table Agence si nécessaire
            } else if (nomClasse.equals("ContratMaintenance")) {
                // Ajoutez la logique pour charger depuis la table ContratMaintenance si nécessaire
            } else if (nomClasse.equals("TypeContrat")) {
                // Ajoutez la logique pour charger depuis la table TypeContrat si nécessaire
            } else if (nomClasse.equals("Employe")) {
                // Ajoutez la logique pour charger depuis la table Employe si nécessaire
            } else if (nomClasse.equals("Intervention")) {
                // Ajoutez la logique pour charger depuis la table Intervention si nécessaire
            } else if (nomClasse.equals("Materiel")) {
                // Ajoutez la logique pour charger depuis la table Materiel si nécessaire
            } else if (nomClasse.equals("TypeMateriel")) {
                // Ajoutez la logique pour charger depuis la table TypeMateriel si nécessaire
            } else if (nomClasse.equals("Technicien")) {
                // Ajoutez la logique pour charger depuis la table Technicien si nécessaire
            } else {
                // Si la classe n'est pas gérée, retourner null
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return obj;
    }

    public PreparedStatement prepareStatement(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'prepareStatement'");
    }

}
