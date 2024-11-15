package com.android.drinklist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var drinkMenu : AutoCompleteTextView
    lateinit var price : TextView
    lateinit var submitButton : Button
    val drinkList = listOf("Mango Juice", "Apple Juice","Orange Juice","Kewi Juice")
    val drikmap = mapOf("Mango Juice" to 25 ,"Apple Juice" to 20, "Orange Juice" to 30, "Kewi Juice" to 35)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialization()

        val adapter = ArrayAdapter(this, R.layout.drink_menu, drinkList)
        drinkMenu.setAdapter(adapter)

        drinkMenu.setOnItemClickListener { _, _, _, _ ->
            price.setText(drikmap[drinkMenu.text.toString()].toString())
        }

        submitButton.setOnClickListener {
            val submit = Intent(Intent.ACTION_SENDTO)
            submit.data = Uri.parse("mailto:")
            submit.putExtra(Intent.EXTRA_EMAIL, arrayOf("khmeltazy@gmail.com"))
            submit.putExtra(Intent.EXTRA_SUBJECT,"ORDER")
            submit.putExtra(Intent.EXTRA_TEXT,"I want to drink ${drinkMenu.text.toString()}")
            startActivity(submit)
        }
    }
    private fun initialization(){
        drinkMenu = findViewById(R.id.driklSelectMenu)
        price = findViewById(R.id.price_View)
        submitButton = findViewById(R.id.submitButton)
    }
}