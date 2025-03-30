package fr.univamu.iut.panier;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;

/**
 * Classe permettant d'accéder aux paniers stockés dans une base de données Mariadb
 */
public class PanierRepositoryMariadb implements PanierRepositoryInterface, Closeable {

    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection ;

    /**
     * Constructeur de la classe
     * @param infoConnection chaîne de caractères avec les informations de connexion
     *                       (p. ex. jdbc:mariadb://mysql-archi.alwaysdata.net/archi_library_db
     * @param user chaîne de caractères contenant l'identifiant de connexion à la base de données
     * @param pwd chaîne de caractères contenant le mot de passe à utiliser
     */
    public PanierRepositoryMariadb(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Panier getPanier(String id) {

        Panier selectedPanier = null;

        String query = "SELECT * FROM panier WHERE id=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, id);

            // exécution de la requête
            ResultSet result = ps.executeQuery();

            // récupération du premier (et seul) tuple résultat
            // (si la référence du panier est valide)
            if( result.next() )
            {
                int quantite = result.getInt("quantite");
                Map<String, Map<Integer, String>> produits = result.getObject("produits", Map.class);
                int prix = result.getInt("prix");
                Date dateMaj = result.getDate("dateMaj");


                // création et initialisation de l'objet panier
                selectedPanier = new Panier(id, quantite, produits, prix, dateMaj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedPanier;
    }

    @Override
    public ArrayList<Panier> getAllPaniers() {
        ArrayList<Panier> listPaniers ;

        String query = "SELECT * FROM Panier";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listPaniers = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                String id = result.getString("id");
                int quantite = result.getInt("quantite");
                Map<String, Map<Integer, String>> produits = result.getObject("produits", Map.class);
                int prix = result.getInt("prix");
                Date dateMaj = result.getDate("dateMaj");

                // création du panier courant
                Panier currentPanier = new Panier(id, quantite, produits, prix, dateMaj);

                listPaniers.add(currentPanier);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listPaniers;
    }

    @Override
    public boolean updatePanier(String id, int quantite, Map<String, Map<Integer, String>> produits, int prix) {
        String query = "UPDATE panier SET quantite=?, produits=?, prix=?, dateMaj=?  where id=?";
        int nbRowModified = 0;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, quantite);
            ps.setString(2, produits.toString());
            ps.setInt(3, prix);
            ps.setDate(4, (java.sql.Date) new Date());
            ps.setString(5, id);

            // exécution de la requête
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }
}