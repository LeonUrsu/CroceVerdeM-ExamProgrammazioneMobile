import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';

class ProfiloMilite extends StatefulWidget {
  late String username;

  ProfiloMilite(String _username) {
    this.username = _username;
  }

  @override
  _ProfiloMilite createState() => _ProfiloMilite();
}

class _ProfiloMilite extends State<ProfiloMilite> {
  @override
  Widget build(BuildContext context) {
    var datiMilite = {};
    return Scaffold(
        appBar: PreferredSize(
            preferredSize: Size.fromHeight(0.0), // here the desired height
            child: AppBar(
              backgroundColor: Theme.of(context).colorScheme.inversePrimary,
            )),
        body: StreamBuilder<QuerySnapshot>(
            stream: FirebaseFirestore.instance.collection("militi").snapshots(),
            builder: (context, snapshot) {
              if (!snapshot.hasData) {
                return const Center(child: CircularProgressIndicator());
              }
              if (snapshot.hasData) {
                final militi = snapshot.data?.docs.reversed.toList();
                for (var milite in militi!) {
                  if (widget.username == milite.get(AutofillHints.username)) {
                    var temp = milite.get("nome");
                    datiMilite['nome'] = temp;
                    datiMilite['cognome'] = milite.get("cognome");
                    datiMilite['dataDiNascita'] = milite.get("dataDiNascita");
                    datiMilite['oreTurnoh24prima'] =
                        milite.get("oreTurnoh24prima");
                    datiMilite['oreTurnoh24seconda'] =
                        milite.get("oreTurnoh24seconda");
                    datiMilite['oreTurnoh24terza'] =
                        milite.get("oreTurnoh24terza");
                    datiMilite['oreTurno118prima'] =
                        milite.get("oreTurno118prima");
                    datiMilite['oreTurno118seconda'] =
                        milite.get("oreTurno118seconda");
                    datiMilite['oreTurno118terza'] =
                        milite.get("oreTurno118terza");
                  }
                }
              }
              return Padding(
                padding: const EdgeInsets.fromLTRB(16, 50, 16, 25),
                // Distanza sinistra, sopra, destra, sotto
                child: Center(
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const Spacer(),
                      Image.asset(
                        'assets/account_image.png',
                        width: 200,
                        height: 200,
                      ),
                      const SizedBox(height: 60),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                              datiMilite["nome"] + " " + datiMilite["cognome"]),
                          Text(datiMilite["dataDiNascita"]),
                          Text(
                              "ore in 118 di prima: ${datiMilite["oreTurno118prima"]}"),
                          Text(
                              "ore in 118 di seconda: ${datiMilite["oreTurno118seconda"]}"),
                          Text(
                              "ore in 118 di terza: ${datiMilite["oreTurno118terza"]}"),
                          Text(
                              "ore in H24 di prima: ${datiMilite["oreTurnoh24prima"]}"),
                          Text(
                              "ore in H24 di seconda: ${datiMilite["oreTurnoh24seconda"]}"),
                          Text(
                              "ore in H24 di terza: ${datiMilite["oreTurnoh24terza"]}"),
                        ],
                      ),
                      const SizedBox(
                        height: 20,
                      ),
                      FilledButton.tonal(
                        onPressed: () {
                          Navigator.pushNamedAndRemoveUntil(
                            context,
                            '/',
                            (route) =>
                                false, //tutte le rotte precedenti sono rimosse tranne quella di login
                          );
                        },
                        style: ButtonStyle(
                          fixedSize: MaterialStateProperty.all<Size>(
                            const Size(100, 43),
                          ),
                        ),
                        child: const Text('Esci'),
                      ),
                      const Spacer()
                    ],
                  ),
                ),
              );
            }));
  }
}
