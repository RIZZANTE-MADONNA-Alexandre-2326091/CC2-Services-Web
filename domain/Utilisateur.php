<?php

namespace domain;

/**
 * Classe qui représente un utilisateur
 * */
class Utilisateur
{
    /**
     * Identifiant utilisateur
     * */
    private string $id;


    /**
     * Mot de passe utilisateur
     * */
    private string $password;

    /**
     * Email utilisateur
     * */
    private string $email;

    /**
     * Gestionnaire utilisateur
     * */
    private string $gestionnaire;

    /**
     * Nom utilisateur
     * */
    private string $name;


    /**
     * Constructeur de la classe
     * @param string $id
     * @param string $password
     * @param string $email
     * @param string $gestionnaire
     * @param string $name
     * */
    public function __construct(string $id, string $password, string $email, string $gestionnaire, string $name)
    {
        $this->id = $id;
        $this->password = $password;
        $this->email = $email;
        $this->gestionnaire = $gestionnaire;
        $this->name = $name;
    }

    /**
     * @return string
     */
    public function getName(): string
    {
        return $this->name;
    }

    /**
     * @return string
     */
    public function getPassword(): string
    {
        return $this->password;
    }
}