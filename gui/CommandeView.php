<?php

namespace gui;

use gui\View;

include_once "View.php";

class CommandeView extends View
{
    public function __construct(Layout $layout, $login)
    {
        parent::__construct($layout, $login);
    }
}