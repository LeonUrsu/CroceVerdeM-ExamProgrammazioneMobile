package com.example.croceverdeplus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfiloVolontarioDipendente.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfiloVolontarioDipendente : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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