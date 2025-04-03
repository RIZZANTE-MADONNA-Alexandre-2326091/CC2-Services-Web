<?php

namespace domain;

/**
 * Classe représentant un produit
 * */
class Produit
{
    /**
     * Identifiant du produit
     * */
    private int $id;

    /**
     * Nom du produit
     * */
    private string $name;

    /**
     * Prix du produit
     * */
    private float $prix;

    /**
     * Quantité du produit
     * */
    private int $quantite;

    /**
     * Unité de mesure du produit
     * */
    private string $unit;

    /**
     * Constructeur de la classe
     * @param int $id
     * @param string $name
     * @param int $prix
     * @param int $quantite
     * @param string $unit
     * */
    public function __construct(int $id, string $name, int $prix, int $quantite, string $unit)
    {
        $this->id = $id;
        $this->name = $name;
        $this->prix = $prix;
        $this->quantite = $quantite;
        $this->unit = $unit;
    }

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
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
    public function getUnit(): string
    {
        return $this->unit;
    }

    /**
     * @return int
     */
    public function getQuantite(): int
    {
        return $this->quantite;
    }

    /**
     * @return float
     */
    public function getPrix(): float
    {
        return $this->prix;
    }
}