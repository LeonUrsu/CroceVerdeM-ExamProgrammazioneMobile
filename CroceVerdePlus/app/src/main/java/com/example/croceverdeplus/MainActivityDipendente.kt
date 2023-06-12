package com.example.croceverdeplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityDipendente : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< HEAD
        setContentView(R.layout.activity_main)
        loadFragment(TabelloneTurniDipendente())
        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setSelectedItemId(R.id.tabellone)
=======
        setContentView(R.layout.activity_main_dipendente)
        bottomNav.setSelectedItemId(R.id.tabellone)
        bottomNav = findViewById(R.id.bottomNavDipendente) as BottomNavigationView
>>>>>>> b9e2d51307e24a17accf43c549339f6db3bd7c40
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tabellone -> {
                    loadFragment(TabelloneTurniDipendente())
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

}
