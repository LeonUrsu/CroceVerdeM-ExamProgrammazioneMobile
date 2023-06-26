package com.example.croceverdeplus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot


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
        val numero_tabella =
            TabelloneTurni().setta_settimana_corrente(true) // TODO : valore booleano ricevuto dal DB
        vf_volontario.setDisplayedChild(numero_tabella) //TODO : qui si cambia settimana H24/118 & 118, si passa il valore 1 o 2 una volta implementato il metodo di scelta in amministratore
        //TODO : si deve implementare il modo per far vedere la lista dei volontari disponibili ne tabellone turni tramite richiesta al database
        var servizio_val =
            TabelloneTurni().rileva_valori_spinner(root.findViewById(R.id.servizio_input))
        var orario_val =
            TabelloneTurni().rileva_valori_spinner(root.findViewById(R.id.orario_input))
        var giorno_val =
            TabelloneTurni().rileva_valori_spinner(root.findViewById(R.id.giorno_input))
        var grado_val = TabelloneTurni().rileva_valori_spinner(root.findViewById(R.id.grado_input))
        /*TODO questo metodo serve per rilevare i nomi dei militi che andranno ad essere registrati
           nel turno scelto, la rilevazione deve essere effettuata tramite il metodo sottostante oppure tramite altro metodo tramite il database
        var nome_val = rileva_valori_spinner(
            root,
            R.id.grado_input,
            R.array.nome_input_array,
            root.findViewById(R.id.nome_input)
         */

        val segnami_cancellami_btn = root.findViewById(R.id.segna_cancella_btn) as Button
        segnami_cancellami_btn.setOnClickListener {
            val id_trovato = TabelloneTurni().id_int_val_builder(
                resources,
                servizio_val,
                giorno_val,
                orario_val,
                grado_val,
                requireContext().packageName
            )
            segnami_cancellami_btn_function(id_trovato, root)
            //todo(da fare segna cancella)
        }
        val disponibilita_btn = root.findViewById(R.id.disponibilita_btn) as Button
        disponibilita_btn.setOnClickListener {
            var id = TabelloneTurni().id_builder(servizio_val, giorno_val, orario_val, grado_val)
            disponibilita_btn_function(id)
            Toast.makeText(requireActivity(), "Disponibilità assegnata", Toast.LENGTH_SHORT).show()
        }
        val settimana_n_btn = root.findViewById(R.id.settimana_n) as Button
        settimana_n_btn.setOnClickListener {
            val tipo_settimana = true // TODO : valore ricevuto dallle settimana ricevute dal DB
            vf_volontario.setDisplayedChild(TabelloneTurni().setta_settimana_corrente(tipo_settimana))
            TabelloneTurni().tipo_settimana(tipo_settimana, root)
        }
        val settimana_n_plus_btn = root.findViewById(R.id.settimana_n_plus_1) as Button
        settimana_n_plus_btn.setOnClickListener {
            val tipo_settimana = false // TODO : valore ricevuto dallle settimana ricevute dal DB
            vf_volontario.setDisplayedChild(TabelloneTurni().setta_settimana_corrente(tipo_settimana))
            TabelloneTurni().tipo_settimana(tipo_settimana, root)
        }
        return root
    }


    /*
    Metodo per fornire la propria disponibilità per effettaure il turno
     */
    fun disponibilita_btn_function(id: String) {
        //TODO al click bisogna che il sistema mandi nel database i dati
    }

    /*
    Metodo per far fiunzionare il pulsante della cancellazione
    */
    fun segnami_cancellami_btn_function(id_passed: Int, root: View) {
        //TODO al click bisogna che il sistema mandi nel database i dati della registrazione e aggiorni la tabella dei militi
        var textView = root.findViewById<TextView>(id_passed)
        //TODO bisogna anche che permetta di cancellarsi se è il tunro in cui si vuole cancellare
    }


}

