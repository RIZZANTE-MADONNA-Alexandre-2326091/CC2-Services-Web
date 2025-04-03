<?php

namespace control;

/**
 * Contrôleur du site
 */
class Controllers
{
    /**
     * Contrôle l'identifiaction des utilisateurs
     * @param $userCheck
     * @param $dataUsers
     */
    public function authentifierAction($userCheck, $dataUsers){

        // Si l'utilisateur n'a pas de session ouverte
        if( !isset($_SESSION['login']) ) {

            // Si la page d'origine est le formulaire de connexion
            if( isset($_POST['login']) && isset($_POST['password']) )
            {
             // Vérification de l'authentification si la précédente page était le formulaire de connexion
                if( !$userCheck->authentifier($_POST['login'], $_POST['password'], $dataUsers) )
                {
                    // retourne une erreur si le compte n'est pas enregistré
                    $error = 'bad login or pwd';
                    return $error;

                }
                // Enregistrement des informations de session après une authentification réussie
                else {
                    $_SESSION['login'] = $_POST['login'] ;
                }
            }
            else{
                // retourne une erreur si la personne ne passe pas par le forumlaire de création ou de connexion
                $error = 'not connected';
                return $error;
            }
        }
    }

    /**
     * Contrôleur d'action sur le panier
     * @param $id
     * @param $data
     * @param $panierCheck
     */
    public function panierAction($id, $data, $panierCheck)
    {
        $
    }

    /**
     * Contrôleur d'action sur la commande
     * @param $id
     * @param $data
     * @param $commandeCheck
     */
    public function commmandeAction($id, $data, $commandeCheck)
    {

    }

    /**
     * Contrôleur de création de commande
     * @param $data
     * @param $commandeCreation
     */
    public function commandeCreation($data, $commandeCreation)
    {

    }

    /**
     * Contrôleur d'action sur le produit
     * @param $id
     * @param $data
     * @param $produitCheck
     */
    public function produitAction($id, $data, $produitCheck)
    {

    }
}