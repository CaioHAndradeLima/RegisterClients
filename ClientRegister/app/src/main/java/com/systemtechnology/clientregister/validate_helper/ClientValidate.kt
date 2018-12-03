package com.systemtechnology.clientregister.validate_helper

import com.systemtechnology.clientregister.R
import com.systemtechnology.clientregister.entity.Client
import java.util.regex.Pattern

object ClientValidate {

    fun isValid(client : Client) : Int {
        if( client.name.length < 10 ||
            Pattern.matches("[a-zA-Z]+", client.name) ) {
            return R.string.form_client_error_name
        }

        if( client.CPF.length != 11 ) {
            return R.string.form_client_error_cpf
        }

        if( !client.addressIsInitialized() ) {
            return R.string.form_client_error_address
        }

        return 0
    }

}
