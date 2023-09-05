import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

import 'Amministratore/widget_spinner_amministratore.dart';

class TabelloneTurniAmministratore extends StatefulWidget {
  @override
  _TabelloneTurniAmministratore createState() => _TabelloneTurniAmministratore();
}

class _TabelloneTurniAmministratore extends State<TabelloneTurniAmministratore> {
  @override
  Widget build(BuildContext context) {
    //ottenimento degli argomenti passati
    //final Map<String, dynamic> args = ModalRoute.of(context)!.settings.arguments as Map<String, dynamic>;
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: Text('Tabellone dei Turni'),
        ),
        body: const WidgetSpinnerAmministratore());
  }
}
