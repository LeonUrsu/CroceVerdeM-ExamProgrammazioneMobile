package com.example.croceverdeplus

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.random.Random


class Database {

    private val db = Firebase.firestore

    //metodo per creare un centralinista
    fun addUser(
        nome: String, cognome: String, dataDiNascita: String, residenza: String
    ) {

        val centralinista = Centralinisti(nome, cognome, dataDiNascita, residenza)

        val username = nome + "." + cognome

        val password = (1..5).map {
            when (Random.nextInt(3)) {
                0 -> Random.nextInt(48, 58).toChar() // Numeri da '0' a '9'
                1 -> Random.nextInt(65, 91).toChar() // Lettere maiuscole da 'A' a 'Z'
                else -> Random.nextInt(97, 123).toChar() // Lettere minuscole da 'a' a 'z'
            }
        }.joinToString("")

        db.collection("centralinisti").add(centralinista)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    TAG, "DocumentSnapshot added with ID: ${documentReference.id}"
                )

                db.collection("centralinisti").document(documentReference.id)
                    .update("username", username)
                db.collection("centralinisti").document(documentReference.id)
                    .update("password", password)

            }.addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    //metodo per eliminare un centralinista
    fun deleteUser(
        nome: String, cognome: String, dataDiNascita: String, residenza: String
    ) {

        db.collection("centralinisti").whereEqualTo("nome", nome).whereEqualTo("cognome", cognome)
            .whereEqualTo("dataDiNascita", dataDiNascita).whereEqualTo("residenza", residenza).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.data} => ${document.id}")
                    db.collection("centralinisti").document(document.id).delete()
                        .addOnSuccessListener {
                            Log.d(
                                TAG, "DocumentSnapshot successfully deleted!"
                            )
                        }.addOnFailureListener { e ->
                            Log.w(
                                TAG, "Error deleting document", e
                            )
                        }
                }
            }.addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

    }


    /*
    Metodo per polulare lo spinner dei militi per segnarli sul tabellone
    */
    fun set_spinner(root: View, militi_array: MutableList<String>, act: Activity) {
        val gameKindArray: ArrayAdapter<String> =
            ArrayAdapter<String>(
                act,
                android.R.layout.simple_spinner_item,
                militi_array
            )
        gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val convert_from_spinner: Spinner = root.findViewById(R.id.milite_input)
        convert_from_spinner.adapter = gameKindArray
    }

    //metodo per creare un milite
    fun addUserM(
        nome: String, cognome: String, dataDiNascita: String, residenza: String,
        grado118prima: Boolean?, grado118seconda: Boolean?,
        grado118terza: Boolean?, gradoh24prima: Boolean?,
        gradoh24seconda: Boolean?, gradoh24terza: Boolean?,
        volontario: Boolean?, dipendente: Boolean?
    ) {
        val milite = Milite(
            nome, cognome, dataDiNascita, residenza,
            grado118prima, grado118seconda,
            grado118terza, gradoh24prima,
            gradoh24seconda, gradoh24terza,
            volontario, dipendente
        )

        val nomeSenzaSpazi = nome.replace("\\s".toRegex(), "")
        val cognomeSenzaSpazi = cognome.replace("\\s".toRegex(), "")
        val username = nomeSenzaSpazi + "." + cognomeSenzaSpazi

        val password = (1..5).map {
            when (Random.nextInt(3)) {
                0 -> Random.nextInt(48, 58).toChar() // Numeri da '0' a '9'
                1 -> Random.nextInt(65, 91).toChar() // Lettere maiuscole da 'A' a 'Z'
                else -> Random.nextInt(97, 123).toChar() // Lettere minuscole da 'a' a 'z'
            }
        }.joinToString("")

        val cognomenomespinner = cognome + " " + nome


        db.collection("militi").add(milite).addOnSuccessListener { documentReference ->
            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

            db.collection("militi").document(documentReference.id).update("username", username)
            db.collection("militi").document(documentReference.id).update("password", password)
            db.collection("militi").document(documentReference.id)
                .update("cognomeNomeSpinner", cognomenomespinner)

        }.addOnFailureListener { e ->
            Log.w(TAG, "Error adding document", e)
        }
    }

    //metodo per eliminare un milite
    fun deleteUserM(
        nome: String, cognome: String, dataDiNascita: String, residenza: String
    ) {

        db.collection("militi").whereEqualTo("nome", nome).whereEqualTo("cognome", cognome)
            .whereEqualTo("dataDiNascita", dataDiNascita).whereEqualTo("residenza", residenza).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.data} => ${document.id}")
                    db.collection("militi").document(document.id).delete().addOnSuccessListener {
                        Log.d(
                            TAG, "DocumentSnapshot successfully deleted!"
                        )
                    }.addOnFailureListener { e ->
                        Log.w(
                            TAG, "Error deleting document", e
                        )
                    }
                }
            }.addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

    }

    /*
    Metodo per segnare o cancellare un milite dal turno,  se il milite è presente nel tunro oppure non si trova nel turno
     */
    fun segna_o_cancella_milite_dal_turno_centralinista(
        nome_tipo_tabella: String,
        turno: String,
        cognomeNomeSpinner: String, root: View, act: Activity
    ) {
        val docRef = db.collection("tabelle").document(nome_tipo_tabella)
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                val presenzaMilite = document.getString(turno)
                if (presenzaMilite == "") {// RAMO AGGIUNGI MILITE AL TURNO
                    aggiorna_tabellone_milite(cognomeNomeSpinner, nome_tipo_tabella, turno, root)
                    aggiungi_ore_lavorate(cognomeNomeSpinner, turno)
                    aggiungi_prenotazione(cognomeNomeSpinner, turno, nome_tipo_tabella)
                    Toast.makeText(act, "Milite segnato", Toast.LENGTH_SHORT).show()
                } else
                    if (presenzaMilite == cognomeNomeSpinner) {// RAMO CANCElLA
                        aggiorna_tabellone_milite("", nome_tipo_tabella, turno, root)
                        rimuovi_ore_lavorate(cognomeNomeSpinner, turno)
                        rimuovi_prenotazione(cognomeNomeSpinner, turno, nome_tipo_tabella)
                        Toast.makeText(act, "Milite cancellato", Toast.LENGTH_SHORT).show()
                    } else {
                        //cancellazione milite vecchio
                        aggiorna_tabellone_milite("", nome_tipo_tabella, turno, root)
                        rimuovi_ore_lavorate(cognomeNomeSpinner, turno)
                        rimuovi_prenotazione(cognomeNomeSpinner, turno, nome_tipo_tabella)
                        //aggiunta del milite nuovo
                        val nuovoMilite = presenzaMilite.toString()
                        aggiorna_tabellone_milite(nuovoMilite, nome_tipo_tabella, turno, root)
                        aggiungi_ore_lavorate(nuovoMilite, turno)
                        aggiungi_prenotazione(nuovoMilite, turno, nome_tipo_tabella)
                        Toast.makeText(act, "Milite sostituito", Toast.LENGTH_SHORT).show()

                    }
            } else {
                Log.d(TAG, "No such document")
            }
        }.addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
    }

    /*
    Metodo per segnare o cancellare un milite dal turno,  se il milite è presente nel tunro oppure non si trova nel turno
     */
    fun segna_o_cancella_milite_dal_turno_volontario(
        nome_tipo_tabella: String,
        turno: String,
        cognomeNomeSpinner: String, root: View, act: Activity
    ) {
        val docRef = db.collection("tabelle").document(nome_tipo_tabella)
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                recupera_milite_gestisci_verifica(
                    cognomeNomeSpinner,
                    turno,
                    document,
                    nome_tipo_tabella, root, act
                )
            } else {
                Log.d(TAG, "No such document")
            }
        }.addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
    }

    fun gestisci_prenotazione_verifica_milite(
        milite: Milite,
        turno: String,
        document: DocumentSnapshot,
        cognomeNomeSpinner: String,
        nome_tipo_tabella: String,
        root: View, act: Activity
    ) {
        val risultato_campo = trova_campo(turno)
        val campo = risultato_campo!![2]
        val autorizzazioneGradoMilite = when (campo) {
            "1181" -> milite.grado118prima
            "1182" -> milite.grado118seconda
            "1183" -> milite.grado118terza
            "h241" -> milite.gradoh24prima
            "h242" -> milite.gradoh24seconda
            "h243" -> milite.gradoh24terza
            else -> {
                false
            }
        }
        val autorizzazoneMiliteDoppione = autorizzazione_prenotazione_turno_unica(
            turno,
            cognomeNomeSpinner,
            risultato_campo[3],
            document
        )
        val autorizzazioneGradoMiliteCorretta =
            if (autorizzazioneGradoMilite == null) false else autorizzazioneGradoMilite
        val presenzaMilite = document.getString(turno)
        if (presenzaMilite == "") {// RAMO AGGIUNGI MILITE AL TURNO
            if (autorizzazioneGradoMiliteCorretta && autorizzazoneMiliteDoppione) {
                aggiorna_tabellone_milite(cognomeNomeSpinner, nome_tipo_tabella, turno, root)
                aggiungi_ore_lavorate(cognomeNomeSpinner, turno)
                aggiungi_prenotazione(cognomeNomeSpinner, turno, nome_tipo_tabella)
                Toast.makeText(act, "segnato", Toast.LENGTH_SHORT).show()
            }
        } else
            if (presenzaMilite == cognomeNomeSpinner) {// RAMO CANCElLA MILITE
                aggiorna_tabellone_milite("", nome_tipo_tabella, turno, root)
                rimuovi_ore_lavorate(cognomeNomeSpinner, turno)
                rimuovi_prenotazione(cognomeNomeSpinner, turno, nome_tipo_tabella)
                Toast.makeText(act, "cancellato dal turno", Toast.LENGTH_SHORT).show()
            }
    }

    /*
    Metodo per rimuovere una prenotazione dal db grazie al nome completo della tabella e del turno
    che si andrà a rimuovere
     */
    private fun rimuovi_prenotazione(
        cognomeNomeSpinner: String,
        turno: String,
        nome_tipo_tabella: String
    ) {
        db.collection("prenotazioni")
            .get()
            .addOnSuccessListener { result ->
                for (prenotazione in result) {
                    if (prenotazione.getString("cognomeNomeSpinner") == cognomeNomeSpinner) {
                        val turnoPrenotazioneCompleto =
                            prenotazione.getString("turnoPrenotazioneCompleto")
                        val turnoPrenotazioneCostruito = nome_tipo_tabella + "_" + turno
                        if (turnoPrenotazioneCompleto == turnoPrenotazioneCostruito) {
                            cancella_documento_da_db("prenotazioni", prenotazione.id)
                        }
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    /*
    Metodo per aggiungere una prenotazione al db
     */
    private fun aggiungi_prenotazione(
        cognomeNomeSpinner: String,
        turno: String,
        nome_tipo_tabella: String
    ) {
        val dataPrenotazione = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        val map = trova_campo(turno)
        val prenotazione = Prenotazione(
            dataPrenotazione,
            map!![2],
            cognomeNomeSpinner,
            nome_tipo_tabella + "_" + turno
        )
        aggiungi_documento_a_db(prenotazione, "prenotazioni")
    }

    /*
    definizione metodo di ProfiloVolontarioDipendente
     */
    @SuppressLint("SetTextI18n")
    fun setta_stato_regolarita_turno(root: View, cognomeNomeSpinner: String) {
        db.collection("prenotazioni")
            .get()
            .addOnSuccessListener { result ->
                var turni_fatti = 0
                for (prenotazione in result) {
                    if (prenotazione.getString("cognomeNomeSpinner") == cognomeNomeSpinner) {
                        //todo fare il calcolo delle ore necessarie e aggiungerle al layout
                        val days_ago_30 =
                            LocalDateTime.now().minusDays(30) //.toEpochSecond(ZoneOffset.UTC)
                        var data_prenotazione_in_seconds =
                            prenotazione.getLong("dataPrenotazione")
                        data_prenotazione_in_seconds = data_prenotazione_in_seconds!!.toLong()
                        data_prenotazione_in_seconds.toLong()
                        var data_prenotazione = LocalDateTime.ofEpochSecond(
                            data_prenotazione_in_seconds,
                            0,
                            ZoneOffset.UTC
                        )
                        data_prenotazione = data_prenotazione.plusDays(30)
                        if (data_prenotazione.isAfter(days_ago_30)) {
                            turni_fatti += 1
                        }
                    }
                }
                if (turni_fatti >= 2) {
                    root.findViewById<TextView>(R.id.regolarita_turno).text =
                        "stato regolare $turni_fatti/2 turni fatti"
                    root.findViewById<TextView>(R.id.regolarita_turno)
                        .setBackgroundColor(Color.GREEN)
                } else {
                    root.findViewById<TextView>(R.id.regolarita_turno).text =
                        "Stato non regolare ${turni_fatti}/2 turni fatti"
                    root.findViewById<TextView>(R.id.regolarita_turno).setBackgroundColor(Color.RED)
                }


            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }


        //var regolarita_turno = "Turno non regolare ore mancanti: "
        //document.getLong(oreTurno118prima).toString()//TODO da finire qui
    }

    /*
    Metodo per verificare se qualche altro milite è presente nello stesso turno ma su grado diverso
    return = true se il milite non è presente nei turni
    return = false se il milite è presente nei turni
    */
    private fun autorizzazione_prenotazione_turno_unica(
        turno: String,
        cognomeNomeSpinner: String,
        grado: String,
        document: DocumentSnapshot
    ): Boolean {
        val turno1passato = turno.replace("_$grado", "_1")
        val turno2passato = turno.replace("_$grado", "_2")
        val turno3passato = turno.replace("_$grado", "_3")
        val risultato = when (cognomeNomeSpinner) {
            document.getString(turno1passato) -> false
            document.getString(turno2passato) -> false
            document.getString(turno3passato) -> false
            else -> {
                true
            }
        }
        return risultato
    }

    /*
    Metodo per aggiungere un oggetto generrico alla collection passata tramite valore String
     */
    fun <T : Any> aggiungi_documento_a_db(ogg: T, collection: String) {
        db.collection(collection)
            .add(ogg)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    /*
    Metodo per cancellare documento da db
     */
    fun cancella_documento_da_db(collection: String, documentId: String) {
        db.collection(collection).document(documentId)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    /*
    Metodo per registrare le disponibilità nel db
     */
    fun disponibilita_btn(cognomeNomeSpinner: String, root: View, nome_tipo_settimana: String) {
        val docRef = db.collection("tabelle").document(nome_tipo_settimana)
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                val turno = TabelloneTurni().rileva_valori_spinner(root)
                val dataDisponibilita = costruisci_data_disponibilita_in_long(document, turno)
                costruisci_trasmetti_disponibilita(
                    dataDisponibilita,
                    cognomeNomeSpinner,
                    nome_tipo_settimana + "_" + turno
                )
            } else {
                Log.d(TAG, "No such document")
            }
        }.addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
    }


    /*
    Metodo per costruire la data della disponibilità del milite, si registra il long.
    Nel metodo bisogna passare un turno e grazie a questo nome si riesce a trovare la data
    corrispondente che si associa ad esso.
    */
    private fun costruisci_data_disponibilita_in_long(
        document: DocumentSnapshot,
        turno: String
    ): Long {
        val giorno = trova_giorno_dal_campo(turno)
        val data_estratta = when (giorno) {
            "lun" -> document.getTimestamp("data_lunedi")
            "mar" -> document.getTimestamp("data_martedi")
            "mer" -> document.getTimestamp("data_mercoledi")
            "gio" -> document.getTimestamp("data_giovedi")
            "ven" -> document.getTimestamp("data_venerdi")
            "sab" -> document.getTimestamp("data_sabato")
            "dom" -> document.getTimestamp("data_domenica")
            else -> {
                document.getTimestamp("data_lunedi")
            }
        }
        val data_long = data_estratta!!.seconds * 1000
        return data_long
    }


    /*
    Metodo per costruire un oggetto disponibilità e mandartlo nel DB
     */
    private fun costruisci_trasmetti_disponibilita(
        dataDisponibilita: Long?,
        cognomeNomeSpinner: String,
        turno: String
    ) {
        val collection = "disponibilità"

        class Disponibilita {
            var nomeCognomeSpinner: String? = null
            var dataDisponibilita: Long? = null
            var turnoDisponibilita: String? = null
        }

        val ogg = Disponibilita()
        ogg.nomeCognomeSpinner = cognomeNomeSpinner
        ogg.dataDisponibilita = dataDisponibilita
        ogg.turnoDisponibilita = turno
        aggiungi_documento_a_db(ogg, collection)
    }


    /*
    Metodo per verificare il gradfo del milite e se ritorna true allora il milite è autorizzato a prenotarsi nel turno
     */
    private fun recupera_milite_gestisci_verifica(
        cognomeNomeSpinner: String,
        turno: String,
        document: DocumentSnapshot,
        nome_tipo_tabella: String,
        root: View, act: Activity
    ) {
        db.collection("militi")
            .get()
            .addOnSuccessListener { result ->
                for (milite in result) {
                    Log.d(TAG, "${milite.id} => ${milite.data}")
                }
                for (milite in result) {
                    if (milite.getString("cognomeNomeSpinner") == cognomeNomeSpinner) {
                        gestisci_prenotazione_verifica_milite(
                            milite.toObject<Milite>(),
                            turno,
                            document,
                            cognomeNomeSpinner,
                            nome_tipo_tabella, root, act
                        )
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    /*
    Metodo per rimuovere le ore lavorate da un milite se si cancella da un turno
     */
    private fun rimuovi_ore_lavorate(cognomeNomeSpinner: String, turno: String) {
        db.collection("militi")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                for (document in result) {
                    if (document.getString("cognomeNomeSpinner") == cognomeNomeSpinner) {
                        val militeRef = db.collection("militi").document(document.id)
                        val risultati = trova_campo(turno)
                        val decrementValue: Long = risultati!!.get(1).toLong() * -1
                        val oreTurno = risultati[0]
                        militeRef.update(oreTurno, FieldValue.increment(decrementValue))
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    /*
    Metodo per aggiornare una tabella con un milite passato tramite il suo cognomeNomeSpinner
    nome = nome del milite
     */
    fun aggiorna_tabellone_milite(nome: String, tabella: String, turno: String, root: View) {
        val washingtonRef = db.collection("tabelle").document(tabella)
        washingtonRef
            .update(turno, nome)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully updated!")
                TabelloneTurni().setta_settiamna_118_h24(root)
                TabelloneTurni().setta_settiamna_118(root)
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    /*
    Metodo che prende un turno e trova il giorno della settimana corrispondente
     */
    fun trova_giorno_dal_campo(turno: String): String? {
        val number = 13
        var str = ""
        if (turno.length == number) {
            return null
        } else if (turno.length > number) {
            str = turno.substring(turno.length - number)
        } else {
            // whatever is appropriate in this case
            throw IllegalArgumentException("word has fewer than 13 characters!")
        }
        val giorno = str.substring(4, 7)
        return giorno
    }

    /*
    Metodo per trovare dove aggiungere le ore che il milite
     */
    fun trova_campo(turno: String): Array<String>? {
        val number = 13
        var str = ""
        if (turno.length == number) {
            return null
        } else if (turno.length > number) {
            str = turno.substring(turno.length - number)
        } else {
            // whatever is appropriate in this case
            throw IllegalArgumentException("word has fewer than 13 characters!")
        }
        val servizio = str.substring(0, 3)
        var orario = str.substring(8, 11)
        orario = when (orario) {
            "mat" -> "7"
            "pom" -> "7"
            "ser" -> "10"
            else -> {
                "0"
            }
        }
        val grado = str.substring(12)
        val formato = servizio + grado
        val turno = when (formato) {
            "1181" -> "oreTurno118prima"
            "1182" -> "oreTurno118seconda"
            "1183" -> "oreTurno118terza"
            "h241" -> "oreTurnoh24prima"
            "h242" -> "oreTurnoh24seconda"
            "h243" -> "oreTurnoh24terza"
            else -> {
                "oreTurnoh24terza"
            }
        }
        return arrayOf(turno, orario, formato, grado)
    }

    /*
    Metodo per aggiungere ad un milite le ore a cui si è prenotato, in questo modo le sue statistiche verranno aggiornate con le ore dovute
     */
    fun aggiungi_ore_lavorate(cognomeNomeSpinner: String, turno: String) {
        db.collection("militi")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                for (document in result) {
                    if (document.getString("cognomeNomeSpinner") == cognomeNomeSpinner) {
                        val militeRef = db.collection("militi").document(document.id)
                        val risultati = trova_campo(turno)
                        val incrementValue: Long = risultati!![1].toLong()
                        val oreTurno = risultati.get(0)
                        militeRef.update(oreTurno, FieldValue.increment(incrementValue))
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    /*
    Metodo per cercare mel database i militi e aggiungerli nella listview del centralinista in modo
    che lui possa vedere i militi disponibili per fare turno
     */
    fun cerca_militi_disponibili(root: View, context: Context) {
        db.collection("disponibilità")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                val disponibilita: MutableList<Disponibilita> = mutableListOf()
                for (document in result) {
                    val dis = document.toObject<Disponibilita>()
                    disponibilita.add(dis)
                }
                carica_disponibilita_nel_fragment(root, disponibilita, context)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }

    }

    /*
    Metodo per caricare la disponibilità dei militi e vedersli in una scrollview
     */
    private fun carica_disponibilita_nel_fragment(
        root: View,
        disponibilita: MutableList<Disponibilita>,
        context: Context
    ) {
        val linearLayout = root.findViewById(R.id.militi_disponibili_layout) as LinearLayout
        disponibilita.forEach {
            it.nomeCognomeSpinner
            val dis = TextView(context)
            var long_time = it.dataDisponibilita
            if (long_time == null) long_time = 0.toLong()
            val localdatatime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(long_time), ZoneOffset.UTC)
            dis.hint =
                "${it.nomeCognomeSpinner}  ${it.turnoDisponibilita}  ${localdatatime.toLocalDate()}"
            dis.textSize = 20F
            linearLayout.addView(dis)
        }
    }

    /*
    Metodo per popoare lo spinner con militi filtrati in base al grado staabilito dal centralinista
     */
    fun popula_spinner_militi_filtrati(servizio: String, grado: String, root: View, act: Activity) {
        db.collection("militi")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                val militi: MutableList<Milite> = mutableListOf()
                for (document in result) {
                    militi.add(document.toObject<Milite>())
                }
                val militi_filtrati: MutableList<Milite> =
                    TabelloneTurni().filtra_militi(militi, servizio, grado)
                val militi_filtrati_string: MutableList<String> = mutableListOf()
                for (milite in militi_filtrati) {
                    if (milite.cognomeNomeSpinner != null)
                        militi_filtrati_string.add(milite.cognomeNomeSpinner!!)
                }
                militi_filtrati_string.sortWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it })
                set_spinner(root, militi_filtrati_string, act)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    /*
    Metodo per aggiornare la tabella H24/118 ìn tempo reale
     */
    fun aggiorna_tabella_118_h24_in_tempo_reale(root: View) {
        val db = Firebase.firestore
        val docRef = db.collection("tabelle").document("tabella_118_h24")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                Log.d(TAG, "Current data: ${snapshot.data}")
                val tabellaRicevuta = snapshot.toObject<Tabella118h24>()
                TabelloneTurni().setta_info_tabella_118_h24(root, tabellaRicevuta)
            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }

    /*
    Metodo per aggiornare la tabella H24/118 ìn tempo reale
     */
    fun aggiorna_tabella_118_in_tempo_reale(root: View) {
        val db = Firebase.firestore
        val docRef = db.collection("tabelle").document("tabella_118")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                Log.d(TAG, "Current data: ${snapshot.data}")
                val tabellaRicevuta = snapshot.toObject<Tabella118>()
                TabelloneTurni().setta_info_tabella_118(root, tabellaRicevuta)
            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }


}
