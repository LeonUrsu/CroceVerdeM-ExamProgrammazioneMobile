package com.example.croceverdeplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    //TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO: i seguenti due metodi dovranno essere implementati per cambiare il tabellone dei turni sullo stesso fragment
        //findViewById<View>(R.id.tabellone_turni_118).setVisibility(View.VISIBLE)
        //findViewById<View>(R.id.tabellone_turni_h24_118).setVisibility(View.GONE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabellone_turni_dipendente, container, false)
    }

}