import 'package:croce_verde_plus/tabellone_turni_amministratore.dart';
import 'package:croce_verde_plus/theme/app_themes.dart';
import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';

import 'amministratore.dart';
import 'crea_milite.dart';
import 'gestione_militi.dart';
import 'login.dart';
import 'modifica_milite.dart';
import 'schermata_milite.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: lightTheme,
      initialRoute: '/', // Rotta iniziale
      routes: {
        '/': (context) => Login(),
        '/amministratore': (context) => Amministratore(),
        '/lista_militi':  (context) => GestioneMiliti(),
        '/lista_militi':  (context) => GestioneMiliti(),
        '/crea_milite':  (context) => AggiungiMilite(),
        '/schermata_milite': (context) => SchermataMilite(),
        '/modifica_milite': (context) => ModificaMilite(),
        '/gestisciTurniMiliti': (context) => TabelloneTurniAmministratore(),
      },
    );
  }
}
