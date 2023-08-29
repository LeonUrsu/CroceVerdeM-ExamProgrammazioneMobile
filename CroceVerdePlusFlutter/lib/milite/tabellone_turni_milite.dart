import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

class TabelloneTurniMilite extends StatefulWidget {
  @override
  _TabelloneTurniMilite createState() => _TabelloneTurniMilite();
}

class _TabelloneTurniMilite extends State<TabelloneTurniMilite> {
  @override
  Widget build(BuildContext context) {
    //ottenimento degli argomenti passati
    //final Map<String, dynamic> args = ModalRoute.of(context)!.settings.arguments as Map<String, dynamic>;
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        ),
        body: const Center(child: Text("tabellone")));
  }
}
