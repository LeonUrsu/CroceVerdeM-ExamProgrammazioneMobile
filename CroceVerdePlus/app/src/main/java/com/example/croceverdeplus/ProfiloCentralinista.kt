package com.example.croceverdeplus

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfiloCentralinista(val cognomeNomeSpinner: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_profilo_centralinista, container, false)
        setta_info_profilo(root, cognomeNomeSpinner)
        root.findViewById<Button>(R.id.logout).setOnClickListener { exit_function(requireActivity()) }
        return root
    }

    fun exit_function(act: Activity) {
        val intent = Intent(act, MainActivity::class.java)
        startActivity(intent)
    }

    /*
    Metodo per settare il nome e cognome e data di nascita del milite nel suo profilo
     */
    fun setta_info_profilo(root: View, cognomeNomeSpinner: String) {
        val db = Firebase.firestore
        db.collection("centralinisti")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                }
                for (document in result) {
                    if (document.getString("cognomeNomeSpinner") == cognomeNomeSpinner) {
                        root.findViewById<TextView>(R.id.nome_text_centralinista).text =
                            document.getString("nome")
                        root.findViewById<TextView>(R.id.cognome_text_centralinista).text =
                            document.getString("cognome")
                        root.findViewById<TextView>(R.id.data_di_nascita_text_centralinista).text =
                            document.getString("dataDiNascita")

                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }
}