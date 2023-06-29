package com.example.croceverdeplus

import android.app.Activity
import android.content.ContentValues
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject


class TabelloneTurniVolontario : Fragment() {

    var cognomeNomeSpinner = "" //TODO passare il nome dal login al main al tabellone


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
            inflater.inflate(R.layout.fragment_tabellone_turni_volontario, container, false)
        val vf_volontario = root.findViewById(R.id.vf_volontario) as ViewFlipper
        val numero_tabella = 1 //118 quindi false
        vf_volontario.displayedChild = numero_tabella

        TabelloneTurni().setta_settiamna_118_h24(root)
        TabelloneTurni().setta_settiamna_118(root)

        val segnami_cancellami_btn = root.findViewById(R.id.segna_cancella_btn) as Button
        segnami_cancellami_btn.setOnClickListener {
            Toast.makeText(requireActivity(), "segnato ", Toast.LENGTH_SHORT).show()
            segnami_cancellami_btn_function(root, vf_volontario)
        }


        val disponibilita_btn = root.findViewById(R.id.disponibilita_btn) as Button
        disponibilita_btn.setOnClickListener {
            Toast.makeText(requireActivity(), "disponibilità assegnata", Toast.LENGTH_SHORT).show()
            disponibilita_btn_function(root, vf_volontario) //TODO (da fare disponibilità btn)
            //TODO per la disponibilita potei fare una lista separata e il nome del milite disponibile si colora diverde o blu nella lista dei militi del centralinista
        }


        val settimana_n_btn = root.findViewById(R.id.settimana_n) as Button
        settimana_n_btn.setOnClickListener {
            vf_volontario.displayedChild = 1
            Toast.makeText(requireActivity(), "Settimana cambiata", Toast.LENGTH_SHORT).show()
        }


        val settimana_n_plus_btn = root.findViewById(R.id.settimana_n_plus_1) as Button
        settimana_n_plus_btn.setOnClickListener {
            vf_volontario.displayedChild = 2
            Toast.makeText(requireActivity(), "Settimana cambiata", Toast.LENGTH_SHORT).show()
        }

        return root
    }


    /*
    Metodo per settare il nome del bottone
     */
    fun setta_nome_bottone(root: View, id_view: Int, settimana: Tabella118h24?, ordine: String) {
        var data_lunedi_settimana1: Timestamp? = null
        var data_lunedi_settimana2: Timestamp? = null
        var time: Timestamp? = null
        if (settimana != null) {
            if (ordine == "n+1") {
                var risultato = data_lunedi_settimana1?.compareTo(data_lunedi_settimana2)
                if (risultato != null) {
                    if (risultato >= 0)
                        time = data_lunedi_settimana2
                }
            } else if (ordine == "n") {
                var risultato = data_lunedi_settimana1?.compareTo(data_lunedi_settimana2)
                if (risultato != null) {
                    if (risultato <= 0)
                        time = data_lunedi_settimana1
                }
                val cal: Calendar = Calendar.getInstance()
                //time.time
                if (time != null) {
                    cal.timeInMillis = time.seconds
                }
                var text =
                    cal.get(Calendar.DAY_OF_MONTH).toString() + cal.get(Calendar.MONTH).toString()
                root.findViewById<TextView>(id_view).text = text
            }
        }
    }


    /*
    Metodo per fornire la propria disponibilità per effettaure il turno
    */
    fun disponibilita_btn_function(root: View, vf_volontario: ViewFlipper) {
        var id_string = TabelloneTurni().rileva_valori_spinner(
            root,
            TabelloneTurni().tipo_settimana(vf_volontario)
        )
        val id_trovato = TabelloneTurni().id_int_val_builder(
            id_string,
            resources,
            requireContext().packageName,
            root
        )
        //TODO al click bisogna che il sistema mandi nel database i dati
    }


    /*
    Metodo per far funzionare il pulsante di essere segnati o cancellare da un turno
    id_passed = id passato per il turno in questione
    */
    fun segnami_cancellami_btn_function(root: View, vf_volontario: ViewFlipper) {
        var id_string = TabelloneTurni().rileva_valori_spinner(
            root,
            TabelloneTurni().tipo_settimana(vf_volontario)
        )
        val id_trovato = TabelloneTurni().id_int_val_builder(
            id_string,
            resources,
            requireContext().packageName,
            root
        )
        var milite = cognomeNomeSpinner
        var tabella = ""
        if (id_string.contains("tabella118h24")) {
            tabella = "tabella_118_h24"
        } else tabella = "tabella_118"
        Database().segna_o_cancella_milite_dal_turno(tabella, id_string, milite)
        root.findViewById<TextView>(id_trovato).setText(cognomeNomeSpinner)
    }


}
