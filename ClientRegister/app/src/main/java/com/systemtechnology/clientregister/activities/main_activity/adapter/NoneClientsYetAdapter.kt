package com.systemtechnology.clientregister.activities.main_activity.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.systemtechnology.clientregister.R
import com.systemtechnology.clientregister.define_rules.adapter.AdapterDependency
import com.systemtechnology.clientregister.define_rules.adapter.RulesAdapterRecycler

class NoneClientsYetAdapter( ad: AdapterDependency ) : RulesAdapterRecycler<Any>( null , ad) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return NoneClientsYetHolder( layoutInflater.inflate( R.layout.holder_none_clients_yet , viewGroup , false ) )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) { }

    override fun getItemCount(): Int {
        return 1
    }
}
