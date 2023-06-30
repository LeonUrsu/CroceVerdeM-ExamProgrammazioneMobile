package com.example.croceverdeplus

import android.content.ContentValues
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import android.text.format.DateUtils.formatDateRange
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ToggleButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale



class GestioneSettimanaH24 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gestione_settimana_h24, container, false)

        val password: EditText = root.findViewById(R.id.editTextTextPassword)
        val buttonSettimana1: Button = root.findViewById(R.id.button2)
        val buttonSettimana2: Button = root.findViewById(R.id.button3)
        val buttonOk: Button = root.findViewById(R.id.button)
        val db = Firebase.firestore

        val currentDate = Calendar.getInstance().time

        // Data di inizio e fine della settimana corrente
        val currentWeekDates = getWeekDates(currentDate)
        val currentWeekStart = currentWeekDates.first
        val currentWeekEnd = currentWeekDates.second

        // Data di inizio e fine della settimana successiva
        val nextWeekDates = getWeekDates(getNextMonday(currentWeekEnd))
        val nextWeekStart = nextWeekDates.first
        val nextWeekEnd = nextWeekDates.second

        buttonSettimana1.text = "${formatDateRange(currentWeekStart, currentWeekEnd)}"
        buttonSettimana2.text = "${formatDateRange(nextWeekStart, nextWeekEnd)}"

        //Log.d(ContentValues.TAG, "${currentWeekStart}")
        //DEVO CONVERTIRE CURRENTWEEKSTART IN TIMESTAMP


        val selectedButtonColor = Color.parseColor("#2BB107")
        val deselectedButtonColor = Color.TRANSPARENT
        var clickSett1: Boolean? = null
        var clickSett2: Boolean? = null

        setButtonStyle(buttonSettimana1, deselectedButtonColor)
        setButtonStyle(buttonSettimana2, deselectedButtonColor)

        buttonSettimana1.setOnClickListener {
            setButtonStyle(buttonSettimana1, selectedButtonColor)
            setButtonStyle(buttonSettimana2, deselectedButtonColor)
            clickSett1 = true
            clickSett2 = false
        }

        buttonSettimana2.setOnClickListener {
            setButtonStyle(buttonSettimana2, selectedButtonColor)
            setButtonStyle(buttonSettimana1, deselectedButtonColor)
            clickSett1 = false
            clickSett2 = true
        }

        buttonOk.setOnClickListener {
            if (password.text.toString() == "password") {
                if (clickSett1 == true) {
                    db.collection("tabelle")
                        .document("tabella_118").update("data_lunedi", nextWeekStart)
                    db.collection("tabelle")
                        .document("tabella_118_h24").update("data_lunedi", currentWeekStart)

                }
                if (clickSett2 == true) {
                    db.collection("tabelle")
                        .document("tabella_118").update("data_lunedi", currentWeekStart)
                    db.collection("tabelle")
                        .document("tabella_118_h24").update("data_lunedi", nextWeekStart)

                }

            }
        }

        return root
    }

}
private fun setButtonStyle(button: Button, color: Int) {
    button.setBackgroundColor(color)
}


// Ottengo la data di inizio(lunedì) e fine(domenica) settimana in base ad una data specifica(corrente)
private fun getWeekDates(date: Date): Pair<Date, Date> {
    val calendar = Calendar.getInstance()
    calendar.time = date

    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
    // Ottengo la data di inizio settimana (lunedì)
    val weekStart = calendar.time

    // Aggiungo 6 giorni per ottenere la data di fine settimana (domenica)
    calendar.add(Calendar.DAY_OF_WEEK, 6)
    val weekEnd = calendar.time

    return Pair(weekStart, weekEnd)
}

// Ottengo il lunedì successivo a una data specifica
private fun getNextMonday(date: Date): Date {
    val calendar = Calendar.getInstance()
    calendar.time = date

    // Se il giorno corrente è domenica, aggiungo 1 giorno per ottenere il lunedì successivo
    if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
        calendar.add(Calendar.DAY_OF_WEEK, 1)
    }

    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

    return calendar.time
}

// Da coppia di date a string
private fun formatDateRange(start: Date, end: Date): String {
    val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
    return "${dateFormat.format(start)} - ${dateFormat.format(end)}"
}



