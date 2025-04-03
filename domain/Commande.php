<?php

namespace domain;

/**
 * Classe représentant une commande
 * */
class Commande
{
    /**
     * Identifiant d'une commande
     * */
    private int $idCommande;

    /**
     * Classe représentant une commande
     * */
    private int $idUser;

    /**
     * Prix d'une commande
     * */
    private float $prix;

    /**
     * Date de retrait de la commande
     * */
    private int $dateRetrait;

    /**
     * Status de la commande
     * */
    private string $status;

    /**
     * Relais une commande
     * */
    private string $relais;

    /**
     * Constructeur de la classe
     * @param int $idCommande
     * @param int $idUser
     * @param int $prix
     * @param int $dateRetrait
     * @
     * */
    public function __construct(int $idUser, int $idCommande, int $prix, int $dateRetrait, string $status, string $relais)
    {
        $this->idCommande = $idCommande;
        $this->idUser = $idUser;
        $this->prix = $prix;
        $this->dateRetrait = $dateRetrait;
        $this->status = $status;
        $this->relais = $relais;
    }

    /**
     * @return int
     */
    public function getIdCommande(): int
    {
        return $this->idCommande;
    }

    /**
     * @return int
     */
    public function getIdUser(): int
    {
        return $this->idUser;
    }

    /**
     * @return float
     */
    public function getPrix(): float
    {
        return $this->prix;
    }

    /**
     * @return int
     */
    public function getDateRetrait(): int
    {
        return $this->dateRetrait;
    }

    /**
     * @return string
     */
    public function getRelais(): string
    {
        return $this->relais;
    }

    /**
     * @return string
     */
    public function getStatus(): string
    {
        return $this->status;
    }
}