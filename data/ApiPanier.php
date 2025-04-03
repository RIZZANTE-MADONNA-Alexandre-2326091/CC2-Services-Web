<?php

namespace data;

use service\PanierAccessInterface;
include_once 'service/PanierAccessInterface.php';

class ApiPanier implements PanierAccessInterface
{
    public function getPaniers()
    {
        $apiUrl = "http://localhost:8080/panier-1.0-SNAPSHOT/panier/paniers";

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

    public function getPanier($id)
    {
        $panierSerialized = file_get_contents('data/cache_commande');
        $panier = unserialize($panierSerialized);

        return $panier[$id];
    }
}