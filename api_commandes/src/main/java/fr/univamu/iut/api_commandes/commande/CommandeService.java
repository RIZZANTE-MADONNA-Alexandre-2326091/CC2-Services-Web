package fr.univamu.iut.api_commandes.commande;

import java.sql.SQLException;
import java.util.List;

public class CommandeService {

    private CommandeRepositoryInterface commandeRepo;

    public CommandeService(CommandeRepositoryInterface commandeRepo) {
        this.commandeRepo = commandeRepo;
    }

    public List<Commande> getAllCommandes() throws SQLException {
        return commandeRepo.getAllCommande();
    }

    public Commande getCommande(int id) {
        return commandeRepo.getCommande(id);
    }

    public boolean updateCommande(int id, Commande commande) {
        return commandeRepo.updateCommande(id, commande.getId_user(), commande.getPrix(), commande.getDate_retrait(), commande.getStatus(), commande.getRelais());
    }
}