package com.example.croceverdeplus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs


class GestioneCentralinista : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gestione_centralinista, container, false)

        val args: GestioneCentralinistaArgs by navArgs()

        val immProfilo: ImageView = root.findViewById(R.id.imageView4)
        immProfilo.setImageResource(R.drawable.accountimage)
        var nome: TextView = root.findViewById(R.id.nome_text_centralinista)
        var cognome: TextView = root.findViewById(R.id.cognome_text_centralinista)
        var dataDiNascita: TextView = root.findViewById(R.id.data_di_nascita_text_centralinista)
        var residenza: TextView = root.findViewById(R.id.indirizzo_milite_text)
        val button: Button = root.findViewById(R.id.cancella_centralinista_btn)

        val selectedUser = args.selectedUser

        val userParts = selectedUser.split(" ")
        nome.text = userParts[0]
        cognome.text = userParts[1]
        dataDiNascita.text = userParts[2]
        residenza.text = userParts[3]

        val data = Database()

        button.setOnClickListener{

            data.deleteUser(nome.text.toString(), cognome.text.toString(),
                dataDiNascita.text.toString(), residenza.text.toString())

            Toast.makeText(requireActivity(), "Centralinista eliminato", Toast.LENGTH_SHORT).show()
        }

        return root
    }


}