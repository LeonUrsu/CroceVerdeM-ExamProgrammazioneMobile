package com.example.croceverdeplus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment

class TabelloneTurniCentralinista : Fragment() {
    var arrayMiliti = null
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
        var servizio_val =
            TabelloneTurni().rileva_valori_spinner(root.findViewById(R.id.servizio_input))
        var orario_val =
            TabelloneTurni().rileva_valori_spinner(root.findViewById(R.id.orario_input))
        var giorno_val =
            TabelloneTurni().rileva_valori_spinner(root.findViewById(R.id.giorno_input))
        var grado_val = TabelloneTurni().rileva_valori_spinner(root.findViewById(R.id.grado_input))
        var milite_val = gestione_spinner_con_militi(
            root.findViewById(R.id.milite_input),
            root,
            servizio_val,
            giorno_val,
            orario_val,
            grado_val
        )

        val segna_cancella_btn = root.findViewById(R.id.segna_cancella_btn) as Button
        segna_cancella_btn.setOnClickListener {
            var id = TabelloneTurni().id_builder(servizio_val, giorno_val, orario_val, grado_val)
            segna_cancella_btn_function(id)
            Toast.makeText(requireActivity(), "Segnato", Toast.LENGTH_SHORT).show()
        }
        val settimana_n_btn = root.findViewById(R.id.settimana_n) as Button
        settimana_n_btn.setOnClickListener {
            vf_centralinista.setDisplayedChild(TabelloneTurni().setta_settimana_corrente(false))
            val tipo_settimana = false // TODO : valore ricevuto dallle settimana ricevute dal DB
            vf_centralinista.setDisplayedChild(
                TabelloneTurni().setta_settimana_corrente(
                    tipo_settimana
                )
            )
            TabelloneTurni().tipo_settimana(tipo_settimana, root)
            Toast.makeText(requireActivity(), "Settimana cambiata", Toast.LENGTH_SHORT).show()
        }
        val settimana_n_plus_btn = root.findViewById(R.id.settimana_n_plus_1) as Button
        settimana_n_plus_btn.setOnClickListener {
            vf_centralinista.setDisplayedChild(TabelloneTurni().setta_settimana_corrente(true))
            val tipo_settimana = false // TODO : valore ricevuto dallle settimana ricevute dal DB
            vf_centralinista.setDisplayedChild(
                TabelloneTurni().setta_settimana_corrente(tipo_settimana)
            )
            TabelloneTurni().tipo_settimana(tipo_settimana, root)
            Toast.makeText(requireActivity(), "Settimana cambiata", Toast.LENGTH_SHORT).show()
        }
        return root
    }

    fun segna_cancella_btn_function(id: String) {
        //TODO al click bisogna che il sistema mandi nel database i dati della registrazione e aggiorni l tabella dei militi
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
        popula_spinner_militi(root, array_militi_string)
    }

    /*
    Metodo per avere una lista di nomi da una lista di militi
    */
    fun militi_list_to_string_list(vecchia: MutableList<Milite>): Array<String> {
        var array = mutableListOf<String>()
        vecchia.forEach { array.add(it.nomeCognomeSpinner) }
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
        //TODO forse il DB deve registrare i militi in tabelle diverse a seconda del grado
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

    /*
    Metodo per polulare lo spinner dei militi per segnarli sul tabellone
     */
    fun popula_spinner_militi(root: View, militi_array: Array<String>) {
        val gameKindArray: ArrayAdapter<String> =
            ArrayAdapter<String>(
                requireActivity(),
                android.R.layout.simple_spinner_item,
                militi_array
            )
        gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val convert_from_spinner: Spinner = root.findViewById(R.id.milite_input)
        convert_from_spinner.setAdapter(gameKindArray)
    }

}
