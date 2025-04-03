<?php
// charger les paramètres globaux en chargeant les classes
include_once 'gui/Layout.php';
include_once 'gui/ErrorView.php';
include_once 'gui/LoginView.php';
include_once 'gui/ViewCreateCommande.php';
include_once 'gui/CommandesView.php';
include_once 'gui/CommandeView.php';
include_once 'gui/PanierView.php';
include_once 'gui/ProduitView.php';
include_once 'gui/UtilisateurView.php';

include_once 'domain/Commande.php';
include_once 'domain/Produit.php';
include_once 'domain/Commande.php';
include_once 'domain/Produit.php';

include_once 'data/ApiUtilisateur.php';
include_once 'data/ApiProduit.php';
include_once 'data/ApiPanier.php';
include_once 'data/ApiCommande.php';

include_once 'control/Presenter.php';
include_once 'control/Controllers.php';

include 'service/CommandeChecking.php';
include 'service/CommandeCreation.php';
include 'service/PanierChecking.php';
include 'service/ProduitChecking.php';
include 'service/UtilisateurChecking.php';

use gui\{Layout, LoginView, ErrorView, ViewCreateCommande, CommandesView, CommandeView, PanierView, ProduitView, UtilisateurView};
use domain\{Commande, Produit, Panier, Utilisateur};
use data\{ApiCommande, ApiProduit, ApiPanier, ApiUtilisateur};
use control\{Controllers, Presenter};
use service\{CommandeChecking, CommandeCreation, ProduitChecking, PanierChecking, UtilisateurChecking};

try
{
    //Connection aux API
    $apiCommande = new ApiCommande();
    $apiProduit = new ApiProduit();
    $apiPanier = new ApiPanier();
    $apiUtilisateur = new ApiUtilisateur();
}
catch (Exception $e)
{
    print "Erreur de connexion !: " . $e->getMessage() . "<br/>";
    die();
}

// Initialisation du contrôlleur, presenteur et services

$controller = new Controllers();

$utilisateurCheck = new UtilisateurChecking();

$produitCheck = new ProduitChecking();

$panierCheck = new PanierChecking();

$commandeChecking = new CommandeChecking();

$commandeCreation = new CommandeCreation();

$presenter = new Presenter();


//chemin de l'URL
$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);

// Définition d'une session
ini_set('session.gc_maxlifetime', 3600);
session_set_cookie_params(3600);
session_start();


if ('/services-web-ihm/' !== $uri && 'services-web-ihm/index.php' !== $uri && '/services-web-ihm/index.php/logout' !== $uri)
{
    $error = $controller->authentifierAction($utilisateurCheck, $apiUtilisateur);
    if( $error != null )
    {
        $uri = '/services-web-ihm/index.php/error' ;
        if ( $error == 'bad login or pwd' or $error == 'not connected')
            $redirect = '/services-web-ihm/index.php';
    }
}
if ('/services-web-ihm/' === $uri || 'services-web-ihm/index.php' === $uri || '/services-web-ihm/index.php/logout' === $uri)
{
    // affichage de la page de connexion
    session_destroy();
    $layout = new Layout("gui/layout.html");
    $vueLogin = new LoginView($layout);

    $vueLogin->display();
}
else if ('/services-web-ihm/error' === $uri)
{
    $layout = new Layout("gui/layout.html");
    $vueError = new ErrorView($layout, $error, $redirect);
    $vueError->display();
}
else if ('/services-web-ihm/index.php/utilisateur' === $uri)
{
    $controller->authentifierAction($utilisateurCheck, $apiUtilisateur);


}
else if ('/services-web-ihm/index.php/panier' === $uri && isset($_GET['id']))
{
    $controller->panierAction($_GET['id'], $apiPanier, $panierCheck);
}
else if ('/services-web-ihm/index.php/produit' === $uri && isset($_GET['id']))
{
    $controller->produitAction($_GET['id'], $apiProduit, $produitCheck);
}
else if ('/services-web-ihm/index.php/commandecreation' === $uri)
{
    echo "commande création";
}
else if ('/services-web-ihm/index.php/commande' === $uri && isset($_GET['id']))
{
    $controller->commmandeAction($_GET['id'], $apiCommande, $commandeChecking);
}
else
{
    header('Status: 404 Not Found');
    echo '<html>
            <body>
              <h1 style="text-align: center">Error 404: Page Not Found.</h1>
            </body>
          </html>';
}
