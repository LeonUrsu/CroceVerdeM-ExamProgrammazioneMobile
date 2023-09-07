package com.example.croceverdeplus

import android.content.ContentValues
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class GestioneSettimanaH24 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
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
        //Date dei giorni della settimana corrente
        val currentDays = getDays(currentDate)
        val tuesday = currentDays[0]
        val wednesday = currentDays[1]
        val thursday = currentDays[2]
        val friday = currentDays[3]
        val saturday = currentDays[4]

        // Data di inizio e fine della settimana successiva
        val nextWeekDates = getWeekDates(getNextMonday(currentWeekEnd))
        val nextWeekStart = nextWeekDates.first
        val nextWeekEnd = nextWeekDates.second
        //Date dei giorni della settimana successiva
        val nextDays = getDays(getNextMonday(currentWeekEnd))
        val tuesdayNext = nextDays[0]
        val wednesdayNext = nextDays[1]
        val thursdayNext = nextDays[2]
        val fridayNext = nextDays[3]
        val saturdayNext = nextDays[4]


        buttonSettimana1.text = "${formatDateRange(currentWeekStart, currentWeekEnd)}"
        buttonSettimana2.text = "${formatDateRange(nextWeekStart, nextWeekEnd)}"

        val selectedButtonColor = Color.parseColor("#3FA325")
        val deselectedButtonColor = Color.parseColor("#ACACAC")
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
            db.collection("amministratori").whereEqualTo("password", password.text.toString()).get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        //aggiorno i giorni della settimana delle due tabelle in base alla scelta dell'utente
                        if (clickSett1 == true) {
                            db.collection("tabelle")
                                .document("tabella_118").update("data_lunedi", nextWeekStart,
                                "data_martedi", tuesdayNext,
                                    "data_mercoledi", wednesdayNext,
                                    "data_giovedi", thursdayNext,
                                    "data_venerdi", fridayNext,
                                    "data_sabato", saturdayNext,
                                    "data_domenica", nextWeekEnd)
                            db.collection("tabelle")
                                .document("tabella_118_h24").update("data_lunedi", currentWeekStart,
                                    "data_martedi", tuesday,
                                    "data_mercoledi", wednesday,
                                    "data_giovedi", thursday,
                                    "data_venerdi", friday,
                                    "data_sabato", saturday,
                                    "data_domenica", currentWeekEnd)

                            updateTabelle()

                        }
                        if (clickSett2 == true) {
                            db.collection("tabelle")
                                .document("tabella_118").update("data_lunedi", currentWeekStart,
                                    "data_martedi", tuesday,
                                    "data_mercoledi", wednesday,
                                    "data_giovedi", thursday,
                                    "data_venerdi", friday,
                                    "data_sabato", saturday,
                                    "data_domenica", currentWeekEnd)
                            db.collection("tabelle")
                                .document("tabella_118_h24").update("data_lunedi", nextWeekStart,
                                    "data_martedi", tuesdayNext,
                                    "data_mercoledi", wednesdayNext,
                                    "data_giovedi", thursdayNext,
                                    "data_venerdi", fridayNext,
                                    "data_sabato", saturdayNext,
                                    "data_domenica", nextWeekEnd)

                            updateTabelle()
                        }
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
internal fun getWeekDates(date: Date): Pair<Date, Date> {
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

//ottengo i giorni interni della settimana
private fun getDays(date: Date): List<Any>{
    val calendar = Calendar.getInstance()
    calendar.time = date

    calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY)
    val tuesday = calendar.time
    calendar.add(Calendar.DAY_OF_WEEK, 1)
    val wednesday = calendar.time
    calendar.add(Calendar.DAY_OF_WEEK, 1)
    val thursday = calendar.time
    calendar.add(Calendar.DAY_OF_WEEK, 1)
    val friday = calendar.time
    calendar.add(Calendar.DAY_OF_WEEK, 1)
    val saturday = calendar.time

    return listOf(tuesday, wednesday, thursday, friday, saturday)
}

//utilizzato per formattare in String
private fun formatDateRange(start: Date, end: Date): String {
    val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
    return "${dateFormat.format(start)} - ${dateFormat.format(end)}"
}

//utilizzato per resettare le tabelle
private fun updateTabelle() {
    val db = Firebase.firestore

    val docRef = db.collection("tabelle").document("tabella_118")
    val updates = hashMapOf<String, Any>(
        "turno_118_dom_mat_1" to "",
        "turno_118_dom_mat_2" to "",
        "turno_118_dom_mat_3" to "",
        "turno_118_dom_pom_1" to "",
        "turno_118_dom_pom_2" to "",
        "turno_118_dom_pom_3" to "",
        "turno_118_dom_ser_1" to "",
        "turno_118_dom_ser_2" to "",
        "turno_118_dom_ser_3" to "",

        "turno_118_gio_mat_1" to "",
        "turno_118_gio_mat_2" to "",
        "turno_118_gio_mat_3" to "",
        "turno_118_gio_pom_1" to "",
        "turno_118_gio_pom_2" to "",
        "turno_118_gio_pom_3" to "",
        "turno_118_gio_ser_1" to "",
        "turno_118_gio_ser_2" to "",
        "turno_118_gio_ser_3" to "",

        "turno_118_lun_mat_1" to "",
        "turno_118_lun_mat_2" to "",
        "turno_118_lun_mat_3" to "",
        "turno_118_lun_pom_1" to "",
        "turno_118_lun_pom_2" to "",
        "turno_118_lun_pom_3" to "",
        "turno_118_lun_ser_1" to "",
        "turno_118_lun_ser_2" to "",
        "turno_118_lun_ser_3" to "",

        "turno_118_mar_mat_1" to "",
        "turno_118_mar_mat_2" to "",
        "turno_118_mar_mat_3" to "",
        "turno_118_mar_pom_1" to "",
        "turno_118_mar_pom_2" to "",
        "turno_118_mar_pom_3" to "",
        "turno_118_mar_ser_1" to "",
        "turno_118_mar_ser_2" to "",
        "turno_118_mar_ser_3" to "",

        "turno_118_mer_mat_1" to "",
        "turno_118_mer_mat_2" to "",
        "turno_118_mer_mat_3" to "",
        "turno_118_mer_pom_1" to "",
        "turno_118_mer_pom_2" to "",
        "turno_118_mer_pom_3" to "",
        "turno_118_mer_ser_1" to "",
        "turno_118_mer_ser_2" to "",
        "turno_118_mer_ser_3" to "",

        "turno_118_sab_mat_1" to "",
        "turno_118_sab_mat_2" to "",
        "turno_118_sab_mat_3" to "",
        "turno_118_sab_pom_1" to "",
        "turno_118_sab_pom_2" to "",
        "turno_118_sab_pom_3" to "",
        "turno_118_sab_ser_1" to "",
        "turno_118_sab_ser_2" to "",
        "turno_118_sab_ser_3" to "",

        "turno_118_ven_mat_1" to "",
        "turno_118_ven_mat_2" to "",
        "turno_118_ven_mat_3" to "",
        "turno_118_ven_pom_1" to "",
        "turno_118_ven_pom_2" to "",
        "turno_118_ven_pom_3" to "",
        "turno_118_ven_ser_1" to "",
        "turno_118_ven_ser_2" to "",
        "turno_118_ven_ser_3" to "",
    )

    docRef.update(updates)
        .addOnSuccessListener {
            Log.w(ContentValues.TAG, "Tabella118 aggiornata con successo")
        }
        .addOnFailureListener { e ->
            Log.w(ContentValues.TAG, "Errore nell'aggiornamento della tabella118", e)
        }


    val docRefH24 = db.collection("tabelle").document("tabella_118_h24")
    val updatesH24 = hashMapOf<String, Any>(
        "turno_118_dom_mat_1" to "",
        "turno_118_dom_mat_2" to "",
        "turno_118_dom_mat_3" to "",
        "turno_118_dom_pom_1" to "",
        "turno_118_dom_pom_2" to "",
        "turno_118_dom_pom_3" to "",
        "turno_118_dom_ser_1" to "",
        "turno_118_dom_ser_2" to "",
        "turno_118_dom_ser_3" to "",

        "turno_118_gio_mat_1" to "",
        "turno_118_gio_mat_2" to "",
        "turno_118_gio_mat_3" to "",
        "turno_118_gio_pom_1" to "",
        "turno_118_gio_pom_2" to "",
        "turno_118_gio_pom_3" to "",
        "turno_118_gio_ser_1" to "",
        "turno_118_gio_ser_2" to "",
        "turno_118_gio_ser_3" to "",

        "turno_118_lun_mat_1" to "",
        "turno_118_lun_mat_2" to "",
        "turno_118_lun_mat_3" to "",
        "turno_118_lun_pom_1" to "",
        "turno_118_lun_pom_2" to "",
        "turno_118_lun_pom_3" to "",
        "turno_118_lun_ser_1" to "",
        "turno_118_lun_ser_2" to "",
        "turno_118_lun_ser_3" to "",

        "turno_118_mar_mat_1" to "",
        "turno_118_mar_mat_2" to "",
        "turno_118_mar_mat_3" to "",
        "turno_118_mar_pom_1" to "",
        "turno_118_mar_pom_2" to "",
        "turno_118_mar_pom_3" to "",
        "turno_118_mar_ser_1" to "",
        "turno_118_mar_ser_2" to "",
        "turno_118_mar_ser_3" to "",

        "turno_118_mer_mat_1" to "",
        "turno_118_mer_mat_2" to "",
        "turno_118_mer_mat_3" to "",
        "turno_118_mer_pom_1" to "",
        "turno_118_mer_pom_2" to "",
        "turno_118_mer_pom_3" to "",
        "turno_118_mer_ser_1" to "",
        "turno_118_mer_ser_2" to "",
        "turno_118_mer_ser_3" to "",

        "turno_118_sab_mat_1" to "",
        "turno_118_sab_mat_2" to "",
        "turno_118_sab_mat_3" to "",
        "turno_118_sab_pom_1" to "",
        "turno_118_sab_pom_2" to "",
        "turno_118_sab_pom_3" to "",
        "turno_118_sab_ser_1" to "",
        "turno_118_sab_ser_2" to "",
        "turno_118_sab_ser_3" to "",

        "turno_118_ven_mat_1" to "",
        "turno_118_ven_mat_2" to "",
        "turno_118_ven_mat_3" to "",
        "turno_118_ven_pom_1" to "",
        "turno_118_ven_pom_2" to "",
        "turno_118_ven_pom_3" to "",
        "turno_118_ven_ser_1" to "",
        "turno_118_ven_ser_2" to "",
        "turno_118_ven_ser_3" to "",


        "turno_h24_dom_mat_1" to "",
        "turno_h24_dom_mat_2" to "",
        "turno_h24_dom_mat_3" to "",
        "turno_h24_dom_pom_1" to "",
        "turno_h24_dom_pom_2" to "",
        "turno_h24_dom_pom_3" to "",
        "turno_h24_dom_ser_1" to "",
        "turno_h24_dom_ser_2" to "",
        "turno_h24_dom_ser_3" to "",

        "turno_h24_gio_mat_1" to "",
        "turno_h24_gio_mat_2" to "",
        "turno_h24_gio_mat_3" to "",
        "turno_h24_gio_pom_1" to "",
        "turno_h24_gio_pom_2" to "",
        "turno_h24_gio_pom_3" to "",
        "turno_h24_gio_ser_1" to "",
        "turno_h24_gio_ser_2" to "",
        "turno_h24_gio_ser_3" to "",

        "turno_h24_lun_mat_1" to "",
        "turno_h24_lun_mat_2" to "",
        "turno_h24_lun_mat_3" to "",
        "turno_h24_lun_pom_1" to "",
        "turno_h24_lun_pom_2" to "",
        "turno_h24_lun_pom_3" to "",
        "turno_h24_lun_ser_1" to "",
        "turno_h24_lun_ser_2" to "",
        "turno_h24_lun_ser_3" to "",

        "turno_h24_mar_mat_1" to "",
        "turno_h24_mar_mat_2" to "",
        "turno_h24_mar_mat_3" to "",
        "turno_h24_mar_pom_1" to "",
        "turno_h24_mar_pom_2" to "",
        "turno_h24_mar_pom_3" to "",
        "turno_h24_mar_ser_1" to "",
        "turno_h24_mar_ser_2" to "",
        "turno_h24_mar_ser_3" to "",

        "turno_h24_mer_mat_1" to "",
        "turno_h24_mer_mat_2" to "",
        "turno_h24_mer_mat_3" to "",
        "turno_h24_mer_pom_1" to "",
        "turno_h24_mer_pom_2" to "",
        "turno_h24_mer_pom_3" to "",
        "turno_h24_mer_ser_1" to "",
        "turno_h24_mer_ser_2" to "",
        "turno_h24_mer_ser_3" to "",

        "turno_h24_sab_mat_1" to "",
        "turno_h24_sab_mat_2" to "",
        "turno_h24_sab_mat_3" to "",
        "turno_h24_sab_pom_1" to "",
        "turno_h24_sab_pom_2" to "",
        "turno_h24_sab_pom_3" to "",
        "turno_h24_sab_ser_1" to "",
        "turno_h24_sab_ser_2" to "",
        "turno_h24_sab_ser_3" to "",

        "turno_h24_ven_mat_1" to "",
        "turno_h24_ven_mat_2" to "",
        "turno_h24_ven_mat_3" to "",
        "turno_h24_ven_pom_1" to "",
        "turno_h24_ven_pom_2" to "",
        "turno_h24_ven_pom_3" to "",
        "turno_h24_ven_ser_1" to "",
        "turno_h24_ven_ser_2" to "",
        "turno_h24_ven_ser_3" to "",

    )

    docRefH24.update(updatesH24)
        .addOnSuccessListener {
            Log.w(ContentValues.TAG, "Tabella118H24 aggiornata con successo")
        }
        .addOnFailureListener { e ->
            Log.w(ContentValues.TAG, "Errore nell'aggiornamento della tabella118H24", e)
        }
}


