package com.example.croceverdeplus

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class GestioneModificaMilite : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gestione_modifica_milite, container, false)

        val args: GestioneModificaMiliteArgs by navArgs()

        val nome: EditText = root.findViewById(R.id.editTextnomeMod)
        val cognome: EditText = root.findViewById(R.id.editTextcognomeMod)
        val dataDiNascita: EditText = root.findViewById(R.id.editTextDataMod)
        val residenza: EditText = root.findViewById(R.id.editTextresidenzaMod)
        val spinner: Spinner = root.findViewById(R.id.spinnerMod)
        val buttonModify: Button = root.findViewById(R.id.buttonMiliteMod)

        val nomeM = args.nome

        val cognomeM = args.cognome

        val dataDiNascitaM = args.dataDiNascita

        val residenzaM = args.residenza


        val userList = listOf(Militi())
        val userGradi = mutableListOf<String>()

        userList.firstOrNull()?.grado?.forEach { grado ->
            userGradi.add(grado)
        }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, userGradi)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedGrado = parent.getItemAtPosition(position) as? String


                val db = Firebase.firestore

                buttonModify.setOnClickListener{

                    db.collection("militi")
                        .whereEqualTo("nome", nomeM)
                        .whereEqualTo("cognome", cognomeM)
                        .whereEqualTo("dataDiNascita", dataDiNascitaM)
                        .whereEqualTo("residenza", residenzaM)
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                Log.d(ContentValues.TAG, "${document.data} => ${document.id}")
                                db.collection("militi")
                                    .document(document.id).update("nome", nome.text.toString())
                                db.collection("militi")
                                    .document(document.id).update("cognome", cognome.text.toString())
                                db.collection("militi")
                                    .document(document.id).update("dataDiNascita", dataDiNascita.text.toString())
                                db.collection("militi")
                                    .document(document.id).update("residenza", residenza.text.toString())
                                db.collection("militi")
                                    .document(document.id).update("grado", "[$selectedGrado]")
                                db.collection("militi")
                                    .document(document.id).update("username", nome.text.toString() + "." + cognome.text.toString())
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                        }



                    /*data.modifyUserM(nome.text.toString(), cognome.text.toString(),
                        dataDiNascita.text.toString(), residenza.text.toString(),
                        selectedGrado!!
                    )

                    Toast.makeText(requireActivity(), "Milite creato", Toast.LENGTH_SHORT).show()


                     */
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(requireActivity(), "Seleziona un grado", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }
}


