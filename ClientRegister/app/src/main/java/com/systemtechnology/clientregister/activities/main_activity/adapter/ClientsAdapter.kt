package com.systemtechnology.clientregister.activities.main_activity.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.systemtechnology.clientregister.R
import com.systemtechnology.clientregister.define_rules.adapter.AdapterDependency
import com.systemtechnology.clientregister.define_rules.adapter.RulesAdapterRecycler
import com.systemtechnology.clientregister.entity.Client

class ClientsAdapter(list: List<Client>, ad: AdapterDependency) : RulesAdapterRecycler<Client>(list, ad) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return ClientsHolder( layoutInflater.inflate( R.layout.holder_clients , viewGroup , false ) )
    }
}
