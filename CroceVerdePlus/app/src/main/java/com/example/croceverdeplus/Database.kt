package com.example.croceverdeplus

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.io.Serializable
import kotlin.random.Random


class Database {

    private val db = Firebase.firestore

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

        val ruolo = "Centralinista"

        db.collection("centralinisti").add(centralinista)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    TAG, "DocumentSnapshot added with ID: ${documentReference.id}"
                )

                db.collection("centralinisti").document(documentReference.id)
                    .update("username", username)
                db.collection("centralinisti").document(documentReference.id)
                    .update("password", password)
                db.collection("centralinisti").document(documentReference.id).update("ruolo", ruolo)

            }.addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }


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
    Metodo per recuperare i militidal db
     */
    fun popula_spinner_militi(root: View, act: Activity) {
        db.collection("militi")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                var militi: MutableList<String> = mutableListOf()
                for (document in result) {
                    var nome_temp = document.getString("cognomeNomeSpinner")
                    if (nome_temp != null)
                        militi.add(nome_temp)
                }
                militi.sortWith(compareBy(String.CASE_INSENSITIVE_ORDER, { it }))
                set_spinner(root, militi, act)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
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


    fun addUserM(
        nome: String, cognome: String, dataDiNascita: String, residenza: String,
        grado118prima: Boolean?, grado118seconda: Boolean?,
        grado118terza: Boolean?, gradoh24prima: Boolean?,
        gradoh24seconda: Boolean?, gradoh24terza: Boolean?
    ) {
        val milite = Milite(
            nome, cognome, dataDiNascita, residenza,
            grado118prima, grado118seconda,
            grado118terza, gradoh24prima,
            gradoh24seconda, gradoh24terza
        )

        val username = nome + "." + cognome

        val password = (1..5).map {
            when (Random.nextInt(3)) {
                0 -> Random.nextInt(48, 58).toChar() // Numeri da '0' a '9'
                1 -> Random.nextInt(65, 91).toChar() // Lettere maiuscole da 'A' a 'Z'
                else -> Random.nextInt(97, 123).toChar() // Lettere minuscole da 'a' a 'z'
            }
        }.joinToString("")

        val cognomenomespinner = cognome + " " + nome

        val ruolo = "Milite"

        db.collection("militi").add(milite).addOnSuccessListener { documentReference ->
            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

            db.collection("militi").document(documentReference.id).update("username", username)
            db.collection("militi").document(documentReference.id).update("password", password)
            db.collection("militi").document(documentReference.id).update("ruolo", ruolo)
            db.collection("militi").document(documentReference.id)
                .update("cognomeNomeSpinner", cognomenomespinner)

        }.addOnFailureListener { e ->
            Log.w(TAG, "Error adding document", e)
        }
    }

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
    Metodo per ricevere un array di tutti i militi presenti nel DB, i militi del
     */
    fun ricevi_array_militi(): ArrayList<Milite> {
        var array_militi = ArrayList<Milite>()
        return array_militi
    }

    /*
    fun trova_tabelle_e_date(): Boolean {
        var documento1 = ricevi_tabella_118_h24()
        var documento2 = ricevi_tabella_118_h24()
        if (documento1 == null || documento2 == null) return false
        var time1 = documento1.data_lunedi
        var time2 = documento2.data_lunedi
        if (time1 == null || time2 == null) return false
        var comparato = time1.compareTo(time2)
        if (comparato <= 0) {
            settimana1 = documento2
            settimana2 = documento1
            data_lunedi_settimana1 = Timestamp(time1.seconds * 1000)
            data_lunedi_settimana2 = Timestamp(time2.seconds * 1000)
        } else {
            settimana1 = documento2
            settimana2 = documento1
            data_lunedi_settimana1 = Timestamp(time2.seconds * 1000)
            data_lunedi_settimana2 = Timestamp(time1.seconds * 1000)
        }
        return true
    }
     */

    /*
    Metodo per ricevere i dati delle due tabelle, deve restituire le due tabelle in un ArrayOf<Tabella>
    in ordine cronologico
    */
    fun ricevi_tabella_118_h24() {
        val docRef = db.collection("tabelle").document("tabella_118_h24")
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                document.toObject<Tabella118>()
            } else {
                Log.d(TAG, "No such document")
            }
        }.addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
    }

    /*
    Metodo per ricevere i dati delle due tabelle, deve restituire le due tabelle in un ArrayOf<Tabella>
    in ordine cronologico
    */
    fun ricevi_tabella_118() {
        val docRef = db.collection("tabelle").document("tabella_118")
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                document.toObject<Tabella118>()
                Log.d(TAG, "No such document")
            }
        }.addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
    }

    /*
    Metodo per segnare o cancellare un milite dal turno,  se il milite è presente nel tunro oppure non si trova nel turno
     */
    fun segna_o_cancella_milite_dal_turno(
        tabella: String,
        turno: String,
        nomeCognomeSpinner: String
    ) {
        //TODO (In questo punto prima di fare qulasiasi cosa bisogna controllare se il milite ha il grado necesario per iscriversi al turno)
        val docRef = db.collection("tabelle").document(tabella)
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                if (document.getString(turno) == "") {
                    var autorizzazioneMilite: Boolean = varifica_grado_milite(document, turno)
                    if(autorizzazioneMilite)
                        segna_milite(nomeCognomeSpinner, tabella, turno)
                        aggiungi_ore_lavorate(nomeCognomeSpinner, turno)
                } else
                    if (document.getString(turno) == nomeCognomeSpinner) {
                        cancella_milite(nomeCognomeSpinner, turno)
                        rimuovi_ore_lavorate(nomeCognomeSpinner, turno)

                    }
            } else {
                Log.d(TAG, "No such document")
            }
        }.addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
    }

    /*
    Metodo per verificare il gradfo del milite e se ritorna true allora il milite è autorizzato a prenotarsi nel turno
     */
    private fun varifica_grado_milite(document: DocumentSnapshot, turno: String): Boolean {
        var turno = trova_campo(turno)!![0]
        turno = when (turno) {
            "1181" -> "grado118prima"
            "1182" -> "grado118seconda"
            "1183" -> "grado118terza"
            "h241" -> "gradoh24prima"
            "h242" -> "gradoh24seconda"
            "h243" -> "gradoh24terza"
            else -> {
                "gradoh24terza"
            }
        }
        var risultato = document.getBoolean(turno)
        if (risultato != null && risultato == true)
            return true
        else return false
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
                        var decrementValue: Long = risultati!!.get(1).toLong() * -1
                        var oreTurno = risultati.get(0)
                        militeRef.update(oreTurno, FieldValue.increment(decrementValue))
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    /*
    Metodo per segnare un milite passato tl tabellone dei turni e aggiungere le sue ore
     */
    fun segna_milite(nome: String, tabella: String, turno: String) {
        aggiorna_tabellone_milite(nome, tabella, turno)
    }

    /*
    Metodo per cancellare un milite dal turno e rimuovere le ore dalla sua banca ore
     */
    fun cancella_milite(tabella: String, turno: String) {
        aggiorna_tabellone_milite("", tabella, turno)
    }

    /*
    Metodo per aggiornare una tabella con un milite passato tramite il suo cognomeNomeSpinner
    nome = nome del milite
     */
    fun aggiorna_tabellone_milite(nome: String, tabella: String, turno: String) {
        val washingtonRef = db.collection("tabelle").document(tabella)

        // Set the "isCapital" field of the city 'DC'
        washingtonRef
            .update(turno, nome)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
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
            throw IllegalArgumentException("word has fewer than 13 characters!");
        }
        var servizio = str.substring(0, 2)
        var orario = str.substring(8, 10)
        orario = when (orario) {
            "mat" -> "7"
            "pom" -> "7"
            "ser" -> "10"
            else -> {
                "0"
            }
        }
        var grado = str.substring(12)
        var formato = servizio + grado
        var turno = when (formato) {
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
        return arrayOf(turno, orario)
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
                        var incrementValue: Long = risultati!!.get(1).toLong()
                        var oreTurno = risultati.get(0)
                        militeRef.update(oreTurno, FieldValue.increment(incrementValue))
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    fun disponibilita_milite(nome: String) {
        db.collection("")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                var militi: MutableList<String> = mutableListOf<String>()
                //document.toObject<Tabella118>()
                for (document in result) {
                    var nome_temp = document.getString("cognomeNomeSpinner")
                    if (nome_temp != null)
                        militi.add(nome_temp)
                }
                //set_spinner(root, militi, act)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }


}
