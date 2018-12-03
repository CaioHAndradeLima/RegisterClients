package com.systemtechnology.clientregister

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.systemtechnology.clientregister.activities.main_activity.MainActivity
import com.systemtechnology.clientregister.activities.main_activity.adapter.ClientsAdapter
import com.systemtechnology.clientregister.activities.main_activity.adapter.NoneClientsYetAdapter
import com.systemtechnology.clientregister.model.ModelClient
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest  {

    @get :Rule
    var rule : ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)


    @Test
    fun checkViews_isDisplayed() {
        onView( withId( R.id.recyclerview ) ).check( matches(isDisplayed()) )
    }


    @Test
    fun checkAdapterIfIsCorrect() {
        val adapter = rule.activity.findViewById<RecyclerView>( R.id.recyclerview ).adapter

        val list = ModelClient().getAllClients()

        if( list == null || list.size == 0 ) {
            Assert.assertTrue(adapter is NoneClientsYetAdapter )
        } else {
            Assert.assertTrue( adapter is ClientsAdapter )

            Assert.assertTrue( adapter!!.itemCount == list.size )
        }
    }

}
