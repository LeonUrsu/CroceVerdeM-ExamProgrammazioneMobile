import 'package:croce_verde_plus/theme/colors_cv.dart';
import 'package:flutter/material.dart';


ThemeData lightTheme = ThemeData(
  brightness: Brightness.light,
  useMaterial3: true,
  colorScheme: ColorScheme.fromSeed(seedColor: Colors.green),
  cardTheme: const CardTheme(color: AppColorCV.lightGray_cv)
);

//lista di temi  per aggiornamenti futuri dell'app
List<ThemeData> _appThemes = [
  ///Theme 1
  ThemeData(
    textSelectionTheme:
        const TextSelectionThemeData(selectionHandleColor: Colors.white),
  ),

  ///Theme 2
  ThemeData(
    textSelectionTheme:
        const TextSelectionThemeData(selectionHandleColor: Colors.white),
  ),

  ///Theme 3
  ThemeData(
    textSelectionTheme:
        const TextSelectionThemeData(selectionHandleColor: Colors.white),
  ),
];
