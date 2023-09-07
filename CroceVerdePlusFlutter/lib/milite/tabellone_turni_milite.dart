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

      appBar: PreferredSize(
          preferredSize: Size.fromHeight(0.0), // here the desired height
          child: AppBar(
            backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          )
      ),
      body: Center(child: WidgetTabella(),)
    );

  }
}
