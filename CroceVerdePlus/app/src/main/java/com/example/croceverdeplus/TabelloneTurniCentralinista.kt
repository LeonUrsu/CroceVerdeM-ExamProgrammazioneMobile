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

        //TODO questo metodo serve per rilevare i nomi dei militi che andranno ad essere registrati nel turno scelto, la rilevazione deve essere effettuata tramite il metodo sottostante oppure tramite altro metodo tramite il database


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
        val milite = root.findViewById<Spinner>(R.id.milite_input).getSelectedItem().toString()
        var id_string = TabelloneTurni().rileva_valori_spinner(
            root,
            TabelloneTurni().tipo_settimana(vf_centralinista)
        )
        val id_turno = TabelloneTurni().id_int_val_builder(
            id_string,
            resources,
            requireContext().packageName,
            root
        )
        var tabella = ""
        if (id_string.contains("tabella118h24")) {
            tabella = "tabella_118_h24"
        } else tabella = "tabella_118"
        Database().segna_o_cancella_milite_dal_turno(tabella, id_string, milite)
        /*
        val washingtonRef = db.collection("cities").document("DC")
        // Set the "isCapital" field of the city 'DC'
        washingtonRef
            .update("capital", true)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
*/
        //TODO prima di invocare il seguente metodo bisogna verificare se è presente qualcuno nel turno che è stato selezionato e quindi cambiare il testo del Button in cancella_milite
        //resources.getIdentifier(id, "id", requireContext().packageName // TODO questa riga serwve per trovare la casella tramite nonme della stringa7 e modificare il nome al suo interno
        //var risultato : Boolean = Database().segna(id_casella, nomeCognome_val)
        //if (risultato == false) Database().cancella_milite_nel_turno(id_casella, nomeCognome_val)
        //TODO al Milite che viene segnato o cancellato bisogna aggiungere o togliere le ore di lavoro per le statistiche
        //TODO if nella tabella nella casella segnata è già registrato il milite, cambia il testo del "Button" e cancella da DB il milite della casella
        //TODO else nella tabella nella casella segnata non è segnato nessun milite segna il milite nel DB ovviamente il controllo va fatto sulla tabella del DB
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


}
