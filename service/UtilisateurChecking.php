<?php

namespace service;

/**
 * Service des utilisateurs
 */
class UtilisateurChecking
{
    /**
     * S'authentifier en tant que client
     * @param $login
     * @param $password
     * @param $data
     */
    public function authentifier($login, $password, $data)
    {
        return ($data->getUser($login, $password) != null);
    }
}