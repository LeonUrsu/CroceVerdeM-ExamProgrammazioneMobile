package com.example.croceverdeplus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment


class TabelloneTurniCentralinista : Fragment() {

    var dropdown_servizio: Spinner? = null
    var dropdown_orario: Spinner? = null
    var dropdown_grado: Spinner? = null
    var dropdown_giorno: Spinner? = null
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


        dropdown_servizio = root.findViewById(R.id.servizio_input);
        dropdown_orario = root.findViewById(R.id.orario_input);
        dropdown_giorno = root.findViewById(R.id.giorno_input);
        dropdown_grado = root.findViewById(R.id.grado_input);
        var servizio_val = initspinnerfooter_servizio(root, R.id.servizio_input, R.array.servzio_input_array, dropdown_servizio)
        var orario_val = initspinnerfooter_servizio(root, R.id.orario_input, R.array.orario_input_array, dropdown_orario)
        var giorno_val = initspinnerfooter_servizio(root, R.id.giorno_input, R.array.giorno_input_array, dropdown_giorno)
        var grado_val = initspinnerfooter_servizio(root, R.id.grado_input, R.array.grado_input_array, dropdown_grado)
        /*
        val servizi = arrayOf("H24", "118")
        val giorno = arrayOf("Lun", "Mar", "Mer", "Gio", "Ven", "Sab", "Dom")
        val orario = arrayOf("mat", "pom", "ser")
        val grado = arrayOf("1","2","3")
        */
        var id = id_builder(servizio_val, giorno_val, orario_val, grado_val)
        return root
    }

    fun id_builder(servizio_val : Int, giorno_val : Int, orario_val : Int, grado_val : Int) : String {
        val servizio = when (servizio_val) { //turno_118_mar_mat_3
            0 -> "h24"
            1 -> "118"
            else -> {"h24"}
        }
        val giorno = when(giorno_val){
            0 -> "lun"
            1 -> "mar"
            2 -> "mer"
            3 -> "gio"
            4 -> "ven"
            5 -> "sab"
            6 -> "dom"
            else -> {"lun"}
        }
        val orario = when(orario_val){
            0 -> "mat"
            1 -> "pom"
            2 -> "ser"
            else -> {"mat"}
        }
        val grado = when(grado_val){
            0 -> "1"
            1 -> "2"
            2 -> "3"
            else -> {"1"}
        }
        return "turno"+"_"+servizio+"_"+giorno_val+"_"+orario_val+"_"+grado_val
    }

    private fun initspinnerfooter_servizio(root : View, spinner : Int, id_array : Int, dropdown : Spinner?) : Int {
        var mTestArray = getResources().getStringArray(id_array);
        var ret_position = 0
        //val adapter = ArrayAdapter(requireActivity(), spinner, mTestArray)//TODO due ricghe non necessarie
        //dropdown?.setAdapter(adapter)
        dropdown?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ){
                root.findViewById<TextView>(R.id.textView5).setText(position.toString())//TODO qui si deve settare il valore di ritorno
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        })
        return ret_position
    }










}
