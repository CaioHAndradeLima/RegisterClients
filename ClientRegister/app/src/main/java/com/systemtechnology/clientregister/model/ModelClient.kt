package com.systemtechnology.clientregister.model

import com.systemtechnology.clientregister.define_rules.RulesBaseModel
import com.systemtechnology.clientregister.entity.Address
import com.systemtechnology.clientregister.entity.Client

class ModelClient : RulesBaseModel() {

    fun getAllClients() : MutableList<Client>? {
        val list = Client.getAllClients() ?: return null

        val listAddress = Address.getAllAddress()!!

        list.forEach { client ->

            for( address in listAddress){
                if(address.id == client.id) {
                    client.address = address
                    continue
                }
            }
        }

        return list
    }
}
