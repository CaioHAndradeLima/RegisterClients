package com.systemtechnology.clientregister.entity

import com.orm.SugarRecord

class Client : SugarRecord() {
    var name : String = ""
    var CPF  : String = ""

    lateinit var address : Address

    companion object {
        fun getAllClients(): MutableList<Client>? {
            return listAll( Client::class.java )
        }
    }

    override fun save(): Long {
        address.id = super.save()
        return address.save()
    }

    fun addressIsInitialized() : Boolean {
        return ::address.isInitialized
    }


}
