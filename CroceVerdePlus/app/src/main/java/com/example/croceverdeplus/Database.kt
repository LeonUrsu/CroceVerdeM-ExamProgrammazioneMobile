package com.example.croceverdeplus

import android.content.ContentValues
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

class Database {

private val db = Firebase.firestore

    fun addUser(
        nome: String, cognome: String,
        dataDiNascita: String, residenza: String
    ) {

        val user = User(nome, cognome, dataDiNascita, residenza)

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

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    ContentValues.TAG,
                    "DocumentSnapshot added with ID: ${documentReference.id}"
                )

                db.collection("users")
                    .document(documentReference.id).update("username", username)
                db.collection("users")
                    .document(documentReference.id).update("password", password)
                db.collection("users")
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

        db.collection("users")
            .whereEqualTo("nome", nome)
            .whereEqualTo("cognome", cognome)
            .whereEqualTo("dataDiNascita", dataDiNascita)
            .whereEqualTo("residenza", residenza)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(ContentValues.TAG, "${document.data} => ${document.id}")
                    db.collection("users").document(document.id)
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
    fun ricevi_info_tabelle(): Array<Tabella118h24> {
        var tabelle: Array<Tabella118h24> = emptyArray<Tabella118h24>()
        return tabelle
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
