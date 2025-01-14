package com.example.croceverdeplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ViewFlipper

class TabelloneTurniDipendente : Fragment() {

    var tipo_settimana : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tabellone_turni_dipendente, container, false)
        val vf_dipendente = root.findViewById(R.id.vf_dipendente) as ViewFlipper

        //segue l'aggiornamento in tempo reale dei dati delle tabelle dal firebase db
        val db = Database()
        db.aggiorna_tabella_118_h24_in_tempo_reale(root)
        db.aggiorna_tabella_118_in_tempo_reale(root)

        val settimana_n_btn = root.findViewById(R.id.settimana_n) as Button
        settimana_n_btn.setOnClickListener {
            vf_dipendente.displayedChild = 1
            tipo_settimana = 1
        }

        val settimana_n_plus_btn = root.findViewById(R.id.settimana_n_plus_1) as Button
        settimana_n_plus_btn.setOnClickListener {
            vf_dipendente.displayedChild = 2
            tipo_settimana = 2
        }
        return root
    }

}