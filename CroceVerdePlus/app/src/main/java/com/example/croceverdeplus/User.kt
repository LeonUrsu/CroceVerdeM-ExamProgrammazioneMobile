package com.example.croceverdeplus


data class User(
    val username: String? = null,
    val password: String? = null,
    var nome: String? = null,
    var cognome: String? = null,
    var dataDiNascita: String? = null,
    var residenza: String? = null,
    val ruolo: String? = null) {

    constructor(  nome: String?,  cognome: String?,
                  dataDiNascita: String?, residenza: String?) : this() {
        this.nome = nome
        this.cognome = cognome
        this.dataDiNascita = dataDiNascita
        this.residenza = residenza

    }
    }
