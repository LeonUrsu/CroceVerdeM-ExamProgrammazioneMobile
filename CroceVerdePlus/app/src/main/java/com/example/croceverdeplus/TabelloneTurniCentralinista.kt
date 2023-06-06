package com.example.croceverdeplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ViewFlipper

class TabelloneTurniCentralinista : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_tabellone_turni_centralinista, container, false)
        val vf = root.findViewById(R.id.vf) as ViewFlipper
        vf.setDisplayedChild(1); //TODO qui si cambia settimana h24/118 & 118 si passa il valore 1 o 2 una volta implementato il metodo di scelta in amministratore
        //TODO si deve implementare il modo per far vedere la lista dei volontari disponibili ne tabellone turni
        return root
    }

}