package com.systemtechnology.clientregister

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.systemtechnology.clientregister.activities.update_or_register_activity.UpdateOrRegisterActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.widget.EditText
import org.hamcrest.core.AllOf.allOf


@RunWith(AndroidJUnit4::class)
class UpdateOrRegisterActivityTest {

    @get :Rule
    var rule : ActivityTestRule<UpdateOrRegisterActivity> =
                ActivityTestRule(UpdateOrRegisterActivity::class.java)


    @Test
    fun isDisplayed() {
        onView( withId(R.id.edit_text_name)  ).check(matches(ViewMatchers.isDisplayed()))
        onView( withId(R.id.edit_text_cpf)   ).check(matches(ViewMatchers.isDisplayed()))
        onView( withId(R.id.card_view)       ).check(matches(ViewMatchers.isDisplayed()))
        onView( withId(R.id.textview)        ).check(matches(ViewMatchers.isDisplayed()))
        onView( withId(R.id.button)          ).check(matches(ViewMatchers.isDisplayed()))
        onView( withId(R.id.text_view_photo) ).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testStringsOnLayout() {

        onView( withId(R.id.textview) )
            .check( matches( ViewMatchers.withText( R.string.update_register_inserting_title ) ) )

        onView( withId(R.id.text_view_photo) )
            .check( matches( ViewMatchers.withText( R.string.update_register_inserting_photo ) ) )

        onView( withId(R.id.button) )
            .check( matches( ViewMatchers.withText( R.string.update_register_inserting_button ) ) )


    }

    @Test
    fun sampleClickButton_openSnackbar(){
        clickButton_openSnackbar( R.string.form_client_error_name )
    }

    fun clickButton_openSnackbar( stringId : Int ) {
        onView( withId( R.id.button ) ).perform( ViewActions.click() )
        onView( allOf(withId(android.support.design.R.id.snackbar_text), withText( stringId ) ) )
    }

    @Test
    fun clickButton_openSnackbarTextCPF(){
        rule.activity
            .findViewById<EditText>( R.id.edit_text_name )
            .setText( "Caio Henrique Andrade" )
        clickButton_openSnackbar( R.string.form_client_error_cpf )
    }

    @Test
    fun clickButton_openSnackbarTextAddress(){
        rule.activity
            .findViewById<EditText>( R.id.edit_text_cpf )
            .setText( "44574551801" )

        rule.activity
            .findViewById<EditText>( R.id.edit_text_name )
            .setText( "Name Correct Example" )

        clickButton_openSnackbar( R.string.form_client_error_address )
    }


}
