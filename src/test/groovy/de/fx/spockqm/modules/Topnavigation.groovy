package de.fx.spockqm.modules

import geb.Module

class Topnavigation extends Module {
    static base = { $("#top-menu-nav") }
    static content = {
        kontakt { $("#menu-item-66 a") }
        portfolio { $("#menu-item-220") }
        portfolio_automation { portfolio.find("#menu-item-768") }
        portfolio_submenu { portfolio.find(".sub-menu").first() }
    }
}
