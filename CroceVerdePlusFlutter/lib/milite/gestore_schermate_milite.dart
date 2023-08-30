import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:croce_verde_plus/milite/profilo_milite.dart';
import 'package:croce_verde_plus/milite/tabellone_turni_milite.dart';
import 'package:flutter/material.dart';

class GestoreSchermateMilite extends StatefulWidget {
  const GestoreSchermateMilite({Key? key}) : super(key: key);

  @override
  _GestoreSchermateMilite createState() => _GestoreSchermateMilite();
}

class _GestoreSchermateMilite extends State<GestoreSchermateMilite> {
  var shape = const CircularNotchedRectangle();
  int _currentIndex = 0;
  var fabLocation = FloatingActionButtonLocation.endDocked;
  final tabs = [TabelloneTurniMilite(), ProfiloMilite()];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        ),
        bottomNavigationBar: BottomNavigationBar(
            currentIndex: _currentIndex,
            items: const <BottomNavigationBarItem>[
              BottomNavigationBarItem(
                icon: Icon(Icons.table_chart_rounded),
                label: 'Tabellone',
              ),
              BottomNavigationBarItem(
                icon: Icon(Icons.account_circle_rounded),
                label: 'Profilo',
              ),
            ],
            selectedItemColor: Colors.green,
            onTap: (index) {
              setState(() {
                _currentIndex = index;
              });
            }),
        body: tabs[_currentIndex]);
  }



/*
  Widget costruzione_bottom_bar_navigation(BuildContext context) {
    return BottomAppBar(
      shape: shape,
      color: Colors.green,
      child: IconTheme(
        data: IconThemeData(color: Theme.of(context).colorScheme.onPrimary),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            IconButton(
              iconSize: 40.0,
              tooltip: 'Profilo personale',
              icon: const Icon(Icons.table_chart_rounded),
              onPressed: () {},
            ),
            IconButton(
              iconSize: 40.0,
              tooltip: 'Tabellone turni',
              icon: const Icon(Icons.account_circle_rounded),
              onPressed: () {},
            ),
          ],
        ),
      ),
    );
  }

   */
}
