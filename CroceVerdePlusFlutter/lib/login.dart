import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

import 'amministratore.dart';




/*class LoginScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    //Firebase.initializeApp();
    return MaterialApp(
      //debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.green,
      ),
      home: const Login(),
    );
  }
}

 */

class Login extends StatefulWidget {
  const Login({Key? key}) : super(key: key);
  @override
  _Login createState() => _Login();
}

class _Login extends State<Login> {

  final _usernameController = TextEditingController();
  final _passwordController = TextEditingController();
  //bool _isLoading = false;

  Future<void> _login (BuildContext context) async {
    final username = _usernameController.text;
    final password = _passwordController.text;

    final amministratoreQuery = FirebaseFirestore.instance
        .collection('amministratori')
        .where('username', isEqualTo: username)
        .where('password', isEqualTo: password)
        .limit(1)
        .get();

    final militeQuery = FirebaseFirestore.instance
        .collection('militi')
        .where('username', isEqualTo: username)
        .where('password', isEqualTo: password)
        .limit(1)
        .get();

    final List<QuerySnapshot> results = await Future.wait([amministratoreQuery, militeQuery]);

    if (results[0].docs.isNotEmpty) {
      // Amministratore trovato
      Navigator.pushReplacementNamed(context, '/amministratore');
    } else if (results[1].docs.isNotEmpty) {
      // Milite trovato
      Navigator.pushReplacementNamed(context, '/militi');
    } else {
      // Credenziali non valide
      Fluttertoast.showToast(
        msg: 'Utente non valido',
        toastLength: Toast.LENGTH_SHORT,
        gravity: ToastGravity.BOTTOM,
        timeInSecForIosWeb: 1,
        backgroundColor: Colors.grey,
        textColor: Colors.white,
        fontSize: 16.0,
      );
    }
  }






  /*void _login(String username, String password) {

    if(username == "a" && password == "p"){
      /*Navigator.push(
          context,
          MaterialPageRoute(
              builder: (context) => Amministratore()));

       */
      Navigator.pushNamed(context, '/amministratore'); //pushnamed aggiunge la schermata alla pila delle rotte
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
              children: [
                Image.asset('assets/Logo CV.png'),
                SizedBox(height: 75), // Spazio tra l'immagine e i TextField
                TextField(
                  controller: _usernameController,
                  decoration: InputDecoration(
                    labelText: 'Username',
                    border: OutlineInputBorder(),
                  ),
                ),
                SizedBox(height: 16), // Spazio tra il primo e il secondo TextField
                TextField(
                  controller: _passwordController,
                  obscureText: true,
                  decoration: InputDecoration(
                    labelText: 'Password',
                    border: OutlineInputBorder(),
                  ),
                ),
                SizedBox(height: 50),
                FilledButton(
                  onPressed: () => _login(context),
                  child: Text('Login'),
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