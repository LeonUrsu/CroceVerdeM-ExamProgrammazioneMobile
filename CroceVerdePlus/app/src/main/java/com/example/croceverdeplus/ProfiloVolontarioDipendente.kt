package com.example.croceverdeplus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

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

    /*
    Metodo per settare il nome e cognome e data di nascita del milite nel suo profilo
     */
    fun setta_info_profilo(root : View){
        //TODO implementare il metodo per ricevere informazioni e settarle
        //TODO implementare le statistiche del volontario
        val nome = "nome"
        val cognome = "cognome"
        val datadinacita = "11/09/2012"
        root.findViewById<TextView>(R.id.cognome_text_profilo_volontario).setText(nome)
        root.findViewById<TextView>(R.id.cognome_text_profilo_volontario).setText(cognome)
        root.findViewById<TextView>(R.id.data_di_nascita_text_profilo_volontario).setText(datadinacita)

    }

    fun exit_function(act: Activity){
        val intent = Intent(act, MainActivity::class.java)
        startActivity(intent)
    }
}