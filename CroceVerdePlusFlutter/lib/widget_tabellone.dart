import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:intl/date_symbol_data_local.dart';


class WidgetTabella extends StatefulWidget {
  bool visualizza118 = true;
  bool visualizzaH24118 = false;
  @override
  _WidgetTabella createState() => _WidgetTabella();
}

class _WidgetTabella extends State<WidgetTabella> {

  Timestamp lunedi118 = Timestamp.fromDate(DateTime(2023));
  Timestamp martedi118 = Timestamp.fromDate(DateTime(2023));
  Timestamp mercoledi118 = Timestamp.fromDate(DateTime(2023));
  Timestamp giovedi118 = Timestamp.fromDate(DateTime(2023));
  Timestamp venerdi118 = Timestamp.fromDate(DateTime(2023));
  Timestamp sabato118 = Timestamp.fromDate(DateTime(2023));
  Timestamp domenica118 = Timestamp.fromDate(DateTime(2023));
  Timestamp lunediH24 = Timestamp.fromDate(DateTime(2023));
  Timestamp martediH24 = Timestamp.fromDate(DateTime(2023));
  Timestamp mercolediH24 = Timestamp.fromDate(DateTime(2023));
  Timestamp giovediH24 = Timestamp.fromDate(DateTime(2023));
  Timestamp venerdiH24 = Timestamp.fromDate(DateTime(2023));
  Timestamp sabatoH24 = Timestamp.fromDate(DateTime(2023));
  Timestamp domenicaH24 = Timestamp.fromDate(DateTime(2023));
  String turno118LunMat1 = '';
  String turno118LunMat2 = '';
  String turno118LunMat3 = '';
  String turno118LunPom1 = '';
  String turno118LunPom2 = '';
  String turno118LunPom3 = '';
  String turno118LunSer1 = '';
  String turno118LunSer2 = '';
  String turno118LunSer3 = '';
  String turno118MarMat1 = '';
  String turno118MarMat2 = '';
  String turno118MarMat3 = '';
  String turno118MarPom1 = '';
  String turno118MarPom2 = '';
  String turno118MarPom3 = '';
  String turno118MarSer1 = '';
  String turno118MarSer2 = '';
  String turno118MarSer3 = '';
  String turno118MerMat1 = '';
  String turno118MerMat2 = '';
  String turno118MerMat3 = '';
  String turno118MerPom1 = '';
  String turno118MerPom2 = '';
  String turno118MerPom3 = '';
  String turno118MerSer1 = '';
  String turno118MerSer2 = '';
  String turno118MerSer3 = '';
  String turno118GioMat1 = '';
  String turno118GioMat2 = '';
  String turno118GioMat3 = '';
  String turno118GioPom1 = '';
  String turno118GioPom2 = '';
  String turno118GioPom3 = '';
  String turno118GioSer1 = '';
  String turno118GioSer2 = '';
  String turno118GioSer3 = '';
  String turno118VenMat1 = '';
  String turno118VenMat2 = '';
  String turno118VenMat3 = '';
  String turno118VenPom1 = '';
  String turno118VenPom2 = '';
  String turno118VenPom3 = '';
  String turno118VenSer1 = '';
  String turno118VenSer2 = '';
  String turno118VenSer3 = '';
  String turno118SabMat1 = '';
  String turno118SabMat2 = '';
  String turno118SabMat3 = '';
  String turno118SabPom1 = '';
  String turno118SabPom2 = '';
  String turno118SabPom3 = '';
  String turno118SabSer1 = '';
  String turno118SabSer2 = '';
  String turno118SabSer3 = '';
  String turno118DomMat1 = '';
  String turno118DomMat2 = '';
  String turno118DomMat3 = '';
  String turno118DomPom1 = '';
  String turno118DomPom2 = '';
  String turno118DomPom3 = '';
  String turno118DomSer1 = '';
  String turno118DomSer2 = '';
  String turno118DomSer3 = '';
  String? turno118H24LunMat1;
  String? turno118H24LunMat2;
  String? turno118H24LunMat3;
  String? turno118H24LunPom1;
  String? turno118H24LunPom2;
  String? turno118H24LunPom3;
  String? turno118H24LunSer1;
  String? turno118H24LunSer2;
  String? turno118H24LunSer3;
  String? turno118H24MarMat1;
  String? turno118H24MarMat2;
  String? turno118H24MarMat3;
  String? turno118H24MarPom1;
  String? turno118H24MarPom2;
  String? turno118H24MarPom3;
  String? turno118H24MarSer1;
  String? turno118H24MarSer2;
  String? turno118H24MarSer3;
  String? turno118H24MerMat1;
  String? turno118H24MerMat2;
  String? turno118H24MerMat3;
  String? turno118H24MerPom1;
  String? turno118H24MerPom2;
  String? turno118H24MerPom3;
  String? turno118H24MerSer1;
  String? turno118H24MerSer2;
  String? turno118H24MerSer3;
  String? turno118H24GioMat1;
  String? turno118H24GioMat2;
  String? turno118H24GioMat3;
  String? turno118H24GioPom1;
  String? turno118H24GioPom2;
  String? turno118H24GioPom3;
  String? turno118H24GioSer1;
  String? turno118H24GioSer2;
  String? turno118H24GioSer3;
  String? turno118H24VenMat1;
  String? turno118H24VenMat2;
  String? turno118H24VenMat3;
  String? turno118H24VenPom1;
  String? turno118H24VenPom2;
  String? turno118H24VenPom3;
  String? turno118H24VenSer1;
  String? turno118H24VenSer2;
  String? turno118H24VenSer3;
  String? turno118H24SabMat1;
  String? turno118H24SabMat2;
  String? turno118H24SabMat3;
  String? turno118H24SabPom1;
  String? turno118H24SabPom2;
  String? turno118H24SabPom3;
  String? turno118H24SabSer1;
  String? turno118H24SabSer2;
  String? turno118H24SabSer3;
  String? turno118H24DomMat1;
  String? turno118H24DomMat2;
  String? turno118H24DomMat3;
  String? turno118H24DomPom1;
  String? turno118H24DomPom2;
  String? turno118H24DomPom3;
  String? turno118H24DomSer1;
  String? turno118H24DomSer2;
  String? turno118H24DomSer3;
  String? turnoH24LunMat1;
  String? turnoH24LunMat2;
  String? turnoH24LunMat3;
  String? turnoH24LunPom1;
  String? turnoH24LunPom2;
  String? turnoH24LunPom3;
  String? turnoH24LunSer1;
  String? turnoH24LunSer2;
  String? turnoH24LunSer3;
  String? turnoH24MarMat1;
  String? turnoH24MarMat2;
  String? turnoH24MarMat3;
  String? turnoH24MarPom1;
  String? turnoH24MarPom2;
  String? turnoH24MarPom3;
  String? turnoH24MarSer1;
  String? turnoH24MarSer2;
  String? turnoH24MarSer3;
  String? turnoH24MerMat1;
  String? turnoH24MerMat2;
  String? turnoH24MerMat3;
  String? turnoH24MerPom1;
  String? turnoH24MerPom2;
  String? turnoH24MerPom3;
  String? turnoH24MerSer1;
  String? turnoH24MerSer2;
  String? turnoH24MerSer3;
  String? turnoH24GioMat1;
  String? turnoH24GioMat2;
  String? turnoH24GioMat3;
  String? turnoH24GioPom1;
  String? turnoH24GioPom2;
  String? turnoH24GioPom3;
  String? turnoH24GioSer1;
  String? turnoH24GioSer2;
  String? turnoH24GioSer3;
  String? turnoH24VenMat1;
  String? turnoH24VenMat2;
  String? turnoH24VenMat3;
  String? turnoH24VenPom1;
  String? turnoH24VenPom2;
  String? turnoH24VenPom3;
  String? turnoH24VenSer1;
  String? turnoH24VenSer2;
  String? turnoH24VenSer3;
  String? turnoH24SabMat1;
  String? turnoH24SabMat2;
  String? turnoH24SabMat3;
  String? turnoH24SabPom1;
  String? turnoH24SabPom2;
  String? turnoH24SabPom3;
  String? turnoH24SabSer1;
  String? turnoH24SabSer2;
  String? turnoH24SabSer3;
  String? turnoH24DomMat1;
  String? turnoH24DomMat2;
  String? turnoH24DomMat3;
  String? turnoH24DomPom1;
  String? turnoH24DomPom2;
  String? turnoH24DomPom3;
  String? turnoH24DomSer1;
  String? turnoH24DomSer2;
  String? turnoH24DomSer3;



  //operazione eseguite una sola volta quando il widget viene inizializzato
  @override
  void initState() {
    super.initState();
    initializeDateFormatting('it', null);
  }


  //gestione della visualizzazione delle tabelle
  void switchTo118() {
    setState(() {
      widget.visualizza118 = true;
      widget.visualizzaH24118 = false;
    });
  }
  void switchToH24118() {
    setState(() {
      widget.visualizza118 = false;
      widget.visualizzaH24118 = true;
    });
  }

  //ottengo i dati dalla tabella118
  Future<void> getDatiTabella118() async {
    DocumentReference documentReference = FirebaseFirestore.instance.collection('tabelle').doc('tabella_118');
    DocumentSnapshot documentSnapshot = await documentReference.get();
    Map<String, dynamic> data = documentSnapshot.data() as Map<String, dynamic>;
    //ottengo i dati
    lunedi118 = data['data_lunedi'];
    martedi118 = data['data_martedi'];
    mercoledi118 = data['data_mercoledi'];
    giovedi118 = data['data_giovedi'];
    venerdi118 = data['data_venerdi'];
    sabato118 = data['data_sabato'];
    domenica118 = data['data_domenica'];

    turno118LunMat1 = data['turno_118_lun_mat_1'];
    turno118LunMat2 = data['turno_118_lun_mat_2'];
    turno118LunMat3 = data['turno_118_lun_mat_3'];
    turno118LunPom1 = data['turno_118_lun_pom_1'];
    turno118LunPom2 = data['turno_118_lun_pom_2'];
    turno118LunPom3 = data['turno_118_lun_pom_3'];
    turno118LunSer1 = data['turno_118_lun_ser_1'];
    turno118LunSer2 = data['turno_118_lun_ser_2'];
    turno118LunSer3 = data['turno_118_lun_ser_3'];
    turno118MarMat1 = data['turno_118_mar_mat_1'];
    turno118MarMat2 = data['turno_118_mar_mat_2'];
    turno118MarMat3 = data['turno_118_mar_mat_3'];
    turno118MarPom1 = data['turno_118_mar_pom_1'];
    turno118MarPom2 = data['turno_118_mar_pom_2'];
    turno118MarPom3 = data['turno_118_mar_pom_3'];
    turno118MarSer1 = data['turno_118_mar_ser_1'];
    turno118MarSer2 = data['turno_118_mar_ser_2'];
    turno118MarSer3 = data['turno_118_mar_ser_3'];
    turno118MerMat1 = data['turno_118_mer_mat_1'];
    turno118MerMat2 = data['turno_118_mer_mat_2'];
    turno118MerMat3 = data['turno_118_mer_mat_3'];
    turno118MerPom1 = data['turno_118_mer_pom_1'];
    turno118MerPom2 = data['turno_118_mer_pom_2'];
    turno118MerPom3 = data['turno_118_mer_pom_3'];
    turno118MerSer1 = data['turno_118_mer_ser_1'];
    turno118MerSer2 = data['turno_118_mer_ser_2'];
    turno118MerSer3 = data['turno_118_mer_ser_3'];
    turno118GioMat1 = data['turno_118_gio_mat_1'];
    turno118GioMat2 = data['turno_118_gio_mat_2'];
    turno118GioMat3 = data['turno_118_gio_mat_3'];
    turno118GioPom1 = data['turno_118_gio_pom_1'];
    turno118GioPom2 = data['turno_118_gio_pom_2'];
    turno118GioPom3 = data['turno_118_gio_pom_3'];
    turno118GioSer1 = data['turno_118_gio_ser_1'];
    turno118GioSer2 = data['turno_118_gio_ser_2'];
    turno118GioSer3 = data['turno_118_gio_ser_3'];
    turno118VenMat1 = data['turno_118_ven_mat_1'];
    turno118VenMat2 = data['turno_118_ven_mat_2'];
    turno118VenMat3 = data['turno_118_ven_mat_3'];
    turno118VenPom1 = data['turno_118_ven_pom_1'];
    turno118VenPom2 = data['turno_118_ven_pom_2'];
    turno118VenPom3 = data['turno_118_ven_pom_3'];
    turno118VenSer1 = data['turno_118_ven_ser_1'];
    turno118VenSer2 = data['turno_118_ven_ser_2'];
    turno118VenSer3 = data['turno_118_ven_ser_3'];
    turno118SabMat1 = data['turno_118_sab_mat_1'];
    turno118SabMat2 = data['turno_118_sab_mat_2'];
    turno118SabMat3 = data['turno_118_sab_mat_3'];
    turno118SabPom1 = data['turno_118_sab_pom_1'];
    turno118SabPom2 = data['turno_118_sab_pom_2'];
    turno118SabPom3 = data['turno_118_sab_pom_3'];
    turno118SabSer1 = data['turno_118_sab_ser_1'];
    turno118SabSer2 = data['turno_118_sab_ser_2'];
    turno118SabSer3 = data['turno_118_sab_ser_3'];
    turno118DomMat1 = data['turno_118_dom_mat_1'];
    turno118DomMat2 = data['turno_118_dom_mat_2'];
    turno118DomMat3 = data['turno_118_dom_mat_3'];
    turno118DomPom1 = data['turno_118_dom_pom_1'];
    turno118DomPom2 = data['turno_118_dom_pom_2'];
    turno118DomPom3 = data['turno_118_dom_pom_3'];
    turno118DomSer1 = data['turno_118_dom_ser_1'];
    turno118DomSer2 = data['turno_118_dom_ser_2'];
    turno118DomSer3 = data['turno_118_dom_ser_3'];

    setState(() {});
  }

  //ottengo i dati dalla tabella118H24
  Future<void> getDatiTabella118H24() async {
    DocumentReference documentReference = FirebaseFirestore.instance.collection('tabelle').doc('tabella_118_h24');
    DocumentSnapshot documentSnapshot = await documentReference.get();
    Map<String, dynamic> data = documentSnapshot.data() as Map<String, dynamic>;
    //ottengo i dati
    lunediH24 = data['data_lunedi'];
    martediH24 = data['data_martedi'];
    mercolediH24 = data['data_mercoledi'];
    giovediH24 = data['data_giovedi'];
    venerdiH24 = data['data_venerdi'];
    sabatoH24 = data['data_sabato'];
    domenicaH24 = data['data_domenica'];

    turno118H24LunMat1 = data['turno_118_lun_mat_1'];
    turno118H24LunMat2 = data['turno_118_lun_mat_2'];
    turno118H24LunMat3 = data['turno_118_lun_mat_3'];
    turno118H24LunPom1 = data['turno_118_lun_pom_1'];
    turno118H24LunPom2 = data['turno_118_lun_pom_2'];
    turno118H24LunPom3 = data['turno_118_lun_pom_3'];
    turno118H24LunSer1 = data['turno_118_lun_ser_1'];
    turno118H24LunSer2 = data['turno_118_lun_ser_2'];
    turno118H24LunSer3 = data['turno_118_lun_ser_3'];
    turno118H24MarMat1 = data['turno_118_mar_mat_1'];
    turno118H24MarMat2 = data['turno_118_mar_mat_2'];
    turno118H24MarMat3 = data['turno_118_mar_mat_3'];
    turno118H24MarPom1 = data['turno_118_mar_pom_1'];
    turno118H24MarPom2 = data['turno_118_mar_pom_2'];
    turno118H24MarPom3 = data['turno_118_mar_pom_3'];
    turno118H24MarSer1 = data['turno_118_mar_ser_1'];
    turno118H24MarSer2 = data['turno_118_mar_ser_2'];
    turno118H24MarSer3 = data['turno_118_mar_ser_3'];
    turno118H24MerMat1 = data['turno_118_mer_mat_1'];
    turno118H24MerMat2 = data['turno_118_mer_mat_2'];
    turno118H24MerMat3 = data['turno_118_mer_mat_3'];
    turno118H24MerPom1 = data['turno_118_mer_pom_1'];
    turno118H24MerPom2 = data['turno_118_mer_pom_2'];
    turno118H24MerPom3 = data['turno_118_mer_pom_3'];
    turno118H24MerSer1 = data['turno_118_mer_ser_1'];
    turno118H24MerSer2 = data['turno_118_mer_ser_2'];
    turno118H24MerSer3 = data['turno_118_mer_ser_3'];
    turno118H24GioMat1 = data['turno_118_gio_mat_1'];
    turno118H24GioMat2 = data['turno_118_gio_mat_2'];
    turno118H24GioMat3 = data['turno_118_gio_mat_3'];
    turno118H24GioPom1 = data['turno_118_gio_pom_1'];
    turno118H24GioPom2 = data['turno_118_gio_pom_2'];
    turno118H24GioPom3 = data['turno_118_gio_pom_3'];
    turno118H24GioSer1 = data['turno_118_gio_ser_1'];
    turno118H24GioSer2 = data['turno_118_gio_ser_2'];
    turno118H24GioSer3 = data['turno_118_gio_ser_3'];
    turno118H24VenMat1 = data['turno_118_ven_mat_1'];
    turno118H24VenMat2 = data['turno_118_ven_mat_2'];
    turno118H24VenMat3 = data['turno_118_ven_mat_3'];
    turno118H24VenPom1 = data['turno_118_ven_pom_1'];
    turno118H24VenPom2 = data['turno_118_ven_pom_2'];
    turno118H24VenPom3 = data['turno_118_ven_pom_3'];
    turno118H24VenSer1 = data['turno_118_ven_ser_1'];
    turno118H24VenSer2 = data['turno_118_ven_ser_2'];
    turno118H24VenSer3 = data['turno_118_ven_ser_3'];
    turno118H24SabMat1 = data['turno_118_sab_mat_1'];
    turno118H24SabMat2 = data['turno_118_sab_mat_2'];
    turno118H24SabMat3 = data['turno_118_sab_mat_3'];
    turno118H24SabPom1 = data['turno_118_sab_pom_1'];
    turno118H24SabPom2 = data['turno_118_sab_pom_2'];
    turno118H24SabPom3 = data['turno_118_sab_pom_3'];
    turno118H24SabSer1 = data['turno_118_sab_ser_1'];
    turno118H24SabSer2 = data['turno_118_sab_ser_2'];
    turno118H24SabSer3 = data['turno_118_sab_ser_3'];
    turno118H24DomMat1 = data['turno_118_dom_mat_1'];
    turno118H24DomMat2 = data['turno_118_dom_mat_2'];
    turno118H24DomMat3 = data['turno_118_dom_mat_3'];
    turno118H24DomPom1 = data['turno_118_dom_pom_1'];
    turno118H24DomPom2 = data['turno_118_dom_pom_2'];
    turno118H24DomPom3 = data['turno_118_dom_pom_3'];
    turno118H24DomSer1 = data['turno_118_dom_ser_1'];
    turno118H24DomSer2 = data['turno_118_dom_ser_2'];
    turno118H24DomSer3 = data['turno_118_dom_ser_3'];

    turnoH24LunMat1 = data['turno_h24_lun_mat_1'];
    turnoH24LunMat2 = data['turno_h24_lun_mat_2'];
    turnoH24LunMat3 = data['turno_h24_lun_mat_3'];
    turnoH24LunPom1 = data['turno_h24_lun_pom_1'];
    turnoH24LunPom2 = data['turno_h24_lun_pom_2'];
    turnoH24LunPom3 = data['turno_h24_lun_pom_3'];
    turnoH24LunSer1 = data['turno_h24_lun_ser_1'];
    turnoH24LunSer2 = data['turno_h24_lun_ser_2'];
    turnoH24LunSer3 = data['turno_h24_lun_ser_3'];
    turnoH24MarMat1 = data['turno_h24_mar_mat_1'];
    turnoH24MarMat2 = data['turno_h24_mar_mat_2'];
    turnoH24MarMat3 = data['turno_h24_mar_mat_3'];
    turnoH24MarPom1 = data['turno_h24_mar_pom_1'];
    turnoH24MarPom2 = data['turno_h24_mar_pom_2'];
    turnoH24MarPom3 = data['turno_h24_mar_pom_3'];
    turnoH24MarSer1 = data['turno_h24_mar_ser_1'];
    turnoH24MarSer2 = data['turno_h24_mar_ser_2'];
    turnoH24MarSer3 = data['turno_h24_mar_ser_3'];
    turnoH24MerMat1 = data['turno_h24_mer_mat_1'];
    turnoH24MerMat2 = data['turno_h24_mer_mat_2'];
    turnoH24MerMat3 = data['turno_h24_mer_mat_3'];
    turnoH24MerPom1 = data['turno_h24_mer_pom_1'];
    turnoH24MerPom2 = data['turno_h24_mer_pom_2'];
    turnoH24MerPom3 = data['turno_h24_mer_pom_3'];
    turnoH24MerSer1 = data['turno_h24_mer_ser_1'];
    turnoH24MerSer2 = data['turno_h24_mer_ser_2'];
    turnoH24MerSer3 = data['turno_h24_mer_ser_3'];
    turnoH24GioMat1 = data['turno_h24_gio_mat_1'];
    turnoH24GioMat2 = data['turno_h24_gio_mat_2'];
    turnoH24GioMat3 = data['turno_h24_gio_mat_3'];
    turnoH24GioPom1 = data['turno_h24_gio_pom_1'];
    turnoH24GioPom2 = data['turno_h24_gio_pom_2'];
    turnoH24GioPom3 = data['turno_h24_gio_pom_3'];
    turnoH24GioSer1 = data['turno_h24_gio_ser_1'];
    turnoH24GioSer2 = data['turno_h24_gio_ser_2'];
    turnoH24GioSer3 = data['turno_h24_gio_ser_3'];
    turnoH24VenMat1 = data['turno_h24_ven_mat_1'];
    turnoH24VenMat2 = data['turno_h24_ven_mat_2'];
    turnoH24VenMat3 = data['turno_h24_ven_mat_3'];
    turnoH24VenPom1 = data['turno_h24_ven_pom_1'];
    turnoH24VenPom2 = data['turno_h24_ven_pom_2'];
    turnoH24VenPom3 = data['turno_h24_ven_pom_3'];
    turnoH24VenSer1 = data['turno_h24_ven_ser_1'];
    turnoH24VenSer2 = data['turno_h24_ven_ser_2'];
    turnoH24VenSer3 = data['turno_h24_ven_ser_3'];
    turnoH24SabMat1 = data['turno_h24_sab_mat_1'];
    turnoH24SabMat2 = data['turno_h24_sab_mat_2'];
    turnoH24SabMat3 = data['turno_h24_sab_mat_3'];
    turnoH24SabPom1 = data['turno_h24_sab_pom_1'];
    turnoH24SabPom2 = data['turno_h24_sab_pom_2'];
    turnoH24SabPom3 = data['turno_h24_sab_pom_3'];
    turnoH24SabSer1 = data['turno_h24_sab_ser_1'];
    turnoH24SabSer2 = data['turno_h24_sab_ser_2'];
    turnoH24SabSer3 = data['turno_h24_sab_ser_3'];
    turnoH24DomMat1 = data['turno_h24_dom_mat_1'];
    turnoH24DomMat2 = data['turno_h24_dom_mat_2'];
    turnoH24DomMat3 = data['turno_h24_dom_mat_3'];
    turnoH24DomPom1 = data['turno_h24_dom_pom_1'];
    turnoH24DomPom2 = data['turno_h24_dom_pom_2'];
    turnoH24DomPom3 = data['turno_h24_dom_pom_3'];
    turnoH24DomSer1 = data['turno_h24_dom_ser_1'];
    turnoH24DomSer2 = data['turno_h24_dom_ser_2'];
    turnoH24DomSer3 = data['turno_h24_dom_ser_3'];

    setState(() {});
  }

    @override
    Widget build(BuildContext context) {
    getDatiTabella118();
    getDatiTabella118H24();
    return Scaffold(
        body: Column(
          children: [
            SizedBox(height: 10),
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
                      Size(160, 40),
                    ),
                  ),
                ),
                Spacer(flex: 1),
                ElevatedButton(
                  onPressed: () {
                    switchToH24118();
                  },
                  child: Text('SETT 118/H24'),
                  style: ButtonStyle(
                    fixedSize: MaterialStateProperty.all<Size>(
                      Size(160, 40),
                    ),
                  ),
                ),
              ],
            ),
            SizedBox(height: 10),
            //tabella 118/H24
            if (widget.visualizzaH24118)
            Container(
              margin: EdgeInsets.fromLTRB(58, 0, 0, 0),
              child: Table(
                border: TableBorder.all(),
                children: [
                  TableRow(
                    children: [
                      TableCell(
                        child: Container(
                          height: 40,
                          width: 75,
                          color: Colors.green,
                          // Imposta il colore di sfondo a verde
                          child: const Center(
                              child: Text(
                                'H24'
                                , style: TextStyle(
                                  fontSize: 25, color: Colors.white),
                              )
                          ),
                        ),
                      ),
                      TableCell(
                        child: Container(
                          height: 40,
                          width: 75,
                          color: Colors.green,
                          // Imposta il colore di sfondo a verde
                          child: const Center(
                              child: Text(
                                '118'
                                , style: TextStyle(
                                  fontSize: 25, color: Colors.white),
                              )
                          ),
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),

            if(widget.visualizzaH24118)
            Table(
              border: TableBorder.all(),
              children: [
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        color: Colors.green,
                        child: const Center(
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
                        height: 66,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: Text(
                          'Lunedì ${DateFormat('dd/MM', 'it_IT').format(lunediH24.toDate())}',
                          textAlign: TextAlign.center,
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('${turnoH24LunMat1}\n${turnoH24LunMat2}\n${turnoH24LunMat3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turnoH24LunPom1}\n${turnoH24LunPom2}\n${turnoH24LunPom3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turnoH24LunSer1}\n${turnoH24LunSer2}\n${turnoH24LunSer3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24LunMat1}\n${turno118H24LunMat2}\n${turno118H24LunMat3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24LunPom1}\n${turno118H24LunPom2}\n${turno118H24LunPom3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24LunSer1}\n${turno118H24LunSer2}\n${turno118H24LunSer3}',
                            style: TextStyle(fontSize: 7)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 66,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: Text(
                          'Martedì ${DateFormat('dd/MM', 'it_IT').format(martediH24.toDate())}',
                          textAlign: TextAlign.center,
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('${turnoH24MarMat1}\n${turnoH24MarMat2}\n${turnoH24MarMat3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turnoH24MarPom1}\n${turnoH24MarPom2}\n${turnoH24MarPom3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turnoH24MarSer1}\n${turnoH24MarSer2}\n${turnoH24MarSer3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24MarMat1}\n${turno118H24MarMat2}\n${turno118H24MarMat3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24MarPom1}\n${turno118H24MarPom2}\n${turno118H24MarPom3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24MarSer1}\n${turno118H24MarSer2}\n${turno118H24MarSer3}',
                            style: TextStyle(fontSize: 7)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 66,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: Text(
                          'Mercoledì ${DateFormat('dd/MM', 'it_IT').format(mercolediH24.toDate())}',
                          textAlign: TextAlign.center,
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('${turnoH24MerMat1}\n${turnoH24MerMat2}\n${turnoH24MerMat3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turnoH24MerPom1}\n${turnoH24MerPom2}\n${turnoH24MerPom3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turnoH24MerSer1}\n${turnoH24MerSer2}\n${turnoH24MerSer3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24MerMat1}\n${turno118H24MerMat2}\n${turno118H24MerMat3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24MerPom1}\n${turno118H24MerPom2}\n${turno118H24MerPom3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24MerSer1}\n${turno118H24MerSer2}\n${turno118H24MerSer3}',
                            style: TextStyle(fontSize: 7)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 66,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: Text(
                          'Giovedì ${DateFormat('dd/MM', 'it_IT').format(giovediH24.toDate())}',
                          textAlign: TextAlign.center,
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('${turnoH24GioMat1}\n${turnoH24GioMat2}\n${turnoH24GioMat3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turnoH24GioPom1}\n${turnoH24GioPom2}\n${turnoH24GioPom3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turnoH24GioSer1}\n${turnoH24GioSer2}\n${turnoH24GioSer3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24GioMat1}\n${turno118H24GioMat2}\n${turno118H24GioMat3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24GioPom1}\n${turno118H24GioPom2}\n${turno118H24GioPom3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24GioSer1}\n${turno118H24GioSer2}\n${turno118H24GioSer3}',
                            style: TextStyle(fontSize: 7)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 66,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: Text(
                          'Venerdì ${DateFormat('dd/MM', 'it_IT').format(venerdiH24.toDate())}',
                          textAlign: TextAlign.center,
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('${turnoH24VenMat1}\n${turnoH24VenMat2}\n${turnoH24VenMat3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turnoH24VenPom1}\n${turnoH24VenPom2}\n${turnoH24VenPom3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turnoH24VenSer1}\n${turnoH24VenSer2}\n${turnoH24VenSer3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24VenMat1}\n${turno118H24VenMat2}\n${turno118H24VenMat3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24VenPom1}\n${turno118H24VenPom2}\n${turno118H24VenPom3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24VenSer1}\n${turno118H24VenSer2}\n${turno118H24VenSer3}',
                            style: TextStyle(fontSize: 7)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 66,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: Text(
                          'Sabato ${DateFormat('dd/MM', 'it_IT').format(sabatoH24.toDate())}',
                          textAlign: TextAlign.center,
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('${turnoH24SabMat1}\n${turnoH24SabMat2}\n${turnoH24SabMat3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turnoH24SabPom1}\n${turnoH24SabPom2}\n${turnoH24SabPom3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turnoH24SabSer1}\n${turnoH24SabSer2}\n${turnoH24SabSer3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24SabMat1}\n${turno118H24SabMat2}\n${turno118H24SabMat3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24SabPom1}\n${turno118H24SabPom2}\n${turno118H24SabPom3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24SabSer1}\n${turno118H24SabSer2}\n${turno118H24SabSer3}',
                            style: TextStyle(fontSize: 7)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 66,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: Text(
                          'Domenica ${DateFormat('dd/MM', 'it_IT').format(domenicaH24.toDate())}',
                          textAlign: TextAlign.center,
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('${turnoH24DomMat1}\n${turnoH24DomMat2}\n${turnoH24DomMat3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turnoH24DomPom1}\n${turnoH24DomPom2}\n${turnoH24DomPom3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turnoH24DomSer1}\n${turnoH24DomSer2}\n${turnoH24DomSer3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24DomMat1}\n${turno118H24DomMat2}\n${turno118H24DomMat3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24DomPom1}\n${turno118H24DomPom2}\n${turno118H24DomPom3}',
                            style: TextStyle(fontSize: 7)))),
                    TableCell(child: Center(
                        child: Text('${turno118H24DomSer1}\n${turno118H24DomSer2}\n${turno118H24DomSer3}',
                            style: TextStyle(fontSize: 7)))),
                  ],
                ),
              ],
            ),


          //tabella 118
          if(widget.visualizza118)
            Container(
              margin: EdgeInsets.fromLTRB(102, 0, 0, 0),
              child: Table(
                border: TableBorder.all(),
                children: [
                  TableRow(
                    children: [
                      TableCell(
                        child: Container(
                          height: 40,
                          width: 75,
                          color: Colors.green,
                          child: Center(
                              child: Text(
                                '118'
                                , style: TextStyle(
                                  fontSize: 25, color: Colors.white),
                              )
                          ),
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),

            if(widget.visualizza118)
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
                        height: 66,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: Text(
                          'Lunedì\n${DateFormat('dd/MM', 'it_IT').format(lunedi118.toDate())}',
                          textAlign: TextAlign.center,
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('${turno118LunMat1}\n${turno118LunMat2}\n${turno118LunMat3}',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('${turno118LunPom1}\n${turno118LunPom2}\n${turno118LunPom3}',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('${turno118LunSer1}\n${turno118LunSer2}\n${turno118LunSer3}',
                            style: TextStyle(fontSize: 8)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 66,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: Text(
                          'Martedì\n${DateFormat('dd/MM', 'it_IT').format(martedi118.toDate())}',
                          textAlign: TextAlign.center,
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('${turno118MarMat1}\n${turno118MarMat2}\n${turno118MarMat3}',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('${turno118MarPom1}\n${turno118MarPom2}\n${turno118MarPom3}',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('${turno118MarSer1}\n${turno118MarSer2}\n${turno118MarSer3}',
                            style: TextStyle(fontSize: 8)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 66,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: Text(
                          'Mercoledì\n${DateFormat('dd/MM', 'it_IT').format(mercoledi118.toDate())}',
                          textAlign: TextAlign.center,
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('${turno118MerMat1}\n${turno118MerMat2}\n${turno118MerMat3}',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('${turno118MerPom1}\n${turno118MerPom2}\n${turno118MerPom3}',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('${turno118MerSer1}\n${turno118MerSer2}\n${turno118MerSer3}',
                            style: TextStyle(fontSize: 8)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 66,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: Text(
                          'Giovedì\n${DateFormat('dd/MM', 'it_IT').format(giovedi118.toDate())}',
                          textAlign: TextAlign.center,
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('${turno118GioMat1}\n${turno118GioMat2}\n${turno118GioMat3}',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('${turno118GioPom1}\n${turno118GioPom2}\n${turno118GioPom3}',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('${turno118GioSer1}\n${turno118GioSer2}\n${turno118GioSer3}',
                            style: TextStyle(fontSize: 8)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 66,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: Text(
                          'Venerdì\n${DateFormat('dd/MM', 'it_IT').format(venerdi118.toDate())}',
                          textAlign: TextAlign.center,
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('${turno118VenMat1}\n${turno118VenMat2}\n${turno118VenMat3}',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('${turno118VenPom1}\n${turno118VenPom2}\n${turno118VenPom3}',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('${turno118VenSer1}\n${turno118VenSer2}\n${turno118VenSer3}',
                            style: TextStyle(fontSize: 8)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 66,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: Text(
                          'Sabato\n${DateFormat('dd/MM', 'it_IT').format(sabato118.toDate())}',
                          textAlign: TextAlign.center,
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('${turno118SabMat1}\n${turno118SabMat2}\n${turno118SabMat3}',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('${turno118SabPom1}\n${turno118SabPom2}\n${turno118SabPom3}',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('${turno118SabSer1}\n${turno118SabSer2}\n${turno118SabSer3}',
                            style: TextStyle(fontSize: 8)))),
                  ],
                ),
                TableRow(
                  children: [
                    TableCell(
                      child: Container(
                        alignment: Alignment.center,
                        height: 66,
                        width: 75,
                        decoration: const BoxDecoration(
                          color: Colors.green,
                        ),
                        child: Text(
                          'Domenica\n${DateFormat('dd/MM', 'it_IT').format(domenica118.toDate())}',
                          textAlign: TextAlign.center,
                          style: TextStyle(fontSize: 10, color: Colors
                              .white),
                        ),
                      ),
                    ),
                    TableCell(child: Center(
                        child: Text('${turno118DomMat1}\n${turno118DomMat2}\n${turno118DomMat3}',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('${turno118DomPom1}\n${turno118DomPom2}\n${turno118DomPom3}',
                            style: TextStyle(fontSize: 8)))),
                    TableCell(child: Center(
                        child: Text('${turno118DomSer1}\n${turno118DomSer2}\n${turno118DomSer3}',
                            style: TextStyle(fontSize: 8)))),
                  ],
                ),
              ],
            ),

          ],
        ),
      );
    }
  }
