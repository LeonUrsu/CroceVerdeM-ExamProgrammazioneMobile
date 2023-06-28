package com.example.croceverdeplus

import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.sql.Timestamp
import kotlin.random.Random

class Database {

    private val db = Firebase.firestore

    fun addUser(
        nome: String, cognome: String,
        dataDiNascita: String, residenza: String
    ) {

        val centralinisti = Centralinisti(nome, cognome, dataDiNascita, residenza)

        val username = nome + "." + cognome

        val password = (1..5)
            .map {
                when (Random.nextInt(3)) {
                    0 -> Random.nextInt(48, 58).toChar() // Numeri da '0' a '9'
                    1 -> Random.nextInt(65, 91).toChar() // Lettere maiuscole da 'A' a 'Z'
                    else -> Random.nextInt(97, 123).toChar() // Lettere minuscole da 'a' a 'z'
                }
            }
            .joinToString("")

        val ruolo = "Centralinista"

        db.collection("centralinisti")
            .add(centralinisti)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    ContentValues.TAG,
                    "DocumentSnapshot added with ID: ${documentReference.id}"
                )

                db.collection("centralinisti")
                    .document(documentReference.id).update("username", username)
                db.collection("centralinisti")
                    .document(documentReference.id).update("password", password)
                db.collection("centralinisti")
                    .document(documentReference.id).update("ruolo", ruolo)

            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }

    }

    fun deleteUser(
        nome: String, cognome: String,
        dataDiNascita: String, residenza: String
    ) {

        db.collection("centralinisti")
            .whereEqualTo("nome", nome)
            .whereEqualTo("cognome", cognome)
            .whereEqualTo("dataDiNascita", dataDiNascita)
            .whereEqualTo("residenza", residenza)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(ContentValues.TAG, "${document.data} => ${document.id}")
                    db.collection("centralinisti").document(document.id)
                        .delete()
                        .addOnSuccessListener {
                            Log.d(
                                ContentValues.TAG,
                                "DocumentSnapshot successfully deleted!"
                            )
                        }
                        .addOnFailureListener { e ->
                            Log.w(
                                ContentValues.TAG,
                                "Error deleting document",
                                e
                            )
                        }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }

    }

    fun addUserM(
        nome: String, cognome: String,
        dataDiNascita: String, residenza: String, grado: String
    ) {

        val militi = Militi(nome, cognome, dataDiNascita, residenza, grado)

        val username = nome + "." + cognome

        val password = (1..5)
            .map {
                when (Random.nextInt(3)) {
                    0 -> Random.nextInt(48, 58).toChar() // Numeri da '0' a '9'
                    1 -> Random.nextInt(65, 91).toChar() // Lettere maiuscole da 'A' a 'Z'
                    else -> Random.nextInt(97, 123).toChar() // Lettere minuscole da 'a' a 'z'
                }
            }
            .joinToString("")

        val ruolo = "Milite"

        db.collection("militi")
            .add(militi)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    ContentValues.TAG,
                    "DocumentSnapshot added with ID: ${documentReference.id}"
                )

                db.collection("militi")
                    .document(documentReference.id).update("username", username)
                db.collection("militi")
                    .document(documentReference.id).update("password", password)
                db.collection("militi")
                    .document(documentReference.id).update("ruolo", ruolo)

            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }

    }

    fun deleteUserM(
        nome: String, cognome: String,
        dataDiNascita: String, residenza: String
    ) {

        db.collection("militi")
            .whereEqualTo("nome", nome)
            .whereEqualTo("cognome", cognome)
            .whereEqualTo("dataDiNascita", dataDiNascita)
            .whereEqualTo("residenza", residenza)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(ContentValues.TAG, "${document.data} => ${document.id}")
                    db.collection("militi").document(document.id)
                        .delete()
                        .addOnSuccessListener {
                            Log.d(
                                ContentValues.TAG,
                                "DocumentSnapshot successfully deleted!"
                            )
                        }
                        .addOnFailureListener { e ->
                            Log.w(
                                ContentValues.TAG,
                                "Error deleting document",
                                e
                            )
                        }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
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
    Metodo per ricevere i dati delle due tabelle, deve restituire le due tabelle in un ArrayOf<Tabella>
    in ordine cronologico
    */
    fun ricevi_tabelle(tabella : String): DocumentSnapshot? {
        val docRef = db.collection("tabelle").document(tabella)
        var documento : DocumentSnapshot? = null
        docRef.get()
        .addOnSuccessListener { document ->
            if (document != null) {
                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                documento = document
            } else {
                Log.d(TAG, "No such document")
            }
        }
        .addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
        return documento
    }

    fun ricevi_tabelle_test(root : View, activity: Activity): DocumentSnapshot? {
        val docRef = db.collection("tabelle").document("tabella_118")
        var documento : DocumentSnapshot? = null
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    documento = document
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
        return documento
    }


    /*
    Metodo per segnare un milite nel turno passato tramite id come String, ogni casella ha un suo
    univoco id_casella, return "true" l'operazione è andata a buon fine, return "false" significa cheè già
    presente qualcuno nel turno
     */
    fun segna_milite_nel_turno(id_casella: String, nomeCognome_val: String): Boolean {
        return true // per ora ho impostato un valore fisso di ritorno true
    }

    /*
    Metodo per cancellare un milite nel turno passato tramite id come String, ogni casella ha un suo
    univoco id_casella, return "true" l'operazione è andata a buon fine, return "false" significa che
    non è presente nessun milite nel turno e quindi si è verificato un errore
     */
    fun cancella_milite_nel_turno(id_casella: String, nomeCognome_val: String): Boolean {
        return true // per ora ho impostato un valore fisso di ritorno true
    }
}
