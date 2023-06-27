package com.example.croceverdeplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class GestioneMilite : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gestione_milite, container, false)

        val args: GestioneMiliteArgs by navArgs()

        val nome: TextView = root.findViewById(R.id.nome_text_milite)
        val cognome: TextView = root.findViewById(R.id.cognome_text_milite)
        val dataDiNascita: TextView = root.findViewById(R.id.data_di_nascita_text_milite)
        val residenza: TextView = root.findViewById(R.id.indirizzo_milite_text)
        val grado: TextView = root.findViewById(R.id.grado_milite)
        val buttonDelete: Button = root.findViewById(R.id.cancella_milite_btn)
        val buttonModify: Button = root.findViewById(R.id.aggiorna_milite_btn)

        val selectedUser = args.selectedUser

        val userParts = selectedUser.split(" ")
        nome.text = userParts[0]
        cognome.text = userParts[1]
        dataDiNascita.text = userParts[2]
        residenza.text = userParts[3]
        grado.text = userParts[4]

        val data = Database()



        buttonDelete.setOnClickListener{

            data.deleteUserM(nome.text.toString(), cognome.text.toString(),
                dataDiNascita.text.toString(), residenza.text.toString())

            Toast.makeText(requireActivity(), "Milite eliminato", Toast.LENGTH_SHORT).show()
        }

        buttonModify.setOnClickListener{

            val action = GestioneMiliteDirections.actionGestioneMiliteToGestioneModificaMilite(
                nome.text.toString(), cognome.text.toString(), dataDiNascita.text.toString(),
                residenza.text.toString())
            findNavController().navigate(action)
        }

        return root
    }

}