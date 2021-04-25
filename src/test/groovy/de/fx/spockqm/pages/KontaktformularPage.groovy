package de.fx.spockqm.pages


import de.fx.spockqm.modules.Topnavigation
import geb.Page

class KontaktformularPage extends Page {
    static at = { title == "Bewerbungsformular | QualityMinds" }
    static content = {
        topnavigation { module Topnavigation}
        button_submit { $("#fld_1982372_1")}
        email_input { $("#fld_3149235_1")}
        email_validation { $("#parsley-id-11")}
        firstname_input { $("#fld_1144146_1")}
        firstname_validation { $("#parsley-id-7")}
        lastname_input { $("#fld_7067875_1")}
        lastname_validation { $("#parsley-id-9")}
        upload_input { $("#cf2-fld_8583967_1 input[type=file]")}
        upload_filename { $("#fld_8583967_1_file_0 span.file-name")}
        privacy_input { $("#fld_4989725_1_opt1865542")}
        privacy_validation { $("#parsley-id-multiple-fld_4989725_1")}
    }
}
