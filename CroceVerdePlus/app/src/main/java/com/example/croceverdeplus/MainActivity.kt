package com.example.croceverdeplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)
        title = "CroceVerdePlus"

        val username: EditText = findViewById(R.id.username_input)
        val password: EditText = findViewById(R.id.password_input)
        val button: Button = findViewById(R.id.accedi)
        val l = LogIn()

        button.setOnClickListener {

            if (l.gestioneAccesso(username, password) == 1) {
                val intent = Intent(this@MainActivity, MainActivityAmministratore::class.java)
                startActivity(intent)
            }
            if (l.gestioneAccesso(username, password) == 2) {
                val intent = Intent(this@MainActivity, MainActivityCentralinista::class.java)
                startActivity(intent)
            }
            if (l.gestioneAccesso(username, password) == 3) {
                val intent = Intent(this@MainActivity, MainActivityDipendente::class.java)
                startActivity(intent)
            }
            if (l.gestioneAccesso(username, password) == 4) {
                val intent = Intent(this@MainActivity, MainActivityVolontario::class.java)
                startActivity(intent)
            }
        }
    }
}

class LogIn {
    //TODO probabilmente questa classe va dichiarata nel Login.kt e usata tramite import
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
                username.error = "Username non valido";
                password.error = "Password non valida";
            }
        }
        return 0
    }
}
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