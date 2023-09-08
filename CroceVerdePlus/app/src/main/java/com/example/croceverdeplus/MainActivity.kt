package com.example.croceverdeplus

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.cv_green)))
        setContentView(R.layout.fragment_login)
        title = "CroceVerdePlus"
        val username: EditText = findViewById(R.id.username_input)
        val password: EditText = findViewById(R.id.password_input)
        val button: Button = findViewById(R.id.accedi)
        val loading: ProgressBar = findViewById(R.id.progressBar)


        button.setOnClickListener {

            loading.visibility = View.VISIBLE

            val firestore = FirebaseFirestore.getInstance()

            val militiCollection = firestore.collection("militi")
            val centralinistiCollection = firestore.collection("centralinisti")
            val amministratoriCollection = firestore.collection("amministratori")

            militiCollection.whereEqualTo("username", username.text.toString())
                .whereEqualTo("password", password.text.toString()).get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) { // L'utente è presente nella collezione "militi"
                        val documentId = querySnapshot.documents[0].id

                        militiCollection.document(documentId).get()
                            .addOnSuccessListener { documentSnapshot ->
                                val cognomeNome = documentSnapshot.getString("cognomeNomeSpinner")

                                val volontario = documentSnapshot.getBoolean("volontario") //Ottengo il valore del campo "volontario"
                                //controllo se il milite è un volontario o un dipendente
                                if (volontario == true) {
                                    val intent = Intent(this@MainActivity, MainActivityVolontario::class.java)
                                    intent.putExtra("cognomeNomeSpinner", cognomeNome)
                                    startActivity(intent)
                                } else {
                                    val intent = Intent(this@MainActivity, MainActivityDipendente::class.java)
                                    intent.putExtra("cognomeNomeSpinner", cognomeNome)
                                    startActivity(intent)
                                }
                            }
                    } else {
                        centralinistiCollection.whereEqualTo("username", username.text.toString())
                            .whereEqualTo("password", password.text.toString()).get()
                            .addOnSuccessListener { centralinistiQuerySnapshot ->
                                if (!centralinistiQuerySnapshot.isEmpty) { // L'utente è presente nella collezione "centralinisti"

                                    val documentId = centralinistiQuerySnapshot.documents[0].id

                                    centralinistiCollection.document(documentId).get()
                                        .addOnSuccessListener { documentSnapshot ->
                                            val cognomeNome = documentSnapshot.getString("cognomeNomeSpinner")

                                            val intent = Intent(this@MainActivity, MainActivityCentralinista::class.java)
                                            intent.putExtra("cognomeNomeSpinner", cognomeNome)
                                            startActivity(intent)
                                        }
                                } else {
                                    amministratoriCollection.whereEqualTo(
                                        "username",
                                        username.text.toString()
                                    ).whereEqualTo("password", password.text.toString()).get()
                                        .addOnSuccessListener { querySnapshot ->
                                            if (!querySnapshot.isEmpty) { // L'utente è presente nella collezione "amministratori"

                                                val intent = Intent(this@MainActivity, MainActivityAmministratore::class.java)
                                                startActivity(intent)
                                            }

                                            //gestione degli errori
                                            if (username.text.toString().isNotEmpty() && password.text.toString().isNotEmpty() && querySnapshot.isEmpty){
                                                Toast.makeText(this@MainActivity, "Utente non trovato", Toast.LENGTH_SHORT).show()
                                                loading.visibility = View.INVISIBLE
                                            }
                                            if (username.text.toString().isEmpty() && password.text.toString().isEmpty() && querySnapshot.isEmpty) {
                                                Toast.makeText(this@MainActivity, "Username e Password non inseriti", Toast.LENGTH_SHORT).show()
                                                loading.visibility = View.INVISIBLE
                                            } else {
                                                if (username.text.toString().isEmpty() && querySnapshot.isEmpty){
                                                    Toast.makeText(this@MainActivity, "Username non inserito", Toast.LENGTH_SHORT).show()
                                                    loading.visibility = View.INVISIBLE
                                                } else {
                                                    if (password.text.toString().isEmpty() && querySnapshot.isEmpty) {
                                                        Toast.makeText(this@MainActivity, "Password non inserita", Toast.LENGTH_SHORT).show()
                                                        loading.visibility = View.INVISIBLE
                                                    }
                                                }
                                            }
                                        }
                                }
                            }
                    }
                }
            }
        }

    //utilizzato per nascondere la rotellina quando si cambia schermata
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        val loading: ProgressBar = findViewById(R.id.progressBar)

        if (hasFocus) {
            loading.visibility = View.INVISIBLE
        }
    }

    }