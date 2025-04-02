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

    @Override
    public Commande getCommande(int id_commande) {
        Commande selectedCommande = null;
        String query = "SELECT * FROM Commande WHERE id_commande=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id_commande);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                selectedCommande = new Commande(
                        result.getInt("id_commande"),
                        result.getInt("id_user"),
                        result.getInt("prix"),
                        result.getDate("date_retrait"),
                        result.getString("status"),
                        result.getString("relais")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedCommande;
    }

    @Override
    public List<Commande> getAllCommande() {
        List<Commande> listCommande = new ArrayList<>();
        String query = "SELECT * FROM Commande";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                Commande currentCommande = new Commande(
                        result.getInt("id_commande"),
                        result.getInt("id_user"),
                        result.getInt("prix"),
                        result.getDate("date_retrait"),
                        result.getString("status"),
                        result.getString("relais")
                );
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
