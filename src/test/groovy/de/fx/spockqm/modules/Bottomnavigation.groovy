package de.fx.spockqm.modules

import geb.Module

/**
 * Module for the Bottomnavigation.
 * Base is: #main-footer
 */
class Bottomnavigation extends Module {
    static base = { $("#main-footer") }
    static content = {
        kontakt { $("#menu-item-747 a").first() }
    }
}
