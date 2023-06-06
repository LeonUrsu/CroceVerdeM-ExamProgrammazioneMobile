package com.example.croceverdeplus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ProfiloCentralinista : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_profilo_centralinista, container, false)
        return root
    }

    fun exit_function(act: Activity){
        val intent = Intent(act, MainActivity::class.java)
        startActivity(intent)
    }

    fun set_info_centralinista(my_root : View, nome : String, cognome : String, data_di_nascita : String){
        my_root.findViewById<TextView>(R.id.nome_text_centralinista).setText(nome)
        my_root.findViewById<TextView>(R.id.cognome_text_centralinista).setText(cognome)
        my_root.findViewById<TextView>(R.id.data_di_nascita_text_centralinista).setText(data_di_nascita) //TODO controllate type se String o Datetime
    }
}