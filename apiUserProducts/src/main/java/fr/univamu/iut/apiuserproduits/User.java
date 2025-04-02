package fr.univamu.iut.apiuserproduits;

/**
 * Représente un utilisateur avec ses détails.
 */
public class User {

    protected String id;
    protected String name;
    protected String mail;
    protected String pwd;
    protected boolean gestionnaire;

    /**
     * Constructeur avec paramètres.
     *
     * @param id l'identifiant de l'utilisateur
     * @param name le nom de l'utilisateur
     * @param mail l'adresse mail de l'utilisateur
     * @param pwd le mot de passe de l'utilisateur
     * @param gestionnaire si l'utilisateur a des droits administrateur
     */
    public User(String id, String name, String mail, String pwd, boolean gestionnaire) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.pwd = pwd;
        this.gestionnaire = User.this.gestionnaire;
    }

    /**
     * Constructeur par défaut.
     */
    public User() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean gestionnaire() {
        return gestionnaire;
    }

    public void setAdmin(boolean admin) {
        gestionnaire = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", pwd='[PROTECTED]'" +  // Masqué pour des raisons de sécurité
                ", gestionnaire=" + gestionnaire +
                '}';
    }
}