import 'package:flutter/material.dart';

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
                      Navigator.pushNamedAndRemoveUntil(
                        context,
                        '/',
                            (route) => false, //tutte le rotte precedenti sono rimosse tranne quella di login
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
                    onPressed: () {
                      Navigator.pushNamedAndRemoveUntil(
                        context,
                        '/',
                            (route) => false, //tutte le rotte precedenti sono rimosse tranne quella di login
                      );
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