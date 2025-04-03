<?php

namespace gui;

use gui\View;

include_once "View.php";

class CommandesView extends View
{
    public function __construct(Layout $layout)
    {
        parent::__construct($layout);
    }
}