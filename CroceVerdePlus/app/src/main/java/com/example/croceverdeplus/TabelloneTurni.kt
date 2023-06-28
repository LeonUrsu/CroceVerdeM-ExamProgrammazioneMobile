package com.example.croceverdeplus

import android.app.Activity
import android.content.res.Resources
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class TabelloneTurni {

    /*
   Metodo per stabilire se il primo pulsante corrisponde alla settimana solo 118 oppure 118/H24
   si passa il valore booleano per capire che tipo di settiaman serve in quel caso
   settiamna = True -> sta per H24/118
   settiamna = False -> sta per 118
    */
    fun setta_settimana_corrente(settimana: Boolean): Int {
        // TODO Prendi le informazione dal db dell'admin della settimana h24 e 118 le informazioni devono essere due oggetti: prima settimana, seconda settimana
        // TODO prima settimana = possiede il valore "tipologia" che dice che è h24/118
        // TODO seconda settimana = possiede il valore "tipologia" che dice che è 118
        return if (settimana) 1 else 2
    }

    /*
    Metodo per rilevarela posizione del valore dello  spinner selezionato nel
    TabelloneTurniCentralinista una volta selezionato il valore viene ritornato il valore dello
    spinner tramite il valore ret_posizion
    */
    fun rileva_valori_spinner(
        dropdown: Spinner?
    ): Int {
        var ret_position = 0
        dropdown?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long
            ) {
                // TODO qui si deve settare il valore di ritorno
                ret_position = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
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
    Metodo per decidere il tipo di riempimento di date in base al tipo della settimana ricevuto dal
    metodo, se arriva il tipo solo 118 si attiva "setta_date_tabelloni_118()" mentre se arriva il tipo
    118/h24 si attiva il metodo setta_date_tabelloni_h24_118()"
    */
    fun tipo_settimana(tipo_settimana: Boolean): Int {
        if (tipo_settimana == false) return 1
        else return 2
        //if (tipo_settimana) setta_info_tabella_118_h24(root)
        //else setta_info_tabella_118(root) //TODO : obj due oggetti tabellone vanno settate le date
    }

    fun temp(root: View, activity: Activity, tabella: Tabella118?){
        Toast.makeText(activity, tabella!!.turno_118_dom_mat_1, Toast.LENGTH_SHORT).show()
    }

    /*
    Metodo per settare le date su una tabella
    la cosa ricevuta e un oggetto di configurazione da li si estraggono le date
    la tabella con 118 corrisponde al valore false
    */
    fun setta_info_tabella_118(root: View, tabella: Tabella118?) {
        if (tabella != null) {
            var milli = tabella.data_lunedi!!.seconds * 1000
            val lunedi =
                Instant.ofEpochMilli(milli).atZone(ZoneId.systemDefault()).toLocalDateTime()
            val formatter = DateTimeFormatter.ofPattern("dd/MM")
            root.findViewById<TextView>(R.id.lunedi118).text = "Lunedì "+lunedi.format(formatter)
            root.findViewById<TextView>(R.id.martedi118).text = "Martedì "+lunedi.plusDays(1).format(formatter)
            root.findViewById<TextView>(R.id.mercoledi118).text = "Mercoledì "+lunedi.plusDays(2).format(formatter)
            root.findViewById<TextView>(R.id.giovedi118).text = "Giovedì "+lunedi.plusDays(3).format(formatter)
            root.findViewById<TextView>(R.id.venerdi118).text = "Venerdì "+lunedi.plusDays(4).format(formatter)
            root.findViewById<TextView>(R.id.sabato118).text = "Sabato "+lunedi.plusDays(5).format(formatter)
            root.findViewById<TextView>(R.id.domenica118).text = "Domenica "+lunedi.plusDays(6).format(formatter)

            root.findViewById<TextView>(R.id.tabella118_turno_118_lun_mat_1).text = tabella.turno_118_dom_mat_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_lun_mat_2).text = tabella.turno_118_lun_mat_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_lun_mat_3).text = tabella.turno_118_lun_mat_3
            root.findViewById<TextView>(R.id.tabella118_turno_118_lun_pom_1).text = tabella.turno_118_lun_pom_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_lun_pom_2).text = tabella.turno_118_lun_pom_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_lun_pom_3).text = tabella.turno_118_lun_pom_3
            root.findViewById<TextView>(R.id.tabella118_turno_118_lun_ser_1).text = tabella.turno_118_lun_ser_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_lun_ser_2).text = tabella.turno_118_lun_ser_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_lun_ser_3).text = tabella.turno_118_lun_ser_3

            root.findViewById<TextView>(R.id.tabella118_turno_118_mar_mat_1).text = tabella.turno_118_mar_mat_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_mar_mat_2).text = tabella.turno_118_mar_mat_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_mar_mat_3).text = tabella.turno_118_mar_mat_3
            root.findViewById<TextView>(R.id.tabella118_turno_118_mar_pom_1).text = tabella.turno_118_mar_pom_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_mar_pom_2).text = tabella.turno_118_mar_pom_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_mar_pom_3).text = tabella.turno_118_mar_pom_3
            root.findViewById<TextView>(R.id.tabella118_turno_118_mar_ser_1).text = tabella.turno_118_mar_ser_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_mar_ser_2).text = tabella.turno_118_mar_ser_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_mar_ser_3).text = tabella.turno_118_mar_ser_3

            root.findViewById<TextView>(R.id.tabella118_turno_118_mer_mat_1).text = tabella.turno_118_mer_mat_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_mer_mat_2).text = tabella.turno_118_mer_mat_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_mer_mat_3).text = tabella.turno_118_mer_mat_3
            root.findViewById<TextView>(R.id.tabella118_turno_118_mer_pom_1).text = tabella.turno_118_mer_pom_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_mer_pom_2).text = tabella.turno_118_mer_pom_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_mer_pom_3).text = tabella.turno_118_mer_pom_3
            root.findViewById<TextView>(R.id.tabella118_turno_118_mer_ser_1).text = tabella.turno_118_mer_ser_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_mer_ser_2).text = tabella.turno_118_mer_ser_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_mer_ser_3).text = tabella.turno_118_mer_ser_3

            root.findViewById<TextView>(R.id.tabella118_turno_118_gio_mat_1).text = tabella.turno_118_gio_mat_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_gio_mat_2).text = tabella.turno_118_gio_mat_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_gio_mat_3).text = tabella.turno_118_gio_mat_3
            root.findViewById<TextView>(R.id.tabella118_turno_118_gio_pom_1).text = tabella.turno_118_gio_pom_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_gio_pom_2).text = tabella.turno_118_gio_pom_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_gio_pom_3).text = tabella.turno_118_gio_pom_3
            root.findViewById<TextView>(R.id.tabella118_turno_118_gio_ser_1).text = tabella.turno_118_gio_ser_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_gio_ser_2).text = tabella.turno_118_gio_ser_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_gio_ser_3).text = tabella.turno_118_gio_ser_3

            root.findViewById<TextView>(R.id.tabella118_turno_118_ven_mat_1).text = tabella.turno_118_ven_mat_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_ven_mat_2).text = tabella.turno_118_ven_mat_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_ven_mat_3).text = tabella.turno_118_ven_mat_3
            root.findViewById<TextView>(R.id.tabella118_turno_118_ven_pom_1).text = tabella.turno_118_ven_pom_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_ven_pom_2).text = tabella.turno_118_ven_pom_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_ven_pom_3).text = tabella.turno_118_ven_pom_3
            root.findViewById<TextView>(R.id.tabella118_turno_118_ven_ser_1).text = tabella.turno_118_ven_ser_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_ven_ser_2).text = tabella.turno_118_ven_ser_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_ven_ser_3).text = tabella.turno_118_ven_ser_3

            root.findViewById<TextView>(R.id.tabella118_turno_118_sab_mat_1).text = tabella.turno_118_sab_mat_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_sab_mat_2).text = tabella.turno_118_sab_mat_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_sab_mat_3).text = tabella.turno_118_sab_mat_3
            root.findViewById<TextView>(R.id.tabella118_turno_118_sab_pom_1).text = tabella.turno_118_sab_pom_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_sab_pom_2).text = tabella.turno_118_sab_pom_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_sab_pom_3).text = tabella.turno_118_sab_pom_3
            root.findViewById<TextView>(R.id.tabella118_turno_118_sab_ser_1).text = tabella.turno_118_sab_ser_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_sab_ser_2).text = tabella.turno_118_sab_ser_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_sab_ser_3).text = tabella.turno_118_sab_ser_3

            root.findViewById<TextView>(R.id.tabella118_turno_118_dom_mat_1).text = tabella.turno_118_dom_mat_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_dom_mat_2).text = tabella.turno_118_dom_mat_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_dom_mat_3).text = tabella.turno_118_dom_mat_3
            root.findViewById<TextView>(R.id.tabella118_turno_118_dom_pom_1).text = tabella.turno_118_dom_pom_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_dom_pom_2).text = tabella.turno_118_dom_pom_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_dom_pom_3).text = tabella.turno_118_dom_pom_3
            root.findViewById<TextView>(R.id.tabella118_turno_118_dom_ser_1).text = tabella.turno_118_dom_ser_1
            root.findViewById<TextView>(R.id.tabella118_turno_118_dom_ser_2).text = tabella.turno_118_dom_ser_2
            root.findViewById<TextView>(R.id.tabella118_turno_118_dom_ser_3).text = tabella.turno_118_dom_ser_3
        }
    }

    /*
    Metodo per settare le date su una tabella
    la cosa ricevuta e un oggetto di configurazione da li si estraggono le date
    la tabella con 118 corrisponde al valore false
    */
    fun setta_info_tabella_118_h24(root: View, tabella: Tabella118h24?) {
        if (tabella != null){
            var milli = tabella.data_lunedi!!.seconds * 1000
            val lunedi =
                Instant.ofEpochMilli(milli).atZone(ZoneId.systemDefault()).toLocalDateTime()
            val formatter = DateTimeFormatter.ofPattern("dd/MM")
            root.findViewById<TextView>(R.id.lunedi118h24).text = "Lunedì "+lunedi.format(formatter)
            root.findViewById<TextView>(R.id.martedi118h24).text = "Martedì "+lunedi.plusDays(1).format(formatter)
            root.findViewById<TextView>(R.id.mercoledi118h24).text =
                "Mercoledì "+lunedi.plusDays(2).format(formatter)
            root.findViewById<TextView>(R.id.giovedi118h24).text = "Giovedì "+lunedi.plusDays(3).format(formatter)
            root.findViewById<TextView>(R.id.venerdi118h24).text = "Venerdì "+lunedi.plusDays(4).format(formatter)
            root.findViewById<TextView>(R.id.sabato118h24).text = "Sabato "+lunedi.plusDays(5).format(formatter)
            root.findViewById<TextView>(R.id.domenica118h24).text = "Domenica "+lunedi.plusDays(6).format(formatter)

            root.findViewById<TextView>(R.id.tabella118h24_turno_118_lun_mat_1).text = tabella.turno_118_dom_mat_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_lun_mat_2).text = tabella.turno_118_lun_mat_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_lun_mat_3).text = tabella.turno_118_lun_mat_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_lun_pom_1).text = tabella.turno_118_lun_pom_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_lun_pom_2).text = tabella.turno_118_lun_pom_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_lun_pom_3).text = tabella.turno_118_lun_pom_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_lun_ser_1).text = tabella.turno_118_lun_ser_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_lun_ser_2).text = tabella.turno_118_lun_ser_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_lun_ser_3).text = tabella.turno_118_lun_ser_3

            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mar_mat_1).text = tabella.turno_118_mar_mat_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mar_mat_2).text = tabella.turno_118_mar_mat_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mar_mat_3).text = tabella.turno_118_mar_mat_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mar_pom_1).text = tabella.turno_118_mar_pom_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mar_pom_2).text = tabella.turno_118_mar_pom_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mar_pom_3).text = tabella.turno_118_mar_pom_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mar_ser_1).text = tabella.turno_118_mar_ser_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mar_ser_2).text = tabella.turno_118_mar_ser_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mar_ser_3).text = tabella.turno_118_mar_ser_3

            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mer_mat_1).text = tabella.turno_118_mer_mat_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mer_mat_2).text = tabella.turno_118_mer_mat_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mer_mat_3).text = tabella.turno_118_mer_mat_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mer_pom_1).text = tabella.turno_118_mer_pom_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mer_pom_2).text = tabella.turno_118_mer_pom_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mer_pom_3).text = tabella.turno_118_mer_pom_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mer_ser_1).text = tabella.turno_118_mer_ser_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mer_ser_2).text = tabella.turno_118_mer_ser_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_mer_ser_3).text = tabella.turno_118_mer_ser_3

            root.findViewById<TextView>(R.id.tabella118h24_turno_118_gio_mat_1).text = tabella.turno_118_gio_mat_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_gio_mat_2).text = tabella.turno_118_gio_mat_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_gio_mat_3).text = tabella.turno_118_gio_mat_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_gio_pom_1).text = tabella.turno_118_gio_pom_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_gio_pom_2).text = tabella.turno_118_gio_pom_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_gio_pom_3).text = tabella.turno_118_gio_pom_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_gio_ser_1).text = tabella.turno_118_gio_ser_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_gio_ser_2).text = tabella.turno_118_gio_ser_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_gio_ser_3).text = tabella.turno_118_gio_ser_3

            root.findViewById<TextView>(R.id.tabella118h24_turno_118_ven_mat_1).text = tabella.turno_118_ven_mat_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_ven_mat_2).text = tabella.turno_118_ven_mat_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_ven_mat_3).text = tabella.turno_118_ven_mat_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_ven_pom_1).text = tabella.turno_118_ven_pom_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_ven_pom_2).text = tabella.turno_118_ven_pom_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_ven_pom_3).text = tabella.turno_118_ven_pom_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_ven_ser_1).text = tabella.turno_118_ven_ser_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_ven_ser_2).text = tabella.turno_118_ven_ser_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_ven_ser_3).text = tabella.turno_118_ven_ser_3

            root.findViewById<TextView>(R.id.tabella118h24_turno_118_sab_mat_1).text = tabella.turno_118_sab_mat_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_sab_mat_2).text = tabella.turno_118_sab_mat_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_sab_mat_3).text = tabella.turno_118_sab_mat_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_sab_pom_1).text = tabella.turno_118_sab_pom_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_sab_pom_2).text = tabella.turno_118_sab_pom_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_sab_pom_3).text = tabella.turno_118_sab_pom_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_sab_ser_1).text = tabella.turno_118_sab_ser_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_sab_ser_2).text = tabella.turno_118_sab_ser_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_sab_ser_3).text = tabella.turno_118_sab_ser_3

            root.findViewById<TextView>(R.id.tabella118h24_turno_118_dom_mat_1).text = tabella.turno_118_dom_mat_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_dom_mat_2).text = tabella.turno_118_dom_mat_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_dom_mat_3).text = tabella.turno_118_dom_mat_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_dom_pom_1).text = tabella.turno_118_dom_pom_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_dom_pom_2).text = tabella.turno_118_dom_pom_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_dom_pom_3).text = tabella.turno_118_dom_pom_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_dom_ser_1).text = tabella.turno_118_dom_ser_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_dom_ser_2).text = tabella.turno_118_dom_ser_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_118_dom_ser_3).text = tabella.turno_118_dom_ser_3
            //---------------------------------------------------
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_lun_mat_1).text = tabella.turno_h24_lun_mat_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_lun_mat_2).text = tabella.turno_h24_lun_mat_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_lun_mat_3).text = tabella.turno_h24_lun_mat_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_lun_pom_1).text = tabella.turno_h24_lun_pom_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_lun_pom_2).text = tabella.turno_h24_lun_pom_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_lun_pom_3).text = tabella.turno_h24_lun_pom_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_lun_ser_1).text = tabella.turno_h24_lun_ser_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_lun_ser_2).text = tabella.turno_h24_lun_ser_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_lun_ser_3).text = tabella.turno_h24_lun_ser_3

            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mar_mat_1).text = tabella.turno_h24_mar_mat_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mar_mat_2).text = tabella.turno_h24_mar_mat_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mar_mat_3).text = tabella.turno_h24_mar_mat_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mar_pom_1).text = tabella.turno_h24_mar_pom_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mar_pom_2).text = tabella.turno_h24_mar_pom_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mar_pom_3).text = tabella.turno_h24_mar_pom_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mar_ser_1).text = tabella.turno_h24_mar_ser_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mar_ser_2).text = tabella.turno_h24_mar_ser_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mar_ser_3).text = tabella.turno_h24_mar_ser_3

            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mer_mat_1).text = tabella.turno_h24_mer_mat_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mer_mat_2).text = tabella.turno_h24_mer_mat_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mer_mat_3).text = tabella.turno_h24_mer_mat_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mer_pom_1).text = tabella.turno_h24_mer_pom_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mer_pom_2).text = tabella.turno_h24_mer_pom_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mer_pom_3).text = tabella.turno_h24_mer_pom_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mer_ser_1).text = tabella.turno_h24_mer_ser_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mer_ser_2).text = tabella.turno_h24_mer_ser_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_mer_ser_3).text = tabella.turno_h24_mer_ser_3

            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_gio_mat_1).text = tabella.turno_h24_gio_mat_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_gio_mat_2).text = tabella.turno_h24_gio_mat_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_gio_mat_3).text = tabella.turno_h24_gio_mat_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_gio_pom_1).text = tabella.turno_h24_gio_pom_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_gio_pom_2).text = tabella.turno_h24_gio_pom_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_gio_pom_3).text = tabella.turno_h24_gio_pom_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_gio_ser_1).text = tabella.turno_h24_gio_ser_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_gio_ser_2).text = tabella.turno_h24_gio_ser_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_gio_ser_3).text = tabella.turno_h24_gio_ser_3

            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_ven_mat_1).text = tabella.turno_h24_ven_mat_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_ven_mat_2).text = tabella.turno_h24_ven_mat_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_ven_mat_3).text = tabella.turno_h24_ven_mat_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_ven_pom_1).text = tabella.turno_h24_ven_pom_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_ven_pom_2).text = tabella.turno_h24_ven_pom_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_ven_pom_3).text = tabella.turno_h24_ven_pom_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_ven_ser_1).text = tabella.turno_h24_ven_ser_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_ven_ser_2).text = tabella.turno_h24_ven_ser_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_ven_ser_3).text = tabella.turno_h24_ven_ser_3

            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_sab_mat_1).text = tabella.turno_h24_sab_mat_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_sab_mat_2).text = tabella.turno_h24_sab_mat_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_sab_mat_3).text = tabella.turno_h24_sab_mat_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_sab_pom_1).text = tabella.turno_h24_sab_pom_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_sab_pom_2).text = tabella.turno_h24_sab_pom_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_sab_pom_3).text = tabella.turno_h24_sab_pom_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_sab_ser_1).text = tabella.turno_h24_sab_ser_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_sab_ser_2).text = tabella.turno_h24_sab_ser_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_sab_ser_3).text = tabella.turno_h24_sab_ser_3

            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_dom_mat_1).text = tabella.turno_h24_dom_mat_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_dom_mat_2).text = tabella.turno_h24_dom_mat_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_dom_mat_3).text = tabella.turno_h24_dom_mat_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_dom_pom_1).text = tabella.turno_h24_dom_pom_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_dom_pom_2).text = tabella.turno_h24_dom_pom_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_dom_pom_3).text = tabella.turno_h24_dom_pom_3
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_dom_ser_1).text = tabella.turno_h24_dom_ser_1
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_dom_ser_2).text = tabella.turno_h24_dom_ser_2
            root.findViewById<TextView>(R.id.tabella118h24_turno_h24_dom_ser_3).text = tabella.turno_h24_dom_ser_3
        }


    }




}