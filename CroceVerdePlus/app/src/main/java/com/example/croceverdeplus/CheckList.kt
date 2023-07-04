package com.example.croceverdeplus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.time.LocalDateTime
import java.time.ZoneOffset

class CheckList : Fragment() {
    var cognomeNomeSpinner : String = "Rossi Mario" //TODO deve essere passato

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
            inflater.inflate(R.layout.fragment_check_list, container, false)

        val salva_btn = root.findViewById(R.id.salva) as Button
        salva_btn.setOnClickListener { salva_btn_function(root) }
        return root
    }

    fun salva_btn_function(root : View) {

        class ChecklistEseguita{
            var presidi_list: MutableList<String>? = null
            var cognomeNomeSpinner : String? = null
            var data : Long? = null
        }
        val ogg = ChecklistEseguita()
        val now = LocalDateTime.now(ZoneOffset.UTC)
        ogg.data = now.atZone(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()
        ogg.cognomeNomeSpinner = cognomeNomeSpinner
        ogg.presidi_list = conta_presidi(root)
        Database().aggiungi_documento_a_db(ogg, "checklist_eseguite")
        Toast.makeText(requireActivity(), "Checklist salvata", Toast.LENGTH_SHORT).show()
    }





    fun conta_presidi(root: View): MutableList<String> {
        var presidi_list: MutableList<String> = mutableListOf()
        if (root.findViewById<CheckBox>(R.id.checkBox).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox2).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox2).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox3).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox3).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox4).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox4).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox5).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox5).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox6).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox6).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox7).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox7).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox8).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox8).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox9).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox9).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox10).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox10).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox11).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox11).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox12).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox12).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox13).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox13).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox14).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox14).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox15).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox15).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox16).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox16).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox17).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox17).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox18).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox18).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox19).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox19).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox20).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox20).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox21).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox21).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox22).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox22).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox23).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox23).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox24).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox24).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox25).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox25).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox26).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox26).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox27).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox27).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox28).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox28).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox29).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox29).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox30).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox30).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox31).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox31).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox32).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox32).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox33).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox33).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox34).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox34).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox35).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox35).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox36).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox36).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox37).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox37).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox38).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox38).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox39).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox39).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox40).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox40).text.toString())
        if (root.findViewById<CheckBox>(R.id.checkBox41).isChecked)presidi_list.add(root.findViewById<CheckBox>(R.id.checkBox41).text.toString())
        return presidi_list
    }
}