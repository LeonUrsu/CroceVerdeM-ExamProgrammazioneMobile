import 'package:flutter/material.dart';

import '../widget_tabellone.dart';

class WidgetSpinnerAmministratore extends StatefulWidget {
  @override
  _WidgetSpinnerAmministratore createState() => _WidgetSpinnerAmministratore();
}

class _WidgetSpinnerAmministratore extends State<WidgetSpinnerAmministratore> {
  //variabili per gli spinner (con valori std)
  String valoreServizio = "118";
  String valoreGiorno = "lun";
  String valoreOrario = "mat";
  String valoreGrado = "1";

  //variabili da inserire negli spinner
  List list_servizio = ["h24", "118"];
  List list_giorno = [
    "lunedì",
    "martedì",
    "mercoledì",
    "giovedì",
    "venerdì",
    "sabato",
    "domenica"
  ];
  List list_orario = ["7 - 14", "14 - 21", "21 - 7"];
  List list_grado = ["1a", "2a", "3a"];

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Container(height: 400,child: WidgetTabella(),),
        Center(
            child: Container(
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
                          hint: Text("Servizio"),
                          underline: Container(height: 2, color: Colors.green),
                          onChanged: (String? nuovoServizio) {
                            setState(() {
                              valoreServizio = nuovoServizio!;
                            });
                          },
                          items: const [
                            DropdownMenuItem<String>(
                                value: '118', child: Text("118")),
                            DropdownMenuItem<String>(
                                value: 'H24', child: Text("H24"))
                          ]),
                      DropdownButton<String>(
                          hint: Text("Orario"),
                          underline: Container(height: 2, color: Colors.green),
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
                          ])
                    ],
                  ),
                  Spacer(),
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      DropdownButton<String>(
                          hint: Text("Giorno"),
                          underline: Container(height: 2, color: Colors.green),
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
                          ]),
                      DropdownButton<String>(
                          hint: Text("Grado"),
                          underline: Container(height: 2, color: Colors.green),
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
                          ])
                    ],
                  ),
                ],
              ),
              Row(
                children: [
                  DropdownButton<String>(
                      hint: Text("Milite"),
                      underline: Container(height: 2, color: Colors.green),
                      onChanged: (String? nuovoOrario) {
                        setState(() {
                          valoreOrario = nuovoOrario!;
                        });
                      },
                      items: null),
                  // TODO mettere la lista dei militi che si riempie
                  Spacer()
                ],
              ),
              Spacer(),
              Container(
                width: 230,
                child: ElevatedButton(
                    onPressed: null,
                    child: Text("Segna/Cancella"),
                    style: ElevatedButton.styleFrom(
                      textStyle: TextStyle(fontSize: 18),
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12)),
                    )),
              )
            ],
          ),
        ))
      ],
    );
  }
}
