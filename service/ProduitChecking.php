<?php

namespace service;

class ProduitChecking
{
    public function getProduit($id, $data) {
        return ($data->getProduit($id) != null);
    }
}