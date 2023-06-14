package com.example.croceverdeplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.ViewFlipper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabelloneTurniDipendente.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabelloneTurniDipendente : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tabellone_turni_dipendente, container, false)
        val vf_dipendente = root.findViewById(R.id.vf_volontario) as ViewFlipper
        val settimana_n_btn = root.findViewById(R.id.settimana_n) as Button
        settimana_n_btn.setOnClickListener {
            vf_dipendente.setDisplayedChild(1); //TODO qui si cambia settimana H24/118 & 118, si passa il valore 1 o 2 una volta implementato il metodo di scelta in amministratore
            //var id = id_builder(servizio_val, giorno_val, orario_val, grado_val)
            //disponibilita_btn_function(id)
            Toast.makeText(requireActivity(), "Settimana cambiata", Toast.LENGTH_SHORT).show()
        }
        val settimana_n_plus_btn = root.findViewById(R.id.settimana_n_plus_1) as Button
        settimana_n_plus_btn.setOnClickListener {
            vf_dipendente.setDisplayedChild(2); //TODO qui si cambia settimana H24/118 & 118, si passa il valore 1 o 2 una volta implementato il metodo di scelta in amministratore
            //var id = id_builder(servizio_val, giorno_val, orario_val, grado_val)
            //disponibilita_btn_function(id)
            Toast.makeText(requireActivity(), "Settimana cambiata", Toast.LENGTH_SHORT).show()
        }
        return root
    }

}