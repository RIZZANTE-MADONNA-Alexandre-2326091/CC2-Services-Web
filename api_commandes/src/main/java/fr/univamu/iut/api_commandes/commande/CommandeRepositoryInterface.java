package fr.univamu.iut.api_commandes.commande;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CommandeRepositoryInterface {

    public void close();


    public Commande getCommande( int id_commande);


    public ArrayList<Commande> getAllCommande() ;


    public boolean updateCommande(Commande commande, int id_commande, int id_user, int prix, Date date_retrait, String status, String relais);

    public boolean deleteCommande(Commande commande);

    public boolean addCommande(Commande commande);
}
