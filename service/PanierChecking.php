<?php

namespace service;

class PanierChecking
{
    public function getPanier($id, $data) {
        return ($data->getProduit($id) != null);
    }
}