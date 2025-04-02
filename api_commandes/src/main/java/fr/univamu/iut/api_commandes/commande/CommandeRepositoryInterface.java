package fr.univamu.iut.api_commandes.commande;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface CommandeRepositoryInterface {

    public void close();


    public Commande getCommande( int id );


    public List<Commande> getAllCommande() throws SQLException;


    public boolean updateCommande(int id_commande, int id_user, int prix, Date date_retrait, String status, String relais);

    public boolean deleteCommande(Commande commande);

    public boolean addCommande(Commande commande);
}
