package com.systemtechnology.clientregister.activities.main_activity.adapter

import android.content.Intent
import android.view.View
import android.widget.TextView
import com.mikhaellopez.circularimageview.CircularImageView
import com.systemtechnology.clientregister.R
import com.systemtechnology.clientregister.activities.update_or_register_activity.UpdateOrRegisterActivity
import com.systemtechnology.clientregister.define_rules.RulesBaseActivity
import com.systemtechnology.clientregister.define_rules.adapter.RulesHolderAdapter
import java.lang.IllegalStateException

class NoneClientsYetHolder(view: View) : RulesHolderAdapter(view), View.OnClickListener {
    private lateinit var circularImageView  : CircularImageView
    private lateinit var textView           : TextView

    override fun getReferences() {
        circularImageView   = findViewById( R.id.circularimageview )
        textView            = findViewById( R.id.textview )

    }

    override fun setSettingsWhenExists() {
        itemView.setOnClickListener( this )
    }

    override fun onClick(v: View?) {
        if( doubleClick.isSingleClick() )
            itemView.context.startActivity( Intent( itemView.context , UpdateOrRegisterActivity::class.java ) )
    }

    override fun bindViewHolder(obj: Any?) {
        throw IllegalStateException("don't need call bindViewHolder")
    }
}
