import 'package:flutter/material.dart';

class Amministratore extends StatefulWidget {
  const Amministratore({Key? key}) : super(key: key);

  @override
  _Amministratore createState() => _Amministratore();
}

class _Amministratore extends State<Amministratore> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Amministratore'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: Padding(
        padding: const EdgeInsets.fromLTRB(16, 50, 16, 50),
        // Distanza sinistra, sopra, destra, sotto
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Image.asset('assets/Logo CV.png',
                width: 300,
                height: 300),
              const SizedBox(height: 40),
              ElevatedButton(
                onPressed: () {
                  Navigator.pushNamed(context, '/lista_militi');
                },
                style: ButtonStyle(
                  fixedSize: MaterialStateProperty.all<Size>(
                    const Size(300, 50),
                  ),
                ),
                child: const Text('Gestisci Militi'),
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: () {
                  Navigator.pushNamed(context, '/gestisciTurniMiliti');
                },
                style: ButtonStyle(
                  fixedSize: MaterialStateProperty.all<Size>(
                    const Size(300, 50),
                  ),
                ),
                child: const Text("Gestisci Tabellone"),
              ),
              const SizedBox(height: 60),
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
            ],
          ),
        ),
      ),
    );
  }
}
