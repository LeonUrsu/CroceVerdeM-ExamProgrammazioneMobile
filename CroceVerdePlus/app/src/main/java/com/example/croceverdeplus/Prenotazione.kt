package com.example.croceverdeplus

import java.time.LocalDateTime
import java.time.ZoneOffset

class Prenotazione(
    var dataPrenotazione: Long = 0,
    var turnoPrenotazione: String = "",
    var cognomeNomeSpinner: String = "",
    var turnoPrenotazioneCompleto : String = ""
)