import 'package:flutter/material.dart';

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

  void _login(String username, String password) {

    if(username == "a" && password == "p"){
      /*Navigator.push(
          context,
          MaterialPageRoute(
              builder: (context) => Amministratore()));

       */
      Navigator.pushNamed(context, '/amministratore');
    }

  }

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
                SizedBox(height: 75), // Spazio tra il testo e i TextField
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
                  onPressed: () {
                    String username = _usernameController.text;
                    String password = _passwordController.text;
                    // Controllo username e password inseriti
                    _login(username, password);
                  },
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