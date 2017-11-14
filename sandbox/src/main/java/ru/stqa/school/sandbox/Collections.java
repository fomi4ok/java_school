package ru.stqa.school.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main ( String[] args) {
    String[] langs = {"Java","Python", "php" };

    List<String> languages = Arrays.asList("Java","Python", "php");


    for (String l: languages) {
      System.out.println( " I want to learn " + l);
    }




  }
}
