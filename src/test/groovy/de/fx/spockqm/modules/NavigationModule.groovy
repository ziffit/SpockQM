package de.fx.spockqm.modules

import geb.Module

class NavigationModule extends Module {
    static base = { $("#top-menu-nav") }
    static content = {
        kontakt { $("#menu-item-66").first() }
    }
}
