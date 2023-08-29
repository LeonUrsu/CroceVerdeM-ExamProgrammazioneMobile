import 'package:flutter/material.dart';

class ProfiloMilite extends StatefulWidget {
  @override
  _ProfiloMilite createState() => _ProfiloMilite();
}

class _ProfiloMilite extends State<ProfiloMilite> {
  @override
  Widget build(BuildContext context) {
    //ottenimento degli argomenti passati
    //final Map<String, dynamic> args = ModalRoute.of(context)!.settings.arguments as Map<String, dynamic>;
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        ),
        body: const Center( child: Text("profilo milite")));
  }
}
