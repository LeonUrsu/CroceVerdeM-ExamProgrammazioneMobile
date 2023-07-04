package com.example.croceverdeplus
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityVolontario : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_volontario)
        val cognomeNome = intent.getStringExtra("cognomeNomeSpinner").toString()
        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavVolontario)
        bottomNav.selectedItemId = R.id.tabellone
        loadFragment(TabelloneTurniVolontario(cognomeNome))
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tabellone -> {
                    loadFragment(TabelloneTurniVolontario(cognomeNome))
                    true
                }
                R.id.account -> {
                    loadFragment(ProfiloVolontarioDipendente(cognomeNome))
                    true
                }
                R.id.checklist -> {
                    loadFragment(CheckList(cognomeNome))
                    true
                }
                else -> {throw IllegalAccessError()}
            }
        }
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}
