package com.example.croceverdeplus

import android.content.res.Resources
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.core.content.ContentProviderCompat.requireContext

class TabelloneTurni {

    /*
   Metodo per stabilire se il primo pulsante corrisponde alla settimana solo 118 oppure 118/H24
    */
    fun setta_settimana_corrente(): Int {
        //TODO Prendi le informazione dal db dell'admin della settimana h24 e 118 le informazioni devono essere due oggetti: prima settimana, seconda settimana
        //TODO prima settimana = possiede il valore "tipologia" che dice che è h24/118
        //TODO seconda settimana = possiede il valore "tipologia" che dice che è 118
        var settimana_solo_118 = true //true or false : info rilevate dal DB
        var val_return = if (settimana_solo_118) 1 else 2
        return val_return
    }


    /*
    Metodo per rilevarela posizione del valore dello  spinner selezionato nel
    TabelloneTurniCentralinista una volta selezionato il valore viene ritornato il valore dello
    spinner tramite il valore ret_posizion
    */
    fun rileva_valori_spinner(
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

    /*
    Metodo per la costruzione dell'id per riempire la tabella nella schermata del tabellone dei turni
    ogni id corrispone al luogo di posizionamento nella tabella
     */
    fun id_builder(
        servizio_val: Int,
        giorno_val: Int,
        orario_val: Int,
        grado_val: Int
    ): String {
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
    Metodo per trovare dalla stringa il numero nella risorsa da segnare
     */
    fun id_int_val_builder(
        resources: Resources,
        servizio_val: Int,
        giorno_val: Int,
        orario_val: Int,
        grado_val: Int, context: String
    ) :Int {
        var id_string = TabelloneTurni().id_builder(servizio_val, giorno_val, orario_val, grado_val)
        val res = resources
        val id_trovato = res.getIdentifier(id_string, "id", context)
        return id_trovato
    }

}