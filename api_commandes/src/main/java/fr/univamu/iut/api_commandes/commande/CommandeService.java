package fr.univamu.iut.api_commandes.commande;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

@ApplicationScoped
public class CommandeService {

    protected CommandeRepositoryInterface commandeRepo;

    @Inject
    public CommandeService(CommandeRepositoryInterface commandeRepo) {
        this.commandeRepo = commandeRepo;
    }

    public CommandeService() {}

    public String getAllCommandeJSON() {
        ArrayList<Commande> allCommande = commandeRepo.getAllCommande();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(allCommande);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

    public String getCommandeJSON(int id_commande) {
        Commande myCommande = commandeRepo.getCommande(id_commande);
        if (myCommande == null) {
            return null;
        }
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(myCommande);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

    public boolean updateCommande( Commande commande) {
        return commandeRepo.updateCommande(commande.getId_commande(), commande.getId_user(), commande.getPrix(), commande.getDate_retrait(), commande.getStatus(), commande.getRelais());
    }

    public boolean deleteCommande(Commande commande) {
        return commandeRepo.deleteCommande(commande);
    }

    public boolean addCommande(Commande commande) {
        return commandeRepo.addCommande(commande);
    }
}