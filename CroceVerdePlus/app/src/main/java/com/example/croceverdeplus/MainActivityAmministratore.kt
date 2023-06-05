package com.example.croceverdeplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivityAmministratore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity_amministratore)
    }

    fun esci_btn(){
        MainActivityCentralinista().back_function()
    }
}
