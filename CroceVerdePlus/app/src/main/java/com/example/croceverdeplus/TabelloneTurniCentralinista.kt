package com.example.croceverdeplus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment


class TabelloneTurniCentralinista : Fragment() {
    var arrayMiliti = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
            inflater.inflate(R.layout.fragment_tabellone_turni_centralinista, container, false)
        val vf_centralinista = root.findViewById(R.id.vf) as ViewFlipper
        vf_centralinista.displayedChild = 2

        Database().popula_spinner_militi(root, requireActivity())
        TabelloneTurni().setta_settiamna_118_h24(root)
        TabelloneTurni().setta_settiamna_118(root)


        //setta_tabelle_con_militi()
        val segna_cancella_btn = root.findViewById(R.id.segna_cancella_btn) as Button
        segna_cancella_btn.setOnClickListener {


            segna_cancella_btn_function(root, vf_centralinista)

            Toast.makeText(requireActivity(), "Segnato", Toast.LENGTH_SHORT).show()

        }

        val settimana_n_btn = root.findViewById(R.id.settimana_n) as Button
        settimana_n_btn.setOnClickListener {
            vf_centralinista.displayedChild = 1
            Toast.makeText(requireActivity(), "Settimana cambiata", Toast.LENGTH_SHORT).show()
        }


        val settimana_n_plus_btn = root.findViewById(R.id.settimana_n_plus_1) as Button
        settimana_n_plus_btn.setOnClickListener {
            vf_centralinista.displayedChild = 2
            Toast.makeText(requireActivity(), "Settimana cambiata", Toast.LENGTH_SHORT).show()
        }

        return root
    }


    /*
    Metodo per segnare o cancellare un milite dal turno nel database
     */
    fun segna_cancella_btn_function(root: View, vf_centralinista: ViewFlipper) {
        //TODO bisogna settare lo spinner che faccia vedere solamente i militi conb il grado adatt a svolgere il turno selezionato
        val milite = root.findViewById<Spinner>(R.id.milite_input).selectedItem.toString()
        var id_string = TabelloneTurni().rileva_valori_spinner(
            root,
            TabelloneTurni().tipo_settimana(vf_centralinista)
        )
        var tabella = ""
        if (id_string.contains("tabella118h24")) {
            tabella = "tabella_118_h24"
        } else tabella = "tabella_118"
        Database().segna_o_cancella_milite_dal_turno(tabella, id_string, milite)
        TabelloneTurni().setta_settiamna_118_h24(root)
        TabelloneTurni().setta_settiamna_118(root)
    }


    /*
    Metodo per gestire lo spinner con i militi inseriti
     */
    fun gestione_spinner_con_militi(
        spinner: Spinner,
        root: View,
        servizio_val: Int,
        giorno_val: Int,
        orario_val: Int,
        grado_val: Int
    ) {
        val array_ricevuto: ArrayList<Milite> = Database().ricevi_array_militi()
        var array_fltrati = filtra_militi(array_ricevuto, servizio_val, giorno_val)
        val array_militi_string = militi_list_to_string_list(array_fltrati)
        //popula_spinner_militi(root, array_militi_string)
    }

    /*
    Metodo per avere una lista di nomi da una lista di militi
    */
    fun militi_list_to_string_list(vecchia: MutableList<Milite>): Array<String> {
        var array = mutableListOf<String>()
        vecchia.forEach { array.add(it.cognomeNomeSpinner.toString()) }
        return array.toTypedArray()
    }

    /*
    Metodo per filtrare l'array completo di militi in base al grado del servizio selezionato
    */
    fun filtra_militi(
        array: MutableList<Milite>,
        servizio_val: Int,
        grado_val: Int
    ): MutableList<Milite> {
        var grado118prima = true
        var grado118seconda = true
        var grado118terza = true
        var gradoh24prima = true
        var gradoh24seconda = true
        var gradoh24terza = true
        if (grado_val == 1) {
            grado118prima = false
            grado118seconda = false
            grado118terza = false
        }
        if (servizio_val == 1) {
            gradoh24prima = false
        }
        if (servizio_val == 2) {
            gradoh24prima = false
            gradoh24seconda = false
        }
        array.forEach {
            if (it.grado118prima == grado118prima
                && it.grado118seconda == grado118seconda
                && it.grado118terza == grado118terza
                && it.gradoh24prima == gradoh24prima
                && it.gradoh24seconda == gradoh24seconda
                && it.gradoh24terza == gradoh24terza
            ) else {
                array.remove(it)
            }
        }
        return array
    }


}
