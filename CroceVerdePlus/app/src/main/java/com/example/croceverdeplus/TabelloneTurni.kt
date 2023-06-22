package com.example.croceverdeplus

import android.content.res.Resources
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import android.widget.ViewFlipper

class TabelloneTurni {

    /*
   Metodo per stabilire se il primo pulsante corrisponde alla settimana solo 118 oppure 118/H24
   si passa il valore booleano per capire che tipo di settiaman serve in quel caso
   settiamna = True -> sta per H24/118
   settiamna = False -> sta per 118
    */
    fun setta_settimana_corrente(settimana: Boolean): Int {
        //TODO Prendi le informazione dal db dell'admin della settimana h24 e 118 le informazioni devono essere due oggetti: prima settimana, seconda settimana
        //TODO prima settimana = possiede il valore "tipologia" che dice che è h24/118
        //TODO seconda settimana = possiede il valore "tipologia" che dice che è 118
        return if (settimana) 1 else 2
    }

    /*
    Metodo per rilevarela posizione del valore dello  spinner selezionato nel
    TabelloneTurniCentralinista una volta selezionato il valore viene ritornato il valore dello
    spinner tramite il valore ret_posizion
    */
    fun rileva_valori_spinner(dropdown: Spinner?
    ): Int {
        var ret_position = 0
        dropdown?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long
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
        servizio_val: Int, giorno_val: Int, orario_val: Int, grado_val: Int
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
        grado_val: Int,
        context: String
    ): Int {
        var id_string = TabelloneTurni().id_builder(servizio_val, giorno_val, orario_val, grado_val)
        val res = resources
        val id_trovato = res.getIdentifier(id_string, "id", context)
        return id_trovato
    }

    /*
    Metodo per settare le date su una tabella
    la cosa ricevuta e un oggetto di configurazione da li si estraggono le date
    la tabella con 118 corrisponde al valore true
     */
    fun setta_date_tabelloni_h24_118(tabella: Tabella, root: View) {
        (root.findViewById(R.id.lunedi118h24) as TextView).setText(tabella.lunedi.toString())
        (root.findViewById(R.id.martedi118h24) as TextView).setText(tabella.martedi.toString())
        (root.findViewById(R.id.mercoledi118h24) as TextView).setText(tabella.mercoledi.toString())
        (root.findViewById(R.id.giovedi118h24) as TextView).setText(tabella.giovedi.toString())
        (root.findViewById(R.id.venerdi118h24) as TextView).setText(tabella.venerdi.toString())
        (root.findViewById(R.id.sabato118h24) as TextView).setText(tabella.sabato.toString())
        (root.findViewById(R.id.domenica118h24) as TextView).setText(tabella.domenica.toString())
    }

    /*
    Metodo per settare le date su una tabella
    la cosa ricevuta e un oggetto di configurazione da li si estraggono le date
    la tabella con 118 corrisponde al valore false
    */
    fun setta_date_tabelloni_118(tabella: Tabella, root: View) {
        (root.findViewById(R.id.lunedi118) as TextView).setText(tabella.lunedi.toString())
        (root.findViewById(R.id.martedi118) as TextView).setText(tabella.martedi.toString())
        (root.findViewById(R.id.mercoledi118) as TextView).setText(tabella.mercoledi.toString())
        (root.findViewById(R.id.giovedi118) as TextView).setText(tabella.giovedi.toString())
        (root.findViewById(R.id.venerdi118) as TextView).setText(tabella.venerdi.toString())
        (root.findViewById(R.id.sabato118) as TextView).setText(tabella.sabato.toString())
        (root.findViewById(R.id.domenica118) as TextView).setText(tabella.domenica.toString())
    }

    /*
Metodo per decidere il tipo di riuempimento di date in bse al tipo della settimana passato
 */
    fun tipo_settimana(tipo_settimana: Boolean, root: View){
        if (tipo_settimana) TabelloneTurni().setta_date_tabelloni_h24_118(
            Tabella(),
            root
        ) else TabelloneTurni().setta_date_tabelloni_118(
            Tabella(),
            root
        ) // TODO : obj due oggetto tabellone vanno settate le date
    }

}