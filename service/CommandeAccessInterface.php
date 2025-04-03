<?php

namespace service;

interface CommandeAccessInterface
{
    public function getCommande($id);
    public function getCommandes();
}