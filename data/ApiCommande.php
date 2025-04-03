<?php

namespace data;

use service\CommandeAccessInterface;
include_once 'service/CommandeAccessInterface.php';

class ApiCommande implements CommandeAccessInterface
{
    public function getCommandes()
    {

    }

    public function getCommande($id)
    {
        $commandeSerialized = file_get_contents('data/cache_commande');
        $commande = unserialize($commandeSerialized);

        return $commande[$id];
    }
}