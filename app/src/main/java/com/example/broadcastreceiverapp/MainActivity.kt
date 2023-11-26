package com.example.broadcastreceiverapp

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.broadcastreceiverapp.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private val itemAddedReceiver = ItemAddedReceiver()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerReceiver(itemAddedReceiver, IntentFilter("ITEM_ADDED_TO_SHOPPING_LIST"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(itemAddedReceiver)
        Intent(applicationContext, NotificationService::class.java).also{
            it.action = NotificationService.Actions.STOP.toString()
            startService(it)
        }
        Log.d("Notification_service","Service is stopping...")
    }
}