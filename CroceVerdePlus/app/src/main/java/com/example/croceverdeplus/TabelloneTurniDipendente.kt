package com.example.croceverdeplus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment


class TabelloneTurniDipendente : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
            inflater.inflate(R.layout.fragment_tabellone_turni_dipendente, container, false)
        val vf_dipendente = root.findViewById(R.id.vf_dipendente) as ViewFlipper
        vf_dipendente.setDisplayedChild(2); //TODO qui si cambia settimana h24/118 & 118, si passa il valore 1 o 2 una volta implementato il metodo di scelta in amministrator
        //TODO si deve implementare il modo per far vedere la lista dei volontari disponibili ne tabellone turni tramite richiesta al databs

        return root
    }

}
