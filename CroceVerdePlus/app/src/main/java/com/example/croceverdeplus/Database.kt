package com.example.croceverdeplus

import android.content.ContentValues
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Database {

    private val db = Firebase.firestore

    fun addUser(nome: String, cognome: String,
                    dataDiNascita: String, residenza: String) {
    //username: String, password: String,
        val user = User(nome, cognome, dataDiNascita, residenza)

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                //Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }

    }

    fun deleteUser(username: String, password: String) {

        db.collection("users")
            .whereEqualTo("username", username)
            .whereEqualTo("password", password)
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
}