package fr.univamu.iut.panier;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

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

    /**
     * Ferme une connection
     */
    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Méthode retournant les informations sur un panier
     * @return un Panier avec les attributs donnés dans la base de données
     */
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
                String produits = result.getString("produits");
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

    /**
     * Méthode retournant les informations sur les paniers
     * @return une ArrayList contenant les informations sur les paniers
     */
    @Override
    public ArrayList<Panier> getAllPaniers() {
        ArrayList<Panier> listPaniers ;

        String query = "SELECT * FROM panier";

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
                String produits = result.getString("produits");
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

    /**
     * Méthode permettant de créer un panier
     * @param id Id du panier
     * @param quantite Nouvelle quantité disponible du panier
     * @param produits Nouveaux id des produits composant le panier associés à leur quantité dans le panier et à leur unité
     * @param prix Nouveau prix du panier
     * @param dateMaj Date de la dernière mise à jour du panier
     * @return true si le panier a été créé, false sinon
     */
    @Override
    public boolean updatePanier(String id, int quantite, String produits, int prix, Date dateMaj) {
        String query = "UPDATE panier SET quantite=?, produits=?, prix=?, dateMaj=?  where id=?";
        int nbRowModified;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, quantite);
            ps.setString(2, produits);
            ps.setInt(3, prix);
            ps.setDate(4, dateMaj);
            ps.setString(5, id);

            // exécution de la requête
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

    /**
     * Méthode permettant de créer un panier
     * @param id Id du panier
     * @param quantite Nouvelle quantité disponible du panier
     * @param produits Nouveaux id des produits composant le panier associés à leur quantité dans le panier et à leur unité
     * @param prix Nouveau prix du panier
     * @param dateMaj Date de la dernière mise à jour du panier
     * @return true si le panier a été créé, false sinon
     */
    @Override
    public boolean createPanier(String id, int quantite, String produits, int prix, Date dateMaj) {
        String query = "INSERT INTO `panier`(`id`, `quantite`, `produits`, `prix`, `dateMaj`) VALUES (?,?,?,?,?)";
        int nbRowCreated;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, quantite);
            ps.setString(2, produits);
            ps.setInt(3, prix);
            ps.setDate(4, dateMaj);
            ps.setString(5, id);

            // exécution de la requête
            nbRowCreated = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowCreated != 0 );
    }

    /**
     * Méthode permettant de créer un panier
     * @param id Id du panier
     * @return true si le panier existe et la suppression a été faite, false sinon
     */
    @Override
    public boolean deletePanier(String id) {
        String query = "DELETE FROM panier WHERE id=?";
        int nbRowDeleted;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)){
            ps.setString(1,id);

            nbRowDeleted = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowDeleted != 0 );
    }
}