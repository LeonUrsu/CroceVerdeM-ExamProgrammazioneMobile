package com.example.croceverdeplus

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivityAmministratore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity_amministratore)


        //val navController = this.findNavController(R.id.nav_host_fragment)


        /*val buttonAccedi: Button = findViewById(R.id.button8)

        buttonAccedi.setOnClickListener {
            val newFragment = GestioneTuttiCentralinisti()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, newFragment)
                .commit()

        }


         */

    }
}