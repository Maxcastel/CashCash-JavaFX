package com.cashcash.entities;
import com.cashcash.repositories.ClientRepository;

public class GestionMateriels {
    // donnees : PersistanceSQL 
    //private PersistanceSQL donnees;
    // Attribut qui permet de rendre les objets métiers accessibles.

    // Constructeur

    ClientRepository clientRepository = new ClientRepository();
    
    /*public GestionMateriels(PersistanceSQL lesDonnes) {
        //this.donnees = new PersistanceSQL("185.207.226.14", 3306, "CashCash");
        this.donnees = lesDonnes;
    }*/

    //getter
    /*public PersistanceSQL getDonnees() {
        return this.donnees;
    }*/

    //setter
    /*public void setDonnees(PersistanceSQL donnees) {
        this.donnees = donnees;
    }*/

    public Client getClient(int numClient) {
        // if (this.donnees != null) {
        //     // Retourne l'objet Distributeur qui possède l'identifiant idDistributeur passé en paramètre
            
            
        // }
        // return null;
        return clientRepository.getClientByNum(numClient);
    }
    /*fonction XmlClient (unClient : Client) : chaîne
    Retourne une chaîne de caractères qui représente le document XML de la liste des matériels
    // du client passé en paramètre comme le montre l'exemple de l'annexe. */

    /*public String XmlClient(Client unClient) {
        return this.donnees.XmlClient(unClient);
    }*/

    /*fonction statique XmlClientValide (xml : chaine) : booleen
    */

/*public boolean XmlClientValide(String xml) {
    if (this.donnees != null) {
        // Vérifier si le fichier XML respecte la DTD
        return this.donnees.XmlClientValide(xml);
    }
    return false; // Retourner false en cas de données null
}*/

}