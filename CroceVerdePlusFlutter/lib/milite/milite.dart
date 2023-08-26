import 'package:flutter/material.dart';

class Milite extends StatefulWidget {
  const Milite({Key? key}) : super(key: key);

  @override
  _Milite createState() => _Milite();
}

class _Milite extends State<Milite> {
  var shape = const CircularNotchedRectangle();
  var fabLocation = FloatingActionButtonLocation.endDocked;

  /*void _login(String username, String password) {

    if(username == "a" && password == "p"){
      Navigator.push(
          context,
          MaterialPageRoute(
              builder: (context) => MiliteScreen()));
    }

  }

   */

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Milite'),
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        ),
        bottomNavigationBar: costruzione_bottom_bar_navigation(context));
  }

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
}
