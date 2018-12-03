package com.systemtechnology.clientregister.activities.get_address_by_cep

import com.systemtechnology.clientregister.R
import com.systemtechnology.clientregister.define_rules.ActivityMethods
import com.systemtechnology.clientregister.define_rules.RulesBasePresenter
import com.systemtechnology.clientregister.entity.Address
import com.systemtechnology.clientregister.model.ModelVivaCepApi
import com.systemtechnology.clientregister.model.VivaCepListener
import io.reactivex.Observable

class GetAddressByCepPresenter(activityMethods: ActivityMethods) : RulesBasePresenter(activityMethods),
    VivaCepListener {

    private val modelVivaCepApi = ModelVivaCepApi( this )


    override fun whenSearchAddressByCepCompleted(address: Address) {
        getView().whenAddressArrived( address )
    }

    override fun whenErrorSearchAddressByCep() {
        getView().notifyErrorSearchAddress( R.string.form_cep_not_found )
    }



    fun whenNeedSearch( cep : String ) {
        Observable
            .just( cep )
            .map { it.length == 8 }
            .doOnNext {
                if( !it ) {
                    getView().whenCepWrong( R.string.form_cep_incorrect )
                } else {
                    getView().notifyGoingSearchAddress()
                    searchAddressByCep( cep )
                }
            }
            .subscribe()

    }

    private fun searchAddressByCep(cep: String) {
        clearDisposables()
        addDisposable( modelVivaCepApi.startSearch( cep ) )
    }

    private fun getView() : GetAddressByCepMethods {
        return (activityMethods as GetAddressByCepMethods)
    }

    fun whenClickedImageView( cep : String) {
        whenNeedSearch( cep )
    }
}


interface GetAddressByCepMethods : ActivityMethods {
    fun whenCepWrong( idMessage : Int )
    fun notifyErrorSearchAddress( idMessage : Int )

    fun whenAddressArrived( address : Address)
    fun notifyGoingSearchAddress()
}
