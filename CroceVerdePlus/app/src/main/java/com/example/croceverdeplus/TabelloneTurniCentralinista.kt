package com.example.croceverdeplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.ViewFlipper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabelloneTurniCentralinista.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabelloneTurniCentralinista : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //findViewById<View>(R.id.tabellone_turni_118).setVisibility(View.VISIBLE)
        //findViewById<View>(R.id.tabellone_turni_h24_118).setVisibility(View.GONE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_tabellone_turni_centralinista, container, false)
        val vf = root.findViewById(R.id.vf) as ViewFlipper
        vf.setDisplayedChild(1); //TODO qui si cambia settimana h24/118 & 118 si passa il valore 1 o 2
        val input_servizio = root.findViewById<Spinner>(R.id.servizio_input)
        return root
    }







}