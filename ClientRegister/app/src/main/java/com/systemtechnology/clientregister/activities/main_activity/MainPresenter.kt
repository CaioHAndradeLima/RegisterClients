package com.systemtechnology.clientregister.activities.main_activity

import android.view.Menu
import com.systemtechnology.clientregister.define_rules.ActivityMethods
import com.systemtechnology.clientregister.define_rules.RulesBasePresenter
import com.systemtechnology.clientregister.entity.Client
import com.systemtechnology.clientregister.model.ModelClient
import io.reactivex.Observable

class MainPresenter(activityMethods: MainMethods) : RulesBasePresenter(activityMethods) {
    val mainMethods = activityMethods
    private lateinit var list : MutableList<Client>

    override fun onCreate() {
        super.onCreate()

        searchClientsAndAfterNotifyView()
    }

    private fun searchClientsAndAfterNotifyView() {
        Observable
            .fromArray(ModelClient().getAllClients())
            .subscribe {
                if (it != null && it.isNotEmpty()) {
                    mainMethods.whenClientsFound(it!!)
                    list = it

                } else {
                    mainMethods.whenNotExistsClientsYet()
                }
            }

    }

    fun whenClientModified(client : Client, inserting: Boolean) {
        if( inserting ) {

            if( ::list.isInitialized ) {
                list.add( client )
            } else {
                searchClientsAndAfterNotifyView()
                return
            }

        } else {

            for (cl in list) {
                if( cl.id == client.id ) {
                    list.remove( cl )
                    list.add( client )
                    break
                }
            }
        }

        mainMethods.whenListModified( list )

    }

}

interface MainMethods : ActivityMethods {
    fun whenNotExistsClientsYet()
    fun whenClientsFound( listClients : MutableList<Client> )
    fun whenListModified(list: MutableList<Client>)
}