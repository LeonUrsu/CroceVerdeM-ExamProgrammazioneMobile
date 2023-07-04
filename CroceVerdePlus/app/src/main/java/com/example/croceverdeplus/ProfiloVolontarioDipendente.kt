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
        var root = inflater.inflate(
            R.layout.fragment_profilo_volontario_dipendente,
            container,
            false
        )
        val bundle = arguments
        val variable = bundle?.getString("cognomeNomeSpinner")

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
                        var oreTurno118prima = "oreTurno118prima"
                        var oreTurno118seconda = "oreTurno118seconda"
                        var oreTurno118terza = "oreTurno118terza"
                        var oreTurnoh24prima = "oreTurnoh24prima"
                        var oreTurnoh24seconda = "oreTurnoh24seconda"
                        var oreTurnoh24terza = "oreTurnoh24terza"
                        root.findViewById<TextView>(R.id.prima_118).text =
                            oreTurno118prima + "  " + document.getLong(oreTurno118prima).toString()
                        root.findViewById<TextView>(R.id.seconda_118).text =
                            oreTurno118seconda + "  " + document.getLong(oreTurno118seconda)
                                .toString()
                        root.findViewById<TextView>(R.id.terza_118).text =
                            oreTurno118terza + "  " + document.getLong(oreTurno118terza).toString()
                        root.findViewById<TextView>(R.id.prima_h24).text =
                            oreTurnoh24prima + "  " + document.getLong(oreTurnoh24prima).toString()
                        root.findViewById<TextView>(R.id.seconda_h24).text =
                            oreTurnoh24seconda + "  " + document.getLong(oreTurnoh24seconda)
                                .toString()
                        root.findViewById<TextView>(R.id.terza_h24).text =
                            oreTurnoh24terza + "  " + document.getLong(oreTurnoh24terza).toString()
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