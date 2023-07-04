package com.example.croceverdeplus
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityVolontario : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_volontario)

        val cognomeNome = intent.getStringExtra("cognomeNomeSpinner")

        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavVolontario)
        bottomNav.selectedItemId = R.id.tabellone
        loadFragment(TabelloneTurniVolontario())
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tabellone -> {
                    loadFragment(TabelloneTurniVolontario())
                    true
                }
                R.id.account -> {
                    loadFragment(ProfiloVolontarioDipendente())
                    true
                }
                R.id.checklist -> {
                    loadFragment(CheckList())
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
