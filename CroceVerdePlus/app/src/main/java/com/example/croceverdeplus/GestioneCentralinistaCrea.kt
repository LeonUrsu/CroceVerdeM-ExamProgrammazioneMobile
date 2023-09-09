package com.example.croceverdeplus

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController

class GestioneCentralinistaCrea : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_gestione_centralinista_crea, container, false)

        val handler = Handler(Looper.getMainLooper())
        //imposta un ritardo di 1 secondo (1000 millisecondi)
        val delayMillis = 1000L

        val nome: EditText = root.findViewById(R.id.editTextText3)
        val cognome: EditText = root.findViewById(R.id.editTextText2)
        val dataDiNascita: EditText = root.findViewById(R.id.editTextText)
        val residenza: EditText = root.findViewById(R.id.editTextText4)
        val button: Button = root.findViewById(R.id.button4)

        val data = Database()

        button.setOnClickListener{
            //gestione inserimento corretto dei dati
            val nomeText = nome.text.toString().trim()
            val cognomeText = cognome.text.toString().trim()
            val dataDiNascitaText = dataDiNascita.text.toString().trim()
            val residenzaText = residenza.text.toString().trim()

            if(nomeText.isNotEmpty() && cognomeText.isNotEmpty() &&
                dataDiNascitaText.isNotEmpty() && residenzaText.isNotEmpty()) {

                data.addUser(
                    nome.text.toString(), cognome.text.toString(),
                    dataDiNascita.text.toString(), residenza.text.toString()
                )

                Toast.makeText(requireActivity(), "Centralinista creato", Toast.LENGTH_SHORT).show()

                //torno alla lista centralinisti con un ritardo di 1 secondo
                val runnable = Runnable {
                    val navController = root.findNavController()
                    navController.popBackStack(R.id.gestioneTuttiCentralinisti, true)
                    navController.navigate(R.id.gestioneTuttiCentralinisti)
                }
                handler.postDelayed(runnable, delayMillis)

            } else {
                //se almeno uno dei campi è vuoto visualizza questo messaggio
                Toast.makeText(requireActivity(), "Tutti i campi sono obbligatori", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }



}