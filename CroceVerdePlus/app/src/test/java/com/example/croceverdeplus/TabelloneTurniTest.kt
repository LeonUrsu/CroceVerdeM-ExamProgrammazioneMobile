package com.example.croceverdeplus

import org.junit.Assert
import org.junit.Test

class TabelloneTurniTest {

    @Test
    fun filtra_militi_test() {
        var militi: MutableList<Milite> = mutableListOf()
        val servizio_val: String = "118"
        val grado_val: String = "2a"
        var n = 1
        militi.add(
            Milite(
                "username$n",
                "pass",
                "nome_test$n",
                "cognome_test$n",
                "10/04/1998",
                "residenza$n",
                true,
                false,
                "cogmomeNomeSpinner$n",
                true,
                true,
                true,
                true,
                true,
                true,
                0,
                0,
                0,
                0,
                0,
                0
            )
        )
        for (b in n + 1..10) {
            n = b
            militi.add(
                Milite(
                    "username$b",
                    "pass",
                    "nome_test$b",
                    "cognome_test$b",
                    "10/04/1998",
                    "residenza$b",
                    true,
                    false,
                    "cogmomeNomeSpinner$b",
                    false,
                    false,
                    true,
                    false,
                    true,
                    true,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0
                )
            )
        }
        n += 1
        militi.add(
            Milite(
                "username$n",
                "pass",
                "nome_test$n",
                "cognome_test$n",
                "10/04/1998",
                "residenza$n",
                true,
                false,
                "cogmomeNomeSpinner$n",
                false,
                true,
                true,
                false,
                true,
                true,
                0,
                0,
                0,
                0,
                0,
                0
            )
        )
        n += 1
        militi.add(
            Milite(
                "username$n",
                "pass",
                "nome_test$n",
                "cognome_test$n",
                "10/04/1998",
                "residenza$n",
                true,
                false,
                "cogmomeNomeSpinner$n",
                false,
                false,
                false,
                false,
                false,
                true,
                0,
                0,
                0,
                0,
                0,
                0
            )
        )
        var lista_filtrata = TabelloneTurni().filtra_militi(militi, servizio_val, grado_val)
        Assert.assertEquals(
            2,
            lista_filtrata.size
        ) // si verifica se i militi sono stati filtrati in base al parametro passato
        Assert.assertEquals(12, militi.size)
    }

    @Test
    fun id_builder_test(){
        val servizio_val = "H24"
        val giorno_val = "Domenica"
        val orario_val = "7 - 14"
        val grado_val = "2a"
        val idCostruito = TabelloneTurni().id_builder(servizio_val,giorno_val,orario_val,grado_val)
        Assert.assertEquals("turno_h24_dom_mat_2", idCostruito)
    }

}