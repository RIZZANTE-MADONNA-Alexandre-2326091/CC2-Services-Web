package fr.univamu.iut.apiuserproduits;

import java.sql.*;
import java.util.ArrayList;

public class UserRepositoryMariadb implements UserRepositoryInterface, AutoCloseable {

    private Connection dbConnection;
    private AuthenticationClientInterface authClient;

    public UserRepositoryMariadb(String connectionString, String username, String password)
            throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        this.dbConnection = DriverManager.getConnection(connectionString, username, password);
    }

    public void setAuthClient(AuthenticationClientInterface authClient) {
        this.authClient = authClient;
    }

    @Override
    public void close() {
        try {
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture de la connexion: " + e.getMessage());
        }
    }

    @Override
    public User getUserById(String id) {
        String query = "SELECT * FROM User WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("gestionnaire")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de l'utilisateur", e);
        }
        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM User";

        try (Statement stmt = dbConnection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("gestionnaire")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des utilisateurs", e);
        }
        return users;
    }

    @Override
    public User getUserByName(String name) {
        String query = "SELECT * FROM User WHERE name = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("gestionnaire")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche par nom", e);
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        String query = "INSERT INTO User (id, name, email, password, gestionnaire) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getMail());
            stmt.setString(4, user.getPwd());
            stmt.setBoolean(5, user.gestionnaire());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'utilisateur", e);
        }
    }

    @Override
    public boolean removeUser(String id) {
        String query = "DELETE FROM User WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'utilisateur", e);
        }
    }

    @Override
    public boolean updateUser(String id, String name, String email, String password, boolean isAdmin) {
        String query = "UPDATE User SET name = ?, email = ?, password = ?, gestionnaire = ? WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setBoolean(4, isAdmin);
            stmt.setString(5, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'utilisateur", e);
        }
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        String query = "SELECT password FROM User WHERE name = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("password").equals(password);
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la vérification des identifiants", e);
        }
    }
}