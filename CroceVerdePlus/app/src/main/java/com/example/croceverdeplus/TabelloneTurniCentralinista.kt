package com.example.croceverdeplus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment

class TabelloneTurniCentralinista : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
            inflater.inflate(R.layout.fragment_tabellone_turni_centralinista, container, false)
        val vf_centralinista = root.findViewById(R.id.vf) as ViewFlipper
        vf_centralinista.setDisplayedChild(2); //TODO qui si cambia settimana h24/118 & 118, si passa il valore 1 o 2 una volta implementato il metodo di scelta in amministratore
        //TODO si deve implementare il modo per far vedere la lista dei volontari disponibili ne tabellone turni tramite richiesta al databse

        var servizio_val = TabelloneTurni().rileva_valori_spinner(
            root,
            R.id.servizio_input,
            R.array.servzio_input_array,
            root.findViewById(R.id.servizio_input)
        )
        var orario_val = TabelloneTurni().rileva_valori_spinner(
            root,
            R.id.orario_input,
            R.array.orario_input_array,
            root.findViewById(R.id.orario_input)
        )
        var giorno_val = TabelloneTurni().rileva_valori_spinner(
            root,
            R.id.giorno_input,
            R.array.giorno_input_array,
            root.findViewById(R.id.giorno_input)
        )
        var grado_val = TabelloneTurni().rileva_valori_spinner(
            root,
            R.id.grado_input,
            R.array.grado_input_array,
            root.findViewById(R.id.grado_input)
        )

        val segna_cancella_btn = root.findViewById(R.id.segna_cancella_btn) as Button
        segna_cancella_btn.setOnClickListener {
            var id = TabelloneTurni().id_builder(servizio_val, giorno_val, orario_val, grado_val)
            segna_cancella_btn_function(id)
            Toast.makeText(requireActivity(), "Segnato", Toast.LENGTH_SHORT).show()
        }
        val settimana_n_btn = root.findViewById(R.id.settimana_n) as Button
        settimana_n_btn.setOnClickListener {
            vf_centralinista.setDisplayedChild(1); //TODO qui si cambia settimana H24/118 & 118, si passa il valore 1 o 2 una volta implementato il metodo di scelta in amministratore
            //var id = id_builder(servizio_val, giorno_val, orario_val, grado_val)
            //disponibilita_btn_function(id)
            Toast.makeText(requireActivity(), "Settimana cambiata", Toast.LENGTH_SHORT).show()
        }
        val settimana_n_plus_btn = root.findViewById(R.id.settimana_n_plus_1) as Button
        settimana_n_plus_btn.setOnClickListener {
            vf_centralinista.setDisplayedChild(2); //TODO qui si cambia settimana H24/118 & 118, si passa il valore 1 o 2 una volta implementato il metodo di scelta in amministratore
            //var id = id_builder(servizio_val, giorno_val, orario_val, grado_val)
            //disponibilita_btn_function(id)
            Toast.makeText(requireActivity(), "Settimana cambiata", Toast.LENGTH_SHORT).show()
        }
        return root
    }

    fun segna_cancella_btn_function(id: String) {
        //TODO al click bisogna che il sistema mandi nel database i dati della registrazione e aggiorni l tabella dei militi
    }

}
