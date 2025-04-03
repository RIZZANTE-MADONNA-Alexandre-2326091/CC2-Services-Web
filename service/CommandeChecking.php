<?php

namespace service;

class CommandeChecking
{
    public function getCommande($id, $data) {
        return ($data->getProduit($id) != null);
    }
}