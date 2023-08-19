import 'dart:math';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

import 'milite.dart';

class AggiungiMilite extends StatefulWidget {
  @override
  _AggiungiMilite createState() => _AggiungiMilite();
}

class _AggiungiMilite extends State<AggiungiMilite> {

  final _nomeController = TextEditingController();
  final _cognomeController = TextEditingController();
  final _datadinascitaController = TextEditingController();
  final _residenzaController = TextEditingController();

  String generaPassword() {
    final random = Random();
    const lunghezza = 5;
    final caratteri = List.generate(lunghezza, (index) {
      switch (random.nextInt(3)) {
        case 0:
          return String.fromCharCode(random.nextInt(10) + 48); // numeri da '0' a '9'
        case 1:
          return String.fromCharCode(random.nextInt(26) + 65); // lettere maiuscole da 'A' a 'Z'
        default:
          return String.fromCharCode(random.nextInt(26) + 97); // lettere minuscole da 'a' a 'z'
      }
    });
    return caratteri.join();
  }

  bool valoreSwitch1 = false;
  bool valoreSwitch2 = false;
  bool valoreSwitch3 = false;
  bool valoreSwitch4 = false;
  bool valoreSwitch5 = false;
  bool valoreSwitch6 = false;


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Creazione Milite'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: EdgeInsets.fromLTRB(16, 85, 16, 50), // Distanza sinistra, sopra, destra, sotto
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                TextField(
                  controller: _nomeController,
                  decoration: InputDecoration(
                    labelText: 'Nome',
                    border: OutlineInputBorder(),
                  ),
                ),
                SizedBox(height: 16),
                TextField(
                  controller: _cognomeController,
                  decoration: InputDecoration(
                    labelText: 'Cognome',
                    border: OutlineInputBorder(),
                  ),
                ),
                SizedBox(height: 16),
                TextField(
                  controller: _datadinascitaController,
                  decoration: InputDecoration(
                    labelText: 'Data di nascita',
                    border: OutlineInputBorder(),
                  ),
                ),
                SizedBox(height: 16),
                TextField(
                  controller: _residenzaController,
                  decoration: InputDecoration(
                    labelText: 'Residenza',
                    border: OutlineInputBorder(),
                  ),
                ),
                SizedBox(height: 30),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    Column(
                      children: [
                        Text('Grado 118 prima'),
                        Switch(
                          value: valoreSwitch1,
                          onChanged: (value) {
                            setState(() {
                              valoreSwitch1 = value;
                            });
                          },
                        ),
                        SizedBox(height: 5),
                        Text('Grado 118 seconda'),
                        Switch(
                          value: valoreSwitch2,
                          onChanged: (value) {
                            setState(() {
                              valoreSwitch2 = value;
                            });
                          },
                        ),
                        SizedBox(height: 5),
                        Text('Grado 118 terza'),
                        Switch(
                          value: valoreSwitch3,
                          onChanged: (value) {
                            setState(() {
                              valoreSwitch3 = value;
                            });
                          },
                        ),
                      ],
                    ),
                    Column(
                      children: [
                        Text('Grado H24 prima'),
                        Switch(
                          value: valoreSwitch4,
                          onChanged: (value) {
                            setState(() {
                              valoreSwitch4 = value;
                            });
                          },
                        ),
                        SizedBox(height: 5),
                        Text('Grado H24 seconda'),
                        Switch(
                          value: valoreSwitch5,
                          onChanged: (value) {
                            setState(() {
                              valoreSwitch5 = value;
                            });
                          },
                        ),
                        SizedBox(height: 5),
                        Text('Grado H24 terza'),
                        Switch(
                          value: valoreSwitch6,
                          onChanged: (value) {
                            setState(() {
                              valoreSwitch6 = value;
                            });
                          },
                        ),
                      ],
                    ),
                  ],
                ),
                SizedBox(height: 30),
                FilledButton(
                  onPressed: () async {
                    String nome = _nomeController.text;
                    String cognome = _cognomeController.text;
                    String dataDiNascita = _datadinascitaController.text;
                    String residenza = _residenzaController.text;

                    if (nome.isNotEmpty && cognome.isNotEmpty &&
                        dataDiNascita.isNotEmpty && residenza.isNotEmpty) {
                      Milite nuovoMilite = Milite(
                        nome: nome,
                        cognome: cognome,
                        dataDiNascita: dataDiNascita,
                        residenza: residenza,
                        cognomeNomeSpinner: '$cognome $nome',
                        username: '$nome.$cognome',
                        password: generaPassword(),
                        grado118prima: valoreSwitch1,
                        grado118seconda: valoreSwitch2,
                        grado118terza: valoreSwitch3,
                        gradoh24prima: valoreSwitch4,
                        gradoh24seconda: valoreSwitch5,
                        gradoh24terza: valoreSwitch6,
                      );
                      Map<String, dynamic> militeMap = nuovoMilite.toMap();//permette interazioni con il DB mappando il milite
                      // crea un nuovo documento (milite) nella collezione "militi"
                      await FirebaseFirestore.instance.collection('militi').add(militeMap);

                      Fluttertoast.showToast(
                        msg: 'Milite Creato',
                        toastLength: Toast.LENGTH_SHORT,
                        gravity: ToastGravity.BOTTOM,
                        timeInSecForIosWeb: 1,
                        backgroundColor: Colors.grey,
                        textColor: Colors.white,
                        fontSize: 16.0,
                      );
                    } else {
                      Fluttertoast.showToast(
                        msg: 'Tutti i campi devono essere compilati',
                        toastLength: Toast.LENGTH_SHORT,
                        gravity: ToastGravity.BOTTOM,
                        timeInSecForIosWeb: 1,
                        backgroundColor: Colors.grey,
                        textColor: Colors.white,
                        fontSize: 16.0,
                      );
                    }
                  },
                  child: Text('Crea'),
                  style: ButtonStyle(
                    fixedSize: MaterialStateProperty.all<Size>(
                      Size(100, 43),
                    ),
                  ),
                ),
                //SizedBox(height: 50),
                //CircularProgressIndicator()
              ],
            ),
          ),
        ),
      ),
    );
  }
}