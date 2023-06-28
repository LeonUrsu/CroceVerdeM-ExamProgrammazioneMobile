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
    var settimana1: Tabella118h24? = null
    var data_lunedi_settimana1: Timestamp? = null
    var settimana2: Tabella118h24? = null
    var data_lunedi_settimana2: Timestamp? = null


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
            //Toast.makeText(requireActivity(), Database().init_var(), Toast.LENGTH_SHORT).show()
            //segnami_cancellami_btn_function(id_trovato, root)
            //todo(da fare segna cancella)
        }
        setta_settiamna_118_h24(root)
        setta_settiamna_118(root)
        val disponibilita_btn = root.findViewById(R.id.disponibilita_btn) as Button
        disponibilita_btn.setOnClickListener {
            var id = TabelloneTurni().id_builder(servizio_val, giorno_val, orario_val, grado_val)
            //disponibilita_btn_function(id)


            //Toast.makeText(requireActivity(), ritorno.toString(), Toast.LENGTH_SHORT).show()
        }
        val settimana_n_btn = root.findViewById(R.id.settimana_n) as Button
        settimana_n_btn.setOnClickListener {
            /*
            var temp_tipo_settimana = settimana1?.tipo_settimana
            if (temp_tipo_settimana != null) vf_volontario.setDisplayedChild(
                TabelloneTurni().tipo_settimana(
                    temp_tipo_settimana
                )
            )
            else

             */
            vf_volontario.setDisplayedChild(1)
            //TODO (setta settimana con date)
        }
        val settimana_n_plus_btn = root.findViewById(R.id.settimana_n_plus_1) as Button
        settimana_n_plus_btn.setOnClickListener {
            /*
            var temp_tipo_settimana = settimana2?.tipo_settimana
            if (temp_tipo_settimana != null) vf_volontario.setDisplayedChild(
                TabelloneTurni().tipo_settimana(
                    temp_tipo_settimana
                )
            )
            else
            */
            vf_volontario.setDisplayedChild(2)
            //TODO (setta settimana con date)
        }
        return root
    }

    fun temp(root: View, activity: Activity, tabella: Tabella118?){
        Toast.makeText(requireActivity(), tabella!!.turno_118_dom_mat_1, Toast.LENGTH_SHORT).show()
    }

    /*
    Metodo Per settare la tabella 118
     */
    fun setta_settiamna_118(root: View){
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("tabelle").document("tabella_118")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                    var documento = document.toObject<Tabella118>()
                    TabelloneTurni().setta_info_tabella_118(root, documento)
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
    }

    /*
    Metodo Per settare la tabella 118
     */
    fun setta_settiamna_118_h24(root: View){
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("tabelle").document("tabella_118_h24")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                    var documento = document.toObject<Tabella118h24>()
                    TabelloneTurni().setta_info_tabella_118_h24(root, documento)
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
    }

    fun setta_nome_bottone(root: View, id_view: Int, settimana: Tabella118h24?, ordine: String) {
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
                    cal.setTimeInMillis(time.seconds)
                }
                var text =
                    cal.get(Calendar.DAY_OF_MONTH).toString() + cal.get(Calendar.MONTH).toString()
                root.findViewById<TextView>(id_view).setText(text)
            }
        }
    }


    /*
Metodo per fornire la propria disponibilità per effettaure il turno
 */
    fun disponibilita_btn_function(id: String) {
        //TODO al click bisogna che il sistema mandi nel database i dati
    }

    /*
Metodo per trovare la settimana con data minore
 */


    /*
    Metodo per far fiunzionare il pulsante della cancellazione
    */
    fun segnami_cancellami_btn_function(id_passed: Int, root: View) {
        //TODO al click bisogna che il sistema mandi nel database i dati della registrazione e aggiorni la tabella dei militi
        var textView = root.findViewById<TextView>(id_passed)
        //TODO bisogna anche che permetta di cancellarsi se è il tunro in cui si vuole cancellare
    }


}
