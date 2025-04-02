<?php
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

