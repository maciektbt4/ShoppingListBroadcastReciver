package com.example.broadcastreceiverapp

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.app.TaskStackBuilder
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class NotificationService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            Actions.START.toString() -> start(intent)
            Actions.STOP.toString() -> stopSelf()
        }
        return START_STICKY
    }
    private fun start(intent: Intent?){
        val item = intent?.getStringExtra("ITEM").toString()
        Log.d("Notification_service","Service is running. Broadcast received:\n" +
                "item : $item")

        val intentAddItem = Intent().apply {
            setPackage("com.example.shoppinglist") // Specify the package name of the target application
            setClassName("com.example.shoppinglist", "com.example.shoppinglist.ui.shoppinglist.EditShoppingItem") // Specify the activity to be opened in application2
            putExtra("ITEM", item)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intentAddItem,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("New item added!")
            .setContentIntent(pendingIntent)
            .setContentText("Broadcast received: item:$item"
            ).build()
        startForeground(1, notification)
    }

    enum class Actions{
        START, STOP
    }
}