package com.systemtechnology.clientregister.activities.main_activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.systemtechnology.clientregister.R
import com.systemtechnology.clientregister.activities.main_activity.adapter.ClientsAdapter
import com.systemtechnology.clientregister.activities.main_activity.adapter.NoneClientsYetAdapter
import com.systemtechnology.clientregister.activities.update_or_register_activity.UpdateOrRegisterActivity
import com.systemtechnology.clientregister.define_rules.PresenterAny
import com.systemtechnology.clientregister.define_rules.RulesBaseActivityBroadcasts
import com.systemtechnology.clientregister.entity.Client
import java.lang.IllegalStateException

class MainActivity : MainActivityView() ,
                     MainMethods {


    override fun whenListModified(list: MutableList<Client>) {
        changeAdapterRecyclerToClientsAdapter( list )
    }

    override fun whenNotExistsClientsYet() {
        changeAdapterRecyclerToNoneClientsYetAdapter()
    }

    override fun whenClientsFound(listClients: MutableList<Client>) {
        changeAdapterRecyclerToClientsAdapter( listClients )
    }

    override fun getInstancePresenter() : PresenterAny {
        return MainPresenter( this )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate( R.menu.menu_main_activity , menu )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(doubleClick!!.isSingleClick())
            startActivity( Intent( this , UpdateOrRegisterActivity::class.java ) )

        return false
    }

    override fun onReceiv(intent: Intent) {
        if( intent.action == UpdateOrRegisterActivity.ACTION_CLIENT_REGISTER ) {

            val jsonClient = intent.getStringExtra( UpdateOrRegisterActivity.EXTRA_CLIENT )
            val isInserting = intent.getBooleanExtra( UpdateOrRegisterActivity.EXTRA_IS_INSERTING , false )

            (presenter as MainPresenter)
                .whenClientModified(
                    fromJson( jsonClient , Client::class.java ) ,
                    isInserting
                )
        } else {
            throw IllegalStateException("not implemented action ${intent.action}")
        }

    }

}

abstract class MainActivityView : RulesBaseActivityBroadcasts() {

    private lateinit var recyclerView   : RecyclerView

    private lateinit var clientsAdapter         : ClientsAdapter
    private lateinit var noneClientsYetAdapter  : NoneClientsYetAdapter

    override fun getLayoutResActivity() : Int {
        return R.layout.activity_main
    }

    override fun getReferences() {
        recyclerView = findViewById(R.id.recyclerview)
    }

    override fun setSettingsIfExists() {
        recyclerView.layoutManager  = LinearLayoutManager( this )
    }

    protected fun changeAdapterRecyclerToNoneClientsYetAdapter() {
        noneClientsYetAdapter = NoneClientsYetAdapter(this )
        recyclerView.adapter = noneClientsYetAdapter
    }

    protected fun changeAdapterRecyclerToClientsAdapter(listClients: MutableList<Client>) {
        clientsAdapter = ClientsAdapter( listClients , this )
        recyclerView.adapter = clientsAdapter
    }


    override fun getActions(): Array<String>? {
        return arrayOf( UpdateOrRegisterActivity.ACTION_CLIENT_REGISTER )
    }


}