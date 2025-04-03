<?php

namespace data;

use domain\Produit;
use service\ProduitAccessInterface;
include_once 'service/ProduitAccessInterface.php';

class ApiProduit implements ProduitAccessInterface
{
    public function getProduits()
    {
        $apiUrl = "http://localhost:8080/apiUserProduits-1.0-SNAPSHOT/api/products";

        $curlConnection = curl_init();
        $params = array(
            CURLOPT_URL => $apiUrl,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_HTTPHEADER => array('accept: application/json')
        );
        curl_setopt_array($curlConnection, $params);

        //exécution de la requête HTTP avec CURL
        $reponse = curl_exec($curlConnection);
        curl_close($curlConnection);

        $reponse = json_decode($reponse);

        if(!$reponse)
        {
            echo curl_error($curlConnection);
        }

        $produits = array();
        foreach ($reponse as $produit)
        {
            $id = $produit['id'];
            $name = $produit['name'];
            $prix = $produit['price'];
            $quantite = $produit['quantite'];
            $unit = $produit['unit'];
            $currentProduit = new Produit($id, $name, $prix, $quantite, $unit);
            $produits[$id] = $currentProduit;
        }

        $produitsSerialized = serialize($produits);
        file_put_contents('data/cache_alternance', $produitsSerialized);

        return $produits;
    }
    public function getProduit($id)
    {
        $produitSerialized = file_get_contents('data/cache_produit');
        $produit = unserialize($produitSerialized);

        return $produit[$id];
    }
}