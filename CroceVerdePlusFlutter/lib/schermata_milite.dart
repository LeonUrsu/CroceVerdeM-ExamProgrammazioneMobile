import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

class SchermataMilite extends StatefulWidget {
  @override
  _SchermataMilite createState() => _SchermataMilite();
}

class _SchermataMilite extends State<SchermataMilite> {
  @override
  Widget build(BuildContext context) {
    //ottenimento degli argomenti passati
    final Map<String, dynamic> args = ModalRoute.of(context)!.settings.arguments as Map<String, dynamic>;
    final String nome = args['nome'];
    final String cognome = args['cognome'];
    final String dataDiNascita = args['dataDiNascita'];
    final String residenza = args['residenza'];
    final List<Widget> gradi = args['gradi'];

    //mostra popup di conferma eliminazione milite
    void popupEliminazioneMilite(BuildContext context) {
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: Text('Conferma Eliminazione'),
            content: Text('Sei sicuro di voler eliminare questo milite?'),
            actions: <Widget>[
              TextButton(
                child: Text('No'),
                onPressed: () {
                  Navigator.of(context).pop(); //chiusura popup
                },
              ),
              TextButton(
                child: Text('Sì'),
                onPressed: () async {
                  //eliminazione milite
                  QuerySnapshot querySnapshot = await FirebaseFirestore
                      .instance
                      .collection('militi')
                      .where('nome', isEqualTo: nome)
                      .where('cognome', isEqualTo: cognome)
                      .where('dataDiNascita', isEqualTo: dataDiNascita)
                      .where('residenza', isEqualTo: residenza)
                      .get();
                  if (querySnapshot.size > 0) {
                    await querySnapshot.docs[0].reference.delete();
                  }
                  Fluttertoast.showToast(
                    msg: 'Milite Eliminato',
                    toastLength: Toast.LENGTH_SHORT,
                    gravity: ToastGravity.BOTTOM,
                    timeInSecForIosWeb: 1,
                    backgroundColor: Colors.grey,
                    textColor: Colors.white,
                    fontSize: 16.0,
                  );

                  Navigator.of(context).pop(); //chiusura popup
                },
              ),
            ],
          );
        },
      );
    }

    return Scaffold(
      appBar: AppBar(
        title: Text('Dettaglio Milite'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
        body: SingleChildScrollView(
          child: Padding(
            padding: EdgeInsets.fromLTRB(16, 75, 16, 50), // Distanza sinistra, sopra, destra, sotto
            child: Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Image.asset(
                    'assets/account_image.png',
                    width: 200,
                    height: 200,
                  ),
                  SizedBox(height: 60),
                  Text(nome),
                  Text(cognome),
                  Text('Data di nascita: $dataDiNascita'),
                  Text('Residenza: $residenza'),
                  ...gradi,
                  SizedBox(height: 80),
                  FilledButton.tonal(
                    onPressed: () {
                      Navigator.pushNamed(
                        context,
                        '/modifica_milite',
                        arguments: {'nome': nome, 'cognome': cognome, 'dataDiNascita': dataDiNascita,
                          'residenza': residenza},
                      );
                    },
                    child: Text('Modifica Milite'),
                    style: ButtonStyle(
                      fixedSize: MaterialStateProperty.all<Size>(
                        Size(250, 45),
                      ),
                    ),
                  ),
                  SizedBox(height: 30),
                  FilledButton.tonal(
                    onPressed: () async {
                      popupEliminazioneMilite(context);
                    },
                    child: Text('Elimina Milite'),
                    style: ButtonStyle(
                      fixedSize: MaterialStateProperty.all<Size>(
                        Size(250, 45),
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
    );
  }
}
