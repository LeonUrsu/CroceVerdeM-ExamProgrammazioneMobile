import 'dart:math';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';

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

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Creazione Milite'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: EdgeInsets.fromLTRB(16, 100, 16, 50), // Distanza sinistra, sopra, destra, sotto
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
                SizedBox(height: 50),
                FilledButton(
                  onPressed: () async {
                    String nome = _nomeController.text;
                    String cognome = _cognomeController.text;
                    String dataDiNascita = _datadinascitaController.text;
                    String residenza = _residenzaController.text;

                    Milite nuovoMilite = Milite(
                      nome: nome,
                      cognome: cognome,
                      dataDiNascita: dataDiNascita,
                      residenza: residenza,
                      cognomeNomeSpinner: '$cognome $nome',
                      username: '$nome.$cognome',
                      password: generaPassword(),
                    );
                    Map<String, dynamic> militeMap = nuovoMilite.toMap();//permette interazioni con il DB mappando il milite
                    // crea un nuovo documento (milite) nella collezione "militi"
                    await FirebaseFirestore.instance.collection('militi').add(militeMap);
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