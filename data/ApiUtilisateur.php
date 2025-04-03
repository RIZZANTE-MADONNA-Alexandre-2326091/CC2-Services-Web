<?php

namespace data;

use domain\Utilisateur;
use service\UtilisateurAccessInterface;

include_once 'service/UtilisateurAccessInterface.php';

/**
 * Accès à l'API utilisateur
 */
class ApiUtilisateur implements UtilisateurAccessInterface
{
    /**
     * Récupérer l'utilisateur
     * @param $login
     * @param $password
     * @param $data
     * */
    public function getUser($login, $password)
    {
        $apiUrl = "http://localhost:8080/apiUserProduits-1.0-SNAPSHOT/api/users";
        $curlConnection = curl_init();

        $params = array(
            CURLOPT_URL => $apiUrl,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_HTTPHEADER => array('accept: application/json')
        );

        curl_setopt_array($curlConnection, $params);
        $reponse = curl_exec($curlConnection);
        curl_close($curlConnection);

        if (!$reponse)
        {
            echo curl_error($curlConnection);
        }

        $reponse = json_decode($reponse, true);

        $id = $reponse['id'];
        $password = $reponse['password'];
        $email = $reponse['email'];
        $gestionnaire = $reponse['gestionnaire'];
        $name = $reponse['name'];

        return new Utilisateur($id, $password, $email, $gestionnaire, $name);
    }
}