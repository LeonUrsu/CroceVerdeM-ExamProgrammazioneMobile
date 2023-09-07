import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';

class GestioneMiliti extends StatefulWidget {
  @override
  _GestioneMiliti createState() => _GestioneMiliti();
}

class _GestioneMiliti extends State<GestioneMiliti> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Lista Militi'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: StreamBuilder<QuerySnapshot>(
        stream: FirebaseFirestore.instance.collection('militi').snapshots(),
        builder: (context, snapshot) {
          if (!snapshot.hasData) {
            return Center(); //TODO ELIMINATO child: CircularProgressIndicator() INTERNO A CENTER()
          }

          // elenco di documenti della collezione "militi"
          List<DocumentSnapshot> documents = snapshot.data!.docs;

          return ListView.builder(
            itemCount: documents.length,
            itemBuilder: (context, index) {
              var document = documents[index];
              // dati necessari estratti dal documento
              String nome = document['nome'];
              String cognome = document['cognome'];
              String dataDiNascita = document['dataDiNascita'];
              String residenza = document['residenza'];
              bool volontario = document['volontario'];

              //liste per gestire la visualizzazione dei gradi settati a True
              List<String> gradiNomi = [
                'Grado 118 Prima',
                'Grado 118 Seconda',
                'Grado 118 Terza',
                'Grado H24 Prima',
                'Grado H24 Seconda',
                'Grado H24 Terza',
              ];
              List<bool> gradi = [
                document['grado118prima'],
                document['grado118seconda'],
                document['grado118terza'],
                document['gradoh24prima'],
                document['gradoh24seconda'],
                document['gradoh24terza']
              ];
              List<Widget> gradiTrue = [];
              for (int i = 0; i < gradi.length; i++) {
                if (gradi[i] == true) {
                  gradiTrue.add(Text(gradiNomi[i]));
                }
              }
              if (gradiTrue.isEmpty) {
                gradiTrue.add(Text('Nessun grado'));
              }

              //lista contenente la data di nascita e la residenza del milite
              List<Widget> altreInfo = [
                Text('Data di nascita: $dataDiNascita'),
                Text('Residenza: $residenza'),
              ];
              return Card(child: ListTile(
                title: Text('$nome $cognome'),
                subtitle: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    ...altreInfo, //lista all'interno di column
                    Text(volontario ? 'Volontario' : 'Dipendente'),
                    ...gradiTrue,
                  ],
                ),
                onTap: () {
                  Navigator.pushNamed(
                    context,
                    '/schermata_milite',
                    arguments: {
                      'nome': nome,
                      'cognome': cognome,
                      'dataDiNascita': dataDiNascita,
                      'residenza': residenza,
                      'gradi': gradiTrue
                    },
                  );
                },
              ));
            },
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.pushNamed(context, '/crea_milite');
        },
        child: Icon(Icons.add),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
    );
  }
}

/*class DettaglioMilitiScreen extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Gestione Milite'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Nome: $nome'),
            Text('Cognome: $cognome'),
          ],
        ),
      ),
    );
  }
}

 */
