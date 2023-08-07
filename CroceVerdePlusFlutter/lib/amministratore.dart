import 'package:flutter/material.dart';

/*class AmministratoreScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        title: Text('Schermata Milite'),
      ),
      body: Center(
        child: Text('immagine'),
      ),
    );
  }
}

 */

class Amministratore extends StatefulWidget {
  const Amministratore({Key? key}) : super(key: key);
  @override
  _Amministratore createState() => _Amministratore();
}

class _Amministratore extends State<Amministratore> {

  /*void _login(String username, String password) {

    if(username == "a" && password == "p"){
      Navigator.push(
          context,
          MaterialPageRoute(
              builder: (context) => AmministratoreScreen()));
    }

  }

   */

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Croce Verde Plus'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: EdgeInsets.fromLTRB(16, 100, 16, 50), // Distanza sinistra, sopra, destra, sotto
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                Image.asset('assets/Logo CV.png'),
                SizedBox(height: 75), // Spazio tra il testo e i TextField
                ElevatedButton(
                  onPressed: () {


                  },
                  child: Text('Gestisci Militi'),
                  style: ButtonStyle(
                    fixedSize: MaterialStateProperty.all<Size>(
                      Size(300, 50),
                    ),
                  ),
                ),
                SizedBox(height: 30), // Spazio tra il primo e il secondo TextField
                ElevatedButton(
                  onPressed: () {


                  },
                  child: Text('Gestisci turni militi'),
                  style: ButtonStyle(
                    fixedSize: MaterialStateProperty.all<Size>(
                      Size(300, 50),
                    ),
                  ),
                ),
                SizedBox(height: 80),
                FilledButton.tonal(
                  onPressed: () {
                    Navigator.pushNamedAndRemoveUntil(
                      context,
                      '/',
                          (route) => false, //tutte le rotte precedenti sono rimosse tranne quella di login
                    );
                  },
                  child: Text('Esci'),
                  style: ButtonStyle(
                    fixedSize: MaterialStateProperty.all<Size>(
                      Size(100, 43),
                    ),
                  ),
                ),
                //SizedBox(height: 50),
                //CircularProgressIndicator()
              ],
            ),
          ),
        ),
      ),
    );
  }
}