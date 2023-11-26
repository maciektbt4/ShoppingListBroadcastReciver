package com.example.broadcastreceiverapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.view.LayoutInflater
import com.example.broadcastreceiverapp.databinding.ActivityMainBinding

class ItemAddedReceiver():
    BroadcastReceiver() {
    private lateinit var binding: ActivityMainBinding
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("Receiver", "Broadcast received")
        if (intent?.action == "ITEM_ADDED_TO_SHOPPING_LIST"){
            Intent(context, NotificationService::class.java).also{
                it.putExtra("ITEM", intent?.getStringExtra("ITEM"))
                it.action = NotificationService.Actions.START.toString()
                context?.startService(it)
            }
        }
    }
}