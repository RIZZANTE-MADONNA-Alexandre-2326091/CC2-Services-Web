<?php

namespace gui;

use gui\View;

include_once "View.php";

class UtilisateurView extends View
{
    public function __construct(Layout $layout)
    {
        parent::__construct($layout);

        $this->title = 'Serveurs Web API: Connexion';
        $this->content = '<h1 id="titre">Vous êtes connecté !</h1>';
    }
}