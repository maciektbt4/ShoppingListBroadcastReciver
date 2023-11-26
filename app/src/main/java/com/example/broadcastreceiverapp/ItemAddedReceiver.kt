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
//        binding = ActivityMainBinding.inflate(LayoutInflater.from(context))
//        setContentView(binding.root)
        if (intent?.action == "ITEM_ADDED_TO_SHOPPING_LIST"){
            Intent(context, NotificationService::class.java).also{
                it.putExtra("ITEM", intent?.getStringExtra("ITEM"))
//                it.putExtra("ITEM_AMOUNT", intent?.getStringExtra("ITEM_AMOUNT").toString())
//                it.putExtra("ITEM_PRICE", intent?.getStringExtra("ITEM_PRICE").toString())
                it.action = NotificationService.Actions.START.toString()
                context?.startService(it)
            }
//            val name = intent.getStringExtra("ITEM_NAME").toString()
//            val amount = intent.getStringExtra("ITEM_AMOUNT").toString()
//            val price = intent.getStringExtra("ITEM_PRICE").toString()
//            Log.d("Broadcast_received_extra","item name:$name" +
//                    "\namount: $amount" +
//                    "\nprice: $price")
        }
    }

//    private fun checkPermission(context: Context?): Boolean {
//        // Check if the calling application has the required permission
//        // Replace "com.example.application2.CUSTOM_PERMISSION" with your permission name
//        return context?.checkCallingPermission("CUSTOM_PERMISSION") == PackageManager.PERMISSION_GRANTED
//    }
}