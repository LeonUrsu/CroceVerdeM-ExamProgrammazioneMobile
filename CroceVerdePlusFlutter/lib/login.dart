import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:croce_verde_plus/milite/profilo_milite.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

import 'amministratore.dart';
import 'milite/gestore_milite.dart';

class Login extends StatefulWidget {
  const Login({Key? key}) : super(key: key);

  @override
  _Login createState() => _Login();
}

class _Login extends State<Login> {
  final _usernameController = TextEditingController();
  final _passwordController = TextEditingController();
  bool _rotellina = false;

  Future<void> _login(BuildContext context) async {
    final username = _usernameController.text;
    final password = _passwordController.text;

    setState(() {
      _rotellina = true; //visualizza la rotellina di caricamento
    });

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

    final List<QuerySnapshot> results =
        await Future.wait([amministratoreQuery, militeQuery]);

    if (results[0].docs.isNotEmpty) {
      // Amministratore trovato
      Navigator.pushReplacementNamed(context, '/amministratore');
    } else if (results[1].docs.isNotEmpty) {
      // Milite trovato
      Navigator.of(context).pushReplacement(
        MaterialPageRoute(
          settings: const RouteSettings(name: '/militi'),
          builder: (context) => GestoreMilite(username),
        ),
      );
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

    setState(() {
      _rotellina = false; //nasconde la rotellina di caricamento
    });
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
        title: const Text('Croce Verde Plus'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: Padding(
        padding: EdgeInsets.fromLTRB(16, 20, 16, 50),
        // Distanza sinistra, sopra, destra, sotto
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Image.asset('assets/Logo CV.png'),
              const SizedBox(height: 50),
              // Spazio tra l'immagine e i TextField
              TextField(
                controller: _usernameController,
                decoration: const InputDecoration(
                  labelText: 'Username',
                  border: OutlineInputBorder(),
                ),
              ),
              const SizedBox(height: 10),
              // Spazio tra il primo e il secondo TextField
              TextField(
                controller: _passwordController,
                obscureText: true,
                decoration: const InputDecoration(
                  labelText: 'Password',
                  border: OutlineInputBorder(),
                ),
              ),
              const SizedBox(height: 25),
              ElevatedButton(
                onPressed: () => _login(context),
                child: Text('Login'),
              ),
              const SizedBox(height: 50),
              if (_rotellina) CircularProgressIndicator()
            ],
          ),
        ),
      ),
    );
  }
}
