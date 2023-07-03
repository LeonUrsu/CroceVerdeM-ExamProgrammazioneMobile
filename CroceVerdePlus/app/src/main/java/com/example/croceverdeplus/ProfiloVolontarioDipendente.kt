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

class ProfiloVolontarioDipendente : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(
            R.layout.fragment_profilo_volontario_dipendente,
            container,
            false
        )
        var cognomeNomeSpinner =
            "Di Natale Antonio" //TODO (questo nome deve essere ricevuto in qualche modo per poter ricevere i dati dal database e aggiornare le cose)
        setta_info_profilo(root, cognomeNomeSpinner)
        val logout = root.findViewById(R.id.logout) as Button
        logout.setOnClickListener { exit_function(requireActivity()) }
        return root
    }

    /*
    Metodo per settare il nome e cognome e data di nascita del milite nel suo profilo
     */
    fun setta_info_profilo(root: View, cognomeNomeSpinner: String) {
        val db = Firebase.firestore
        db.collection("militi")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                }
                for (document in result) {
                    if (document.getString("cognomeNomeSpinner") == cognomeNomeSpinner) {
                        root.findViewById<TextView>(R.id.nome_text_profilo_volontario).text =
                            document.getString("nome")
                        root.findViewById<TextView>(R.id.cognome_text_profilo_volontario).text =
                            document.getString("cognome")
                        root.findViewById<TextView>(R.id.data_di_nascita_text_profilo_volontario).text =
                            document.getString("dataDiNascita")
                        root.findViewById<TextView>(R.id.prima_118).text = document.getString("oreTurno118prima")
                        root.findViewById<TextView>(R.id.seconda_118).text = document.getString("oreTurno118seconda")
                        root.findViewById<TextView>(R.id.terza_118).text = document.getString("oreTurno118terza")
                        root.findViewById<TextView>(R.id.prima_h24).text = document.getString("oreTurnoh24prima")
                        root.findViewById<TextView>(R.id.seconda_h24).text = document.getString("oreTurnoh24seconda")
                        root.findViewById<TextView>(R.id.terza_h24).text = document.getString("oreTurnoh24terza")
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }

    fun exit_function(act: Activity) {
        val intent = Intent(act, MainActivity::class.java)
        startActivity(intent)
    }
}