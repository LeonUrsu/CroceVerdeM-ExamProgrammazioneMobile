package com.example.croceverdeplus

import android.content.ContentValues
import android.content.res.Resources
import android.util.Log
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import android.widget.ViewFlipper
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class TabelloneTurni {

    /*
    Metodo per rilevare il nome presente nello spinner dei militi disponibili per il turno
     */
    fun rileva_nomi_spinner(root: View, tipo_settimana: Boolean): String {
        val nome_cognome: String =
            root.findViewById<Spinner>(R.id.milite_input).selectedItem.toString()
        return nome_cognome
    }

    /*
    Metodo per rilevarela posizione del valore dello  spinner selezionato nel
    TabelloneTurniCentralinista una volta selezionato il valore viene ritornato il valore dello
    spinner tramite il valore ret_posizion
    */
    fun rileva_valori_spinner(root: View): String {
        val servizio_val: String =
            root.findViewById<Spinner>(R.id.servizio_input_centralinista).selectedItem.toString()
        val orario_val: String =
            root.findViewById<Spinner>(R.id.orario_input).selectedItem.toString()
        val giorno_val: String =
            root.findViewById<Spinner>(R.id.giorno_input).selectedItem.toString()
        val grado_val: String =
            root.findViewById<Spinner>(R.id.grado_input_centralinista).selectedItem.toString()
        return id_builder(servizio_val, giorno_val, orario_val, grado_val)
    }

    /*
    Metodo per la costruzione dell'id per riempire la tabella nella schermata del tabellone dei turni
    ogni id corrispone al luogo di posizionamento nella tabella
     */
    fun id_builder(
        servizio_val: String,
        giorno_val: String,
        orario_val: String,
        grado_val: String
    ): String {
        val servizio = when (servizio_val) { //turno_118_mar_mat_3
            "118" -> "118"
            "H24" -> "h24"
            else -> {
                "118"
            }
        }
        val giorno = when (giorno_val) {
            "Lunedi" -> "lun"
            "Martedì" -> "mar"
            "Mercoledì" -> "mer"
            "Giovedì" -> "gio"
            "Venerdì" -> "ven"
            "Sabato" -> "sab"
            "Documenica" -> "dom"
            else -> {
                "lun"
            }
        }
        val orario = when (orario_val) {
            "7 - 14" -> "mat"
            "14 - 21" -> "pom"
            "21 - 7" -> "ser"
            else -> {
                "mat"
            }
        }
        val grado = when (grado_val) {
            "1a" -> "1"
            "2a" -> "2"
            "3a" -> "3"
            else -> {
                "1"
            }
        }
        return "turno_" + servizio + "_" + giorno + "_" + orario + "_" + grado
    }

    fun rileva_data_turno(turno: String, dataLunedi: Timestamp): Array<Any> {
        var data = dataLunedi.seconds * 1000
        val lunedi =
            Instant.ofEpochMilli(data.toLong()).atZone(ZoneId.systemDefault()).toLocalDateTime()
        var settimana = ""
        if (turno.contains("turno118h24"))
            settimana = "settimana_118_h24"
        else settimana = "settimana_118"
        var str = turno.substring(turno.length - 9)
        str = str.substring(0, 3)
        //tabella_118_turno_118_lun_mat_1
        val giorno: Int = when (str) {
            "lun" -> 0
            "mar" -> 1
            "mer" -> 2
            "gio" -> 3
            "ven" -> 4
            "sab" -> 5
            "dom" -> 6
            else -> {
                0
            }
        }
        var millis = lunedi.plusDays(giorno.toLong()).second.toLong() * 1000 //data in millesecondi
        var hashmap = hashMapOf("data" to millis, "tipo_tabella" to settimana)
        //Database().aggiungi_documento_a_db(hashmap,"disponibilita")
        return arrayOf(millis, settimana)
    }


    fun rileva_nome_milite(root: View, tipo_settimana: Boolean) {
        var nome_milite = TabelloneTurni().rileva_nomi_spinner(root, tipo_settimana)
    }

    /*
    Metodo Per settare la tabella 118
    */
    fun setta_settiamna_118(root: View) {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("tabelle").document("tabella_118")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                    var documento = document.toObject<Tabella118>()
                    setta_info_tabella_118(root, documento)
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
    fun setta_settiamna_118_h24(root: View) {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("tabelle").document("tabella_118_h24")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                    var documento = document.toObject<Tabella118h24>()
                    setta_info_tabella_118_h24(root, documento)
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
    }

    /*
    Metodo per filtrare l'array completo di militi in base al grado del servizio selezionato, se il milite
    soddisfa i requisiti per svolgere i turni il milite verrà aggiunto alla lista dei filtrati
    */ //TODO si può rendere più efficiente se il confronto nel when() non avviente con stringhe ma con numeri Int
    fun filtra_militi(
        militi: MutableList<Milite>,
        servizio_val: String,
        grado_val: String
    ): MutableList<Milite> {
        val militiFiltrati: MutableList<Milite> = mutableListOf()
        militi.forEach {
            val risultatoVerificaGrado = when(servizio_val + grado_val){
                "1181a" -> it.grado118prima
                "1182a" -> it.grado118prima
                "1183a" -> it.grado118prima
                "h241a" -> it.gradoh24prima
                "h242a" -> it.gradoh24seconda
                "h243a" -> it.gradoh24terza
                else -> {false}
            }
            if(risultatoVerificaGrado != null && risultatoVerificaGrado ) militiFiltrati.add(it)
        }
        return militiFiltrati
    }


    /*
    Metodo per settare le date su una tabella
    la cosa ricevuta e un oggetto di configurazione da li si estraggono le date
    la tabella con 118 corrisponde al valore false
    */
    fun setta_info_tabella_118(root: View, tabella: Tabella118?) {
        if (tabella != null) {

            root.findViewById<TextView>(R.id.tabella_118_turno_118_lun_mat_1).text =
                tabella.turno_118_lun_mat_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_lun_mat_2).text =
                tabella.turno_118_lun_mat_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_lun_mat_3).text =
                tabella.turno_118_lun_mat_3
            root.findViewById<TextView>(R.id.tabella_118_turno_118_lun_pom_1).text =
                tabella.turno_118_lun_pom_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_lun_pom_2).text =
                tabella.turno_118_lun_pom_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_lun_pom_3).text =
                tabella.turno_118_lun_pom_3
            root.findViewById<TextView>(R.id.tabella_118_turno_118_lun_ser_1).text =
                tabella.turno_118_lun_ser_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_lun_ser_2).text =
                tabella.turno_118_lun_ser_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_lun_ser_3).text =
                tabella.turno_118_lun_ser_3

            root.findViewById<TextView>(R.id.tabella_118_turno_118_mar_mat_1).text =
                tabella.turno_118_mar_mat_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mar_mat_2).text =
                tabella.turno_118_mar_mat_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mar_mat_3).text =
                tabella.turno_118_mar_mat_3
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mar_pom_1).text =
                tabella.turno_118_mar_pom_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mar_pom_2).text =
                tabella.turno_118_mar_pom_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mar_pom_3).text =
                tabella.turno_118_mar_pom_3
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mar_ser_1).text =
                tabella.turno_118_mar_ser_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mar_ser_2).text =
                tabella.turno_118_mar_ser_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mar_ser_3).text =
                tabella.turno_118_mar_ser_3

            root.findViewById<TextView>(R.id.tabella_118_turno_118_mer_mat_1).text =
                tabella.turno_118_mer_mat_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mer_mat_2).text =
                tabella.turno_118_mer_mat_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mer_mat_3).text =
                tabella.turno_118_mer_mat_3
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mer_pom_1).text =
                tabella.turno_118_mer_pom_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mer_pom_2).text =
                tabella.turno_118_mer_pom_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mer_pom_3).text =
                tabella.turno_118_mer_pom_3
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mer_ser_1).text =
                tabella.turno_118_mer_ser_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mer_ser_2).text =
                tabella.turno_118_mer_ser_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_mer_ser_3).text =
                tabella.turno_118_mer_ser_3

            root.findViewById<TextView>(R.id.tabella_118_turno_118_gio_mat_1).text =
                tabella.turno_118_gio_mat_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_gio_mat_2).text =
                tabella.turno_118_gio_mat_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_gio_mat_3).text =
                tabella.turno_118_gio_mat_3
            root.findViewById<TextView>(R.id.tabella_118_turno_118_gio_pom_1).text =
                tabella.turno_118_gio_pom_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_gio_pom_2).text =
                tabella.turno_118_gio_pom_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_gio_pom_3).text =
                tabella.turno_118_gio_pom_3
            root.findViewById<TextView>(R.id.tabella_118_turno_118_gio_ser_1).text =
                tabella.turno_118_gio_ser_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_gio_ser_2).text =
                tabella.turno_118_gio_ser_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_gio_ser_3).text =
                tabella.turno_118_gio_ser_3

            root.findViewById<TextView>(R.id.tabella_118_turno_118_ven_mat_1).text =
                tabella.turno_118_ven_mat_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_ven_mat_2).text =
                tabella.turno_118_ven_mat_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_ven_mat_3).text =
                tabella.turno_118_ven_mat_3
            root.findViewById<TextView>(R.id.tabella_118_turno_118_ven_pom_1).text =
                tabella.turno_118_ven_pom_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_ven_pom_2).text =
                tabella.turno_118_ven_pom_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_ven_pom_3).text =
                tabella.turno_118_ven_pom_3
            root.findViewById<TextView>(R.id.tabella_118_turno_118_ven_ser_1).text =
                tabella.turno_118_ven_ser_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_ven_ser_2).text =
                tabella.turno_118_ven_ser_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_ven_ser_3).text =
                tabella.turno_118_ven_ser_3

            root.findViewById<TextView>(R.id.tabella_118_turno_118_sab_mat_1).text =
                tabella.turno_118_sab_mat_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_sab_mat_2).text =
                tabella.turno_118_sab_mat_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_sab_mat_3).text =
                tabella.turno_118_sab_mat_3
            root.findViewById<TextView>(R.id.tabella_118_turno_118_sab_pom_1).text =
                tabella.turno_118_sab_pom_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_sab_pom_2).text =
                tabella.turno_118_sab_pom_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_sab_pom_3).text =
                tabella.turno_118_sab_pom_3
            root.findViewById<TextView>(R.id.tabella_118_turno_118_sab_ser_1).text =
                tabella.turno_118_sab_ser_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_sab_ser_2).text =
                tabella.turno_118_sab_ser_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_sab_ser_3).text =
                tabella.turno_118_sab_ser_3

            root.findViewById<TextView>(R.id.tabella_118_turno_118_dom_mat_1).text =
                tabella.turno_118_dom_mat_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_dom_mat_2).text =
                tabella.turno_118_dom_mat_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_dom_mat_3).text =
                tabella.turno_118_dom_mat_3
            root.findViewById<TextView>(R.id.tabella_118_turno_118_dom_pom_1).text =
                tabella.turno_118_dom_pom_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_dom_pom_2).text =
                tabella.turno_118_dom_pom_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_dom_pom_3).text =
                tabella.turno_118_dom_pom_3
            root.findViewById<TextView>(R.id.tabella_118_turno_118_dom_ser_1).text =
                tabella.turno_118_dom_ser_1
            root.findViewById<TextView>(R.id.tabella_118_turno_118_dom_ser_2).text =
                tabella.turno_118_dom_ser_2
            root.findViewById<TextView>(R.id.tabella_118_turno_118_dom_ser_3).text =
                tabella.turno_118_dom_ser_3

            var milli = tabella.data_lunedi!!.seconds * 1000
            val lunedi =
                Instant.ofEpochMilli(milli).atZone(ZoneId.systemDefault()).toLocalDateTime()
            val formatter = DateTimeFormatter.ofPattern("dd/MM")
            root.findViewById<TextView>(R.id.lunedi118).text = "Lunedì " + lunedi.format(formatter)
            root.findViewById<TextView>(R.id.martedi118).text =
                "Martedì " + lunedi.plusDays(1).format(formatter)
            root.findViewById<TextView>(R.id.mercoledi118).text =
                "Mercoledì " + lunedi.plusDays(2).format(formatter)
            root.findViewById<TextView>(R.id.giovedi118).text =
                "Giovedì " + lunedi.plusDays(3).format(formatter)
            root.findViewById<TextView>(R.id.venerdi118).text =
                "Venerdì " + lunedi.plusDays(4).format(formatter)
            root.findViewById<TextView>(R.id.sabato118).text =
                "Sabato " + lunedi.plusDays(5).format(formatter)
            root.findViewById<TextView>(R.id.domenica118).text =
                "Domenica " + lunedi.plusDays(6).format(formatter)
        }
    }

    /*
    Metodo per settare le date su una tabella
    la cosa ricevuta e un oggetto di configurazione da li si estraggono le date
    la tabella con 118 corrisponde al valore false
    */
    fun setta_info_tabella_118_h24(root: View, tabella: Tabella118h24?) {
        if (tabella != null) {
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_lun_mat_1).text =
                tabella.turno_118_lun_mat_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_lun_mat_2).text =
                tabella.turno_118_lun_mat_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_lun_mat_3).text =
                tabella.turno_118_lun_mat_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_lun_pom_1).text =
                tabella.turno_118_lun_pom_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_lun_pom_2).text =
                tabella.turno_118_lun_pom_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_lun_pom_3).text =
                tabella.turno_118_lun_pom_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_lun_ser_1).text =
                tabella.turno_118_lun_ser_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_lun_ser_2).text =
                tabella.turno_118_lun_ser_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_lun_ser_3).text =
                tabella.turno_118_lun_ser_3

            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mar_mat_1).text =
                tabella.turno_118_mar_mat_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mar_mat_2).text =
                tabella.turno_118_mar_mat_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mar_mat_3).text =
                tabella.turno_118_mar_mat_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mar_pom_1).text =
                tabella.turno_118_mar_pom_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mar_pom_2).text =
                tabella.turno_118_mar_pom_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mar_pom_3).text =
                tabella.turno_118_mar_pom_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mar_ser_1).text =
                tabella.turno_118_mar_ser_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mar_ser_2).text =
                tabella.turno_118_mar_ser_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mar_ser_3).text =
                tabella.turno_118_mar_ser_3

            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mer_mat_1).text =
                tabella.turno_118_mer_mat_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mer_mat_2).text =
                tabella.turno_118_mer_mat_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mer_mat_3).text =
                tabella.turno_118_mer_mat_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mer_pom_1).text =
                tabella.turno_118_mer_pom_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mer_pom_2).text =
                tabella.turno_118_mer_pom_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mer_pom_3).text =
                tabella.turno_118_mer_pom_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mer_ser_1).text =
                tabella.turno_118_mer_ser_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mer_ser_2).text =
                tabella.turno_118_mer_ser_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_mer_ser_3).text =
                tabella.turno_118_mer_ser_3

            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_gio_mat_1).text =
                tabella.turno_118_gio_mat_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_gio_mat_2).text =
                tabella.turno_118_gio_mat_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_gio_mat_3).text =
                tabella.turno_118_gio_mat_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_gio_pom_1).text =
                tabella.turno_118_gio_pom_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_gio_pom_2).text =
                tabella.turno_118_gio_pom_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_gio_pom_3).text =
                tabella.turno_118_gio_pom_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_gio_ser_1).text =
                tabella.turno_118_gio_ser_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_gio_ser_2).text =
                tabella.turno_118_gio_ser_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_gio_ser_3).text =
                tabella.turno_118_gio_ser_3

            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_ven_mat_1).text =
                tabella.turno_118_ven_mat_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_ven_mat_2).text =
                tabella.turno_118_ven_mat_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_ven_mat_3).text =
                tabella.turno_118_ven_mat_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_ven_pom_1).text =
                tabella.turno_118_ven_pom_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_ven_pom_2).text =
                tabella.turno_118_ven_pom_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_ven_pom_3).text =
                tabella.turno_118_ven_pom_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_ven_ser_1).text =
                tabella.turno_118_ven_ser_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_ven_ser_2).text =
                tabella.turno_118_ven_ser_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_ven_ser_3).text =
                tabella.turno_118_ven_ser_3

            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_sab_mat_1).text =
                tabella.turno_118_sab_mat_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_sab_mat_2).text =
                tabella.turno_118_sab_mat_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_sab_mat_3).text =
                tabella.turno_118_sab_mat_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_sab_pom_1).text =
                tabella.turno_118_sab_pom_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_sab_pom_2).text =
                tabella.turno_118_sab_pom_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_sab_pom_3).text =
                tabella.turno_118_sab_pom_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_sab_ser_1).text =
                tabella.turno_118_sab_ser_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_sab_ser_2).text =
                tabella.turno_118_sab_ser_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_sab_ser_3).text =
                tabella.turno_118_sab_ser_3

            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_dom_mat_1).text =
                tabella.turno_118_dom_mat_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_dom_mat_2).text =
                tabella.turno_118_dom_mat_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_dom_mat_3).text =
                tabella.turno_118_dom_mat_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_dom_pom_1).text =
                tabella.turno_118_dom_pom_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_dom_pom_2).text =
                tabella.turno_118_dom_pom_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_dom_pom_3).text =
                tabella.turno_118_dom_pom_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_dom_ser_1).text =
                tabella.turno_118_dom_ser_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_dom_ser_2).text =
                tabella.turno_118_dom_ser_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_118_dom_ser_3).text =
                tabella.turno_118_dom_ser_3
            //---------------------------------------------------
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_lun_mat_1).text =
                tabella.turno_h24_lun_mat_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_lun_mat_2).text =
                tabella.turno_h24_lun_mat_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_lun_mat_3).text =
                tabella.turno_h24_lun_mat_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_lun_pom_1).text =
                tabella.turno_h24_lun_pom_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_lun_pom_2).text =
                tabella.turno_h24_lun_pom_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_lun_pom_3).text =
                tabella.turno_h24_lun_pom_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_lun_ser_1).text =
                tabella.turno_h24_lun_ser_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_lun_ser_2).text =
                tabella.turno_h24_lun_ser_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_lun_ser_3).text =
                tabella.turno_h24_lun_ser_3

            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mar_mat_1).text =
                tabella.turno_h24_mar_mat_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mar_mat_2).text =
                tabella.turno_h24_mar_mat_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mar_mat_3).text =
                tabella.turno_h24_mar_mat_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mar_pom_1).text =
                tabella.turno_h24_mar_pom_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mar_pom_2).text =
                tabella.turno_h24_mar_pom_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mar_pom_3).text =
                tabella.turno_h24_mar_pom_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mar_ser_1).text =
                tabella.turno_h24_mar_ser_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mar_ser_2).text =
                tabella.turno_h24_mar_ser_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mar_ser_3).text =
                tabella.turno_h24_mar_ser_3

            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mer_mat_1).text =
                tabella.turno_h24_mer_mat_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mer_mat_2).text =
                tabella.turno_h24_mer_mat_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mer_mat_3).text =
                tabella.turno_h24_mer_mat_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mer_pom_1).text =
                tabella.turno_h24_mer_pom_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mer_pom_2).text =
                tabella.turno_h24_mer_pom_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mer_pom_3).text =
                tabella.turno_h24_mer_pom_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mer_ser_1).text =
                tabella.turno_h24_mer_ser_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mer_ser_2).text =
                tabella.turno_h24_mer_ser_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_mer_ser_3).text =
                tabella.turno_h24_mer_ser_3

            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_gio_mat_1).text =
                tabella.turno_h24_gio_mat_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_gio_mat_2).text =
                tabella.turno_h24_gio_mat_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_gio_mat_3).text =
                tabella.turno_h24_gio_mat_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_gio_pom_1).text =
                tabella.turno_h24_gio_pom_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_gio_pom_2).text =
                tabella.turno_h24_gio_pom_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_gio_pom_3).text =
                tabella.turno_h24_gio_pom_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_gio_ser_1).text =
                tabella.turno_h24_gio_ser_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_gio_ser_2).text =
                tabella.turno_h24_gio_ser_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_gio_ser_3).text =
                tabella.turno_h24_gio_ser_3

            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_ven_mat_1).text =
                tabella.turno_h24_ven_mat_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_ven_mat_2).text =
                tabella.turno_h24_ven_mat_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_ven_mat_3).text =
                tabella.turno_h24_ven_mat_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_ven_pom_1).text =
                tabella.turno_h24_ven_pom_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_ven_pom_2).text =
                tabella.turno_h24_ven_pom_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_ven_pom_3).text =
                tabella.turno_h24_ven_pom_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_ven_ser_1).text =
                tabella.turno_h24_ven_ser_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_ven_ser_2).text =
                tabella.turno_h24_ven_ser_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_ven_ser_3).text =
                tabella.turno_h24_ven_ser_3

            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_sab_mat_1).text =
                tabella.turno_h24_sab_mat_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_sab_mat_2).text =
                tabella.turno_h24_sab_mat_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_sab_mat_3).text =
                tabella.turno_h24_sab_mat_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_sab_pom_1).text =
                tabella.turno_h24_sab_pom_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_sab_pom_2).text =
                tabella.turno_h24_sab_pom_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_sab_pom_3).text =
                tabella.turno_h24_sab_pom_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_sab_ser_1).text =
                tabella.turno_h24_sab_ser_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_sab_ser_2).text =
                tabella.turno_h24_sab_ser_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_sab_ser_3).text =
                tabella.turno_h24_sab_ser_3

            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_dom_mat_1).text =
                tabella.turno_h24_dom_mat_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_dom_mat_2).text =
                tabella.turno_h24_dom_mat_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_dom_mat_3).text =
                tabella.turno_h24_dom_mat_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_dom_pom_1).text =
                tabella.turno_h24_dom_pom_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_dom_pom_2).text =
                tabella.turno_h24_dom_pom_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_dom_pom_3).text =
                tabella.turno_h24_dom_pom_3
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_dom_ser_1).text =
                tabella.turno_h24_dom_ser_1
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_dom_ser_2).text =
                tabella.turno_h24_dom_ser_2
            root.findViewById<TextView>(R.id.tabella_118_h24_turno_h24_dom_ser_3).text =
                tabella.turno_h24_dom_ser_3

            var milli = tabella.data_lunedi!!.seconds * 1000
            val lunedi =
                Instant.ofEpochMilli(milli).atZone(ZoneId.systemDefault()).toLocalDateTime()
            val formatter = DateTimeFormatter.ofPattern("dd/MM")
            root.findViewById<TextView>(R.id.lunedi118h24).setText(
                "Lunedì   ${lunedi.format(formatter)}"
            )
            root.findViewById<TextView>(R.id.martedi118h24).setText(
                "Martedì   ${lunedi.plusDays(1).format(formatter)}"
            )
            root.findViewById<TextView>(R.id.mercoledi118h24).setText(
                "Mercoledì   ${lunedi.plusDays(2).format(formatter)}"
            )
            root.findViewById<TextView>(R.id.giovedi118h24).setText(
                "Giovedì   ${lunedi.plusDays(3).format(formatter)}"
            )
            root.findViewById<TextView>(R.id.venerdi118h24).setText(
                "Venerdì   ${lunedi.plusDays(4).format(formatter)}"
            )
            root.findViewById<TextView>(R.id.sabato118h24).setText(
                "Sabato   ${lunedi.plusDays(5).format(formatter)}"
            )
            root.findViewById<TextView>(R.id.domenica118h24).setText(
                "Domenica   ${lunedi.plusDays(6).format(formatter)}"
            )

        }
    }
}