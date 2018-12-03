package com.systemtechnology.clientregister

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.EditText
import com.google.gson.Gson
import com.systemtechnology.clientregister.activities.update_or_register_activity.UpdateOrRegisterActivity
import com.systemtechnology.clientregister.entity.Address
import com.systemtechnology.clientregister.entity.Client
import com.systemtechnology.clientregister.mask_helper.CpfCnpjMask
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UpdateOrRegisterActivityTestWithIntent {


    @get :Rule
    var rule: ActivityTestRule<UpdateOrRegisterActivity> =
        object : ActivityTestRule<UpdateOrRegisterActivity>(UpdateOrRegisterActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val intent = Intent()

                val client = Client()
                client.name = "Caio Henrique"
                client.CPF  = "44574551801"
                client.address = Address()
                client.address.houseNumber = "29"
                client.address.street = "rua imaculada"
                client.address.state = "sp"
                client.address.city = "guarulhos"
                client.address.CEP = "07183070"
                client.address.neighborhood = "São Manoel"


                intent.putExtra(UpdateOrRegisterActivity.EXTRA_CLIENT , Gson().toJson( client ) )
                return intent
            }
        }


    @Test
    fun testStringsOnLayout() {

        onView( withId(R.id.textview) )
            .check( matches( ViewMatchers.withText( R.string.update_register_updating_title ) ) )

        onView( withId(R.id.text_view_photo) )
            .check( matches( ViewMatchers.withText( R.string.update_register_updating_photo ) ) )

        onView( withId(R.id.button) )
            .check( matches( ViewMatchers.withText( R.string.update_register_updating_button ) ) )

    }

    @Test
    fun testIfInfoOfUserGoneToLayout() {
        val client = rule.activity.getClient()!!

        onView( withId( R.id.edit_text_name ) ).check( matches( withText(client.name) ) )
        val unmasked = CpfCnpjMask
                                    .unmask( rule.activity.findViewById<EditText>( R.id.edit_text_cpf ).text.toString() )

        Assert.assertTrue( unmasked == client.CPF )
    }

}
