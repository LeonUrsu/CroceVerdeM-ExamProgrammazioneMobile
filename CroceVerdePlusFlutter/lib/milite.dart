class Milite {
  final String? nome;
  final String? cognome;
  final String? dataDiNascita;
  final String? residenza;
  final String? cognomeNomeSpinner;
  final String? username;
  final String? password;
  final bool? grado118prima;
  final bool? grado118seconda;
  final bool? grado118terza;
  final bool? gradoh24prima;
  final bool? gradoh24seconda;
  final bool? gradoh24terza;
  final int? oreTurno118prima;
  final int? oreTurno118seconda;
  final int? oreTurno118terza;
  final int? oreTurnoh24prima;
  final int? oreTurnoh24seconda;
  final int? oreTurnoh24terza;


  Milite({
    required this.nome,
    required this.cognome,
    required this.dataDiNascita,
    required this.residenza,
    this.cognomeNomeSpinner,
    this.username,
    this.password,
    this.grado118prima = false,
    this.grado118seconda = false,
    this.grado118terza = false,
    this.gradoh24prima = false,
    this.gradoh24seconda = false,
    this.gradoh24terza = false,
    this.oreTurno118prima = 0,
    this.oreTurno118seconda = 0,
    this.oreTurno118terza = 0,
    this.oreTurnoh24prima = 0,
    this.oreTurnoh24seconda = 0,
    this.oreTurnoh24terza = 0,
  });



  Map<String, dynamic> toMap() {
    return {
      'nome': nome,
      'cognome': cognome,
      'dataDiNascita': dataDiNascita,
      'residenza': residenza,
      'cognomeNomeSpinner': cognomeNomeSpinner,
      'username': username,
      'password': password,
      'grado118prima': grado118prima,
      'grado118seconda': grado118seconda,
      'grado118terza': grado118terza,
      'gradoh24prima': gradoh24prima,
      'gradoh24seconda': gradoh24seconda,
      'gradoh24terza': gradoh24terza,
      'oreTurno118prima': oreTurno118prima,
      'oreTurno118seconda': oreTurno118seconda,
      'oreTurno118terza': oreTurno118terza,
      'oreTurnoh24prima': oreTurnoh24prima,
      'oreTurnoh24seconda': oreTurnoh24seconda,
      'oreTurnoh24terza': oreTurnoh24terza,
    };
  }
}

