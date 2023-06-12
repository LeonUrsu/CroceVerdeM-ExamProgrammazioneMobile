package com.example.croceverdeplus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ProfiloVolontarioDipendente : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_profilo_volontario_dipendente,
            container,
            false
        )
    }

    fun exit_function(act: Activity){
        val intent = Intent(act, MainActivity::class.java)
        startActivity(intent)
    }
}