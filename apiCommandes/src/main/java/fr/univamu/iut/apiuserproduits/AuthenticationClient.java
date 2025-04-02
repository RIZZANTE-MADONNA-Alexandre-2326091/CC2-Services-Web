package fr.univamu.iut.apiuserproduits;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthenticationClient implements AuthenticationClientInterface {
    @Override
    public boolean checkPermission(String userId, String permission) {
        return false;
    }
}