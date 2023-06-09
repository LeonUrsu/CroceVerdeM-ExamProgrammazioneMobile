package com.example.croceverdeplus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class CheckList : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
            inflater.inflate(R.layout.fragment_check_list, container, false)
        val linearLayout = root.findViewById(R.id.checkListVolontario) as LinearLayout
        array_from_xml().forEach {
            val check = CheckBox(requireContext())
            check.setHint("$it")
            check.setTextSize(20F)
            linearLayout.addView(check)
        }

        val disponibilita_btn = root.findViewById(R.id.salva) as Button
        disponibilita_btn.setOnClickListener {
            //TODO implementare la rilevazione e il salvataggio nel database
            Toast.makeText(requireActivity(), "Check list salvata", Toast.LENGTH_SHORT).show()
        }
        setta_info_profilo(root)
        return root
    }

    /*
    Metodo che strasforma un array da file xml in un arraylist<String>
     */
    fun array_from_xml(): ArrayList<String> {
        val stringArray: ArrayList<String> =
            resources.getStringArray(R.array.presidi_ambulanza_array).toList() as ArrayList<String>
        return stringArray
    }


    fun setta_info_profilo(root : View){
        //TODO implementare il metodo per ricevere informazioni e settarle
        val nome = "nome"
        val cognome = "cognome"
        val datadinacita = "11/09/2012"
        root.findViewById<TextView>(R.id.nome_text_profilo_volontario).setText(nome)
        root.findViewById<TextView>(R.id.cognome_text_profilo_volontario).setText(cognome)
        root.findViewById<TextView>(R.id.data_di_nascita_text_profilo_volontario).setText(datadinacita)

    }


}