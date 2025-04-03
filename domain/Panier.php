<?php

namespace domain;

/**
 * Classe représentant un panier
 * */
class Panier
{
    /**
     * Identifiant du panier
     * */
    private string $id;

    /**
     * Quantité dans le panier
     * */
    private int $quantite;

    /**
     * Produits dans le panier
     * */
    private String $produits;

    /**
     * Prix du panier
     * */
    private int  $prix;

    /**
     * Date du panier
     * */
    private string $date;

    /**
     * Constucteur de la classe
     * @param string $id
     * @param string $quantite
     * @param string $produits
     * @param int $prix
     * @param string $date
     * */
    public function __construct(string $id, string $quantite, string $produits, int $prix, string $date)
    {
        $this->id = $id;
        $this->quantite = $quantite;
        $this->produits = $produits;
        $this->prix = $prix;
        $this->date = $date;
    }

    /**
     * @return string
     */
    public function getDate(): string
    {
        return $this->date;
    }

    /**
     * @return string
     */
    public function getId(): string
    {
        return $this->id;
    }

    /**
     * @return int
     */
    public function getPrix(): int
    {
        return $this->prix;
    }

    /**
     * @return int
     */
    public function getQuantite(): int
    {
        return $this->quantite;
    }

    /**
     * @return String
     */
    public function getProduits(): string
    {
        return $this->produits;
    }
}