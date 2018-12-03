package com.systemtechnology.clientregister.activities.update_or_register_activity

import com.systemtechnology.clientregister.define_rules.ActivityMethods
import com.systemtechnology.clientregister.define_rules.RulesBasePresenter
import com.systemtechnology.clientregister.entity.Address
import com.systemtechnology.clientregister.entity.Client
import com.systemtechnology.clientregister.validate_helper.ClientValidate
import io.reactivex.Observable

class UpdateOrRegisterPresenter( activityMethods : UpdateOrRegisterMethods ) : RulesBasePresenter( activityMethods ) {

    private val uorm = activityMethods
    private var client = activityMethods.getClient()

    private var isInserting = false

    override fun onCreate() {
        super.onCreate()

        isInserting = client == null

        if( isInserting ) {
            client = Client()
            uorm.notifyIsInsertingNewClient()
        } else {
            uorm.notifyIsUpdatingClient( client!! )
        }
    }

    fun isPossibleCreateOptionsMenu() : Boolean {
        return !isInserting
    }

    fun whenClickedButtonConfirm(currentName: String, currentCPF: String) {
        client!!.name = currentName
        client!!.CPF  = currentCPF

        Observable
            .just( client )
            .filter {
                val idMessage = ClientValidate.isValid( it )

                //if some thing wrong in form on layout
                if ( idMessage != 0 ) {
                    uorm.whenFormWrong( idMessage )
                    false
                }  else {
                    true
                }

            }.doOnNext {
                client!!.save()

                if( isInserting ) {
                    uorm.whenClientWasSalved( it!! )
                } else {
                    uorm.whenClientWasUpdated( it!! )
                }
            }.subscribe()
    }

    fun whenReceiveAddress( address : Address) {
        client!!.address = address
    }
}

interface UpdateOrRegisterMethods : ActivityMethods {
    fun getClient() : Client?
    fun whenFormWrong(idMessage: Int)
    fun whenClientWasSalved(client: Client)
    fun whenClientWasUpdated(client: Client)
    fun notifyIsInsertingNewClient()
    fun notifyIsUpdatingClient(client : Client )
}
