package com.example.croceverdeplus

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore

class GestioneTuttiMiliti : Fragment() {

    private lateinit var listView: ListView
    private lateinit var db: FirebaseFirestore
    var selectedItem: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gestione_tutti_militi, container, false)

        val buttonM: Button = root.findViewById(R.id.button6)

        buttonM.setOnClickListener {

            findNavController().navigate(R.id.action_gestioneTuttiMiliti_to_gestioneMiliteModificaCrea)

        }

        listView = root.findViewById(R.id.lista_militi)
        db = FirebaseFirestore.getInstance()
        loadDataFromDB()

        return root
    }

    private fun loadDataFromDB() {
        val usersCollection = db.collection("militi")
        usersCollection.get().addOnSuccessListener { querySnapshot ->
            val userList = mutableListOf<String>()
            for (document in querySnapshot) {
                val nome = document.getString("nome")
                val cognome = document.getString("cognome")
                val dataDiNascita = document.getString("dataDiNascita")
                val residenza = document.getString("residenza")
                val grado118prima = document.getBoolean("grado118prima")
                val grado118seconda = document.getBoolean("grado118seconda")
                val grado118terza = document.getBoolean("grado118terza")
                val gradoh24prima = document.getBoolean("gradoh24prima")
                val gradoh24seconda = document.getBoolean("gradoh24seconda")
                val gradoh24terza = document.getBoolean("gradoh24terza")
                val volontario = document.getBoolean("volontario")
                val dipendente = document.getBoolean("dipendente")

                if (volontario == true) {
                    if (grado118prima == true) {
                        val userInfo = "$nome $cognome $dataDiNascita $residenza Grado118prima Volontario"
                        userList.add(userInfo)
                    }
                    if (grado118seconda == true) {
                        val userInfo = "$nome $cognome $dataDiNascita $residenza Grado118seconda Volontario"
                        userList.add(userInfo)
                    }
                    if (grado118terza == true) {
                        val userInfo = "$nome $cognome $dataDiNascita $residenza Grado118terza Volontario"
                        userList.add(userInfo)
                    }
                    if (gradoh24prima == true) {
                        val userInfo = "$nome $cognome $dataDiNascita $residenza Gradoh24prima Volontario"
                        userList.add(userInfo)
                    }
                    if (gradoh24seconda == true) {
                        val userInfo = "$nome $cognome $dataDiNascita $residenza Gradoh24seconda Volontario"
                        userList.add(userInfo)
                    }
                    if (gradoh24terza == true) {
                        val userInfo = "$nome $cognome $dataDiNascita $residenza Gradoh24terza Volontario"
                        userList.add(userInfo)
                    }
                }
                if (dipendente == true){
                    if (grado118prima == true) {
                        val userInfo = "$nome $cognome $dataDiNascita $residenza Grado118prima Dipendente"
                        userList.add(userInfo)
                    }
                    if (grado118seconda == true) {
                        val userInfo = "$nome $cognome $dataDiNascita $residenza Grado118seconda Dipendente"
                        userList.add(userInfo)
                    }
                    if (grado118terza == true) {
                        val userInfo = "$nome $cognome $dataDiNascita $residenza Grado118terza Dipendente"
                        userList.add(userInfo)
                    }
                    if (gradoh24prima == true) {
                        val userInfo = "$nome $cognome $dataDiNascita $residenza Gradoh24prima Dipendente"
                        userList.add(userInfo)
                    }
                    if (gradoh24seconda == true) {
                        val userInfo = "$nome $cognome $dataDiNascita $residenza Gradoh24seconda Dipendente"
                        userList.add(userInfo)
                    }
                    if (gradoh24terza == true) {
                        val userInfo = "$nome $cognome $dataDiNascita $residenza Gradoh24terza Dipendente"
                        userList.add(userInfo)
                    }
                }

            }
            setupListView(userList)
        }.addOnFailureListener { e->
            Log.w(ContentValues.TAG, "Error getting Data", e)
        }
    }

    private fun setupListView(userList: List<String>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, userList)
        listView.adapter = adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            selectedItem = parent.getItemAtPosition(position) as String
            val selectedUser = userList[position]
            navigateToGestioneMilite(selectedUser)
        }
    }

    private fun navigateToGestioneMilite(selectedUser: String) {
        val action = GestioneTuttiMilitiDirections.actionGestioneTuttiMilitiToGestioneMilite(selectedUser)
        findNavController().navigate(action)
    }


}