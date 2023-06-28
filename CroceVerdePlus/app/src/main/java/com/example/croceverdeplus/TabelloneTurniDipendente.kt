package com.example.croceverdeplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.ViewFlipper

class TabelloneTurniDipendente : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tabellone_turni_dipendente, container, false)
        val vf_dipendente = root.findViewById(R.id.vf_dipendente) as ViewFlipper
        val settimana_n_btn = root.findViewById(R.id.settimana_n) as Button
        settimana_n_btn.setOnClickListener {
            val tipo_settimana = false // TODO : valore ricevuto dallle settimana ricevute dal DB
            vf_dipendente.setDisplayedChild(TabelloneTurni().setta_settimana_corrente(tipo_settimana))
            TabelloneTurni().tipo_settimana(tipo_settimana)
            Toast.makeText(requireActivity(), "Settimana cambiata", Toast.LENGTH_SHORT).show()
        }
        val settimana_n_plus_btn = root.findViewById(R.id.settimana_n_plus_1) as Button
        settimana_n_plus_btn.setOnClickListener {
            val tipo_settimana = false // TODO : valore ricevuto dallle settimana ricevute dal DB
            vf_dipendente.setDisplayedChild(TabelloneTurni().setta_settimana_corrente(tipo_settimana))
            TabelloneTurni().tipo_settimana(tipo_settimana)
            Toast.makeText(requireActivity(), "Settimana cambiata", Toast.LENGTH_SHORT).show()
        }
        return root
    }

}