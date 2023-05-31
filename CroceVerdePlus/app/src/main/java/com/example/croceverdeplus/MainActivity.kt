package com.example.croceverdeplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)
        title = "CroceVerdePlus"

        //-----------------------------
        //QUI E' STATA CAMBIATA L'ACTIVITY A MainActivityVolontario
        val intent = Intent(this@MainActivity, MainActivityVolontario::class.java)
        //val intent = Intent(this@MainActivity, MainActivityDipendente::class.java)
        //val intent = Intent(this@MainActivity, MainActivityAmministratore::class.java)
        //val intent = Intent(this@MainActivity, MainActivityCentralinista::class.java)
        startActivity(intent)
        //-----------------------------

        /* QUI CI VA IL CODICE PER LOGGARE L'UTENTE, QUANDO L'UTENTE PREME LOGIUN LA CLASSE LOGIN()
         VIENE ATTIVATA E CONTROLLA L'UTENTE, RITORNA UN VALORE E GRAZIE A QUESTO VALORE VIENE
         STABILITO A QUALE ACTIVITY SI PASSA IL FUNZIONAMENTO DELL'APPLICAZIONE

        val button:Button = findViewById(R.id.btnOpenAct2)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, NewActivity::class.java)
            startActivity(intent)
        }
        */


    }
}