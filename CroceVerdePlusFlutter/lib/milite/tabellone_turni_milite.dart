import 'package:croce_verde_plus/widget_tabellone.dart';
import 'package:flutter/material.dart';

class TabelloneTurni extends StatefulWidget {
  @override
  _TabelloneTurni createState() => _TabelloneTurni();
}

class _TabelloneTurni extends State<TabelloneTurni> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: Text('Tabellone dei Turni'),
        ),
      body: Center(
        child: WidgetTabella(), //TODO SCHERMATA ERRORE DA PROFILO A TABELLONE
      ),
    );

  }
}
