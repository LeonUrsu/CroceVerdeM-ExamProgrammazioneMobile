import 'package:flutter/material.dart';

class WidgetSpinnerAmministratore extends StatefulWidget {
  @override
  _WidgetSpinnerAmministratore createState() => _WidgetSpinnerAmministratore();
}

class _WidgetSpinnerAmministratore extends State<WidgetSpinnerAmministratore> {
  //variabili per gli spinner
  late String valoreServizio;
  late String valoreGiorno;
  late String valoreOrario;
  late String valoreGrado;

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
    //ottenimento degli argomenti passati
    //final Map<String, dynamic> args = ModalRoute.of(context)!.settings.arguments as Map<String, dynamic>;
    return DropdownButton(hint: Text("Servizio"),
        value: valoreServizio,
        onChanged: (nuovoServizio) {
          setState(() {
            valoreServizio = nuovoServizio.toString();
          });
        },
        items: list_servizio.map((valoreLista) {
          return DropdownMenuItem(value: valoreLista, child: Text(valoreLista));
        }).toList(),);
  }
}
