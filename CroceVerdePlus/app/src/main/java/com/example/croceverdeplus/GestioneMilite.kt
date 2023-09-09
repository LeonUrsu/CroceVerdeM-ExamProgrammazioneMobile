package com.example.croceverdeplus

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class GestioneMilite : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_gestione_milite, container, false)

        val args: GestioneMiliteArgs by navArgs()
        val handler = Handler(Looper.getMainLooper())
        //imposta un ritardo di 1 secondo (1000 millisecondi)
        val delayMillis = 1000L

        val immProfilo: ImageView = root.findViewById(R.id.imageView4)
        immProfilo.setImageResource(R.drawable.accountimage)
        val nome: TextView = root.findViewById(R.id.nome_text_milite)
        val cognome: TextView = root.findViewById(R.id.cognome_text_milite)
        val dataDiNascita: TextView = root.findViewById(R.id.data_di_nascita_text_milite)
        val residenza: TextView = root.findViewById(R.id.indirizzo_milite_text)
        val grado: TextView = root.findViewById(R.id.grado_milite)
        val buttonDelete: Button = root.findViewById(R.id.cancella_milite_btn)
        val buttonModify: Button = root.findViewById(R.id.aggiorna_milite_btn)

        //ottengo i dati relativi al milite selezionato e li inserisco nelle TextView
        val selectedUser = args.selectedUser
        val userParts = selectedUser.split("\n")
        nome.text = userParts[0]
        cognome.text = userParts[1]
        dataDiNascita.text = userParts[2]
        residenza.text = userParts[3]
        if (userParts.size == 5){
            grado.text = "Grado mancante"
        } else {
            val gradoValue = userParts.subList(5, userParts.size).joinToString("\n")
            grado.text = gradoValue
        }

        val data = Database()

        buttonDelete.setOnClickListener {
            val builder = AlertDialog.Builder(requireActivity())
            builder.setTitle("Conferma eliminazione")
            builder.setMessage("Sei sicuro di voler eliminare questo milite?")

            //aggiunge l'opzione "Sì" che eliminerà l'utente
            builder.setPositiveButton("Sì") { _, _ ->

                data.deleteUserM(nome.text.toString(), cognome.text.toString(),
                    dataDiNascita.text.toString(), residenza.text.toString())

                Toast.makeText(requireActivity(), "Milite eliminato", Toast.LENGTH_SHORT).show()

                //torno alla lista militi con un ritardo di 1 secondo
                val runnable = Runnable {
                    val navController = root.findNavController()
                    navController.popBackStack(R.id.gestioneTuttiMiliti, true)
                    navController.navigate(R.id.gestioneTuttiMiliti)
                }
                handler.postDelayed(runnable, delayMillis)

            }
            //aggiunge l'opzione "No" che chiude il popup
            builder.setNegativeButton("No") { _, _ ->
            }

            //mostra il popup di conferma
            val dialog = builder.create()
            dialog.show()
            val negativeButton: Button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
            negativeButton.setTextColor(Color.parseColor("#2e7d19"))
            val positiveButton: Button = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
            positiveButton.setTextColor(Color.parseColor("#2e7d19"))
        }

        buttonModify.setOnClickListener{

            val action = GestioneMiliteDirections.actionGestioneMiliteToGestioneModificaMilite(
                nome.text.toString(), cognome.text.toString(), dataDiNascita.text.toString(),
                residenza.text.toString())
            findNavController().navigate(action)
        }

        return root
    }

}