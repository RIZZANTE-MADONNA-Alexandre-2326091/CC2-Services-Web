<?php

namespace gui;

use gui\View;

include_once "View.php";

/**
 * Vue pour afficher une erreur
 */
class ErrorView extends View
{
    /**
     * Constructeur de la classe
     */
    public function __construct($layout, $error, $redirect)
    {
        parent::__construct($layout);

        header( "refresh:5;url=$redirect" );
        echo $error;
    }
}