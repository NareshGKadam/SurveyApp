package com.android.surveyapp

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.android.surveyapp.ui.datamodel.*
import java.util.*


class SurveyApp : Application() {
    private var mInstance: SurveyApp? = null
    companion object {
        lateinit var technicianAppContext: Context
        lateinit var sharedPreferences: SharedPreferences
        var userID: Int? = null

        var refreshToken: String? = null
        var encryptedProjectID: String? = null
        var TicketEncryptionID: String? = null
        var projectTitle: String? = null
        var ticketTitle: String? = null

        var mPage1_Common: Commondata?=null
        var mPage2_Section: SectionData?=null
        var mPage3_Resident: Residential?=null
        var mPage4_Commercial: Commercial?=null
        var mPage5_Apartments: Apartments?=null
        var mPage6_Institutional: Institutional?=null




        fun hideSoftKeyBoard(view: View) {
            val imm = technicianAppContext?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
            //imm?.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0)
            imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    override fun onCreate() {
        super.onCreate()
        technicianAppContext = applicationContext
//        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        mInstance = this;
        // addObserver
        /*sharedPreferences.edit().apply{
            when(sharedPreferences.getString(PREFERENCES_UUID, null)){
                null -> putString(PREFERENCES_UUID, uniqueDeviceIdentifier)
                else -> sharedPreferences.getString(PREFERENCES_UUID, null)
            }
            this.apply()
        }*/

       /* val analytics = ApplicationAnalytics()
        analytics.addReporter(AnalyticsLogger())
        ProcessLifecycleOwner
            .get()
            .lifecycle
            .addObserver(ApplicationObserver(analytics))*/
    }



    // Adding some callbacks for test and log
    interface ValueChangeListener {
        fun onChanged(value: Boolean?)
    }

    private var visibilityChangeListener: ValueChangeListener? = null
    fun setOnVisibilityChangeListener(listener: ValueChangeListener?) {
        visibilityChangeListener = listener
    }

    private fun isAppInBackground(isBackground: Boolean) {
        if (null != visibilityChangeListener) {
            visibilityChangeListener!!.onChanged(isBackground)
        }
    }

    fun getInstance(): SurveyApp? {
        return mInstance
    }

    /* AppController.getInstance().setOnVisibilityChangeListener(new ValueChangeListener() {
         @Override
         public void onChanged(Boolean value) {
             Log.d("isAppInBackground", String.valueOf(value));
         }
     });*/

}