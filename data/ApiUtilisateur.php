<?php

namespace data;

use service\PanierAccessInterface;
include_once 'service/ProduitAccessInterface.php';

/**
 * Accès à l'API utilisateur
 */
class ApiUtilisateur implements PanierAccessInterface
{
    /**
     * Récupérer l'utilisateur
     * @param $login
     * @param $password
     * @param $data
     * */
    public function getUser($login, $password, $data)
    {
        $apiUrl = "http://localhost:8080/apiUserProduits-1.0-SNAPSHOT/api/users";
        $curlConnection = curl_init();

        curl_close($curlConnection);
    }
}