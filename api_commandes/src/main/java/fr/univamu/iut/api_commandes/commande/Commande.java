package fr.univamu.iut.api_commandes.commande;

import jakarta.ws.rs.Path;

import java.util.Date;

public class Commande {

    protected int id_commande   ;

    protected int id_user;

    protected int prix;

    protected Date date_retrait;

    protected String status;

    protected String relais;


    public Commande() {}

    public Commande (int id_commande, int id_user, int prix, Date date_retrait, String status, String relais ) {
        this.id_commande = id_commande;
        this.id_user = id_user;
        this.prix = prix;
        this.date_retrait = date_retrait;
        this.status = status;
        this.relais = relais;
    }

    public int getId_commande()
    {
        return id_commande;
    }
    public void setId_commande(int id_commande)
    {
        this.id_commande = id_commande;
    }

    public int getId_user()
    {
        return id_user;
    }
    public void setId_user(int id_user)
    {
        this.id_user = id_user;
    }

    public int getPrix()
    {
        return prix;
    }
    public void setPrix(int prix)
    {
        this.prix = prix;
    }

    public java.sql.Date getDate_retrait()
    {
        return (java.sql.Date) date_retrait;
    }
    public void setDate_retrait(Date date_retrait)
    {
        this.date_retrait = date_retrait;
    }

    public String getStatus()
    {
        return status;
    }
    public void setStatus(String relais)
    {
        this.status = status;
    }

    public String getRelais()
    {
        return relais;
    }
    public void setRelais(String relais)
    {
        this.relais = relais;
    }
}
