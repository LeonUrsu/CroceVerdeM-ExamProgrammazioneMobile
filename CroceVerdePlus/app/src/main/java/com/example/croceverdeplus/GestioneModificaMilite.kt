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
import android.widget.Switch
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
        //val spinner: Spinner = root.findViewById(R.id.spinnerMod)
        val switch118prima: Switch = root.findViewById(R.id.switch7)
        val switch118seconda: Switch = root.findViewById(R.id.switch8)
        val switch118terza: Switch = root.findViewById(R.id.switch13)
        val switchH24prima: Switch = root.findViewById(R.id.switch10)
        val switchH24seconda: Switch = root.findViewById(R.id.switch11)
        val switchH24terza: Switch = root.findViewById(R.id.switch12)
        val buttonModify: Button = root.findViewById(R.id.buttonMiliteMod)
        val db = Firebase.firestore

        val nomeM = args.nome

        val cognomeM = args.cognome

        val dataDiNascitaM = args.dataDiNascita

        val residenzaM = args.residenza


        nome.setText(nomeM)
        cognome.setText(cognomeM)
        dataDiNascita.setText(dataDiNascitaM)
        residenza.setText(residenzaM)




        /*val userGradi = listOf("grado118prima", "grado118seconda", "grado118terza",
            "gradoh24prima","gradoh24seconda","gradoh24terza")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, userGradi)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedGrado = parent.getItemAtPosition(position) as? String



         */

        //val db = Firebase.firestore

        buttonModify.setOnClickListener {

            val switch118primaValue = switch118prima.isChecked
            val switch118secondaValue = switch118seconda.isChecked
            val switch118terzaValue = switch118terza.isChecked
            val switchH24primaValue = switchH24prima.isChecked
            val switchH24secondaValue = switchH24seconda.isChecked
            val switchH24terzaValue = switchH24terza.isChecked

            val nomeSenzaSpazi = nome.text.replace("\\s".toRegex(), "")
            val cognomeSenzaSpazi = cognome.text.replace("\\s".toRegex(), "")

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
                            .document(document.id)
                            .update("dataDiNascita", dataDiNascita.text.toString())
                        db.collection("militi")
                            .document(document.id).update("residenza", residenza.text.toString())
                        db.collection("militi")
                            .document(document.id)
                            .update("username", nomeSenzaSpazi + "." + cognomeSenzaSpazi)
                        db.collection("militi")
                            .document(document.id).update(
                                "cognomeNomeSpinner",
                                cognome.text.toString() + " " + nome.text.toString())

                        db.collection("militi").document(document.id).update("grado118prima", switch118primaValue)
                        db.collection("militi").document(document.id).update("grado118seconda", switch118secondaValue)
                        db.collection("militi").document(document.id).update("grado118terza", switch118terzaValue)
                        db.collection("militi").document(document.id).update("gradoh24prima", switchH24primaValue)
                        db.collection("militi").document(document.id).update("gradoh24seconda", switchH24secondaValue)
                        db.collection("militi").document(document.id).update("gradoh24terza", switchH24terzaValue)

                        /*switch118prima.setOnCheckedChangeListener { _, isChecked ->
                            if (isChecked) {
                                db.collection("militi")
                                    .document(document.id).update("grado118prima", true)
                            } else {
                                db.collection("militi")
                                    .document(document.id).update("grado118prima", false)
                            }
                        }
                        switch118seconda.setOnCheckedChangeListener { _, isChecked ->
                            if (isChecked) {
                                db.collection("militi")
                                    .document(document.id).update("grado118seconda", true)
                            } else {
                                db.collection("militi")
                                    .document(document.id).update("grado118seconda", false)
                            }
                        }
                        switch118terza.setOnCheckedChangeListener { _, isChecked ->
                            if (isChecked) {
                                db.collection("militi")
                                    .document(document.id).update("grado118terza", true)
                            } else {
                                db.collection("militi")
                                    .document(document.id).update("grado118terza", false)
                            }
                        }
                        switchH24prima.setOnCheckedChangeListener { _, isChecked ->
                            if (isChecked) {
                                db.collection("militi")
                                    .document(document.id).update("gradoh24prima", true)
                            } else {
                                db.collection("militi")
                                    .document(document.id).update("gradoh24prima", false)
                            }
                        }
                        switchH24seconda.setOnCheckedChangeListener { _, isChecked ->
                            if (isChecked) {
                                db.collection("militi")
                                    .document(document.id).update("gradoh24seconda", true)
                            } else {
                                db.collection("militi")
                                    .document(document.id).update("gradoh24seconda", false)
                            }
                        }
                        switchH24terza.setOnCheckedChangeListener { _, isChecked ->
                            if (isChecked) {
                                db.collection("militi")
                                    .document(document.id).update("gradoh24terza", true)
                            } else {
                                db.collection("militi")
                                    .document(document.id).update("gradoh24terza", false)
                            }
                        }

                         */
                        /*if (selectedGrado == "grado118prima" ) {
                                    db.collection("militi")
                                        .document(document.id).update("grado118prima", true,
                                            "grado118seconda", false, "grado118terza", false,
                                            "gradoh24prima", false, "gradoh24seconda", false, "gradoh24terza", false)
                                }
                                if (selectedGrado == "grado118seconda" ) {
                                    db.collection("militi")
                                        .document(document.id).update("grado118seconda", false,
                                            "grado118seconda", true, "grado118terza", false,
                                            "gradoh24prima", false, "gradoh24seconda", false, "gradoh24terza", false)
                                }
                                if (selectedGrado == "grado118terza" ) {
                                    db.collection("militi")
                                        .document(document.id).update("grado118terza", false,
                                            "grado118seconda", false, "grado118terza", true,
                                            "gradoh24prima", false, "gradoh24seconda", false, "gradoh24terza", false)
                                }
                                if (selectedGrado == "gradoh24prima" ) {
                                    db.collection("militi")
                                        .document(document.id).update("gradoh24prima", false,
                                            "grado118seconda", false, "grado118terza", false,
                                            "gradoh24prima", true, "gradoh24seconda", false, "gradoh24terza", false)
                                }
                                if (selectedGrado == "gradoh24seconda" ) {
                                    db.collection("militi")
                                        .document(document.id).update("gradoh24seconda", false,
                                            "grado118seconda", false, "grado118terza", false,
                                            "gradoh24prima", false, "gradoh24seconda", true, "gradoh24terza", false)
                                }
                                if (selectedGrado == "gradoh24terza" ) {
                                    db.collection("militi")
                                        .document(document.id).update("gradoh24terza", false,
                                            "grado118seconda", false, "grado118terza", false,
                                            "gradoh24prima", false, "gradoh24seconda", false, "gradoh24terza", true)
                                }

                                 */
                    }

                    Toast.makeText(requireActivity(), "Milite aggiornato", Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                }
        }


    //}

    /*override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(requireActivity(), "Seleziona un grado", Toast.LENGTH_SHORT).show()
            }
        }

             */
        return root
    }
}

