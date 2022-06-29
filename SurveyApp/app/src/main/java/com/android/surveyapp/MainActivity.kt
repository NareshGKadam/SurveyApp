package com.android.surveyapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
       /* if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }*/

      val button2:Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }



        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val date1: Date =Date()
            val expdate2: Date = sdf.parse("2022-05-30")
            if(date1.before(expdate2)){
                Log.e("app", "currnet Date1 is before expdate2")  // not expired
            }

            if(date1.after(expdate2)){
                Log.e("app", "current Date1 is after expdate2")   //  expired
               /* val builder = AlertDialog.Builder(this)
                builder.setTitle("Build info")
                builder.setMessage("SurveyApp test version expired")
                builder.setIcon(android.R.drawable.ic_dialog_alert)
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()*/
        }
        } catch (e: Exception) {
            e.printStackTrace()
        }



    }
}