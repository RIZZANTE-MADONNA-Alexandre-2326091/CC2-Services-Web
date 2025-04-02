<?php
namespace gui;

/**
 * Classe abstraite d'une vue qui représente une page
 */
abstract class View
{
    /**
     * Titre de la page
     */
    protected string $title = '';

    /**
     * Contenu d'une page
     */
    protected string $content = '';

    /**
     * Layout d'une page
     */
    protected Layout $layout;

    /**
     * Constructeur de la classe
     * @param Layout $layout Layout de la page
     */
    public function __construct(Layout $layout)
    {
        $this->layout = $layout;
    }

    /**
     * Affichage de la vue
     */
    public function display(): void
    {
        $this->layout->display( $this->title, "", $this->content );
    }
}