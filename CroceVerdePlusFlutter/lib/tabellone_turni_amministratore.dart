import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:croce_verde_plus/widget_tabellone.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

class TabelloneTurniAmministratore extends StatefulWidget {
  @override
  _TabelloneTurniAmministratore createState() =>
      _TabelloneTurniAmministratore();
}

class _TabelloneTurniAmministratore
    extends State<TabelloneTurniAmministratore> {
  //variabili per gli spinner (con valori std)
  String valoreServizio = "118";
  String valoreGiorno = "lun";
  String valoreOrario = "mat";
  String valoreGrado = "1";
  String valoreMilite = "0";
  var widgetTabella = WidgetTabella();
  late var dropdownMenuItemServizio = widgetTabella.visualizzaH24118;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Tabellone Turni dei Militi"),
        iconTheme: const IconThemeData(color: Colors.black),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: SingleChildScrollView(
        child: Column(
          children: [
            SizedBox(
              height: 600,
              child: widgetTabella,
            ),
            Center(
                child: SizedBox(
              width: 230,
              height: 210,
              child: Column(
                children: [
                  Row(
                    children: [
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          DropdownButton<String>(
                            hint: const Text("Servizio"),
                            underline:
                                Container(height: 2, color: Colors.green),
                            onChanged: (String? nuovoServizio) {
                              setState(() {
                                valoreServizio = nuovoServizio!;
                              });
                            },
                            items: const [
                              DropdownMenuItem<String>(
                                  value: '118', child: Text("118")),
                              DropdownMenuItem<String>(
                                  value: 'h24', child: Text("H24"))
                            ],
                            value: valoreServizio,
                            isExpanded: false,
                          ),
                          DropdownButton<String>(
                            hint: const Text("Orario"),
                            underline:
                                Container(height: 2, color: Colors.green),
                            onChanged: (String? nuovoOrario) {
                              setState(() {
                                valoreOrario = nuovoOrario!;
                              });
                            },
                            items: const [
                              DropdownMenuItem<String>(
                                  value: 'mat', child: Text("7 - 14")),
                              DropdownMenuItem<String>(
                                  value: 'pom', child: Text("14 - 21")),
                              DropdownMenuItem<String>(
                                  value: 'ser', child: Text("21 - 7"))
                            ],
                            value: valoreOrario,
                            isExpanded: false,
                          )
                        ],
                      ),
                      const Spacer(),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          DropdownButton<String>(
                            hint: const Text("Giorno"),
                            underline:
                                Container(height: 2, color: Colors.green),
                            onChanged: (String? nuovoGiorno) {
                              setState(() {
                                valoreGiorno = nuovoGiorno!;
                              });
                            },
                            items: const [
                              DropdownMenuItem<String>(
                                  value: 'lun', child: Text("lunedì")),
                              DropdownMenuItem<String>(
                                  value: 'mar', child: Text("martedì")),
                              DropdownMenuItem<String>(
                                  value: 'mer', child: Text("mercoledì")),
                              DropdownMenuItem<String>(
                                  value: 'gio', child: Text("giovedì")),
                              DropdownMenuItem<String>(
                                  value: 'ven', child: Text("venerdì")),
                              DropdownMenuItem<String>(
                                  value: 'sab', child: Text("sabato")),
                              DropdownMenuItem<String>(
                                  value: 'dom', child: Text("domenica"))
                            ],
                            value: valoreGiorno,
                            isExpanded: false,
                          ),
                          DropdownButton<String>(
                            underline:
                                Container(height: 2, color: Colors.green),
                            onChanged: (String? nuovoGrado) {
                              setState(() {
                                valoreGrado = nuovoGrado!;
                              });
                            },
                            items: const [
                              DropdownMenuItem<String>(
                                  value: '1', child: Text("1a")),
                              DropdownMenuItem<String>(
                                  value: '2', child: Text("2a")),
                              DropdownMenuItem<String>(
                                  value: '3', child: Text("3a"))
                            ],
                            value: valoreGrado,
                            isExpanded: false,
                          )
                        ],
                      ),
                    ],
                  ),
                  Center(
                      widthFactor: 200,
                      child: SingleChildScrollView(
                        scrollDirection: Axis.vertical,
                        child: Column(
                          children: [
                            StreamBuilder<QuerySnapshot>(
                                stream: FirebaseFirestore.instance
                                    .collection("militi")
                                    .snapshots(),
                                builder: (context, snapshot) {
                                  List<DropdownMenuItem> listaMiliti = [];
                                  if (!snapshot.hasData) {
                                    const CircularProgressIndicator();
                                  } else {
                                    final militi =
                                        snapshot.data?.docs.reversed.toList();
                                    listaMiliti.add(const DropdownMenuItem(
                                        value: "0",
                                        child: Text("Seleziona il Milite")));
                                    for (var milite in militi!) {
                                      listaMiliti.add(DropdownMenuItem(
                                        value: milite.id,
                                        child:
                                            Text(milite["cognomeNomeSpinner"]),
                                      ));
                                    }
                                  }

                                  return DropdownButton(
                                    items: listaMiliti,
                                    underline: Container(
                                        height: 2, color: Colors.green),
                                    onChanged: (nuovoMilite) {
                                      setState(() {
                                        valoreMilite = nuovoMilite;
                                      });
                                    },
                                    value: valoreMilite,
                                    isExpanded: false,
                                  );
                                })
                          ],
                        ),
                      )),
                  const Spacer(),
                  SizedBox(
                    width: 230,
                    child: ElevatedButton(
                        onPressed: () {
                          onPressedSegnaCancella(valoreMilite);
                        },
                        child: const Text("Segna/Cancella")),
                  ),
                ],
              ),
            )),
            const SizedBox(height: 20)
          ],
        ),
      ),
    );
  }

  /*
  Metodo per stabilire il nome della tabella all'interno del db
   */
  String stabilisci_tipo_della_tabella(nomeTabella) {
    if (nomeTabella) {
      return "tabella_118";
    } else {
      return "tabella_118_h24";
    }
  }

  /*
  Metodo per stabilire il nome del turno all'interno del db
   */
  costruisci_nome_turno_db(String valoreServizio, String valoreGiorno,
      String valoreOrario, String valoreGrado) {
    String std = "turno";
    return "${std}_${valoreServizio}_${valoreGiorno}_${valoreOrario}_$valoreGrado";
  }

  /*
  Metodo per calcolare le ore da aggiungere ad ogni milite
  true = se le ore devono essere eliminate
  false = se le ore devono essere aggiunte
   */
  calcolaOreTurno(String valoreOrario, bool boolVar) {
    int valoreStabilito = 0;
    switch (valoreOrario) {
      case "mat":
        valoreStabilito = 7;
        break;
      case "pom":
        valoreStabilito = 7;
        break;
      case "ser":
        valoreStabilito = 10;
        break;
    }
    if (!boolVar) {
      valoreStabilito *= -1;
    }
    return valoreStabilito;
  }

  /*
  Metodo per stabilire i nome del campo per le statistiche del milite
   */
  stabilisci_nome_turno_svolto(String valoreServizio, String valoreGrado) {
    var gradoLetterale = "";
    switch (valoreGrado) {
      case "1":
        gradoLetterale = "prima";
        break;
      case "2":
        gradoLetterale = "seconda";
        break;
      case "3":
        gradoLetterale = "terza";
        break;
    }
    return "oreTurno$valoreServizio$gradoLetterale";
  }

  /*
  Metodo per mostrare a schermo un messagio toast passato per argomento
   */
  void showToastMessage(String messaggio) =>
      Fluttertoast.showToast(msg: messaggio);

  /*
  Metodo per gestire la prenotazione o la cancellazione del milite nel db
   */
  gestisci_prenotazione_cancellazione_milite_db(String militeId) {
    var db = FirebaseFirestore.instance;
    final militeRef = db.collection("militi").doc(militeId);
    militeRef.get().then(
      (DocumentSnapshot doc) {
        final milite = doc.data() as Map<String, dynamic>;
        var nomeTabellaDB =
            stabilisci_tipo_della_tabella(widgetTabella.visualizza118);
        final tabellaRef = db.collection("tabelle").doc(nomeTabellaDB);
        tabellaRef.get().then(
          (DocumentSnapshot doc) {
            final tabella = doc.data() as Map<String, dynamic>;
            //INIZIO
            var nomeTurnoTabellaDB = costruisci_nome_turno_db(
                valoreServizio, valoreGiorno, valoreOrario, valoreGrado);
            var oreDelTurno = 0;
            var nomeTurnoStatistiche =
                stabilisci_nome_turno_svolto(valoreServizio, valoreGrado);
            if (tabella[nomeTurnoTabellaDB] == milite["cognomeNomeSpinner"]) {
              //elimina milite dal turno e cancella le ore dal suo profilo
              oreDelTurno = calcolaOreTurno(valoreOrario, true);
              militeRef.update(
                {nomeTurnoStatistiche: FieldValue.increment(oreDelTurno)},
              );
              tabellaRef.update({nomeTurnoTabellaDB: ""});
            } else {
              //segna milite nel turno e aggiungi le ore al suo profilo
              oreDelTurno = calcolaOreTurno(valoreOrario, true);
              militeRef.update(
                {nomeTurnoStatistiche: FieldValue.increment(oreDelTurno)},
              );
              tabellaRef
                  .update({nomeTurnoTabellaDB: milite["cognomeNomeSpinner"]});
            }
          },
        );
      },
    );
  }

  void onPressedSegnaCancella(String valoreMilite) {
    if (valoreMilite == "0") {
      showToastMessage("Milite non selezionato");
      return;
    }
    bool tabellaView = true;
    if (valoreServizio == "h24") {
      tabellaView = (widgetTabella.visualizzaH24118) ? true : false;
    }
    if (tabellaView) {
      gestisci_prenotazione_cancellazione_milite_db(
        valoreMilite,
      );
    } else {
      showToastMessage(
          "Milite non prenotato, la tabella non contiene servizi H24");
      return;
    }
  }

// TODO: Non c'è il controllo per verificare se un milite è già nel turno selezionato prima di eliminarlo e sostituirlo con un altro
// TODO: Quando il milite viene eliminato, le ore lavorate non vengono scalate dal vecchio milite ma vengono solamente aggiunte al nuovo milite
// TODO: Come nell'associazione vera, nell'app sarà a discizione dell'amministratore prenotare i militi nei turni a seconda del grado, il sistema non bloccherà tale scelta se il milite non soddisfa il grado necessario
// TODO: Il sistema non esegue i controllo se un volontario è gia presente nello stesso turno 2 o 3 volte,
}
