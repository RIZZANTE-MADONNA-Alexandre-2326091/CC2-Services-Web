<?php

namespace control;

/**
 * Présenteur du site pour afficher les contenus spécifiques
 */
class Presenter
{
    protected $utilisateurCheck;

    public function __construct($utilisateurCheck)
    {
        $this->utilisateurCheck = $utilisateurCheck;
    }

    public function getCommandes()
    {

    }

    public function getCommande($id)
    {

    }
}