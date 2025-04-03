<?php

namespace service;

/**
 * Interface de l'accès aux utilisateur
 * */
interface UtilisateurAccessInterface
{
    /**
     * Récupère l'utilisateur
     * @param $login
     * @param $password
     * */
    public function getUser($login, $password);
}