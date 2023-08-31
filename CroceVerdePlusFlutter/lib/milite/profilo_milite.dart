import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';

class ProfiloMilite extends StatefulWidget {
  late String username;
  ProfiloMilite(String _username){
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
        appBar: AppBar(
          title: Text('Dettaglio Milite'),
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        ),
        body: StreamBuilder<QuerySnapshot>(
            stream: FirebaseFirestore.instance.collection("militi").snapshots(),
            builder: (context, snapshot) {
              if (!snapshot.hasData) {
                return Center(child: CircularProgressIndicator());
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
              return SingleChildScrollView(
                child: Padding(
                  padding: EdgeInsets.fromLTRB(16, 75, 16, 50),
                  // Distanza sinistra, sopra, destra, sotto
                  child: Center(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Image.asset(
                          'assets/account_image.png',
                          width: 200,
                          height: 200,
                        ),
                        SizedBox(height: 60),
                        Text(datiMilite["nome"]),
                        Text(datiMilite["cognome"]),
                        Text(datiMilite["dataDiNascita"]),
                        Text("oreTurno118prima " +
                            datiMilite["oreTurno118prima"].toString()),
                        Text("oreTurno118seconda " +
                            datiMilite["oreTurno118seconda"].toString()),
                        Text("oreTurno118terza " +
                            datiMilite["oreTurno118terza"].toString()),
                        Text("oreTurnoh24prima " +
                            datiMilite["oreTurnoh24prima"].toString()),
                        Text("oreTurnoh24seconda " +
                            datiMilite["oreTurnoh24seconda"].toString()),
                        Text("oreTurnoh24terza " +
                            datiMilite["oreTurnoh24terza"].toString()),
                        FilledButton.tonal(
                          onPressed: () {
                            Navigator.pushNamedAndRemoveUntil(
                              context,
                              '/',
                              (route) =>
                                  false, //tutte le rotte precedenti sono rimosse tranne quella di login
                            );
                          },
                          child: Text('Esci'),
                          style: ButtonStyle(
                            fixedSize: MaterialStateProperty.all<Size>(
                              Size(100, 43),
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              );
            }));
  }
}

//var cognomeNomeSpinner = "Di Natale Antonio";
/*
  var datiMilite = {};

  @override
  Widget build(BuildContext context) {
    var db = FirebaseFirestore.instance;
    db.collection("militi").get().then(
      (querySnapshot) {
        for (var milite in querySnapshot.docs) {
          if (cognomeNomeSpinner == milite.get("cognomeNomeSpinner")) {
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
        return Scaffold(
          appBar: AppBar(
            title: Text('Dettaglio Milite'),
            backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          ),
          body: SingleChildScrollView(
            child: Padding(
              padding: EdgeInsets.fromLTRB(16, 75, 16, 50),
              // Distanza sinistra, sopra, destra, sotto
              child: Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Image.asset(
                      'assets/account_image.png',
                      width: 200,
                      height: 200,
                    ),
                    SizedBox(height: 60),
                    Text(datiMilite["nome"]),
                    Text(datiMilite["cognome"]),
                    Text(datiMilite["dataDiNascita"]),
                    Text("oreTurno118prima " + datiMilite["oreTurno118prima"].toString()),
                    Text("oreTurno118seconda " +
                        datiMilite["oreTurno118seconda"].toString()),
                    Text("oreTurno118terza " + datiMilite["oreTurno118terza"].toString()),
                    Text("oreTurnoh24prima " + datiMilite["oreTurnoh24prima"].toString()),
                    Text("oreTurnoh24seconda " +
                        datiMilite["oreTurnoh24seconda"].toString()),
                    Text("oreTurnoh24terza " + datiMilite["oreTurnoh24terza"].toString()),
                  ],
                ),
              ),
            ),
          ),
        );

      },
      onError: (e) => print("Error completing: $e")
    );


  }

/*
  @override
  Widget build(BuildContext context) {
    //ottenimento degli argomenti passati
    //final Map<String, dynamic> args = ModalRoute.of(context)!.settings.arguments as Map<String, dynamic>;
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        ),
        body: const Center( child: Text("profilo milite")));}
 */
}
*/
