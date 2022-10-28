package com.zelix.biznesplanuzb.util

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MySharedPreference.getInstance(this)

        FirebaseMessaging.getInstance().subscribeToTopic("bpuz")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d(TAG, msg)
               // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
    }

}