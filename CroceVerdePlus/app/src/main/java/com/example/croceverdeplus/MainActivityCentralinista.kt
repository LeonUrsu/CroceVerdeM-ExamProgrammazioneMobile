package com.example.croceverdeplus
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityCentralinista : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_centralinista)
        bottomNav = findViewById(R.id.bottomNavCentralinista) as BottomNavigationView
        loadFragment(TabelloneTurniCentralinista())
        bottomNav.setSelectedItemId(R.id.tabellone)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tabellone -> {
                    loadFragment(TabelloneTurniCentralinista())
                    true
                }
                R.id.account -> {
                    loadFragment(ProfiloCentralinista())
                    true
                }
                else -> {
                    throw IllegalAccessError()
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    /*
    Metodo per effettuare il logout dal profilo e ritornare alla schermata di login
     */
    fun exit_function() {
        val intent = Intent(this@MainActivityCentralinista, MainActivity::class.java)
        startActivity(intent)
    }

}
