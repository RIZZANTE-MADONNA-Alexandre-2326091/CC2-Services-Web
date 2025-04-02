<?php
namespace gui;

/**
 * Layout pour une vue
 */
class Layout
{
    /**
     * Chemin du fichier HTML
     */
    protected string $templateFile;

    /**
     * Constructeur de la classe
     * @param string $templateFile Chemin du fichier HTML
     */
    public function __construct( string $templateFile )
    {
        $this->templateFile = $templateFile;
    }

    /**
     * Affichage du layout
     * @param $title
     * @param $connexion
     * @param $content
     */
    public function display( $title, $connexion, $content )
    {
        $page = file_get_contents( $this->templateFile );
        $page = str_replace( ['%title%','%connexion%','%content%'], [$title, $connexion, $content], $page);
        echo $page;
    }

}