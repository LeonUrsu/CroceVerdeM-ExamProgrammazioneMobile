package com.example.croceverdeplus
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)
        title = "CroceVerdePlus"
        val username: EditText = findViewById(R.id.username_input)
        val password: EditText = findViewById(R.id.password_input)
        val button: Button = findViewById(R.id.accedi)
        val loading: ProgressBar = findViewById(R.id.progressBar)


        button.setOnClickListener {

            loading.visibility = View.VISIBLE
            //val tabelloneTurniVolontario = TabelloneTurniVolontario()
            //val profiloVolontarioDipendente = ProfiloVolontarioDipendente()
            //val profiloCentralinista = ProfiloCentralinista()
            //val bundle = Bundle()

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
                                //bundle.putString("cognomeNomeSpinner", cognomeNome)
                                //tabelloneTurniVolontario.arguments = bundle
                                //profiloVolontarioDipendente.arguments = bundle

                                val volontario = documentSnapshot.getBoolean("volontario") //Ottengo il valore del campo "volontario"
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
                                if (!centralinistiQuerySnapshot.isEmpty) {
                                    // L'utente è presente nella collezione "centralinisti"

                                    val documentId = centralinistiQuerySnapshot.documents[0].id

                                    centralinistiCollection.document(documentId).get()
                                        .addOnSuccessListener { documentSnapshot ->
                                            val cognomeNome = documentSnapshot.getString("cognomeNomeSpinner")
                                            //bundle.putString("cognomeNomeSpinner", cognomeNome)
                                            //profiloCentralinista.arguments = bundle

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
                                            if (!querySnapshot.isEmpty) {
                                                // L'utente è presente nella collezione "amministratori"
                                                val intent = Intent(this@MainActivity, MainActivityAmministratore::class.java)
                                                startActivity(intent)

                                            }
                                            if (username.text.toString().isNotEmpty() && password.text.toString().isNotEmpty() && querySnapshot.isEmpty){
                                                Toast.makeText(this@MainActivity, "Utente non trovato", Toast.LENGTH_SHORT).show()
                                                loading.visibility = View.INVISIBLE
                                            }
                                            if (username.text.toString().isEmpty() && password.text.toString().isEmpty() && querySnapshot.isEmpty) {
                                                username.error = "Inserisci username"
                                                password.error = "Inserisci password"
                                                loading.visibility = View.INVISIBLE
                                            }
                                            if (username.text.toString().isEmpty() && querySnapshot.isEmpty){
                                                username.error = "Inserisci username"
                                                loading.visibility = View.INVISIBLE
                                            }
                                            if (password.text.toString().isEmpty() && querySnapshot.isEmpty) {
                                                password.error = "Inserisci password"
                                                loading.visibility = View.INVISIBLE
                                            }
                                        }
                                }
                                if (username.text.toString().isEmpty() && password.text.toString().isEmpty()) {
                                    username.error = "Inserisci username"
                                    password.error = "Inserisci password"
                                    loading.visibility = View.INVISIBLE
                                }
                                if (username.text.toString().isEmpty()){
                                    username.error = "Inserisci username"
                                    loading.visibility = View.INVISIBLE
                                }
                                if (password.text.toString().isEmpty()) {
                                    password.error = "Inserisci password"
                                    loading.visibility = View.INVISIBLE
                                }



                            }
                    }
                }






            /*if (gestioneAccesso(username, password) == 1) {
                    val intent = Intent(this@MainActivity, MainActivityAmministratore::class.java)
                    startActivity(intent)
                }
                if (gestioneAccesso(username, password) == 2) {
                    val intent = Intent(this@MainActivity, MainActivityCentralinista::class.java)
                    startActivity(intent)
                }
                if (gestioneAccesso(username, password) == 3) {
                    val intent = Intent(this@MainActivity, MainActivityDipendente::class.java)
                    startActivity(intent)
                }
                if (gestioneAccesso(username, password) == 4) {
                    val intent = Intent(this@MainActivity, MainActivityVolontario::class.java)
                    startActivity(intent)
                }


             */


            }


        }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        val loading: ProgressBar = findViewById(R.id.progressBar)

        if (hasFocus) {
            loading.visibility = View.INVISIBLE
        }
    }


    }

/*
    fun gestioneAccesso(username: EditText, password: EditText): Int {

        val idUser: Int

        if (username.text.toString() == "a" && password.text.toString() == "") {
            //if (username.text.toString() == "amministratore" && password.text.toString() == "amministratore") {
            idUser = 1
            return idUser
        }
        if (username.text.toString() == "c" && password.text.toString() == "") {
            //if (username.text.toString() == "centralinista" && password.text.toString() == "centralinista") {
            idUser = 2
            return idUser
        }
        if (username.text.toString() == "d" && password.text.toString() == "") {
            //if (username.text.toString() == "dipendente" && password.text.toString() == "dipendente") {
            idUser = 3
            return idUser
        }
        if (username.text.toString() == "v" && password.text.toString() == "") {
            //if (username.text.toString() == "volontario" && password.text.toString() == "volontario") {
            idUser = 4
            return idUser
        } else {
            if (username.text.toString().isEmpty() && password.text.toString().isEmpty()) {
                username.error = "Username non valido"
                password.error = "Password non valida"
            }
        }
        return 0



    }


 */



//android:theme="@style/Theme.AppCompat" ... > riga eliminata da androidmanifest perchè causava un errore gradle

//-----------------------------
//QUI E' STATA CAMBIATA L'ACTIVITY A MainActivityVolontario
//val intent = Intent(this@MainActivity, MainActivityVolontario::class.java)
//val intent = Intent(this@MainActivity, MainActivityDipendente::class.java)
//val intent = Intent(this@MainActivity, MainActivityAmministratore::class.java)
//val intent = Intent(this@MainActivity, MainActivityCentralinista::class.java)
//startActivity(intent)
//-----------------------------


/* QUI CI VA IL CODICE PER LOGGARE L'UTENTE, QUANDO L'UTENTE PREME LOGIUN LA CLASSE LOGIN()
 VIENE ATTIVATA E CONTROLLA L'UTENTE, RITORNA UN VALORE E GRAZIE A QUESTO VALORE VIENE
 STABILITO A QUALE ACTIVITY SI PASSA IL FUNZIONAMENTO DELL'APPLICAZIONE*/

/*val intent = Intent(this@MainActivity, NewActivity::class.java)
            startActivity(intent)*/