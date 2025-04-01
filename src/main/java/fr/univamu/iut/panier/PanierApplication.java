package fr.univamu.iut.panier;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;


@ApplicationPath("/panier")
@ApplicationScoped
public class PanierApplication extends Application {
    @Override
    public Set<Object> getSingletons() {
        Set<Object> set = new HashSet<>();

        // Création de la connection à la base de données et initialisation du service associé
        PanierService service = null ;
        try{
            PanierRepositoryMariadb db = new PanierRepositoryMariadb("jdbc:mariadb://mysql-archi.alwaysdata.net/archi_panier_db", "archi_panier", "1996Arko$782");
            service = new PanierService(db);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

        // Création de la ressource en lui passant paramètre les services à exécuter en fonction
        // des différents endpoints proposés (i.e. requêtes HTTP acceptées)
        set.add(new PanierResource(service));

        return set;
    }
}