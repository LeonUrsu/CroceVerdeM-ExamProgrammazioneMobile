package com.example.croceverdeplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class GestioneCentralinistaCrea : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gestione_centralinista_crea, container, false)

        val nome: EditText = root.findViewById(R.id.editTextText3)
        val cognome: EditText = root.findViewById(R.id.editTextText2)
        val dataDiNascita: EditText = root.findViewById(R.id.editTextDate)
        val residenza: EditText = root.findViewById(R.id.editTextText4)
        val button: Button = root.findViewById(R.id.button4)

        val data = Database()

        button.setOnClickListener{

            data.addUser(nome.text.toString(), cognome.text.toString(),
                            dataDiNascita.text.toString(), residenza.text.toString(), null)

            Toast.makeText(requireActivity(), "Centralinista creato", Toast.LENGTH_SHORT).show()

        }

        return root
    }



}