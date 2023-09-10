package com.example.croceverdeplus

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment

class TabelloneTurniCentralinista : Fragment() {
    var nome_tipo_tabella: String = ""
    var tipo_servizio_filtro = "118"
    var tipo_grado_filtro = "1a"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
            inflater.inflate(R.layout.fragment_tabellone_turni_centralinista, container, false)
        val vf_centralinista = root.findViewById(R.id.vf) as ViewFlipper
        vf_centralinista.displayedChild = 2
        nome_tipo_tabella = "tabella_118_h24"

        //segue l'aggiornamento nella lista dei militi in base al grado del turno che è stato selezionato
        val spinner_servizio: Spinner = root.findViewById(R.id.servizio_input)
        spinner_servizio.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                root.findViewById<Spinner>(R.id.servizio_input)
                if (position == 0)
                    tipo_servizio_filtro = "118"
                else tipo_servizio_filtro = "h24"
                Database().popula_spinner_militi_filtrati(
                    tipo_servizio_filtro,
                    tipo_grado_filtro,
                    root,
                    requireActivity()
                )
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        val spinner_grado = root.findViewById<Spinner>(R.id.grado_input_centralinista)
        spinner_grado.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val pos = when (position) {
                    0 -> "1a"
                    1 -> "2a"
                    2 -> "3a"
                    else -> {
                        "3a"
                    }
                }
                tipo_grado_filtro = pos
                Database().popula_spinner_militi_filtrati(
                    tipo_servizio_filtro,
                    tipo_grado_filtro,
                    root,
                    requireActivity()
                )
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        //segue l'aggiornamento in tempo reale dei dati delle tabelle dal firebase db
        val db = Database()
        db.aggiorna_tabella_118_h24_in_tempo_reale(root)
        db.aggiorna_tabella_118_in_tempo_reale(root)

        val segna_cancella_btn = root.findViewById(R.id.segna_cancella_btn) as Button
        segna_cancella_btn.setOnClickListener {
            segna_cancella_btn_function(root, requireActivity())
        }

        val settimana_1 = root.findViewById(R.id.settimana_n) as Button
        settimana_1.setOnClickListener {
            vf_centralinista.displayedChild = 1
            nome_tipo_tabella = "tabella_118"
            spinner_servizio.isEnabled = false
        }

        val settimana_2 = root.findViewById(R.id.settimana_n_plus_1) as Button
        settimana_2.setOnClickListener {
            vf_centralinista.displayedChild = 2
            nome_tipo_tabella = "tabella_118_h24"
            spinner_servizio.isEnabled = true
        }
        return root
    }

    /*
    Metodo per segnare o cancellare un milite dal turno nel database
     */
    fun segna_cancella_btn_function(root: View, act: Activity) {
        val milite = root.findViewById<Spinner>(R.id.milite_input).selectedItem.toString()
        val id_turno = TabelloneTurni().rileva_valori_spinner(root)
        Database().segna_o_cancella_milite_dal_turno_centralinista(nome_tipo_tabella, id_turno, milite, root, act)
    }
}
