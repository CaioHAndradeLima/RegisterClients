package com.systemtechnology.clientregister.activities.main_activity.adapter

import android.content.Intent
import android.view.View
import android.widget.TextView
import com.mikhaellopez.circularimageview.CircularImageView
import com.systemtechnology.clientregister.R
import com.systemtechnology.clientregister.activities.update_or_register_activity.UpdateOrRegisterActivity
import com.systemtechnology.clientregister.define_rules.adapter.RulesHolderAdapter
import com.systemtechnology.clientregister.entity.Client
import com.systemtechnology.clientregister.utils.UtilsConvertJson
import com.systemtechnology.clientregister.utils.UtilsFormat

class ClientsHolder(view: View) : RulesHolderAdapter(view), View.OnClickListener {

    private lateinit var circularImageView  : CircularImageView
    private lateinit var textViewTitle      : TextView
    private lateinit var textViewSubTitle   : TextView

    private lateinit var client             : Client

    override fun getReferences() {
        circularImageView   = findViewById( R.id.circularimageview  )
        textViewTitle       = findViewById( R.id.textview_title     )
        textViewSubTitle    = findViewById( R.id.textview_subtitle  )
    }

    override fun setSettingsWhenExists() {
        itemView.setOnClickListener( this )
    }

    override fun onClick( itemView : View ) {
        if( doubleClick.isDoubleClick() ) return

        val it = Intent( itemView.context , UpdateOrRegisterActivity::class.java )
        it.putExtra( UpdateOrRegisterActivity.EXTRA_CLIENT , UtilsConvertJson.toJson( client ) )
        itemView.context.startActivity( it )
    }

    override fun bindViewHolder(obj: Any?) {
        client = obj as Client

        textViewTitle.text = client.name
        textViewSubTitle.text = UtilsFormat
                                    .formatAddressToPutOnLayout( client.address )
    }
}
