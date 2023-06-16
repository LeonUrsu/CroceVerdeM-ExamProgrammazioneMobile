package com.example.croceverdeplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class GestioneCentralinista : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gestione_centralinista, container, false)

        val nome: TextView = root.findViewById(R.id.nome_text_centralinista)
        val cognome: TextView = root.findViewById(R.id.cognome_text_centralinista)
        val dataDiNascita: TextView = root.findViewById(R.id.data_di_nascita_text_centralinista)
        val residenza: TextView = root.findViewById(R.id.indirizzo_milite_text)
        val button: Button = root.findViewById(R.id.cancella_centralinista_btn)

        val data = Database()

        button.setOnClickListener{

            data.deleteUser(nome.text.toString(), cognome.text.toString(),
                dataDiNascita.text.toString(), residenza.text.toString())

            Toast.makeText(requireActivity(), "Centralinista eliminato", Toast.LENGTH_SHORT).show()
        }

        return root
    }


}