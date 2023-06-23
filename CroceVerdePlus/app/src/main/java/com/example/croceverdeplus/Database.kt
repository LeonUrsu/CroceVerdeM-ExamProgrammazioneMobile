package com.example.croceverdeplus

import android.content.ContentValues
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Database {

    private val db = Firebase.firestore

    fun addUser(nome: String, cognome: String,
                    dataDiNascita: String, residenza: String, grado: String?) {

        val user = User(nome, cognome, dataDiNascita, residenza, grado)

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }

    }

    fun deleteUser(nome: String, cognome: String,
                   dataDiNascita: String, residenza: String) {

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
                        .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully deleted!") }
                        .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error deleting document", e) }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }

    }

    /*
    Metodo per ricevere un array di tutti i militi presenti nel DB, i militi del
     */
    fun ricevi_array_militi():ArrayList<Milite>{
        var array_militi = ArrayList<Milite>()
        return array_militi
    }

}