package com.example.desa

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyMessageFirebase: FirebaseMessagingService() {
    override fun onNewToken(token: String) {

        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (!it.isSuccessful) {
                return@addOnCompleteListener
            }

            val tokens = it.result
            Log.d("aaaaaaaaaa token", tokens)
        }


        Log.d("Akukamu", token)
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {

        sendNotification(message.notification?.title.toString(), message.notification?.body.toString() )
//        super.onMessageReceived(message)
    }

    @SuppressLint("SuspiciousIndentation")
    fun sendNotification(judul: String, desk : String) {

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("pesan", "pesan");
        MainActivity.NEXTMENU = "pesan"
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0)


        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.logologin)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.logologin))
            .setContentTitle(judul)
            .setContentText(desk)
            .setSubText(resources.getString(R.string.subtext))
            .setAutoCancel(true)
//            .setPriority(NotificationManager.IMPORTANCE_HIGH)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /* Create or update. */
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = CHANNEL_NAME
            mBuilder.setChannelId(CHANNEL_ID)
            mNotificationManager.createNotificationChannel(channel)
        }

        val notification = mBuilder.build()
        mNotificationManager.notify(NOTIFICATION_ID, notification)
    }

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "dicoding channel"
    }
}