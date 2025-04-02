package fr.univamu.iut.apiuserproduits;

import fr.univamu.iut.apiuserproduits.Service.AuthenticationClientInterface;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthenticationClient implements AuthenticationClientInterface {
    @Override
    public boolean checkPermission(String userId, String permission) {
        return false;
    }
}