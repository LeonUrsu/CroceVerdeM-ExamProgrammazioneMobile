package com.example.croceverdeplus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
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

        val salva_btn = root.findViewById(R.id.salva) as Button
<<<<<<< HEAD
        salva_btn.setOnClickListener { salva_btn_function() }
=======
        salva_btn.setOnClickListener {
            //TODO implementare la rilevazione e il salvataggio nel database
            Toast.makeText(requireActivity(), "Check list salvata", Toast.LENGTH_SHORT).show()
        }
>>>>>>> b9e2d51307e24a17accf43c549339f6db3bd7c40
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

<<<<<<< HEAD
    fun salva_btn_function() {
        //TODO implementare la rilevazione e il salvataggio nel database
        Toast.makeText(requireActivity(), "Check list salvata", Toast.LENGTH_SHORT).show()
    }
=======


>>>>>>> b9e2d51307e24a17accf43c549339f6db3bd7c40


}