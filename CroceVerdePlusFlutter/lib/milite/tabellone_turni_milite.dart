import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:croce_verde_plus/widget_tabellone.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

class TabelloneTurni extends StatefulWidget {
  @override
  _TabelloneTurni createState() => _TabelloneTurni();
}

class _TabelloneTurni extends State<TabelloneTurni> {
  @override
  Widget build(BuildContext context) {
    //ottenimento degli argomenti passati
    //final Map<String, dynamic> args = ModalRoute.of(context)!.settings.arguments as Map<String, dynamic>;
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: Text('Tabellone dei Turni'),
        ),
      body: Center(
        child: WidgetTabella(),

    ),
    );

  }
}
