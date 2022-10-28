package com.zelix.biznesplanuzb.util;

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.zelix.biznesplanuzb.activities.MainActivity2
import com.zelix.biznesplanuzb.R
import com.zelix.biznesplanuzb.models.Click


@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "Alovuddin"

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d(TAG, "From: " + message.from);

     /*   message.let {
            //  Toast.makeText(applicationContext, it!!.body, Toast.LENGTH_SHORT).show()

            if (it.data.isNotEmpty()) {

                val mExample: Click = Gson().fromJson(it.data.toString(), Click::class.java)

                println("aziza: $mExample")

                if (mExample.merchant_trans_id == MySharedPreference.userId) {

                    MySharedPreference.isSuccessPaid = true

                    val notificationIntent = Intent(applicationContext, MainActivity2::class.java)

                    val contentIntent = PendingIntent.getActivity(
                        this,
                        0,
                        notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )

                    val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, "1")
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("Biznes Plan Uzb")
                        .setContentIntent(contentIntent)
                        .setContentText("To'lov muvaffaqiyatli amalga oshdi!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setFullScreenIntent(
                            contentIntent,
                            true
                        ) //Whether true or false same result
                        .setPriority(Notification.PRIORITY_MAX)
                        .setAutoCancel(true)
                    val notification: Notification = builder.build()
                    val notificationManager =
                        getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.notify(1, notification)

                    with(NotificationManagerCompat.from(this)) {
                        // 1 is a unique int for each notification that you must define
                        notify(1, builder.build())
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val name = getString(R.string.app_name)
                        val descriptionText = getString(R.string.app_name)
                        val importance = NotificationManager.IMPORTANCE_DEFAULT
                        val channel = NotificationChannel("1", name, importance).apply {
                            description = descriptionText
                        }
                        notificationManager.createNotificationChannel(channel)

                    }
                    notificationManager.notify(1, builder.build())
                }

                println("alovuddin123: ${it.data}")


            } else {
       *//*         val notificationIntent = Intent(applicationContext, MainActivity2::class.java)

                val contentIntent = PendingIntent.getActivity(
                    this,
                    0,
                    notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )

                val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, "1")
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle("Biznes Plan Uzb")
                    .setContentIntent(contentIntent)
                    .setContentText("To'lov qilishda xatolik yuz berdi :(")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setFullScreenIntent(contentIntent, true) //Whether true or false same result
                    .setPriority(Notification.PRIORITY_MAX)
                    .setAutoCancel(true)
                val notification: Notification = builder.build()
                val notificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(1, notification)

                with(NotificationManagerCompat.from(this)) {
                    // 1 is a unique int for each notification that you must define
                    notify(1, builder.build())
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val name = getString(R.string.app_name)
                    val descriptionText = getString(R.string.app_name)
                    val importance = NotificationManager.IMPORTANCE_DEFAULT
                    val channel = NotificationChannel("1", name, importance).apply {
                        description = descriptionText
                    }
                    notificationManager.createNotificationChannel(channel)

                }
                notificationManager.notify(1, builder.build())*//*
            }


        }
*/
    }


}
