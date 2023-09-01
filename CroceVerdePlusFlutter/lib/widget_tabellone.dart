import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';

class WidgetTabella extends StatefulWidget {
  @override
  _WidgetTabellaState createState() => _WidgetTabellaState();
}

class _WidgetTabellaState extends State<WidgetTabella> {
  bool visualizza118 = true;
  bool visualizzaH24118 = false;
  Timestamp? lunedi118;

  //gestione della visualizzazione delle tabelle
  void switchTo118() {
    setState(() {
      visualizza118 = true;
      visualizzaH24118 = false;
    });
  }
  void switchToH24118() {
    setState(() {
      visualizza118 = false;
      visualizzaH24118 = true;
    });
  }

  //ottengo i dati dalla tabella118
  Future<void> getDatiTabella118() async {
    DocumentReference documentReference = FirebaseFirestore.instance.collection('tabelle').doc('tabella_118');
    DocumentSnapshot documentSnapshot = await documentReference.get();
    Map<String, dynamic> data = documentSnapshot.data() as Map<String, dynamic>;
    //ottengo i dati
    String campo1 = data['campo1'];
    int campo2 = data['campo2'];
    lunedi118 = data['data_lunedi'];



  }

    @override
    Widget build(BuildContext context) {
    return Scaffold(

        body: Column(
          children: [
            SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton(
                  onPressed: () {
                    switchTo118();
                  },
                  child: Text('SETT 118'),
                  style: ButtonStyle(
                    fixedSize: MaterialStateProperty.all<Size>(
                      Size(180, 40),
                    ),
                  ),
                ),
                SizedBox(width: 20),
                ElevatedButton(
                  onPressed: () {
                    switchToH24118();
                  },
                  child: Text('SETT 118/H24'),
                  style: ButtonStyle(
                    fixedSize: MaterialStateProperty.all<Size>(
                      Size(180, 40),
                    ),
                  ),
                ),
              ],
            ),
            SizedBox(height: 20),

            if (visualizzaH24118)
            Container(
              margin: EdgeInsets.fromLTRB(58, 0, 0, 0),
              child: Table(
                border: TableBorder.all(),
                children: [
                  TableRow(
                    children: [
                      TableCell(
                        child: Container(
                          color: Colors.green,
                          // Imposta il colore di sfondo a verde
                          child: Center(
                              child: Text(
                                'H24'
                                , style: TextStyle(
                                  fontSize: 30, color: Colors.white),
                              )
                          ),
                        ),
                      ),
                      TableCell(
                        child: Container(
                          color: Colors.green,
                          // Imposta il colore di sfondo a verde
                          child: Center(
                              child: Text(
                                '118'
                                , style: TextStyle(
                                  fontSize: 30, color: Colors.white),
                              )
                          ),
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),

            if(visualizzaH24118)
            Table(
              border: TableBorder.all(),
              children: [
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        color: Colors.green,
                        child: Center(
                            child: Text(
                              ''
                              , style: TextStyle(color: Colors.white),
                            )
                        ),
                      ),
                    ),
                    TableCell(
                      child: Container(
                        color: Colors.lightGreen,
                        child: Center(
                            child: Text(
                              '7-14'
                              , style: TextStyle(color: Colors.white),
                            )
                        ),
                      ),
                    ),
                    TableCell(
                      child: Container(
                        color: Colors.lightGreen,
                        child: Center(
                            child: Text(
                              '14-21'
                              , style: TextStyle(color: Colors.white),
                            )
                        ),
                      ),
                    ),
                    TableCell(
                      child: Container(
                        color: Colors.lightGreen,
                        child: Center(
                            child: Text(
                              '21-7'
                              , style: TextStyle(color: Colors.white),
                            )
                        ),
                      ),
                    ),
                    TableCell(
                      child: Container(
                        color: Colors.lightGreen,
                        child: Center(
                            child: Text(
                              '7-14'
                              , style: TextStyle(color: Colors.white),
                            )
                        ),
                      ),
                    ),
                    TableCell(
                      child: Container(
                        color: Colors.lightGreen,
                        child: Center(
                            child: Text(
                              '14-21'
                              , style: TextStyle(color: Colors.white),
                            )
                        ),
                      ),
                    ),
                    TableCell(
                      child: Container(
                        color: Colors.lightGreen,
                        child: Center(
                            child: Text(
                              '21-7'
                              , style: TextStyle(color: Colors.white),
                            )
                        ),
                      ),
                    ),
                  ],
                ),


                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 80,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: Text(
                          '$lunedi118',
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 80,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: const Text(
                          'Lunedì\n31/07',
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 80,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: const Text(
                          'Lunedì\n31/07',
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 80,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: const Text(
                          'Lunedì\n31/07',
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 80,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: const Text(
                          'Lunedì\n31/07',
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 80,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: const Text(
                          'Lunedì\n31/07',
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 80,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: const Text(
                          'Lunedì\n31/07',
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('',
                            style: TextStyle(fontSize: 8)))),
                  ],
                ),
              ],
            ),


          //TODO SOPRA TABELLAH24 SOTTO 118

          if(visualizza118)
            Container(
              margin: EdgeInsets.fromLTRB(102, 0, 0, 0),
              child: Table(
                border: TableBorder.all(),
                children: [
                  TableRow(
                    children: [
                      TableCell(
                        child: Container(
                          color: Colors.green,
                          child: Center(
                              child: Text(
                                '118'
                                , style: TextStyle(
                                  fontSize: 30, color: Colors.white),
                              )
                          ),
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),

            if(visualizza118)
            Table(
              border: TableBorder.all(),
              children: [
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        color: Colors.green,
                        child: Center(
                            child: Text(
                              ''
                              , style: TextStyle(color: Colors.white),
                            )
                        ),
                      ),
                    ),
                    TableCell(
                      child: Container(
                        color: Colors.lightGreen,
                        child: Center(
                            child: Text(
                              '7-14'
                              , style: TextStyle(color: Colors.white),
                            )
                        ),
                      ),
                    ),
                    TableCell(
                      child: Container(
                        color: Colors.lightGreen,
                        child: Center(
                            child: Text(
                              '14-21'
                              , style: TextStyle(color: Colors.white),
                            )
                        ),
                      ),
                    ),
                    TableCell(
                      child: Container(
                        color: Colors.lightGreen,
                        child: Center(
                            child: Text(
                              '21-7'
                              , style: TextStyle(color: Colors.white),
                            )
                        ),
                      ),
                    ),
                  ],
                ),


                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 70,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: const Text(
                          'Lunedì\n31/07',
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(child: Text(''))),
                    TableCell(child: Center(child: Text(''))),
                    TableCell(child: Center(child: Text(''))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 70,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: const Text(
                          'Lunedì\n31/07',
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(child: Text(''))),
                    TableCell(child: Center(child: Text(''))),
                    TableCell(child: Center(child: Text(''))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 70,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: const Text(
                          'Lunedì\n31/07',
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(child: Text(''))),
                    TableCell(child: Center(child: Text(''))),
                    TableCell(child: Center(child: Text(''))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 70,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: const Text(
                          'Lunedì\n31/07',
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(child: Text(''))),
                    TableCell(child: Center(child: Text(''))),
                    TableCell(child: Center(child: Text(''))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 70,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: const Text(
                          'Lunedì\n31/07',
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(child: Text(''))),
                    TableCell(child: Center(child: Text(''))),
                    TableCell(child: Center(child: Text(''))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 70,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: const Text(
                          'Lunedì\n31/07',
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(child: Text(''))),
                    TableCell(child: Center(child: Text(''))),
                    TableCell(child: Center(child: Text(''))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 70,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: const Text(
                          'Lunedì\n31/07',
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(child: Text(''))),
                    TableCell(child: Center(child: Text(''))),
                    TableCell(child: Center(child: Text(''))),
                  ],
                ),
              ],
            ),

          ],
        ),
      );
    }
  }
