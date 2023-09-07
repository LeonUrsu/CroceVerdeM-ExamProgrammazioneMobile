package com.example.croceverdeplus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment

class GestioneMiliteModificaCrea : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val root =  inflater.inflate(R.layout.fragment_gestione_milite_modifica_crea, container, false)

        val nome: EditText = root.findViewById(R.id.editTextnome)
        val cognome: EditText = root.findViewById(R.id.editTextcognome)
        val dataDiNascita: EditText = root.findViewById(R.id.editTextData)
        val residenza: EditText = root.findViewById(R.id.editTextresidenza)
        val button: Button = root.findViewById(R.id.buttonMilite)
        val switch118prima: SwitchCompat = root.findViewById(R.id.switch19)
        val switch118seconda: SwitchCompat = root.findViewById(R.id.switch20)
        val switch118terza: SwitchCompat = root.findViewById(R.id.switch21)
        val switchH24prima: SwitchCompat = root.findViewById(R.id.switch22)
        val switchH24seconda: SwitchCompat = root.findViewById(R.id.switch23)
        val switchH24terza: SwitchCompat = root.findViewById(R.id.switch24)
        val spinnerRuolo: Spinner = root.findViewById(R.id.spinner3)

        val milite = Milite()


        val userRuolo = listOf("Volontario", "Dipendente")

        val adapterRuolo = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, userRuolo)
        adapterRuolo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRuolo.adapter = adapterRuolo

        spinnerRuolo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedRuolo = parent.getItemAtPosition(position) as? String

                if (selectedRuolo == "Volontario"){
                    milite.volontario = true
                    milite.dipendente = false
                }
                if (selectedRuolo == "Dipendente"){
                    milite.volontario = false
                    milite.dipendente = true
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(requireActivity(), "Seleziona un ruolo", Toast.LENGTH_SHORT).show()
            }
        }


        val data = Database()

        button.setOnClickListener{

            val switch118primaValue = switch118prima.isChecked
            val switch118secondaValue = switch118seconda.isChecked
            val switch118terzaValue = switch118terza.isChecked
            val switchH24primaValue = switchH24prima.isChecked
            val switchH24secondaValue = switchH24seconda.isChecked
            val switchH24terzaValue = switchH24terza.isChecked

            data.addUserM(nome.text.toString(), cognome.text.toString(),
                dataDiNascita.text.toString(), residenza.text.toString(),
                switch118primaValue, switch118secondaValue,
                switch118terzaValue, switchH24primaValue,
                switchH24secondaValue, switchH24terzaValue,
                milite.volontario, milite.dipendente)

            Toast.makeText(requireActivity(), "Milite creato", Toast.LENGTH_SHORT).show()

        }

        return root
    }
}