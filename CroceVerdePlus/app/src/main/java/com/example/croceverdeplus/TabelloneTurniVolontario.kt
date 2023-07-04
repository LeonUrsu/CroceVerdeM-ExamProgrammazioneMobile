package com.example.croceverdeplus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment

class TabelloneTurniVolontario(val cognomeNomeSpinner: String) : Fragment() {

    val bundle = arguments
    var tipo_tabella: Int = 0
    var nome_tipo_tabella: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
            inflater.inflate(R.layout.fragment_tabellone_turni_volontario, container, false)
        val vf_volontario = root.findViewById(R.id.vf_volontario) as ViewFlipper
        val numero_tabella = 1
        vf_volontario.displayedChild = numero_tabella
        nome_tipo_tabella = "tabella_118"

        val spinner_servizio = root.findViewById<Spinner>(R.id.servizio_input_centralinista)
        spinner_servizio.setEnabled(false)
        TabelloneTurni().setta_settiamna_118_h24(root)
        TabelloneTurni().setta_settiamna_118(root)

        val segnami_cancellami_btn = root.findViewById(R.id.segna_cancella_btn) as Button
        segnami_cancellami_btn.setOnClickListener {
            segnami_cancellami_btn_function(root)
            Toast.makeText(requireActivity(), "segnato", Toast.LENGTH_SHORT).show()
        }

        val disponibilita_btn = root.findViewById(R.id.disponibilita_btn) as Button
        disponibilita_btn.setOnClickListener {
            Database().disponibilita_btn(cognomeNomeSpinner, root, nome_tipo_tabella)
            Toast.makeText(requireActivity(), "disponibilità assegnata", Toast.LENGTH_SHORT).show()
        }


        val settimana_n_btn = root.findViewById(R.id.settimana_n) as Button
        settimana_n_btn.setOnClickListener {
            vf_volontario.displayedChild = 1
            tipo_tabella = 1
            nome_tipo_tabella = "tabella_118"
            spinner_servizio.setEnabled(false)

        }


        val settimana_n_plus_btn = root.findViewById(R.id.settimana_n_plus_1) as Button
        settimana_n_plus_btn.setOnClickListener {
            vf_volontario.displayedChild = 2
            tipo_tabella = 2
            nome_tipo_tabella = "tabella_118_h24"
            spinner_servizio.setEnabled(true)

        }
        return root
    }

    /*
    Metodo per far funzionare il pulsante di essere segnati o cancellare da un turno
    id_passed = id passato per il turno in questione
    */
    fun segnami_cancellami_btn_function(root: View) {
        var id_turno = TabelloneTurni().rileva_valori_spinner(root)
        Database().segna_o_cancella_milite_dal_turno_volontario(
            nome_tipo_tabella,
            id_turno,
            cognomeNomeSpinner, root
        )
        TabelloneTurni().setta_settiamna_118_h24(root)
        TabelloneTurni().setta_settiamna_118(root)
    }


}
