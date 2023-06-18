package com.example.croceverdeplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class GestioneMiliteModificaCrea : Fragment() {

    private var selectedGrado: Grado? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =  inflater.inflate(R.layout.fragment_gestione_milite_modifica_crea, container, false)

        val nome: EditText = root.findViewById(R.id.editTextnome)
        val cognome: EditText = root.findViewById(R.id.editTextcognome)
        val dataDiNascita: EditText = root.findViewById(R.id.editTextdata)
        val residenza: EditText = root.findViewById(R.id.editTextresidenza)
        val button: Button = root.findViewById(R.id.buttonMilite)

        val spinner: Spinner = root.findViewById(R.id.spinner)
        val userList = listOf(User())
        val userGradi = userList.map { it.grado }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, userGradi)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedGrado = parent.getItemAtPosition(position) as Grado
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(requireActivity(), "Seleziona un grado", Toast.LENGTH_SHORT).show()
            }
        }

        val data = Database()

        button.setOnClickListener{

            data.addUser(nome.text.toString(), cognome.text.toString(),
                dataDiNascita.text.toString(), residenza.text.toString(),
                selectedGrado)

            Toast.makeText(requireActivity(), "Milite creato", Toast.LENGTH_SHORT).show()

        }


        return root
    }

}