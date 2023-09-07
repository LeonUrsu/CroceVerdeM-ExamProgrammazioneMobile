package com.example.croceverdeplus

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class GestioneModificaMilite : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_gestione_modifica_milite, container, false)

        val args: GestioneModificaMiliteArgs by navArgs()

        val nome: EditText = root.findViewById(R.id.editTextnomeMod)
        val cognome: EditText = root.findViewById(R.id.editTextcognomeMod)
        val dataDiNascita: EditText = root.findViewById(R.id.editTextDataMod)
        val residenza: EditText = root.findViewById(R.id.editTextresidenzaMod)
        val switch118prima: SwitchCompat = root.findViewById(R.id.switch7)
        val switch118seconda: SwitchCompat = root.findViewById(R.id.switch8)
        val switch118terza: SwitchCompat = root.findViewById(R.id.switch13)
        val switchH24prima: SwitchCompat = root.findViewById(R.id.switch10)
        val switchH24seconda: SwitchCompat = root.findViewById(R.id.switch11)
        val switchH24terza: SwitchCompat = root.findViewById(R.id.switch12)
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

                    }

                    Toast.makeText(requireActivity(), "Milite aggiornato", Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                }
        }

        return root
    }
}

