package fr.univamu.iut.api_commandes.commande;


import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeRepositoryMariadb implements CommandeRepositoryInterface, Closeable {

    protected Connection dbConnection;

    public CommandeRepositoryMariadb(String infoConnection, String utilisateur, String mdp) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, utilisateur, mdp);
    }



    @Override
    public void close() {
        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Commande getCommande(int id_commande) {
        Commande selectedCommande = null;

        String query = "SELECT * FROM Commande WHERE id_commande=?";

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, id_commande);

            ResultSet result = ps.executeQuery();

            if( result.next() )
            {
                int id_user = result.getInt("id_user");
                int prix = result.getInt("prix");
                Date date_retrait = result.getDate("date_retrait");
                String status = result.getString("status");
                String relais = result.getString("relais");
               

                selectedCommande = new Commande( id_commande, id_user, prix, date_retrait, status, relais);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedCommande;
    }

    @Override
    public ArrayList<Commande> getAllCommande() {
        ArrayList<Commande> listCommande ;

        String query = "SELECT * FROM Commande";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listCommande = new ArrayList<>();

            while ( result.next() )
            {
                int id_commande = result.getInt("id_commande");
                int id_user = result.getInt("id_user");
                int prix = result.getInt("prix");
                Date date_retrait = result.getDate("date_retrait");
                String status = result.getString("status");
                String relais = result.getString("relais");
                Commande currentCommande = new Commande(id_commande, id_user, prix, date_retrait, status, relais);

                listCommande.add(currentCommande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCommande;
    }

    @Override
    public boolean updateCommande(int id_commande, int id_user, int prix, Date date_retrait, String status, String relais) {
        String query = "UPDATE Commande SET id_user=?, prix=?, date_retrait=?, status=?, relais=? WHERE id_commande=?";
        int nbRowModified;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id_user);
            ps.setInt(2, prix);
            ps.setDate(3, new java.sql.Date(date_retrait.getTime()));
            ps.setString(4, status);
            ps.setString(5, relais);
            ps.setInt(6, id_commande);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }

    @Override
    public boolean deleteCommande(Commande commande) {
        String query = "DELETE FROM Commande WHERE id_commande=?";
        int nbRowModified;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, commande.getId_commande());
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }

    @Override
    public boolean addCommande(Commande commande) {
        String query = "INSERT INTO Commande (id_user, date_retrait, prix, status, relais) VALUES (?, ?, ?, ?, ?)";
        int nbRowModified;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, commande.getId_user());
            ps.setDate(2, new java.sql.Date(commande.getDate_retrait().getTime()));
            ps.setInt(3, commande.getPrix());
            ps.setString(4, commande.getStatus());
            ps.setString(5, commande.getRelais());

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }
}
