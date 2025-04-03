<?php

namespace service;

interface ProduitAccessInterface
{
    public function getProduit($id);
    public function getProduits();
}