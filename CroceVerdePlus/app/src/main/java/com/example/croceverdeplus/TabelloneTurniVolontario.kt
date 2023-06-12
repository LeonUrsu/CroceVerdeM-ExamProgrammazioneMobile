package com.example.croceverdeplus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment

class TabelloneTurniVolontario : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
            inflater.inflate(R.layout.fragment_tabellone_turni_volontario, container, false)
        val vf_volontario = root.findViewById(R.id.vf_volontario) as ViewFlipper
        vf_volontario.setDisplayedChild(2); //TODO qui si cambia settimana h24/118 & 118, si passa il valore 1 o 2 una volta implementato il metodo di scelta in amministratore
        //TODO si deve implementare il modo per far vedere la lista dei volontari disponibili ne tabellone turni tramite richiesta al database
        var servizio_val = rileva_valori_spinner(
            root,
            R.id.servizio_input,
            R.array.servzio_input_array,
            root.findViewById(R.id.servizio_input)
        )
        var orario_val = rileva_valori_spinner(
            root,
            R.id.orario_input,
            R.array.orario_input_array,
            root.findViewById(R.id.orario_input)
        )
        var giorno_val = rileva_valori_spinner(
            root,
            R.id.giorno_input,
            R.array.giorno_input_array,
            root.findViewById(R.id.giorno_input)
        )
        var grado_val = rileva_valori_spinner(
            root,
            R.id.grado_input,
            R.array.grado_input_array,
            root.findViewById(R.id.grado_input)
        )
        /*TODO questo metodo serve per rilevare i nomi dei militi che andranno ad essere registrati
           nel turno scelto, la rilevazione deve essere effettuata tramite il metodo sottostante oppure tramite altro metodo tramite il database
        var nome_val = rileva_valori_spinner(
            root,
            R.id.grado_input,
            R.array.nome_input_array,
            root.findViewById(R.id.nome_input)
         */
        var nome_val = "Elliot Alderson" // TODO questo nome è temporaneo, serve solamente per provare il funzionamento della tabella
        val segnami_cancellami_btn = root.findViewById(R.id.segna_cancella_btn) as Button
        segnami_cancellami_btn.setOnClickListener {
            var id_builded = id_builder(servizio_val, giorno_val, orario_val, grado_val)
            segnami_cancellami_btn_function(id_builded)
            Toast.makeText(requireActivity(), "Segnato", Toast.LENGTH_SHORT).show()
            var id_string = id_builder(servizio_val, giorno_val, orario_val, grado_val)
            val res = resources
            val id_trovato = res.getIdentifier(id_string, "id", requireContext().packageName)
            segnami_cancellami_btn_function(id_trovato, root, nome_val)
            //TODO per questo medodo serve anche l'inserimento nel database, non solamente nel tabellone
            Toast.makeText(requireActivity(), id_string, Toast.LENGTH_SHORT).show()
        }
        val disponibilita_btn = root.findViewById(R.id.disponibilita_btn) as Button
        disponibilita_btn.setOnClickListener {
            var id = id_builder(servizio_val, giorno_val, orario_val, grado_val)
            disponibilita_btn_function(id)
            Toast.makeText(requireActivity(), "Disponibilità assegnata", Toast.LENGTH_SHORT).show()
        }
        return root
    }

    fun disponibilita_btn_function(id: String){
        //TODO al click bisogna che il sistema mandi nel database i dati
    }

    fun segnami_cancellami_btn_function(id: String) {
        val res = resources
        val id = res.getIdentifier("titleText", "id", requireContext().packageName)
    /*
    Metodo per far fiunzionare il pulsante della cancellazione
     */
    fun segnami_cancellami_btn_function(id_passed: Int, root : View, nome_milite : String) {
        //TODO al click bisogna che il sistema mandi nel database i dati della registrazione e aggiorni l tabella dei militi
        var textView = root.findViewById<TextView>(id_passed)
        //TODO bisogna anche che permetta di cancellarsi se è io tunro in cui si vuole cancellare
        textView.setText(nome_milite)
    }

    /*
    Metodo per la costruzione dell'id per riempire la tabella nella schermata del tabellone dei turni
    ogni id corrispone al luogo di posizionamento nella tabella
     */
    fun id_builder(servizio_val: Int, giorno_val: Int, orario_val: Int, grado_val: Int): String {
        val servizio = when (servizio_val) { //turno_118_mar_mat_3
            0 -> "118"
            1 -> "h24"
            else -> {
                "118"
            }
        }
        val giorno = when (giorno_val) {
            0 -> "lun"
            1 -> "mar"
            2 -> "mer"
            3 -> "gio"
            4 -> "ven"
            5 -> "sab"
            6 -> "dom"
            else -> {
                "lun"
            }
        }
        val orario = when (orario_val) {
            0 -> "mat"
            1 -> "pom"
            2 -> "ser"
            else -> {
                "mat"
            }
        }
        val grado = when (grado_val) {
            0 -> "1"
            1 -> "2"
            2 -> "3"
            else -> {
                "1"
            }
        }
        return "turno" + "_" + servizio + "_" + giorno + "_" + orario + "_" + grado
    }

    /*
    Metodo per rilevarela posizione del valore dello  spinner selezionato nel
    TabelloneTurniCentralinista una volta selezionato il valore viene ritornato il valore dello
    spinner tramite il valore ret_posizion
     */
    private fun rileva_valori_spinner(
        root: View,
        spinner: Int,
        id_array: Int,
        dropdown: Spinner?
    ): Int {
        //var mTestArray = getResources().getStringArray(id_array);
        var ret_position = 0
        //val adapter = ArrayAdapter(requireActivity(), spinner, mTestArray)//TODO due righe non necessarie
        //dropdown?.setAdapter(adapter)
        dropdown?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // TODO qui si deve settare il valore di ritorno
                ret_position = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        })
        return ret_position
    }




}
