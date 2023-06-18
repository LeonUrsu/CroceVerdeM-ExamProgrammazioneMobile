package com.example.croceverdeplus

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore

class GestioneTuttiCentralinisti : Fragment() {

    private lateinit var listView: ListView
    private lateinit var db: FirebaseFirestore
    var selectedItem: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gestione_tutti_centralinisti, container, false)
        listView = root.findViewById(R.id.lista_centralinisti)
        db = FirebaseFirestore.getInstance()
        loadDataFromDB()
        return root
    }

    private fun loadDataFromDB() {
        val usersCollection = db.collection("users")
        usersCollection.get().addOnSuccessListener { querySnapshot ->
            val userList = mutableListOf<String>()
            for (document in querySnapshot) {
                val nome = document.getString("nome")
                val cognome = document.getString("cognome")
                if (nome != null && cognome != null) {
                    val userInfo = "$nome $cognome"
                    userList.add(userInfo)
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
            navigateToGestioneCentralinista(selectedUser)
        }
    }

    private fun navigateToGestioneCentralinista(selectedUser: String) {
        val fragment = GestioneCentralinista()
        val bundle = Bundle()
        bundle.putString("selectedUser", selectedUser)
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, "fragment_gestione_centralinista")
            .addToBackStack(null)
            .commit()
    }
}