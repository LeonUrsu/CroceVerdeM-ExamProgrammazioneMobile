package com.example.croceverdeplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class FragmentAmministratore : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_amministratore, container, false)

        val buttonCen: Button = root.findViewById(R.id.button8)
        val buttonMil: Button = root.findViewById(R.id.button7)
        val buttonSett: Button = root.findViewById(R.id.button13)

        buttonCen.setOnClickListener{

            root.findNavController().navigate(R.id.gestioneTuttiCentralinisti)

        }

        buttonMil.setOnClickListener{

            root.findNavController().navigate(R.id.gestioneTuttiMiliti)

        }

        buttonSett.setOnClickListener{

            root.findNavController().navigate(R.id.gestioneSettimanaH24)

        }

        return root
    }
}