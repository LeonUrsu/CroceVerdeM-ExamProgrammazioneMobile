package com.example.croceverdeplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class GestioneMilite : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gestione_milite, container, false)

        val nome: TextView = root.findViewById(R.id.nome_text_milite)
        val cognome: TextView = root.findViewById(R.id.cognome_text_milite)
        val dataDiNascita: TextView = root.findViewById(R.id.data_di_nascita_text_milite)
        val residenza: TextView = root.findViewById(R.id.indirizzo_milite_text)
        val grado: TextView = root.findViewById(R.id.grado_milite)
        val buttonDelete: Button = root.findViewById(R.id.cancella_milite_btn)
        val buttonModify: Button = root.findViewById(R.id.aggiorna_milite_btn)

        val data = Database()



        buttonDelete.setOnClickListener{

            data.deleteUserM(nome.text.toString(), cognome.text.toString(),
                dataDiNascita.text.toString(), residenza.text.toString(),
                grado.text.toString())

            Toast.makeText(requireActivity(), "Milite eliminato", Toast.LENGTH_SHORT).show()
        }

        return root
    }

}