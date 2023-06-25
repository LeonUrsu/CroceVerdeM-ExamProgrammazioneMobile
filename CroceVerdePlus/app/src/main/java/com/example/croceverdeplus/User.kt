package com.example.croceverdeplus


data class User(
    val username: String? = null,
    val password: String? = null,
    var nome: String? = null,
    var cognome: String? = null,
    var dataDiNascita: String? = null,
    var residenza: String? = null,
    val ruolo: String? = null,
    var grado: Grado? = null) {

    constructor(  nome: String?,  cognome: String?,
                  dataDiNascita: String?, residenza: String?) : this() {
        this.nome = nome
        this.cognome = cognome
        this.dataDiNascita = dataDiNascita
        this.residenza = residenza

    }

    constructor(  nome: String?,  cognome: String?,
                  dataDiNascita: String?, residenza: String?, grado: Grado?) : this() {
        this.nome = nome
        this.cognome = cognome
        this.dataDiNascita = dataDiNascita
        this.residenza = residenza
        this.grado = grado

    }


}

enum class Grado(val grado: String) {
    grado118prima("grado 118 prima"),
    grado118seconda("grado 118 seconda"),
    grado118terza("grado 118 terza"),
    gradoh24prima("grado h24 prima"),
    gradoh24seconda("grado h24 seconda"),
    gradoh24terza("grado h24 terza")
}