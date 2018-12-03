package com.systemtechnology.clientregister.utils

import com.systemtechnology.clientregister.entity.Address

object UtilsFormat {

    fun formatAddressToPutOnLayout(address : Address) : String {
        return "${address.street}, ${address.houseNumber}"
    }

}
